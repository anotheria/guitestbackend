package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("messaging")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Monitor
public class MessagingResource extends BaseResource{

	private MessagingOverviewCounter messagingOverviewCounter = new MessagingOverviewCounter();
	private MessagingSystemMessageCounter messagingSystemMessageCounter = new MessagingSystemMessageCounter();

	@GET @Path("/overview/{success}/{userName}")
	public ReplyObject overview(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			messagingOverviewCounter.success();
		else
			messagingOverviewCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}

	@GET @Path("/systemmessage/{success}/{userName}")
	public ReplyObject systemMessage(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			messagingSystemMessageCounter.success();
		else
			messagingSystemMessageCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}
}
