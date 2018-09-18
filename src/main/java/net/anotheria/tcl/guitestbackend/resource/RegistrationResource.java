package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;
import net.anotheria.tcl.guitestbackend.TestCases;
import net.anotheria.tcl.guitestbackend.notification.CaseResultMessage;
import net.anotheria.tcl.guitestbackend.notification.NotificationSlackSender;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 24.08.18 14:17
 */
@Path("registration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Monitor
public class RegistrationResource extends BaseResource {

    private RegistrationCounter registrationCounter = new RegistrationCounter();
    private MailCounter mailCounter = new MailCounter();
    private ConfirmationCounter confirmationCounter = new ConfirmationCounter();
    private MemberPageCounter memberPageCounter = new MemberPageCounter();
    private NotificationSlackSender notificationSlackSender = NotificationSlackSender.getInstance();

    @GET
    @Path("/registration/{success}/{userName}")
    public ReplyObject registration(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            registrationCounter.success();
        } else {
            registrationCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.REGISTRATION_REGISTRATION, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }

    @GET
    @Path("/email/{success}/{userName}")
    public ReplyObject mail(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            mailCounter.success();
        } else {
            mailCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.REGISTRATION_EMAIL, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }

    @GET
    @Path("/confirmation/{success}/{userName}")
    public ReplyObject confirmation(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            confirmationCounter.success();
        } else {
            confirmationCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.REGISTRATION_CONFIRMATION, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }

    @GET
    @Path("/memberpage/{success}/{userName}")
    public ReplyObject memberPage(@PathParam("success") boolean success, @PathParam("userName") String userName) {
        if (success) {
            memberPageCounter.success();
        } else {
            memberPageCounter.failure();
            notificationSlackSender.sendMessage(userName, TestCases.REGISTRATION_MEMBER_PAGE, success);
        }
        setUserName(userName);
        return ReplyObject.INSTANCE;
    }

}
