package bluesignal.application;

import bluesignal.domain.*;
import bluesignal.infrastructure.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/** Camada de aplicação - integração */
public class BlueSignalServico {

    // ======================== USUÁRIO ========================

    public static String cadastrarUsuario(String nome, String email, String senha, String tipoUsuario) {
        if (nome == null || nome.trim().isEmpty()) return "ERRO: Nome é obrigatório.";
        if (email == null || !email.contains("@")) return "ERRO: Email inválido.";
        if (senha == null || senha.length() < 6) return "ERRO: Senha deve ter ao menos 6 caracteres.";
        if (UsuarioRepositorio.emailExiste(email)) return "ERRO: Email já cadastrado.";

        Usuario usuario = new Usuario(0, nome.trim(), email.trim().toLowerCase(), senha, tipoUsuario);
        UsuarioRepositorio.salvar(usuario);
        return "OK: Usuário '" + nome + "' cadastrado com ID " + usuario.getIdUsuario() + ".";
    }

    // Overload com telefone (polimorfismo overload na camada de serviço)
    public static String cadastrarUsuario(String nome, String email, String senha,
                                           String telefone, String tipoUsuario) {
        if (nome == null || nome.trim().isEmpty()) return "ERRO: Nome é obrigatório.";
        if (email == null || !email.contains("@")) return "ERRO: Email inválido.";
        if (senha == null || senha.length() < 6) return "ERRO: Senha deve ter ao menos 6 caracteres.";
        if (UsuarioRepositorio.emailExiste(email)) return "ERRO: Email já cadastrado.";

        Usuario usuario = new Usuario(0, nome.trim(), email.trim().toLowerCase(), senha, telefone, tipoUsuario);
        UsuarioRepositorio.salvar(usuario);
        return "OK: Usuário '" + nome + "' cadastrado com ID " + usuario.getIdUsuario() + ".";
    }

    public static ArrayList<Usuario> listarUsuarios() {
        return UsuarioRepositorio.listarTodos();
    }

    public static Optional<Usuario> buscarUsuario(int id) {
        return UsuarioRepositorio.buscarPorId(id);
    }

    public static boolean removerUsuario(int id) {
        return UsuarioRepositorio.remover(id);
    }

    // ======================== PROPRIEDADE RURAL ========================

    public static String cadastrarPropriedade(String nome, String localizacao,
                                               double area, int idUsuario) {
        if (nome == null || nome.trim().isEmpty()) return "ERRO: Nome da propriedade é obrigatório.";
        if (localizacao == null || localizacao.trim().isEmpty()) return "ERRO: Localização é obrigatória.";
        if (area <= 0) return "ERRO: Área deve ser maior que zero.";
        if (UsuarioRepositorio.buscarPorId(idUsuario).isEmpty()) return "ERRO: Usuário não encontrado.";

        PropriedadeRural prop = new PropriedadeRural(0, nome.trim(), localizacao.trim(), area, idUsuario);
        PropriedadeRuralRepositorio.salvar(prop);
        return "OK: Propriedade '" + nome + "' cadastrada com ID " + prop.getIdPropriedade() + ".";
    }

    // Overload com tipoCultivo e coordenadas
    public static String cadastrarPropriedade(String nome, String localizacao, double area,
                                               String tipoCultivo, String coordenadas, int idUsuario) {
        if (nome == null || nome.trim().isEmpty()) return "ERRO: Nome da propriedade é obrigatório.";
        if (localizacao == null || localizacao.trim().isEmpty()) return "ERRO: Localização é obrigatória.";
        if (area <= 0) return "ERRO: Área deve ser maior que zero.";
        if (UsuarioRepositorio.buscarPorId(idUsuario).isEmpty()) return "ERRO: Usuário não encontrado.";

        PropriedadeRural prop = new PropriedadeRural(0, nome.trim(), localizacao.trim(),
                area, tipoCultivo, coordenadas, idUsuario);
        PropriedadeRuralRepositorio.salvar(prop);
        return "OK: Propriedade '" + nome + "' cadastrada com ID " + prop.getIdPropriedade() + ".";
    }

