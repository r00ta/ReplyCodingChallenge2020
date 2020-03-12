package com.redhat.developer.types.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.redhat.developer.types.CellType;

public class Graph {
    public List<Node> nodes = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();


    public SortedMap<Integer, Node> managerMap = new TreeMap<>(Collections.reverseOrder());
    public SortedMap<Integer, Node> developerMap = new TreeMap<>(Collections.reverseOrder());

    public boolean insertIfNodeIsConnected(Node node){
        for(Node n : nodes){
            if(n.isAdiacent(node)){
                n.addNeighbour(node);
                node.addNeighbour(n);
                edges.add(new Edge(n, node));
            }
        }

        nodes.add(node);
        return true;
    }

    public void buildSortedMap(){
        for(Node n : nodes){
            if (n.type == CellType.MANAGER){
                managerMap.put(n.nNeighbours, n);
            }
            else{
                developerMap.put(n.nNeighbours, n);
            }
        }
    }

    public void putNewNode(Node node){
        insertIfNodeIsConnected(node);
    }


}
