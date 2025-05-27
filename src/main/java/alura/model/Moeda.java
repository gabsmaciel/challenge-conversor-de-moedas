package alura.model;

public class Moeda {
    private final String base;
    private final String destino;

    public Moeda(String base, String destino) {
        this.base = base;
        this.destino = destino;
    }

    public String getBase() {
        return base;
    }

    public String getDestino() {
        return destino;
    }
}
