package bluesignal.infrastructure;

import bluesignal.domain.PropriedadeRural;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** Repositório em memória de PropriedadeRural (simula banco com ArrayList) */
public class PropriedadeRuralRepositorio {

    private static ArrayList<PropriedadeRural> propriedades = new ArrayList<>();
    private static int proximoId = 1;

    public static void salvar(PropriedadeRural propriedade) {
        propriedade.setIdPropriedade(proximoId++);
        propriedades.add(propriedade);
    }

    public static ArrayList<PropriedadeRural> listarTodos() {
        return new ArrayList<>(propriedades);
    }

    public static ArrayList<PropriedadeRural> listarPorUsuario(int idUsuario) {
        return propriedades.stream()
                .filter(p -> p.getIdUsuario() == idUsuario)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Optional<PropriedadeRural> buscarPorId(int id) {
        return propriedades.stream()
                .filter(p -> p.getIdPropriedade() == id)
                .findFirst();
    }

    public static boolean atualizar(PropriedadeRural propriedadeAtualizada) {
        for (int i = 0; i < propriedades.size(); i++) {
            if (propriedades.get(i).getIdPropriedade() == propriedadeAtualizada.getIdPropriedade()) {
                propriedades.set(i, propriedadeAtualizada);
                return true;
            }
        }
        return false;
    }

    public static boolean remover(int id) {
        return propriedades.removeIf(p -> p.getIdPropriedade() == id);
    }

    public static int contar() {
        return propriedades.size();
    }
}
