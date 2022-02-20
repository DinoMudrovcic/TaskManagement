package com.dinomudrovcic.taskmanagement.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CalculationUtilsUnitTests {

    @Test
    public void testCalculateDuration() {
        Date startDate = Date.from(LocalDateTime.parse("2022-02-17 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDateTime.parse("2022-02-17 12:01:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());

        Long expectedDuration = 60L;

        Long calculatedDuration = CalculationUtils.calculateDuration(startDate, endDate);

        assertEquals(expectedDuration, calculatedDuration);
    }

}
