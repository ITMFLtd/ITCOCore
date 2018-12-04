package com.itmf.core.net;

import com.itmf.core.ITCOCore;

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
            String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(received);
        }
    }
}