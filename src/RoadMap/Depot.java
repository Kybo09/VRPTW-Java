package RoadMap;

public class Depot {
    private String idName;
    private int x;
    private int y;
    private int readyTime;
    private int dueTime;

    public Depot(String idName, int x, int y, int readyTime, int dueTime) {
        this.idName = idName;
        this.x = x;
        this.y = y;
        this.readyTime = readyTime;
        this.dueTime = dueTime;
    }

    public String getIdName() {
        return idName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public int getDueTime() {
        return dueTime;
    }
}
