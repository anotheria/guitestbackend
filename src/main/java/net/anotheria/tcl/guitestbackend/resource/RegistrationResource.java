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

	@GET @Path("/registration/{success}/{userName}")
	public ReplyObject registration(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			registrationCounter.success();
		else
			registrationCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}
}
