package cz.fi.muni.TACOS.rest.exceptions.mappers;

import cz.fi.muni.TACOS.rest.exceptions.ResourceAlreadyExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Provider
public class ResourceAlreadyExistExceptionMapper implements ExceptionMapper<ResourceAlreadyExistException> {
    @Override
    public Response toResponse(ResourceAlreadyExistException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ApiError(e.getLocalizedMessage()))
                .build();
    }
}
