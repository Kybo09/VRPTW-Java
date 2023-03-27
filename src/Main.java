import RoadMap.Client;
import RoadMap.Depot;
import RoadMap.Road;
import RoadMap.Roadmap;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int quantity = 0;
        File file = new File("src\\Datasets\\data1202.vrp");
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));
        String st;
        int i = 0;
        while ((st = br.readLine()) != null) {
            if(st.contains("DATA_CLIENTS")){
                st = br.readLine();
                while(st != null){
                    String[] clientData = st.split(" ");
                    quantity += Integer.parseInt(clientData[5]);
                    st = br.readLine();
                }
            }
        }
        System.out.println(quantity);
    }

}