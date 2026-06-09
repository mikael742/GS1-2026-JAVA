package bluesignal.infrastructure;

import bluesignal.domain.Perfuracao;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** Repositório em memória de Perfuracao (simula banco com ArrayList)  */
public class PerfuracaoRepositorio {

    private static ArrayList<Perfuracao> perfuracoes = new ArrayList<>();
    private static int proximoId = 1;

    public static void salvar(Perfuracao perfuracao) {
        perfuracao.setIdPerfuracao(proximoId++);
        perfuracoes.add(perfuracao);
    }

    public static ArrayList<Perfuracao> listarTodos() {
        return new ArrayList<>(perfuracoes);
    }

    public static ArrayList<Perfuracao> listarPorPropriedade(int idPropriedade) {
        return perfuracoes.stream()
                .filter(p -> p.getIdPropriedade() == idPropriedade)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Optional<Perfuracao> buscarPorId(int id) {
        return perfuracoes.stream()
                .filter(p -> p.getIdPerfuracao() == id)
                .findFirst();
    }

    public static boolean atualizar(Perfuracao perfuracaoAtualizada) {
        for (int i = 0; i < perfuracoes.size(); i++) {
            if (perfuracoes.get(i).getIdPerfuracao() == perfuracaoAtualizada.getIdPerfuracao()) {
                perfuracoes.set(i, perfuracaoAtualizada);
                return true;
            }
        }
        return false;
    }

    public static boolean remover(int id) {
        return perfuracoes.removeIf(p -> p.getIdPerfuracao() == id);
    }

    public static int contar() {
        return perfuracoes.size();
    }
}
