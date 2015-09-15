package org.achut.messenger.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.achut.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException Ex) {
		ErrorMessage errorMessage = new ErrorMessage(Ex.getMessage(),404, "Documentation");
		return Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}	

	
}
