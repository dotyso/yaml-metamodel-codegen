package com.thsoft.gen.utils;

import java.io.File;

public class PathUtils {
    public static String getFileNameWithoutSuffix(File file){
        String file_name = file.getName();
        return file_name.substring(0, file_name.lastIndexOf("."));
    }
}
