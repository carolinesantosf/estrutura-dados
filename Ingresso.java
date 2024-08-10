import java.io.Serializable;

public class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Sessao sessao;
    private final Filme filme;
    private final int numeroPoltrona;
    private final boolean meiaEntrada;

    public Ingresso(Sessao sessao, Filme filme, int numeroPoltrona, boolean meiaEntrada) {
        this.sessao = sessao;
        this.filme = filme;
        this.numeroPoltrona = numeroPoltrona;
        this.meiaEntrada = meiaEntrada;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public boolean isMeiaEntrada() {
        return meiaEntrada;
    }

    public double calcularPreco() {
        double valorEntrada = sessao.getValorEntradaBase();
        if (sessao.isEm3D()) {
            valorEntrada *= 1.25;
        }
        if (meiaEntrada) {
            valorEntrada *= 0.5;
        }
        return valorEntrada;
    }
}


