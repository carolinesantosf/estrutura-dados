import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int numero;
    private final int capacidade;
    private Set<LocalTime> horarios; 

    Sala(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.horarios = new HashSet<>();
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Set<LocalTime> getHorarios() {
        return horarios;
    }

    public Sala getSala() {
        return this;
    }

    public boolean adicionarHorario(Filme filme, LocalTime horario) {
        for (LocalTime h : horarios) {
            LocalTime fimExistente = h.plusMinutes(filme.getDuracao() + 20); 
    
            
            if (horario.isBefore(fimExistente)) {
                return false; 
            }
        }
        horarios.add(horario);
        return true;
    }
    

    public void removerHorario(LocalTime horario) {
        horarios.remove(horario);
    }
  

    @Override
    public String toString() {
        return String.format("Sala %d: %s lugares", numero, capacidade);
    }
}
