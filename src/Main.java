import Graph.Graph;
import Graph.Node;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Graph graph = buildGraph();
        graph.printGraph();
    }

    public static Graph buildGraph(){
        Graph graph = new Graph();
        graph.addNode(new Node(0, "Depot"));
        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 6; j++){
                graph.addNode(new Node(j, "Route " + i));
            }
        }
        ArrayList<Node> nodeRoad1 = graph.getNodesByRoad("Route 1");
        ArrayList<Node> nodeRoad2 = graph.getNodesByRoad("Route 2");
        ArrayList<Node> nodeRoad3 = graph.getNodesByRoad("Route 3");

        // Création de la route 1
        graph.addEdge(graph.getNodesByRoad("Depot").get(0), nodeRoad1.get(0), 3);
        graph.addEdge(nodeRoad1.get(0), nodeRoad1.get(1), 2);
        graph.addEdge(nodeRoad1.get(1), nodeRoad1.get(2), 3);
        graph.addEdge(nodeRoad1.get(2), nodeRoad1.get(3), 4);
        graph.addEdge(nodeRoad1.get(3), nodeRoad1.get(4), 5);
        graph.addEdge(nodeRoad1.get(4), graph.getNodesByRoad("Depot").get(0), 8);

        // Création de la route 2
        graph.addEdge(graph.getNodesByRoad("Depot").get(0), nodeRoad2.get(0), 2);
        graph.addEdge(nodeRoad2.get(0), nodeRoad2.get(1), 1);
        graph.addEdge(nodeRoad2.get(1), nodeRoad2.get(2), 3);
        graph.addEdge(nodeRoad2.get(2), nodeRoad2.get(3), 3);
        graph.addEdge(nodeRoad2.get(3), nodeRoad2.get(4), 3);
        graph.addEdge(nodeRoad2.get(4), graph.getNodesByRoad("Depot").get(0), 10);

        // Création de la route 3
        graph.addEdge(graph.getNodesByRoad("Depot").get(0), nodeRoad3.get(0), 1);
        graph.addEdge(nodeRoad3.get(0), nodeRoad3.get(1), 3);
        graph.addEdge(nodeRoad3.get(1), nodeRoad3.get(2), 4);
        graph.addEdge(nodeRoad3.get(2), nodeRoad3.get(3), 5);
        graph.addEdge(nodeRoad3.get(3), nodeRoad3.get(4), 4);
        graph.addEdge(nodeRoad3.get(4), graph.getNodesByRoad("Depot").get(0), 2);

        return graph;
    }
}