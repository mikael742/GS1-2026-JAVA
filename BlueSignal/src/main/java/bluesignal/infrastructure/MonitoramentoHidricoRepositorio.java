package bluesignal.infrastructure;

import bluesignal.domain.MonitoramentoHidrico;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** Repositório em memória de MonitoramentoHidrico (simula banco com ArrayList) */
public class MonitoramentoHidricoRepositorio {

    private static ArrayList<MonitoramentoHidrico> monitoramentos = new ArrayList<>();
    private static int proximoId = 1;

    public static void salvar(MonitoramentoHidrico monitoramento) {
        monitoramento.setIdMonitoramento(proximoId++);
        monitoramentos.add(monitoramento);
    }

    public static ArrayList<MonitoramentoHidrico> listarTodos() {
        return new ArrayList<>(monitoramentos);
    }

    public static ArrayList<MonitoramentoHidrico> listarPorPropriedade(int idPropriedade) {
        return monitoramentos.stream()
                .filter(m -> m.getIdPropriedade() == idPropriedade)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Optional<MonitoramentoHidrico> buscarPorId(int id) {
        return monitoramentos.stream()
                .filter(m -> m.getIdMonitoramento() == id)
                .findFirst();
    }

    public static boolean atualizar(MonitoramentoHidrico monitoramentoAtualizado) {
        for (int i = 0; i < monitoramentos.size(); i++) {
            if (monitoramentos.get(i).getIdMonitoramento() == monitoramentoAtualizado.getIdMonitoramento()) {
                monitoramentos.set(i, monitoramentoAtualizado);
                return true;
            }
        }
        return false;
    }

    public static boolean remover(int id) {
        return monitoramentos.removeIf(m -> m.getIdMonitoramento() == id);
    }

    public static int contar() {
        return monitoramentos.size();
    }
}
