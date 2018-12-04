package com.itmf.core.store;

import com.itmf.core.blocks.Block;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;

public final class Store {

    private Store() {} // No instantiation

    public static void storeBlock(String hash, Block block) {
        DB db = initDataStore();
        byte[] serialized = SerializationUtils.serialize(block);
        try {
            db.put(bytes(hash), serialized);
        } finally {
            try {
                db.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Block retrieveBlock(String hash) {
        DB db = initDataStore();
        byte[] block;
        try {
            block = db.get(bytes(hash));
        } finally {
            try {
                db.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SerializationUtils.deserialize(block);
    }

    private static DB initDataStore() {
        Options options = new Options();
        options.createIfMissing(true);
        DB db = null;
        try {
            db = factory.open(new File("store"),options);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return db;
    }
}
