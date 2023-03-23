package Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private ArrayList<Node> nodes = new ArrayList<Node>();
    private HashMap<Node, ArrayList<Edge>> edges = new HashMap<Node, ArrayList<Edge>>();

    public void Graph() {}

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2, int value) {
        Edge edge = new Edge(node2, value);
        if (edges.containsKey(node1)) {
            edges.get(node1).add(edge);
        } else {
            ArrayList<Edge> edgeList = new ArrayList<Edge>();
            edgeList.add(edge);
            edges.put(node1, edgeList);
        }
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }

    public ArrayList<Node> getNodesByRoad(String road){
        ArrayList<Node> nodesByRoad = new ArrayList<Node>();
        for(Node node : nodes){
            if(node.getRoad().equals(road)){
                nodesByRoad.add(node);
            }
        }
        return nodesByRoad;
    }

    public void printGraph(){
        for(Node node : nodes){
            System.out.println(node.getRoad() + " | Node " + node.getNumber());
            if(edges.containsKey(node)){
                for(Edge edge : edges.get(node)){
                    System.out.println("    --> " + edge.getEndNode().getRoad() + " Node " + edge.getEndNode().getNumber() + " = " + edge.getWeight());
                }
            }
        }
    }
}
