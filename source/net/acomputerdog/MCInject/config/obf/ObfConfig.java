package net.acomputerdog.MCInject.config.obf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObfConfig {
    private final Map<String, String> classTable = new HashMap<String, String>();
    private final Map<String, String> methodTable = new HashMap<String, String>();
    private final Map<String, String> fieldTable = new HashMap<String, String>();

    public ObfConfig(File f) {
        if (!f.exists() || !f.isFile()) {
            throw new IllegalArgumentException("Config file does not exist!");
        }
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(f));
        } catch (Exception e) {
            throw new RuntimeException("Exception loading config!");
        }
        loadProperties(this, prop);
    }

    public ObfConfig(InputStream in) {
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Exception loading config!");
        }
        loadProperties(this, prop);
    }

    public ObfConfig(Properties prop) {
        loadProperties(this, prop);
    }

    public Map<String, String> getClassTable() {
        return classTable;
    }

    public Map<String, String> getMethodTable() {
        return methodTable;
    }

    public Map<String, String> getFieldTable() {
        return fieldTable;
    }

    private static void loadProperties(ObfConfig config, Properties input) {
        for (Object obj : input.keySet()) {
            String name = (String) obj;
            if (name.startsWith("class_")) {
                config.classTable.put(name.substring(5), input.getProperty(name));
            } else if (name.startsWith("method_")) {
                config.methodTable.put(name.substring(6), input.getProperty(name));
            } else if (name.startsWith("field_")) {
                config.fieldTable.put(name.substring(5), input.getProperty(name));
            } else {
                throw new RuntimeException("Unknown obfuscation mapping type!");
            }
        }
    }
}
