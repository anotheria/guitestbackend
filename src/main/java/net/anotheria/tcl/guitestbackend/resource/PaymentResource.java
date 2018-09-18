package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;
import net.anotheria.tcl.guitestbackend.TestCases;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Monitor
public class PaymentResource extends BaseResource {

    private PaymentSelectCounter paymentSelectCounter = new PaymentSelectCounter();
    private PaymentDetailsCounter paymentDetailsCounter = new PaymentDetailsCounter();


    @GET
    @Path("/select/{success}/{userName}")
    public ReplyObject select(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            paymentSelectCounter.success();
        } else {
            paymentSelectCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.PAYMENT_SELECT, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }

    @GET
    @Path("/details/{success}/{userName}")
    public ReplyObject details(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            paymentDetailsCounter.success();
        } else {
            paymentDetailsCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.PAYMENT_DETAILS, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }
}
