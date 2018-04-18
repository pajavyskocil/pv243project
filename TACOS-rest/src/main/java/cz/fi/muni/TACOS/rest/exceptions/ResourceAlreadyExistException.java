package cz.fi.muni.TACOS.rest.exceptions;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ResourceAlreadyExistException extends RuntimeException {

	public ResourceAlreadyExistException(String message) {
		super(message);

	}
}
