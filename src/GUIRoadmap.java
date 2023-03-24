import Algo.RandomAlgo;
import RoadMap.Client;
import RoadMap.Depot;
import RoadMap.Road;
import RoadMap.Roadmap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class GUIRoadmap extends Application {
    private Map<String, Circle> circles = new HashMap<>();
    private static ArrayList<Client> clients = new ArrayList<>();

    private static Roadmap roadmap = new Roadmap();

    private static double ratio;
    private static int window_size = 1000;
    public static void createRoadmap(){
        try {
            roadmap.fillRoadmap("data101.vrp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        clients = roadmap.getClients();
    }

    @Override
    public void start(Stage stage) {
        // Créer des cercles et des étiquettes pour chaque point
        for(Client c : clients){
            Circle point = new Circle(c.getX()*ratio, c.getY()*ratio, 5, Color.RED);
            circles.put(c.getIdName(), point);
        }
        Circle depotPoint = new Circle(roadmap.getDepot().getX()*ratio, roadmap.getDepot().getY()*ratio, 10, Color.BLUE);
        circles.put(roadmap.getDepot().getIdName(), depotPoint);
        Pane pane = new Pane();
        pane.getChildren().addAll(circles.values());

        ArrayList<Map<String, String>> roadsLinks = roadmap.getRoadsLinks();
        int roadNumber = 0;
        for (Map<String, String> road : roadsLinks) {
            for (Map.Entry<String, String> entry : road.entrySet()) {
                Line line = createLine(entry.getKey(), entry.getValue(), roadNumber);
                pane.getChildren().add(line);
            }
            roadNumber++;
        }

        // Créer une scène avec le conteneur et la dimension souhaitée
        Scene scene = new Scene(pane, window_size, window_size);

        // Configurer la fenêtre principale avec la scène
        stage.setScene(scene);
        stage.show();
    }

    private Line createLine(String start, String end, int roadNumber) {
        // Récupérer les cercles de début et de fin à partir des clés
        Color[] colors = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.BROWN, Color.PINK, Color.GRAY, Color.BLACK};
        Circle startCircle = circles.get(start);
        Circle endCircle = circles.get(end);

        // Créer la ligne reliant les deux cercles
        Line line = new Line(startCircle.getCenterX(), startCircle.getCenterY(),
                endCircle.getCenterX(), endCircle.getCenterY());

        // changer la couleur de la ligne
        line.setStroke(colors[roadNumber]);

        return line;
    }


    public static void main(String[] args) {
        createRoadmap();
        double maxX = clients.stream().mapToDouble(Client::getX).max().orElse(0);
        double maxY = clients.stream().mapToDouble(Client::getY).max().orElse(0);
        double ratioX = maxX > 0 ? (window_size-50) / maxX : 1;
        double ratioY = maxY > 0 ? (window_size-50) / maxY : 1;
        ratio = Math.min(ratioX, ratioY);
        RandomAlgo ra = new RandomAlgo(roadmap);
        ra.calculRoads();
        roadmap = ra.getRoadmap();
        System.out.println("Roads : " + roadmap.getRoads().size());
        launch();
    }
}
