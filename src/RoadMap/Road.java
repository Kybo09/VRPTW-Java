package RoadMap;

import org.w3c.dom.html.HTMLAnchorElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Road {
    private int id;
    private static int idCounter = 1;
    private Depot depot;
    private LinkedList<Client> clients = new LinkedList<>();


    public Road(Depot depot) {
        this.id = idCounter;
        idCounter++;
        this.depot = depot;
    }
    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void printRoad(){
        System.out.println("Road " + this.id + " :");
        System.out.printf("%s --> ", this.depot.getIdName());
        for (Client client : this.clients) {
            System.out.printf("%s --> ", client.getIdName());
        }
        System.out.printf("%s", this.depot.getIdName());
    }

    public Map<String, String> getClients() {
        Map<String, String> clientMap = new HashMap<>();

        clientMap.put(depot.getIdName(), clients.getFirst().getIdName());
        for(int i = 0; i < clients.size() - 1; i++) {
            clientMap.put(clients.get(i).getIdName(), clients.get(i + 1).getIdName());
        }
        clientMap.put(clients.getLast().getIdName(), depot.getIdName());

        return clientMap;
    }

    public double calcDistance(){
        double distance = 0;
        distance += getDistanceBetweenCoords(depot.getX(), depot.getY(), clients.getFirst().getX(), clients.getFirst().getY());
        for(int i = 0; i < clients.size() - 1; i++) {
            distance += getDistanceBetweenCoords(clients.get(i).getX(), clients.get(i).getY(), clients.get(i + 1).getX(), clients.get(i + 1).getY());
        }
        distance += getDistanceBetweenCoords(clients.getLast().getX(), clients.getLast().getY(), depot.getX(), depot.getY());
        return distance;
    }

    private double getDistanceBetweenCoords(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public int getId() {
        return id;
    }

    public Client getLastClient() {
        return this.clients.getLast();
    }
}
