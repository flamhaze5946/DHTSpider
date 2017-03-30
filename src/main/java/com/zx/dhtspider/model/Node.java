package com.zx.dhtspider.model;

import com.zx.dhtspider.test.SpiderUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zx on 2015/10/4.
 */
public class Node implements Comparable<Node>, Comparator<Node>{
    private byte[] id;
    private String ip;
    private int port;

    public Node(byte[] id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public Node setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Node setPort(int port) {
        this.port = port;
        return this;
    }

    public byte[] getId() {
        return id;
    }

    public Node setId(byte[] id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + Arrays.toString(id) +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }


    @Override
    public int compare(Node o1, Node o2) {
        if (o1 == o2)
        {
            return 0;
        }
        else if (o1 != null && o2 != null)
        {
            if (SpiderUtils.bytesToHexString(o1.id).compareTo(SpiderUtils.bytesToHexString(o2.id)) < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.equals(id, node.id);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(id);
    }

    @Override
    public int compareTo(Node o) {
        if (this == o)
        {
            return 0;
        }
        else if (o != null)
        {
            if (SpiderUtils.bytesToHexString(id).compareTo(SpiderUtils.bytesToHexString(o.id)) < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
        return 0;
    }
}
