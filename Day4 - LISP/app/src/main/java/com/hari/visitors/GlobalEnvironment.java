package com.hari.visitors;

import java.util.HashMap;

public class GlobalEnvironment {

    static volatile GlobalEnvironment instance;

    HashMap<String, String> symbolMap = new HashMap<>();

    GlobalEnvironment() {
    }

    public static GlobalEnvironment getInstance() {
        if (instance == null) {
            synchronized (GlobalEnvironment.class) {
                if (instance == null) {
                    instance = new GlobalEnvironment();
                }
            }
        }
        return instance;
    }

    public void addVariable(String s, String o) {
        symbolMap.put(s, o);
    }

    public String getVariable(String s) {
        return symbolMap.get(s);
    }
}
