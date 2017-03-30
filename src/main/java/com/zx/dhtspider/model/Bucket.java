package com.zx.dhtspider.model;

import com.zx.dhtspider.test.SpiderUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zx on 2015/10/4.
 */
public class Bucket {
    private BigDecimal min;
    private BigDecimal max;
    private List<Node> nodes;
    private Date last_accessed_time;

    public Bucket(BigDecimal min, BigDecimal max) {
        this.min = min;
        this.max = max;
        Table.getBucketsFlagList().add(min);
        Table.getBucketsFlagList().add(max);
        Table.setBucketsFlagList(SpiderUtils.removeSameInArray(Table.getBucketsFlagList()));
        Collections.sort(Table.getBucketsFlagList());
        nodes = new ArrayList<Node>();
        last_accessed_time = new Date();
    }

    public boolean nodeIdInRange(byte[] id)
    {
        String hexId = SpiderUtils.bytesToHexString(id);
        return SpiderUtils.toUnsignedString(min, 16).compareTo(hexId) <= 0 && SpiderUtils.toUnsignedString(max, 16).compareTo(hexId) >= 0;
    }

    public void appendNode(Node node)
    {
        assert node.getId().length == 20;
        if (nodes.size() < 8)
        {
            for (Node myNode : nodes)
            {
                if (Arrays.equals(myNode.getId(), node.getId()))
                {
                    nodes.remove(myNode);
                    break;
                }
            }
            nodes.add(node);
            last_accessed_time = new Date();
        }
        else
        {
            throw new RuntimeException("BucketFull");
        }
    }


    public BigDecimal getMin() {
        return min;
    }

    public Bucket setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public Bucket setMax(BigDecimal max) {
        this.max = max;
        return this;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Bucket setNodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }

    public Date getLast_accessed_time() {
        return last_accessed_time;
    }

    public Bucket setLast_accessed_time(Date last_accessed_time) {
        this.last_accessed_time = last_accessed_time;
        return this;
    }
}
