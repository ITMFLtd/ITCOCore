package com.itmf.core.net;

import com.itmf.core.ITCOCore;
import com.itmf.core.blocks.Block;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class PacketListener implements Runnable {

    private int port;
    private DatagramSocket socket;
    private byte[] buffer;

    public PacketListener() {
        this(1760);
    }

    public PacketListener(int port) {
        System.out.println("Initializing packet listener...");
        this.port = port;
        buffer = new byte[65535];
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() {
        while (true) {
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ITCOCore.log.info("New packet received");
            Packet packet = null;
            try {
                packet = SerializationUtils.deserialize(datagramPacket.getData());
            } catch (SerializationException e) {
                ITCOCore.log.warning("Could not deserialize the received packet.");
            }
            if (packet == null || !packet.verifyHeader()) {
                ITCOCore.log.warning("Throwing away invalid packet");
                continue;
            }
            switch (packet.getPacketType()) {
                case PUBLISH:
                    PacketPublish packetPublish = (PacketPublish) packet;
                    if (!packetPublish.getBlock().verifyBlock()) {
                        ITCOCore.log.warning("Received malformed or malicious publish packet");
                        continue;
                    }
                case KEEP_ALIVE:
                    throw new NotImplementedException("");
                case CONFIRM_ACK:
                    throw new NotImplementedException("");
            }
        }
    }
}