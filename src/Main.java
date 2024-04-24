import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tree> tree = new ArrayList<>();
        tree.add(new Tree());
        readFile(tree, "./../baseparateste.csv");
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
                        System.out.printf("Conectando ID %d a ID %d (Backup)\n", parentNode.getId(), currNode.getId());
                        parentNode.addBackup(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }

                if (mapDestiny.get(currNode.getOrigem()) != null) {
                    System.out.println("entrei");
                    for (Node parentNode : mapDestiny.get(currNode.getOrigem())) {
                        System.out.printf("Conectando ID %d a ID %d (Destino)\n", parentNode.getId(), currNode.getId());
                        parentNode.addDestino(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }
            }
            scanner.close();
        } catch (

        FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Reading all data and creating the tree from it
        /*
         * while (scanner.hasNextLine()) {
         * line = scanner.nextLine().strip().replace(" ", "");
         * String[] tokens = line.split(";");
         * 
         * Node node = Node(tokens[0], tokens[1], tokens[2], tokens[3]);
         * 
         * for(Node aux : mapBackup.get(tokens[1])){
         * 
         * }
         * 
         * 
         * boolean isInserted = false;
         * for(int i = 0; i < tree.size(); i++) {
         * isInserted = tree.get(i).insert(node);
         * if(isInserted) break;
         * }
         * 
         * if(!isInserted) {
         * tree.add(new Tree(node));
         * }
         * }
         * }
         * 
         * private static Node getNode(String line) {
         * Node node = new Node();
         * 
         * // If a field is empty, then the attribute receives null
         * if (!tokens[1].isEmpty()) node.setNome(tokens[1]);
         * else node.setNome(null);
         * 
         * if (!tokens[2].isEmpty()) node.setOrigem(tokens[2]);
         * else node.setOrigem(null);
         * 
         * if (!tokens[3].isEmpty()) node.setDestino(tokens[3]);
         * else node.setDestino(null);
         * 
         * if (!tokens[4].isEmpty()) node.setBackup(tokens[4]);
         * else node.setBackup(null);
         * 
         * return node;
         */
    }
}
