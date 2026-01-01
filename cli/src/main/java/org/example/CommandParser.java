package org.example;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    public static Map<String, String> parse(String[] args) {

        Map<String, String> params = new HashMap<>();

        for (int i = 1; i < args.length - 1; i += 2) {
            params.put(args[i], args[i + 1]);
        }

        return params;
    }
}
