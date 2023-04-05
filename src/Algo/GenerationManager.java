package Algo;

import RoadMap.*;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerationManager {

    private Roadmap roadmap;

    private int generationNumber = 0;
    private ArrayList<Roadmap> neighbours = new ArrayList<Roadmap>();
    private Roadmap bestRoadmap;

    public GenerationManager(){}

    public void setRoadmap(Roadmap roadmap) {
        this.roadmap = roadmap;
    }

    public Roadmap getClone(Roadmap r){
        return r.clone();
    }

    public Roadmap getNextGenRoadmap() {
        this.bestRoadmap = this.roadmap;
        ArrayList<Roadmap> reverseNeighbours = getReverseNeighbours();
        ArrayList<Roadmap> relocateNeighbours = getRelocateIntraNeighbours();
        ArrayList<Roadmap> relocateNeighboursInter = getRelocateInterNeighbours();
        ArrayList<Roadmap> allNeighbours = new ArrayList<Roadmap>();
        //allNeighbours.addAll(reverseNeighbours);
        allNeighbours.addAll(relocateNeighbours);
        allNeighbours.addAll(relocateNeighboursInter);
        for(Roadmap r : allNeighbours){
            if(r.getDistance() < this.bestRoadmap.getDistance()){
                this.bestRoadmap = r;
            }
        }
        this.generationNumber++;
        this.roadmap = this.bestRoadmap;
        return this.bestRoadmap;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public ArrayList<Roadmap> getReverseNeighbours(){
        /*ArrayList<Roadmap> reverseNeighbours = new ArrayList<Roadmap>();
        // Pour chaque route de la roadmap initiale
        for(Road r : roadmap.getRoads()){
            // On crée un voisin qui reverse la route actuelle
            Roadmap reverseNeighbour = getClone(roadmap);
            LinkedList<Client> newClients = new LinkedList<Client>();
            for(Client c : r.getClientsList()){
                newClients.addFirst(c);
            }
            reverseNeighbour.getRoads().get(roadmap.getRoads().indexOf(r)).setClientsList(newClients);
            reverseNeighbours.add(reverseNeighbour);
        }*/
        return null;
    }

    public ArrayList<Roadmap> get2OPTNeighbours(){
        ArrayList<Roadmap> neighbours = new ArrayList<Roadmap>();

        return neighbours;
    }

    public ArrayList<Roadmap> getRelocateIntraNeighbours(){
        ArrayList<Roadmap> neighbours = new ArrayList<Roadmap>();
        for(Road road : this.roadmap.getRoads()){
            List<LinkedList<Node>> allNewClients = new ArrayList<>();
            LinkedList<Node> nodes = road.getNodesList();
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
            for(LinkedList<Node> newClients : allNewClients){
                Roadmap neighbour = getClone(this.roadmap);
                neighbour.getRoads().get(this.roadmap.getRoads().indexOf(road)).setNodesList(newClients);
                if(neighbour.isRoadmapValid()){
                    neighbours.add(neighbour);
                }
            }
        }


        return neighbours;
    }

    public ArrayList<Roadmap> getRelocateInterNeighbours(){
        ArrayList<Roadmap> neighbours = new ArrayList<Roadmap>();
        for(Road currentRoad : roadmap.getRoads()){
            for(Node currentNode : currentRoad.getNodesList()){
                // Si c'est un client
                if(currentNode instanceof Client){
                    // On parcourt toutes les autres routes
                    for(Road otherRoad : roadmap.getRoads()){
                        if(currentRoad.getId() != otherRoad.getId()){
                            for(int i=1; i<otherRoad.getNodesList().size()-1; i++){
                                Roadmap neighbour = getClone(this.roadmap);
                                LinkedList<Node> newCurrentRoad = new LinkedList<Node>(neighbour.getRoads().get(this.roadmap.getRoads().indexOf(currentRoad)).getNodesList());
                                newCurrentRoad.remove(currentNode);
                                LinkedList<Node> newOtherRoad = new LinkedList<Node>(neighbour.getRoads().get(this.roadmap.getRoads().indexOf(otherRoad)).getNodesList());
                                newOtherRoad.add(i, currentNode);
                                //System.out.println("Current road : " + currentRoad.getId() + " Other road : " + otherRoad.getId() + " " + i);
                                neighbour.getRoads().get(this.roadmap.getRoads().indexOf(currentRoad)).setNodesList(newCurrentRoad);
                                neighbour.getRoads().get(this.roadmap.getRoads().indexOf(otherRoad)).setNodesList(newOtherRoad);
                                if(neighbour.isRoadmapValid()){
                                    neighbours.add(neighbour);
                                }
                            }
                        }
                    }
                }
            }
        }

        return neighbours;
    }

}
