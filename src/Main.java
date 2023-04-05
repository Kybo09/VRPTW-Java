import RoadMap.*;

import java.io.*;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;

public class Main {

    public static void main(String[] args) throws IOException {
        Client c1 = new Client("c1", 1, 1, 1, 1, 1, 1);
        Client c2 = new Client("c2", 1, 1, 1, 1, 1, 1);
        Client c3 = new Client("c3", 1, 1, 1, 1, 1, 1);
        Client c4 = new Client("c4", 1, 1, 1, 1, 1, 1);
        Client c5 = new Client("c5", 1, 1, 1, 1, 1, 1);
        Depot depot = new Depot("d1", 1, 1, 1, 1);

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(depot);
        nodes.add(c1);
        nodes.add(c2);
        nodes.add(c3);
        nodes.add(depot);

        Map<Node, Node> nodeMap = new HashMap<>();
        nodeMap.put(depot, c1);
        nodeMap.put(c1, c2);
        nodeMap.put(c2, c3);
        nodeMap.put(c3, depot);

        relocate(nodes, nodeMap);
    }


    public static List<LinkedList<Node>> relocate(LinkedList<Node> nodes, Map<Node, Node> nodeMap){
        List<LinkedList<Node>> allNewClients = new ArrayList<>();
        // Pour chaque point on teste les différentes combinaisons
        for(int i=0; i<nodes.size()-1; i++){
            if(i != 0){
                ArrayList<Integer> indexToInsert = new ArrayList<>();
                for(int j=0; j<nodes.size(); j++){
                    if(j != i && j != i-1 && j != i+1 && j != 0){
                        indexToInsert.add(j);
                    }
                }
                for(int k=0; k<indexToInsert.size(); k++){
                    LinkedList<Node> newClients = new LinkedList<>();
                    for(int l=0; l<nodes.size(); l++){
                        if(l == i){
                            continue;
                        }
                        if(l == indexToInsert.get(k)){
                            newClients.add(nodes.get(i));
                        }
                        newClients.add(nodes.get(l));
                    }
                    allNewClients.add(newClients);
                }
            }
        }
        return allNewClients;
    }

    public static void twoopt(LinkedList<Node> nodes, Map<Node, Node> nodeMap){
        List<LinkedList<Client>> allNewClients = new ArrayList<>();
        Map<Node,Node> edgesToSwap = new HashMap<>();
        ArrayList<String> permutationsAlreadyUsed = new ArrayList<>();

        for(Map.Entry<Node, Node> arreteToSwap : nodeMap.entrySet()) {
            for(Map.Entry<Node, Node> arreteToSwapWith : nodeMap.entrySet()) {
                if(arreteToSwap.getKey().getIdName().equals(arreteToSwapWith.getKey().getIdName()) || arreteToSwap.getKey().getIdName().equals(arreteToSwapWith.getValue().getIdName()) || arreteToSwap.getValue().getIdName().equals(arreteToSwapWith.getKey().getIdName()) || arreteToSwap.getValue().getIdName().equals(arreteToSwapWith.getValue().getIdName())) {
                    // Si arrête adjacente, on passe à la suivante
                    continue;
                }
                String permut = arreteToSwap.getKey().getIdName() + arreteToSwap.getValue().getIdName() + arreteToSwapWith.getKey().getIdName() + arreteToSwapWith.getValue().getIdName();
                String oppositePermut = arreteToSwapWith.getKey().getIdName() + arreteToSwapWith.getValue().getIdName() + arreteToSwap.getKey().getIdName() + arreteToSwap.getValue().getIdName();
                // On ajoute les permutations

                if(permutationsAlreadyUsed.contains(oppositePermut)) {
                    // Si la permutation a déjà été utilisée, on passe à la suivante
                    continue;
                }
                permutationsAlreadyUsed.add(permut);


                System.out.println(arreteToSwap.getKey().getIdName() + " " + arreteToSwap.getValue().getIdName() + " " + arreteToSwapWith.getKey().getIdName() + " " + arreteToSwapWith.getValue().getIdName());
                // On supprime les permutations en double
                LinkedList<Node> newNodes = new LinkedList<>(nodes);
                for(int i = 0; i < nodes.size(); i++) {

                }
            }
        }
    }
}