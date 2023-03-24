package RoadMap;

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
}
