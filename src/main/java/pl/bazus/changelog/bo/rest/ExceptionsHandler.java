package pl.bazus.changelog.bo.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.bazus.changelog.bo.exceptions.ChangelogSupplierErrorResponse;
import pl.bazus.changelog.bo.exceptions.ChangelogSupplierException;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */
@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(ChangelogSupplierException.class)
    @ResponseBody
    public ChangelogSupplierErrorResponse exception(HttpServletResponse response, ChangelogSupplierException e) {
        ChangelogSupplierErrorResponse errorResp = new ChangelogSupplierErrorResponse();
        errorResp.setHttpError(response.getStatus());
        errorResp.setAsError();
        errorResp.setMessages(Arrays.asList(e.getMessage()));
        return errorResp;
    }

}

