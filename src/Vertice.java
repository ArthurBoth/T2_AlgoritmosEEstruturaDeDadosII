import java.util.ArrayList;

public class Vertice {
    public final boolean isPorto;
    public final int numeroPorto;
    private final ArrayList<Vertice> adjacentes = new ArrayList<>();

    public Vertice(boolean isPorto, int numeroPorto) {
        this.isPorto = isPorto;
        this.numeroPorto = numeroPorto;
    }

    public Vertice(boolean isPorto) {
        this.isPorto = isPorto;
        this.numeroPorto = -1;
    }

    public void addEdge(Vertice v){
        adjacentes.add(v);
    }
}
