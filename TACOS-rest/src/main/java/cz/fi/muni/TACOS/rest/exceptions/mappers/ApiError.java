package cz.fi.muni.TACOS.rest.exceptions.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public class ApiError {
    private List<String> errors = new ArrayList<>();

    public ApiError(String... errors) {
        this.errors.addAll(Arrays.asList(errors));
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
