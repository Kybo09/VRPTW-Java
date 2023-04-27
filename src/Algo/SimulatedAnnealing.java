package Algo;

import RoadMap.Roadmap;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    private Roadmap currentRoadmap;
    private double initialTemperature = 5000;
    private double coolingRate = 0.99;
    private NeighborManager nm = new NeighborManager();
    private int iterations = 1000;
    private Random random = new Random();

    public SimulatedAnnealing(Roadmap initialRoadmap) {
        this.currentRoadmap = initialRoadmap;
        nm.setRoadmap(initialRoadmap);
    }

    public Roadmap run() {
        double temperature = initialTemperature;
        Roadmap bestRoadmap = currentRoadmap;
        double bestCost = currentRoadmap.getDistance();

        for (int i = 0; i < iterations; i++) {
            ArrayList<Roadmap> neighbors = nm.getAllNeighbours();
            Roadmap neighbor = neighbors.get(random.nextInt(neighbors.size()));
            double neighborCost = neighbor.getDistance();
            double deltaCost = neighborCost - bestCost;

            if (deltaCost < 0) {
                currentRoadmap = neighbor;
                bestCost = neighborCost;
                if (neighborCost < bestRoadmap.getDistance()) {
                    bestRoadmap = neighbor;
                }
            } else if (Math.exp(-deltaCost / temperature) < random.nextDouble()) {
                currentRoadmap = neighbor;
            }else{
                currentRoadmap = bestRoadmap;
            }
            nm.setRoadmap(currentRoadmap);

            temperature *= coolingRate;
        }

        return bestRoadmap;
    }
}
