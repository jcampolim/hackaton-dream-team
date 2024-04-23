import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        readFile(tree, "./baseparateste.csv");

    }

    public static void readFile(Tree tree, String filePath) {
        // Try reading the file path
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);

            // Eliminating the csv's header
            String line;
            if (scanner.hasNextLine()) { line = scanner.nextLine().strip(); }

            // Reading all data and creating the tree from it
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().strip().replace(" ", "");

                Node node = getNode(line);
                tree.insert(node);
                System.out.println(node);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Node getNode(String line) {
        String[] tokens = line.split(";");

//                System.out.println("ID: " + tokens[0]
//                        + "\nNome: " + tokens[1] +
//                        "\nPastaOrigem: " + tokens[2] +
//                        "\nPastaDestino: " + tokens[3] +
//                        "\nPastaBackup: " + tokens[4]);

        Node node = new Node();

        // If a field is empty, then the attribute receives null
        if (!tokens[1].isEmpty()) node.setNome(tokens[1]);
        else node.setNome(null);

        if (!tokens[2].isEmpty()) node.setOrigem(tokens[2]);
        else node.setOrigem(null);

        if (!tokens[3].isEmpty()) node.setDestino(tokens[3]);
        else node.setDestino(null);

        if (!tokens[4].isEmpty()) node.setBackup(tokens[4]);
        else node.setBackup(null);

        return node;
    }
}
