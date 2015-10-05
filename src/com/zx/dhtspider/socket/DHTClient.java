package com.zx.dhtspider.socket;

import com.zx.dhtspider.model.Table;

import java.io.IOException;
import java.net.*;

/**
 * Created by zx on 2015/10/5.
 */
public class DHTClient {
    private DatagramSocket sender = null;
    InetAddress inetAddress = null;
    int port = -1;
    private DatagramPacket sendPacket;

    public DHTClient(String ipAdress, int port)
    {
        try {
            sender = new DatagramSocket();
            sender.setSoTimeout(3000);
            this.inetAddress = InetAddress.getByName(ipAdress);
            this.port = port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public String sendData(String data)
    {
        if (checkFieldStatus())
        {
            sendPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, inetAddress, port);
            try {
                sender.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bytes = new byte[10240];
            DatagramPacket recvPacket = new DatagramPacket(bytes, bytes.length);
            try {
                sender.receive(recvPacket);
                String resultMsg = new String(recvPacket.getData(), 0, recvPacket.getLength(), "utf-8");
                return resultMsg;
            }
            catch (SocketTimeoutException e)
            {
                System.out.println(inetAddress.toString() + ":" + port + " connected time out.");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String findNodeOnDHT(byte[] nodeId)
    {
        String data = "d1:ad2:id20:" + new String(Table.getId()) + "6:target20:" + new String(nodeId) + "1:q9:find_node1:t2:aa1:y1:qe";
        return sendData(data);
    }

    /**
     * 检查客户端状态
     * @return
     */
    private boolean checkFieldStatus()
    {
        if (sender != null && inetAddress != null && port != -1)
        {
            return true;
        }
        return false;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public DHTClient setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
        return this;
    }

    public int getPort() {
        return port;
    }

    public DHTClient setPort(int port) {
        this.port = port;
        return this;
    }

    public DatagramSocket getSender() {
        return sender;
    }

    public DHTClient setSender(DatagramSocket sender) {
        this.sender = sender;
        return this;
    }

    public DatagramPacket getSendPacket() {
        return sendPacket;
    }

    public DHTClient setSendPacket(DatagramPacket sendPacket) {
        this.sendPacket = sendPacket;
        return this;
    }
}
