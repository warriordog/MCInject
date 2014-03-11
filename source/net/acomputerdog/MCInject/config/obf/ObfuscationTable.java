package net.acomputerdog.MCInject.config.obf;

import java.util.HashMap;
import java.util.Map;

public class ObfuscationTable {
    private static final Map<String, String> classTableRaw = new HashMap<String, String>();
    private static final Map<String, String> methodTableRaw = new HashMap<String, String>();
    private static final Map<String, String> fieldTableRaw = new HashMap<String, String>();

    private static final Map<String, String> classTableUsed = new HashMap<String, String>();
    private static final Map<String, String> methodTableUsed = new HashMap<String, String>();
    private static final Map<String, String> fieldTableUsed = new HashMap<String, String>();

    public static void addMappings(ObfConfig config) {
        for (String key : config.getClassTable().keySet()) {
            classTableRaw.put(key, config.getClassTable().get(key));
        }
        for (String key : config.getMethodTable().keySet()) {
            methodTableRaw.put(key, config.getMethodTable().get(key));
        }
        for (String key : config.getFieldTable().keySet()) {
            fieldTableRaw.put(key, config.getFieldTable().get(key));
        }
    }

    public static String obfuscateClass(String name) {
        String obfName = classTableUsed.get(name);
        if (obfName == null) {
            obfName = classTableRaw.get(name);
            classTableUsed.put(name, obfName);
        }
        if (obfName == null) {
            throw new IllegalArgumentException("Class name was not found: " + name);
        }
        return obfName;
    }

    public static String obfuscateMethod(String name) {
        String obfName = methodTableUsed.get(name);
        if (obfName == null) {
            obfName = methodTableRaw.get(name);
            methodTableUsed.put(name, obfName);
        }
        if (obfName == null) {
            throw new IllegalArgumentException("Method name was not found: " + name);
        }
        return obfName;
    }

    public static String obfuscateField(String name) {
        String obfName = fieldTableUsed.get(name);
        if (obfName == null) {
            obfName = fieldTableRaw.get(name);
            fieldTableUsed.put(name, obfName);
        }
        if (obfName == null) {
            throw new IllegalArgumentException("Field name was not found: " + name);
        }
        return obfName;
    }
}
