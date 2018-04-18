package cz.fi.muni.TACOS.rest.exceptions;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class InvalidParameterException extends RuntimeException {

	public InvalidParameterException(String message) {
		super(message);
	}
}
