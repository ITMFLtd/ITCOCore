package com.itmf.core.net;

import com.itmf.core.blocks.Block;

import java.io.Serializable;

public class PacketPublish extends Packet implements Serializable {

    private Block block;

    public PacketPublish() {
        setPacketType(PacketType.PUBLISH);
    }

    public Block getBlock() {
        return block;
    }

}
