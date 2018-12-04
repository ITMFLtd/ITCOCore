package com.itmf.core.blocks;

import com.itmf.commons.ED25519;
import com.itmf.commons.Hashes;
import com.itmf.commons.ITCOHelper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;

public class Block implements Serializable {

    public final static String GENESIS_BLOCK = "237044F8CAA031262648C08252ACF0523A85E7A766172B5709C62017DA5F6835";

    private String previous; // Hash of the previous block state. 0 if initial block.
    private String link; // Hash of corresponding sender or destination address
    private String representative; // Address of this accounts representative
    private String account; // This accounts address
    private String balance; // Resulting balance
    private String work; // Proof of work
    private String signature;

    public boolean verifyBlock() {
        String hash = hash(ITCOHelper.toByteArray(account),
                ITCOHelper.toByteArray(previous),
                ITCOHelper.toByteArray(representative),
                ITCOHelper.toByteArray(balance),
                ITCOHelper.toByteArray(link));
        return ED25519.verify(ITCOHelper.toByteArray(signature), ITCOHelper.toByteArray(hash), extractPublicKey(account));
    }

    public static String hash(byte[]... byteArrays) {
        return ITCOHelper.toHex(Hashes.digest256(byteArrays));
    }

    public byte[] extractPublicKey(String account) {
        if (!account.startsWith("itco_")) {
            return null;
        }
        account = account.substring(5);
        throw new NotImplementedException();
    }
}
