package pl.bazus.changelog.bo.exceptions;

import lombok.Data;

import java.util.List;

/**
 * @author bogdan.mglowski on 2016-09-15.
 */

@Data
public class ChangelogSupplierErrorResponse {
    private int httpError;
    private ETypeErrorResponse type;
    private List<String> messages;


    public void setAsError(){
        this.setType(ETypeErrorResponse.ERROR);
    }

    private enum ETypeErrorResponse{
        INFO, ERROR, WARN
    }

}
