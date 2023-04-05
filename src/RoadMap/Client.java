package RoadMap;

public class Client extends Node implements Cloneable{
    private int demand;
    private int service;

    public Client(String idName, int x, int y, int readyTime, int dueTime, int demand, int service) {
        super(idName, x, y, readyTime, dueTime);
        this.demand = demand;
        this.service = service;
    }

    public int getDemand() {
        return demand;
    }
    public int getService() {
        return service;
    }


}