    public static ArrayList<PropriedadeRural> listarPropriedades() {
        return PropriedadeRuralRepositorio.listarTodos();
    }

    public static ArrayList<PropriedadeRural> listarPropriedadesPorUsuario(int idUsuario) {
        return PropriedadeRuralRepositorio.listarPorUsuario(idUsuario);
    }

    public static Optional<PropriedadeRural> buscarPropriedade(int id) {
        return PropriedadeRuralRepositorio.buscarPorId(id);
    }

    public static boolean removerPropriedade(int id) {
        return PropriedadeRuralRepositorio.remover(id);
    }

    // ======================== MONITORAMENTO HÍDRICO ========================

    public static String cadastrarMonitoramento(double nivelReservatorio, double umidadeSolo,
                                                 double indicePluviometrico, double temperatura,
                                                 double disponibilidadeHidrica, int idPropriedade) {
        if (PropriedadeRuralRepositorio.buscarPorId(idPropriedade).isEmpty())
            return "ERRO: Propriedade não encontrada.";
        if (nivelReservatorio < 0 || nivelReservatorio > 100)
            return "ERRO: Nível do reservatório deve ser entre 0 e 100%.";

        MonitoramentoHidrico m = new MonitoramentoHidrico(0, nivelReservatorio, umidadeSolo,
                indicePluviometrico, temperatura, disponibilidadeHidrica, LocalDate.now(), idPropriedade);
        MonitoramentoHidricoRepositorio.salvar(m);
        return "OK: Monitoramento registrado com ID " + m.getIdMonitoramento() +
               ". Análise: " + m.analisarSituacaoHidrica();
    }

    public static ArrayList<MonitoramentoHidrico> listarMonitoramentos() {
        return MonitoramentoHidricoRepositorio.listarTodos();
    }

    public static ArrayList<MonitoramentoHidrico> listarMonitoramentosPorPropriedade(int idPropriedade) {
        return MonitoramentoHidricoRepositorio.listarPorPropriedade(idPropriedade);
    }

    public static Optional<MonitoramentoHidrico> buscarMonitoramento(int id) {
        return MonitoramentoHidricoRepositorio.buscarPorId(id);
    }

    public static boolean removerMonitoramento(int id) {
        return MonitoramentoHidricoRepositorio.remover(id);
    }

    // ======================== PERFURAÇÃO ========================

    public static String cadastrarPerfuracao(String local, double profundidade, double precisao,
                                              String status, int idMonitoramento, int idPropriedade) {
        if (local == null || local.trim().isEmpty()) return "ERRO: Local de perfuração é obrigatório.";
        if (MonitoramentoHidricoRepositorio.buscarPorId(idMonitoramento).isEmpty())
            return "ERRO: Monitoramento não encontrado.";
        if (PropriedadeRuralRepositorio.buscarPorId(idPropriedade).isEmpty())
            return "ERRO: Propriedade não encontrada.";

        Perfuracao p = new Perfuracao(0, local.trim(), profundidade, precisao,
                status, LocalDate.now(), idMonitoramento, idPropriedade);
        PerfuracaoRepositorio.salvar(p);
        return "OK: Perfuração cadastrada com ID " + p.getIdPerfuracao() + ".";
    }

    public static ArrayList<Perfuracao> listarPerfuracoes() {
        return PerfuracaoRepositorio.listarTodos();
    }

    public static ArrayList<Perfuracao> listarPerfuracoesPorPropriedade(int idPropriedade) {
        return PerfuracaoRepositorio.listarPorPropriedade(idPropriedade);
    }

    public static Optional<Perfuracao> buscarPerfuracao(int id) {
        return PerfuracaoRepositorio.buscarPorId(id);
    }

    public static boolean removerPerfuracao(int id) {
        return PerfuracaoRepositorio.remover(id);
    }

    // ======================== PLANTIO E IRRIGAÇÃO ========================

