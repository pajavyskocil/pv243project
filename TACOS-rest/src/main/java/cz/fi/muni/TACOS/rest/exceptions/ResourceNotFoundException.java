package cz.fi.muni.TACOS.rest.exceptions;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
