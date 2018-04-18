package cz.fi.muni.TACOS.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@XmlRootElement
public class ApiError {

	private List<String> errors;

	public ApiError() {
	}

	public ApiError(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
