import java.util.ArrayList;

public class Tree {
    Node root;

    public Tree() { root = null; }

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }

    public boolean isEmpty() { return root == null; }

    public boolean insert(Node node) {
        if(isEmpty()) {
            root = node;
            return true;
        } else {
            return insert(root, node);
        }
    }

    public boolean insert(Node root, Node node) {
        if(root.getDestino() != null && root.getDestino().equalsIgnoreCase(node.getOrigem())) {
            root.addDestino(node);
            return true;
        } else if(root.getBackup() != null && root.getBackup().equalsIgnoreCase(node.getOrigem())) {
            root.addBackup(node);
            return true;
        } else {
            ArrayList<Node> destinos = root.getPastaDestino();
            for(int i = 0; i < destinos.size(); i++) {
                return insert(destinos.get(i), node);
            }

            ArrayList<Node> backups = root.getPastaBackup();
            for(int i = 0; i < backups.size(); i++) {
                return insert(backups.get(i), node);
            }
        }
        return false;
    }
}
