package com.itmf.core.utils;

import com.itmf.core.ITCOCore;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YamlConfiguration {

    private Map<String, Object> config;
    private String path;

    public YamlConfiguration() {
        this("config.yml");
    }

    public YamlConfiguration(String path) {
        this.path = path;
        ITCOCore.log.info("Initializing config " + path);
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        config = yaml.load(inputStream);
        if (config == null) {
            config = new HashMap<String, Object>();
        }
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void save() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        yaml.dump(config, writer);
    }

}
