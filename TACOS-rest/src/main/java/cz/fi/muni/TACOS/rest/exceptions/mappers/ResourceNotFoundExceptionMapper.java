package cz.fi.muni.TACOS.rest.exceptions.mappers;

import cz.fi.muni.TACOS.rest.exceptions.ResourceNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ApiError(e.getLocalizedMessage()))
                .build();
    }
}