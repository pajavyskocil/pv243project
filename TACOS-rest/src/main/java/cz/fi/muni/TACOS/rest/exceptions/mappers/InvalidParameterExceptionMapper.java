package cz.fi.muni.TACOS.rest.exceptions.mappers;

import cz.fi.muni.TACOS.rest.exceptions.InvalidParameterException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {
    @Override
    public Response toResponse(InvalidParameterException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ApiError(e.getLocalizedMessage()))
                .build();
    }
}
