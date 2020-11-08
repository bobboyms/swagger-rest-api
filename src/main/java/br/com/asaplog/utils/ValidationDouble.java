package br.com.asaplog.utils;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class ValidationDouble {

    public static boolean isValid(Double value) {

        if (Objects.isNull(value) || value.doubleValue() <= 0d) {
            return false;
        }

        return true;
    }

}
