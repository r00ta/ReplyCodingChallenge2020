package com.redhat.developer.types.graph;

public class Edge {
    public Node n1;

    public Node n2;

    public Edge(Node n1, Node n2){
        this.n1 = n1;
        this.n2 = n2;
    }
}
