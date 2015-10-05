package com.zx.dhtspider.thread;

import com.zx.dhtspider.model.Node;
import com.zx.dhtspider.model.Table;
import com.zx.dhtspider.socket.DHTClient;
import com.zx.dhtspider.test.SpiderUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zx on 2015/10/5.
 */
public class NodeFinder implements Runnable{
    /** DHT网络客户端 */
    private DHTClient dhtClient;

    /** 要寻找的node的Id */
    private byte[] nodeTargetId;

    @Override
    public void run() {
        try {
            List<Node> nodeList = SpiderUtils.getNodesInfo(dhtClient.findNodeOnDHT(nodeTargetId));
            for (Node node : nodeList)
            {
                Table.appendNode(node);
                System.out.println(node);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public NodeFinder(String ipAdress, int port, byte[] nodeTargetId) {
        this.dhtClient = new DHTClient(ipAdress, port);
        this.nodeTargetId = nodeTargetId;
    }

    public byte[] getNodeTargetId() {
        return nodeTargetId;
    }

    public NodeFinder setNodeTargetId(byte[] nodeTargetId) {
        this.nodeTargetId = nodeTargetId;
        return this;
    }
}
