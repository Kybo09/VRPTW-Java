package RoadMap;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Roadmap {
    private ArrayList<Road> roads = new ArrayList<Road>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    public HashMap<Client, Boolean> visited = new HashMap<Client, Boolean>();

    public int clientsVisited = 0;
    private Depot depot;
    private int maxQuantity;
    public Roadmap(){}

    public void addRoad(Road road){
        this.roads.add(road);
    }
    public void printRoadmap(){
        for (Road road : this.roads) {
            road.printRoad();
        }
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Depot getDepot() {
        return depot;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<Map<String, String>> getRoadsLinks() {
        ArrayList<Map<String, String>> roadsLinks = new ArrayList<>();
        for (Road road : this.roads) {
            roadsLinks.add(road.getClients());
        }
        return roadsLinks;
    }

    public void fillRoadmap(String filename) throws IOException {
        System.out.println("Création du modèle...");
        File file = new File("src\\Datasets\\" + filename);
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));
        String st;
        int i = 0;
        while ((st = br.readLine()) != null) {
            if(st.contains("MAX_QUANTITY")){
                this.maxQuantity = Integer.parseInt(st.split(": ")[1]);
            }
            if(st.contains("DATA_DEPOTS")){
                st = br.readLine();
                String[] depotData = st.split(" ");
                this.depot = new Depot(depotData[0], Integer.parseInt(depotData[1]), Integer.parseInt(depotData[2]), Integer.parseInt(depotData[3]), Integer.parseInt(depotData[4]));
            }
            if(st.contains("DATA_CLIENTS")){
                st = br.readLine();
                while(st != null){
                    String[] clientData = st.split(" ");
                    Client client = new Client(clientData[0], Integer.parseInt(clientData[1]), Integer.parseInt(clientData[2]), Integer.parseInt(clientData[3]), Integer.parseInt(clientData[4]), Integer.parseInt(clientData[5]), Integer.parseInt(clientData[6]));
                    this.clients.add(client);
                    this.visited.put(client, false);
                    st = br.readLine();
                }
            }
        }
    }

    public double getDistanceBetweenCoords(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public double getDistance() {
        double distance = 0;
        for (Road road : this.roads) {
            distance += road.calcDistance();
        }
        return distance;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
}
