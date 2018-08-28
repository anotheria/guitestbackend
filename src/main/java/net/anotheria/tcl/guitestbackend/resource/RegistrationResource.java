package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;

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
public class RegistrationResource extends BaseResource{

	private RegistrationCounter registrationCounter = new RegistrationCounter();
	private MailCounter mailCounter = new MailCounter();
	private ConfirmationCounter confirmationCounter = new ConfirmationCounter();
	private MemberPageCounter memberPageCounter = new MemberPageCounter();

	@GET @Path("/registration/{success}/{userName}")
	public ReplyObject registration(@PathParam("success") boolean success, @PathParam("userName") String userName){
		System.out.println("Registration fired!");
		if (success)
			registrationCounter.success();
		else
			registrationCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

	@GET @Path("/email/{success}/{userName}")
	public ReplyObject mail(@PathParam("success") boolean success, @PathParam("userName") String userName){
		System.out.println("Mail fired!");
		if (success)
			mailCounter.success();
		else
			mailCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

	@GET @Path("/confirmation/{success}/{userName}")
	public ReplyObject confirmation(@PathParam("success") boolean success, @PathParam("userName") String userName){
		System.out.println("Confirmation fired!");
		if (success)
			confirmationCounter.success();
		else
			confirmationCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

	@GET @Path("/memberpage/{success}/{userName}")
	public ReplyObject memberPage(@PathParam("success") boolean success, @PathParam("userName") String userName){
		System.out.println("Memberpage fired!");
		if (success)
			memberPageCounter.success();
		else
			memberPageCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

}
