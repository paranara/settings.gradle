package org.paranora.mapstruct.starter.test;

import org.paranora.mapstruct.starter.test.entity.Staff;

public class Extension {
    public static String appendSize(String i){
        return i + " " + i.length();
    }

    public static Staff ttt(Staff o){
        return Staff.builder()
                .name("paranora")
                .build();
    }
}
