package cz.fi.muni.TACOS.rest.exceptions;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class NotAuthorizedException extends RuntimeException {

	public NotAuthorizedException(String message) {
		super(message);
	}
}
