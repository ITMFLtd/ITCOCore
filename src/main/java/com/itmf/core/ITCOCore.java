package com.itmf.core;

import com.itmf.core.blocks.Block;
import com.itmf.core.store.Store;
import com.itmf.core.utils.YamlConfiguration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ITCOCore {

    public static Logger log;

    public static void main(String[] args) {

        log = Logger.getLogger("ITCOLog");
        initLogger();

        // Initialize configuration
        YamlConfiguration config = new YamlConfiguration("config.yaml");

        // Initialize block storage system

        // Listen for UDP

        // Broadcast keep alives

    }

    private static void initLogger() {

        FileHandler fh;
        try {
            fh = new FileHandler("itco.log");
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        log.setUseParentHandlers(false);
        log.info("Log initialized.");
    }

}
