package org.achut.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.achut.messenger.Exception.DataNotFoundException;
import org.achut.messenger.model.ErrorMessage;
import org.achut.messenger.model.Message;
import org.achut.messenger.service.MessageService;

@Path("messages")
@Produces(value = { MediaType.APPLICATION_JSON , MediaType.TEXT_XML})
@Consumes(value = { MediaType.APPLICATION_JSON , MediaType.TEXT_XML})
public class MessageResource {

	MessageService ms = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
									 @QueryParam("start") int start,
									 @QueryParam("size") int size) {
		if(year > 0) {
			return ms.getMessageByYear(year);
		}
		if(start >= 0) {
			return ms.getMessageBySize(start, size);
		}
		return ms.getMessages();
	}
	
	
	@POST
	public Response addMessage(Message message , @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = ms.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI info = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(info)
				.entity(newMessage)
				.build();
	}
	
	@Path("/{MessageId}")
	@GET
	public Message getMessageById(@PathParam("MessageId") long MessageId, @Context UriInfo uriInfo) {
		
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(new ErrorMessage("Not found ", 404, "Doc"))
				.build();
		Message message = ms.getMessageById(MessageId);
		if(message == null) {
			// throw new DataNotFoundException("No data found with id " + MessageId);
			// throw new WebApplicationException(response);
			throw new NotFoundException();
		}
		message.addLinks(getUriForSelf(uriInfo, message), "self");
		message.addLinks(getUrlForComments(uriInfo, message), "Comments");
		return message;
	}


	private String getUrlForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(ComentRwsource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		return uri;
	}


	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo
				.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	} 
	
	@Path("/{messageId}/comments")
	public ComentRwsource getCommentResource() {
		return new ComentRwsource();
	}
}
