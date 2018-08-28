package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("messaging")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Monitor
public class MessagingResource extends BaseResource{

	private MessagingCounter messagingCounter = new MessagingCounter();

	@GET @Path("/overview/{success}/{userName}")
	public ReplyObject overview(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			messagingCounter.success();
		else
			messagingCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

	@GET @Path("/systemmessage/{success}/{userName}")
	public ReplyObject systemMessage(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			messagingCounter.success();
		else
			messagingCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}
}