    public static String cadastrarPlantio(String tipoCultura, String periodo, double necIrrigacao,
                                           double qtdAgua, String situacaoClimatica,
                                           String recomendacoes, int idMonitoramento, int idPropriedade) {
        if (tipoCultura == null || tipoCultura.trim().isEmpty()) return "ERRO: Tipo de cultura é obrigatório.";
        if (MonitoramentoHidricoRepositorio.buscarPorId(idMonitoramento).isEmpty())
            return "ERRO: Monitoramento não encontrado.";
        if (PropriedadeRuralRepositorio.buscarPorId(idPropriedade).isEmpty())
            return "ERRO: Propriedade não encontrada.";

        PlantioIrrigacao pl = new PlantioIrrigacao(0, tipoCultura.trim(), periodo, necIrrigacao,
                qtdAgua, situacaoClimatica, recomendacoes, idMonitoramento, idPropriedade);
        PlantioIrrigacaoRepositorio.salvar(pl);
        return "OK: Plantio cadastrado com ID " + pl.getIdPlantio() +
               ". Alerta: " + pl.gerarAlertaIrrigacao();
    }

    public static ArrayList<PlantioIrrigacao> listarPlantios() {
        return PlantioIrrigacaoRepositorio.listarTodos();
    }

    public static ArrayList<PlantioIrrigacao> listarPlantiosPorPropriedade(int idPropriedade) {
        return PlantioIrrigacaoRepositorio.listarPorPropriedade(idPropriedade);
    }

    public static Optional<PlantioIrrigacao> buscarPlantio(int id) {
        return PlantioIrrigacaoRepositorio.buscarPorId(id);
    }

    public static boolean removerPlantio(int id) {
        return PlantioIrrigacaoRepositorio.remover(id);
    }

    // ======================== DASHBOARD ========================

    public static void exibirDashboard() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         DASHBOARD BLUESIGNAL         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║  Usuários cadastrados    : %-9d ║%n", UsuarioRepositorio.contar());
        System.out.printf("║  Propriedades rurais     : %-9d ║%n", PropriedadeRuralRepositorio.contar());
        System.out.printf("║  Monitoramentos hídricos : %-9d ║%n", MonitoramentoHidricoRepositorio.contar());
        System.out.printf("║  Análises de perfuração  : %-9d ║%n", PerfuracaoRepositorio.contar());
        System.out.printf("║  Registros de plantio    : %-9d ║%n", PlantioIrrigacaoRepositorio.contar());
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ======================== DADOS DE DEMONSTRAÇÃO ========================

    public static void carregarDadosDemonstracao() {
        // Usuários
        cadastrarUsuario("João Silva", "joao@fazenda.com", "senha123", "(11) 99999-1111", "PRODUTOR");
        cadastrarUsuario("Maria Oliveira", "maria@agrotech.com", "senha456", "EMPRESA");

        // Propriedades
        cadastrarPropriedade("Fazenda Boa Vista", "Ribeirão Preto - SP", 350.5,
                "Soja/Milho", "-21.1767,-47.8208", 1);
        cadastrarPropriedade("Sítio São João", "Campinas - SP", 80.0, 2);

        // Monitoramentos
        cadastrarMonitoramento(65.0, 42.5, 120.0, 28.5, 15000.0, 1);
        cadastrarMonitoramento(18.0, 22.0, 45.0, 33.0, 4500.0, 2);

        // Perfurações
        cadastrarPerfuracao("Setor Norte - Gleba A", 85.5, 92.0, "APROVADO", 1, 1);
        cadastrarPerfuracao("Área Central", 60.0, 75.0, "EM ANÁLISE", 2, 2);

        // Plantios
        cadastrarPlantio("Soja", "Outubro/Março", 35.0, 8500.0,
                "Quente e úmido", "Irrigação programada para quinzenas", 1, 1);
        cadastrarPlantio("Milho", "Janeiro/Junho", 72.0, 12000.0,
                "Seco e quente", "Irrigação urgente recomendada", 2, 2);
    }
}
