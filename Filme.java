import java.io.Serializable;

public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String titulo;
    private final int duracao; // duração em minutos
    private final String tipoProducao;
    private final String tipoAudio;

    public Filme(String titulo, int duracao, String tipoProducao, String tipoAudio) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.tipoProducao = tipoProducao;
        this.tipoAudio = tipoAudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getTipoProducao() {
        return tipoProducao;
    }

    public String getTipoAudio() {
        return tipoAudio;
    }
}
