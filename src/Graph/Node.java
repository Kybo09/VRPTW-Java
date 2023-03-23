package Graph;

public class Node {

    private String road;
    private int number;

    public Node(int number, String road){
        this.number = number;
        this.road = road;
    }

    public String getRoad(){
        return road;
    }

    public int getNumber(){
        return number;
    }
}
