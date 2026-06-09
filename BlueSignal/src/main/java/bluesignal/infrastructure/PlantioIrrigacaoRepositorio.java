package bluesignal.infrastructure;

import bluesignal.domain.PlantioIrrigacao;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** Repositório em memória de PlantioIrrigacao (simula banco com ArrayList) */
public class PlantioIrrigacaoRepositorio {

    private static ArrayList<PlantioIrrigacao> plantios = new ArrayList<>();
    private static int proximoId = 1;

    public static void salvar(PlantioIrrigacao plantio) {
        plantio.setIdPlantio(proximoId++);
        plantios.add(plantio);
    }

    public static ArrayList<PlantioIrrigacao> listarTodos() {
        return new ArrayList<>(plantios);
    }

    public static ArrayList<PlantioIrrigacao> listarPorPropriedade(int idPropriedade) {
        return plantios.stream()
                .filter(p -> p.getIdPropriedade() == idPropriedade)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Optional<PlantioIrrigacao> buscarPorId(int id) {
        return plantios.stream()
                .filter(p -> p.getIdPlantio() == id)
                .findFirst();
    }

    public static boolean atualizar(PlantioIrrigacao plantioAtualizado) {
        for (int i = 0; i < plantios.size(); i++) {
            if (plantios.get(i).getIdPlantio() == plantioAtualizado.getIdPlantio()) {
                plantios.set(i, plantioAtualizado);
                return true;
            }
        }
        return false;
    }

    public static boolean remover(int id) {
        return plantios.removeIf(p -> p.getIdPlantio() == id);
    }

    public static int contar() {
        return plantios.size();
    }
}
