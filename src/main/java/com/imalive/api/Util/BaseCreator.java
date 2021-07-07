package com.imalive.api.Util;

import com.imalive.api.DataTypes.StatusType;
import com.imalive.api.Model.Base;

import java.time.LocalDateTime;

public class BaseCreator {

    public static Base createValidBaseWithUpDateBiggerWithoutDownDate() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now())
                .upDate(LocalDateTime.now())
                .status(StatusType.RUNNING)
                .build();
    }

    public static Base createValidBaseWithUpAdvertDateBiggerWithoutDownDate() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now())
                .upDate(LocalDateTime.now())
                .upAdvertDate(LocalDateTime.now())
                .status(StatusType.RUNNING)
                .build();
    }

    public static Base createValidBaseWithDownDateBigger() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now().minusMinutes(2))
                .downDate(LocalDateTime.now())
                .upDate(LocalDateTime.now().minusMinutes(2))
                .upAdvertDate(LocalDateTime.now().minusMinutes(1))
                .status(StatusType.NOTRUNNING)
                .build();
    }

    public static Base createValidBaseWithDownAdvertDateBigger() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now().minusMinutes(3))
                .downDate(LocalDateTime.now().minusMinutes(1))
                .downAdvertDate(LocalDateTime.now())
                .upDate(LocalDateTime.now().minusMinutes(3))
                .upAdvertDate(LocalDateTime.now().minusMinutes(2))
                .status(StatusType.NOTRUNNING)
                .build();
    }

    public static Base createValidBaseWithUpDateBigger() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now())
                .downDate(LocalDateTime.now().minusMinutes(2))
                .upDate(LocalDateTime.now())
                .downAdvertDate(LocalDateTime.now().minusMinutes(1))
                .upAdvertDate(LocalDateTime.now().minusMinutes(3))
                .status(StatusType.NOTRUNNING)
                .build();
    }

    public static Base createValidBaseWithUpAdvertDateBigger() {
        return Base.builder()
                .bucketName("unit-test")
                .incrementDate(LocalDateTime.now().minusMinutes(1))
                .downDate(LocalDateTime.now().minusMinutes(3))
                .upDate(LocalDateTime.now().minusMinutes(1))
                .downAdvertDate(LocalDateTime.now().minusMinutes(2))
                .upAdvertDate(LocalDateTime.now())
                .status(StatusType.RUNNING)
                .build();
    }
}
