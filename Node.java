import java.util.ArrayList;

public class Node {
    private String nome;
    private String origem;
    private String destino;
    private String backup;

    private ArrayList<Node> pastaDestino;
    private ArrayList<Node> pastaBackup;

    public Node(String nome, String origem, String destino, String backup) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.backup = backup;

        pastaDestino = new ArrayList<>();
        pastaBackup = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getBackup() {
        return backup;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public void addDestino(Node node) {
        pastaDestino.add(node);
    }

    public void addBackup(Node node) {
        pastaBackup.add(node);
    }

    public ArrayList<Node> getPastaDestino() {
        return pastaDestino;
    }

    public ArrayList<Node> getPastaBackup() {
        return getPastaBackup();
    }
}
