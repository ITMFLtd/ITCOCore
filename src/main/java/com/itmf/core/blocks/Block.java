package com.itmf.core.blocks;

import java.io.Serializable;

public class Block implements Serializable {

    public final static String GENESIS_BLOCK = "237044F8CAA031262648C08252ACF0523A85E7A766172B5709C62017DA5F6835";

    private String previous;
    private String link;
    private String representative;
    private String account;
    private String balance;
    private String work;
    private String signature;

}
