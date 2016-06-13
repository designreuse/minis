package net.minis.api.validation.constraints;

import java.util.Date;

/**
 * The date validate.
 * 
 * @author yen.
 */
public class DateValidation {

    static public void afterBeginDate(Date beginDate, Date endDate) {
        if (beginDate.after(endDate)) {
            throw new IllegalArgumentException("end date could not after begin date.");
        }
    }

    static public void afterBeginDate(Date beginDate, Date endDate, String errorMessage) {
        if (beginDate.after(endDate)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
