package org.achut.messenger.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.achut.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorMessage(ex.getMessage() , 500 , "Doc"))
				.build();
	}
}
