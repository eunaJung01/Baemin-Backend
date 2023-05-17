package baemin_backend.util;

import org.springframework.validation.BindingResult;

public class BindingResultUtils {

    public static String getErrorMessages(BindingResult bindingResult) {
        StringBuilder errorMessages = new StringBuilder();
        bindingResult.getAllErrors()
                .forEach(error -> errorMessages.append("[").append(error.getDefaultMessage()).append("]"));
        return errorMessages.toString();
    }

}
