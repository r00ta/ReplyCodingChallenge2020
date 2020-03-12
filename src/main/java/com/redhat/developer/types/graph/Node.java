package com.redhat.developer.types.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.redhat.developer.types.CellType;

public class Node {

    public CellType type;
    public Integer col;
    public Integer row;
    public boolean visited = false;

    public List<Node> neighbours = new ArrayList<Node>();
    public Integer nNeighbours = 0;

    public Node(Integer col, Integer row, CellType type){
        this.type = type;
        this.col = col;
        this.row = row;
    }

    public boolean isAdiacent(Node node){
        return (node.col - 1 == col && node.row == row ) ||
                (node.col + 1 == col && node.row == row ) ||
                (node.row - 1 == row && node.col == col) ||
                (node.row + 1 == row && node.col == col);
    }

    public void addNeighbour(Node node){
        neighbours.add(node);
        nNeighbours += 1;
    }

}
