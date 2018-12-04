package com.itmf.core.net;

import com.itmf.core.ITCOCore;
import com.itmf.core.blocks.Block;

import java.io.Serializable;

public abstract class Packet implements Serializable  {

    private static final byte[] MAGIC_HEADER = {'I', 'C'};
    private byte[] header;
    private PacketType packetType;

    public boolean verifyHeader() {
        if (header.length != 2 || header != MAGIC_HEADER) {
            ITCOCore.log.warning("Packet did not contain a valid header");
            return false;
        }
        return true;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    protected void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }
}
