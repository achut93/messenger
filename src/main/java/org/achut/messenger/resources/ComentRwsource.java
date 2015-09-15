package org.achut.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ComentRwsource {

	@GET
	public String getAllComments() {
		return "Banana";
	}
	
	@Path("/{commentId}")
	@GET
	public String getCommentById(@PathParam("commentId") long id) {
		return "apple";
	}
}
