package com.ysx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工具类
 */
public class Util {

    /**
     * 获取用户输入字符串,获取披萨名称
     * @return String
     */
    public static String getName() {
        System.out.println("输入披萨名称:");
        String name = "";
        InputStreamReader isr;
        BufferedReader br;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    private Util() {}
}
