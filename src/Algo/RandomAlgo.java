package Algo;

import RoadMap.Client;
import RoadMap.Road;
import RoadMap.Roadmap;

import java.util.Random;

public class RandomAlgo {
    private Roadmap roadmap;

    public RandomAlgo(Roadmap roadmap) {
        this.roadmap = roadmap;
    }

    public void calculRoads(){
        int clientNotVisited = this.roadmap.getClients().size();
        while(clientNotVisited > 0 ){
            Random rand = new Random();
            int numberOfClients = rand.nextInt(clientNotVisited) + 1;
            Road road = new Road(this.roadmap.getDepot());
            double distance = 0;
            double capacity = 0;
            road.addNode(this.roadmap.getDepot());
            for(int i= 0; i< numberOfClients; i++){
                int randomClient = rand.nextInt(this.roadmap.getClients().size());
                while(this.roadmap.visited.get(this.roadmap.getClients().get(randomClient))){
                    randomClient = rand.nextInt(this.roadmap.getClients().size());
                }
                Client client = this.roadmap.getClients().get(randomClient);
                if(i == 0){
                    distance += this.roadmap.getDistanceBetweenCoords(this.roadmap.getDepot().getX(), this.roadmap.getDepot().getY(), client.getX(), client.getY());
                }
                else{
                    distance += this.roadmap.getDistanceBetweenCoords(road.getLastNode().getX(), road.getLastNode().getY(), client.getX(), client.getY());
                }
                double distanceToDepot = this.roadmap.getDistanceBetweenCoords(client.getX(), client.getY(), this.roadmap.getDepot().getX(), this.roadmap.getDepot().getY());
                capacity += client.getDemand();
                if(capacity <= this.roadmap.getMaxQuantity() && distance <= this.roadmap.getDepot().getDueTime() - distanceToDepot){
                    road.addNode(client);
                    this.roadmap.visited.put(client, true);
                    clientNotVisited--;
                }
                else{
                    break;
                }
            }
            road.addNode(this.roadmap.getDepot());
            this.roadmap.addRoad(road);
        }
    }

    public Roadmap getRoadmap() {
        return roadmap;
    }
}
