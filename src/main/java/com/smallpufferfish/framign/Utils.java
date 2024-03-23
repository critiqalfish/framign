package com.smallpufferfish.framign;

import java.util.Arrays;

public class Utils {
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants())
                .map(Enum::name)
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }
}
