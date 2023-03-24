import RoadMap.Client;
import RoadMap.Depot;
import RoadMap.Road;
import RoadMap.Roadmap;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Roadmap roadmap = new Roadmap();
        Client client1 = new Client("C1", 1, 1, 1, 1, 1, 1);
        Client client2 = new Client("C2", 2, 2, 2, 2, 2, 2);
        Client client3 = new Client("C3", 3, 3, 3, 3, 3, 3);
        Depot depot = new Depot("D1", 1, 1, 1, 1);
        Road road = new Road(depot);
        road.addClient(client1);
        road.addClient(client2);
        road.addClient(client3);
        roadmap.addRoad(road);
        roadmap.printRoadmap();
        try {
            roadmap.fillRoadmap("data101.vrp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}