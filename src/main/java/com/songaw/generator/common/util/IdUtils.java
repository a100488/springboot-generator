package com.songaw.generator.common.util;


public abstract class IdUtils {

    public static Long nextId() {
        return SnowFlake.getInstance().nextId();
    }

}