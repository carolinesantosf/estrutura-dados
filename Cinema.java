import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Filme> filmes = new ArrayList<>();
    private List<Sessao> sessoes = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private List<Ingresso> ingressos = new ArrayList<>();

    public Cinema() {
        this.filmes = new ArrayList<>();
        this.sessoes = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.ingressos = new ArrayList<>();
    }

    public void adicionarFilme(Filme filme) {
        this.filmes.add(filme);
    }

    public void adicionarSessao(Sessao sessao) {
        this.sessoes.add(sessao);
    }

    public void adicionarSala(Sala sala) {
        if (!salas.contains(sala)) {
            salas.add(sala);
        }
    }

    public void removerSessao(Sessao sessao) {
        this.sessoes.remove(sessao);
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public Sala getSala(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumero() == numeroSala) {
                return sala;
            }
        }
        return null;
    }

    public Sala buscarSalaPorId(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumero() == numeroSala) {
                return sala;
            }
        }
        return null;
    }

    // public boolean verificarIntervaloMinimo(Sala sala, LocalTime horario) {
    //     for (LocalTime h : sala.getHorarios()) {
    //         if (Math.abs(h.toSecondOfDay() - horario.toSecondOfDay()) < 1200) { // 1200 segundos = 20 minutos
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    public void adicionarIngresso(Ingresso ingresso) throws Exception {
        if (!isPoltronaDisponivel(ingresso.getSessao(), ingresso.getNumeroPoltrona())) {
            throw new Exception("A poltrona " + ingresso.getNumeroPoltrona() + " já está vendida.");
        }
        ingresso.getSessao().venderIngresso(ingresso.getFilme(), ingresso.isMeiaEntrada());
        ingressos.add(ingresso);
    }

    public boolean isPoltronaDisponivel(Sessao sessao, int numeroPoltrona) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao) && ingresso.getNumeroPoltrona() == numeroPoltrona) {
                return false;
            }
        }
        return true;
    }

    public Ingresso buscarIngresso(Sessao sessao, int numeroPoltrona) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao) && ingresso.getNumeroPoltrona() == numeroPoltrona) {
                return ingresso;
            }
        }
        return null;
    }
    
    public void removerIngresso(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    public int calcularIngressosVendidos(Sessao sessao) {
        int count = 0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao)) {
                count++;
            }
        }
        return count;
    }

    public double calcularTaxaOcupacao(Sessao sessao) {
        int vendidos = calcularIngressosVendidos(sessao);
        int capacidade = sessao.getSala().getCapacidade();
        return (double) vendidos / capacidade * 100;
    }

    public Filme buscarFilmePorTitulo(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                return filme;
            }
        }
        return null;
    }

    public Sessao buscarSessaoPorFilme(String titulo) {
        for (Sessao sessao : sessoes) {
                if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                    return sessao;
            }
        }
        return null;
    }

    public void calcularFaturamento() {
        double totalInteira = 0;
        double totalMeia = 0;
        double total3D = 0;
        double total3DMeia = 0;
        double qtdInteira = 0;
        double qtdMeia = 0;
        double qtd3D = 0;
        double qtd3DMeia = 0;

        for (Sessao sessao : getSessoes()) {
            Map<String, Double> ingressosVendidosPorTipo = sessao.getIngressosVendidosPorTipo();

            if (ingressosVendidosPorTipo != null) {
                for (Map.Entry<String, Double> entry : ingressosVendidosPorTipo.entrySet()) {
                    String tipo = entry.getKey();
                    double valor = entry.getValue();

                    switch (tipo) {
                        case "meia entrada":
                            totalMeia += valor;
                            qtdMeia += valor/10;
                            break;
                        case "inteira":
                            totalInteira += valor;
                            qtdInteira += valor / 20;
                            break;
                        case "3D":
                            total3D += valor;
                            qtd3D += valor / 25;
                            break;
                        case "3D meia entrada":
                            total3DMeia += valor;
                            qtd3DMeia += valor / 12.5;
                            break;
                    }
                }
            }
        }

        double total = totalInteira + totalMeia + total3D + total3DMeia;
        double qtd = qtdInteira + qtdMeia + qtd3D + qtd3DMeia;

        System.out.println("Faturamento Total: R$ " + total);
        System.out.println("Quantidade de Ingressos Vendidos: " + qtd);
        System.out.println("Faturamento Inteira: R$ " + totalInteira);
        System.out.println("Quantidade de Ingressos Inteira: " + qtdInteira);
        System.out.println("Faturamento Meia Entrada: R$ " + totalMeia);
        System.out.println("Quantidade de Ingressos Meia Entrada: " + qtdMeia);
        System.out.println("Faturamento 3D: R$ " + total3D);
        System.out.println("Quantidade de Ingressos 3D: " + qtd3D);
        System.out.println("Faturamento 3D Meia Entrada: R$ " + total3DMeia);
        System.out.println("Quantidade de Ingressos 3D Meia Entrada: " + qtd3DMeia);
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
}
