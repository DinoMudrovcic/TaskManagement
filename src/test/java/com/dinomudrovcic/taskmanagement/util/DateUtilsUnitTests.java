package com.dinomudrovcic.taskmanagement.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DateUtilsUnitTests {

    @Test
    public void testGetDate() {
        Date currentActualDate = DateUtils.getDate();
        Date currentExpectedDate = new Date();

        assertTrue((currentActualDate.getTime()/1000) == (currentExpectedDate.getTime()/1000));
    }

}
