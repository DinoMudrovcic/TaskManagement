package com.dinomudrovcic.taskmanagement.util;

import java.util.Date;

public class CalculationUtils {

    public static Long calculateDuration(final Date start, final Date end) {
        return (end.getTime() - start.getTime()) / 1000;
    }

}
