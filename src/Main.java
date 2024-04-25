import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.engine.*;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tree> tree = new ArrayList<>();
        tree.add(new Tree());
        readFile(tree, "baseparateste.csv");

        MutableGraph g = mutGraph("example1").setDirected(true).add(
                mutNode("a").add(Color.RED).addLink(mutNode("b")));

        try {
            Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("example/ex1m.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void readFile(ArrayList<Tree> tree, String filePath) {
        // Try reading the file path
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);
            Map<String, ArrayList<Node>> mapBackup = new HashMap<>();
            Map<String, ArrayList<Node>> mapDestiny = new HashMap<>();
            ArrayList<Node> auxList = new ArrayList<>();
            Node initialNode = null;

            // Eliminating the csv's header
            String line;
            if (scanner.hasNextLine()) {
                line = scanner.nextLine().strip();
            }

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().strip().replace(" ", "");
                String[] tokens = line.split(";");

                Node currNode = new Node(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4]);

                auxList.add(currNode);

                if (mapDestiny.containsKey(tokens[3])) {
                    // System.out.println("entrei");
                    ArrayList<Node> list = mapDestiny.get(tokens[3]);
                    list.add(currNode);
                } else {
                    ArrayList<Node> newList = new ArrayList<>();
                    mapDestiny.put(tokens[3], newList);
                }

                if (mapBackup.containsKey(tokens[4])) {
                    ArrayList<Node> list = mapBackup.get(tokens[4]);
                    list.add(currNode);
                } else {
                    ArrayList<Node> newList = new ArrayList<>();
                    mapBackup.put(tokens[4], newList);
                }
            }

            for (Node currNode : auxList) {
                if (initialNode == null)
                    initialNode = currNode;

                if (mapBackup.get(currNode.getOrigem()) != null) {
                    for (Node parentNode : mapBackup.get(currNode.getOrigem())) {
                        // System.out.printf("Conectando ID %d a ID %d (Backup)\n", parentNode.getId(),
                        // currNode.getId());
                        parentNode.addBackup(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }

                if (mapDestiny.get(currNode.getOrigem()) != null) {
                    System.out.println("entrei");
                    for (Node parentNode : mapDestiny.get(currNode.getOrigem())) {
                        // System.out.printf("Conectando ID %d a ID %d (Destino)\n", parentNode.getId(),
                        // currNode.getId());
                        parentNode.addDestino(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }
            }

            for (Node aux : auxList) {
                System.out.printf("ID: %d\n", aux.getId());
                System.out.printf("Nome: %s\n", aux.getNome());
                if (!aux.getPastaOrigem().isEmpty()) {
                    System.out.printf("Nós da origem: ");
                    for (Node sons : aux.getPastaOrigem())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                if (!aux.getPastaDestino().isEmpty()) {
                    System.out.printf("Nós destino: ");
                    for (Node sons : aux.getPastaDestino())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                if (!aux.getPastaBackup().isEmpty()) {
                    System.out.printf("Nós de backup: ");
                    for (Node sons : aux.getPastaBackup())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                System.out.println();
                System.out.println();
            }
            scanner.close();
        } catch (
                FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}