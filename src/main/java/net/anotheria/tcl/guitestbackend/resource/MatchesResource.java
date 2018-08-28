package net.anotheria.tcl.guitestbackend.resource;

import net.anotheria.moskito.aop.annotation.Monitor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("matches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Monitor
public class MatchesResource extends BaseResource{

	private MatchesCounter matchesCounter = new MatchesCounter();

	@GET @Path("/matches/{success}/{userName}")
	public ReplyObject matches(@PathParam("success") boolean success, @PathParam("userName") String userName){
		if (success)
			matchesCounter.success();
		else
			matchesCounter.failure();
		setUserName(userName);
		return ReplyObject.INSTANCE;
	}
}
