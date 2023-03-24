package Algo;

import RoadMap.Road;
import RoadMap.Roadmap;

import java.util.Random;

public class RandomAlgo {
    private Roadmap roadmap;

    public RandomAlgo(Roadmap roadmap) {
        this.roadmap = roadmap;
    }

    public void calculRoads(){
        int clientNotVisited = this.roadmap.getClients().size() - this.roadmap.clientsVisited;
        while(clientNotVisited > 1){
            Random random = new Random();
            System.out.println("Clients not visited : " + clientNotVisited);
            int numberOfClients = random.nextInt(clientNotVisited - 1) + 1;
            Road road = new Road(this.roadmap.getDepot());
            for(int i = 0; i < numberOfClients; i++){
                int clientIndex = random.nextInt(this.roadmap.getClients().size());
                while(this.roadmap.visited.get(this.roadmap.getClients().get(clientIndex))){
                    clientIndex = random.nextInt(this.roadmap.getClients().size());
                }
                road.addClient(this.roadmap.getClients().get(clientIndex));
                this.roadmap.visited.put(this.roadmap.getClients().get(clientIndex), true);
                this.roadmap.clientsVisited++;
            }
            this.roadmap.addRoad(road);
            clientNotVisited = this.roadmap.getClients().size() - this.roadmap.clientsVisited;
        }
    }

    public Roadmap getRoadmap() {
        return roadmap;
    }
}
