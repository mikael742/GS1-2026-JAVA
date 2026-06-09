package bluesignal.presentation;

import bluesignal.application.BlueSignalServico;
import bluesignal.domain.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Camada de Apresentação - Interface com o usuário via console
 * BlueSignal - Sistema de Inteligência Hídrica e Agrícola
 */
public class BlueSignalApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirBanner();

        System.out.print("\n  Deseja carregar dados de demonstração? (S/N): ");
        String resp = scanner.nextLine().trim();
        if (resp.equalsIgnoreCase("S")) {
            BlueSignalServico.carregarDadosDemonstracao();
            System.out.println("  ✔ Dados de demonstração carregados com sucesso!");
        }

        menuPrincipal();
    }

    // ===================== BANNER =====================

    private static void exibirBanner() {
        System.out.println();
        System.out.println("  -----------------  BLUE SIGNAL --------------------");
        System.out.println("   Inteligência Hídrica e Agrícola para o Agronegócio");
        System.out.println("   ─────────────────────────────────────────────────");
    }

    // ===================== MENU PRINCIPAL =====================

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL            ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Usuários                     ║");
            System.out.println("║  2. Propriedades Rurais          ║");
            System.out.println("║  3. Monitoramento Hídrico        ║");
            System.out.println("║  4. Análise de Perfuração        ║");
            System.out.println("║  5. Plantio e Irrigação          ║");
            System.out.println("║  6. Dashboard                    ║");
            System.out.println("║  0. Sair                         ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("  Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuUsuarios();
                case 2 -> menuPropriedades();
                case 3 -> menuMonitoramento();
                case 4 -> menuPerfuracao();
                case 5 -> menuPlantio();
                case 6 -> BlueSignalServico.exibirDashboard();
                case 0 -> System.out.println("\n  Até logo! BlueSignal - Cuidando da água e do campo.\n");
                default -> System.out.println("  ⚠ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // ===================== MENU USUÁRIOS =====================

    private static void menuUsuarios() {
        int opcao;
        do {
            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│          USUÁRIOS                │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│  1. Cadastrar usuário            │");
            System.out.println("│  2. Listar todos os usuários     │");
            System.out.println("│  3. Buscar usuário por ID        │");
            System.out.println("│  4. Remover usuário              │");
            System.out.println("│  0. Voltar                       │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> buscarUsuarioPorId();
                case 4 -> removerUsuario();
                case 0 -> {}
                default -> System.out.println("  ⚠ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarUsuario() {
        System.out.println("\n  --- CADASTRO DE USUÁRIO ---");
        System.out.print("  Nome completo     : ");
        String nome = scanner.nextLine().trim();

        System.out.print("  Email             : ");
        String email = scanner.nextLine().trim();

        System.out.print("  Senha (mín. 6 car): ");
        String senha = scanner.nextLine().trim();

        System.out.print("  Telefone (opcional, Enter para pular): ");
        String telefone = scanner.nextLine().trim();

        System.out.println("  Tipo de usuário:");
        System.out.println("    1 - PRODUTOR");
        System.out.println("    2 - EMPRESA");
        System.out.println("    3 - ADMIN");
        System.out.print("  Opção: ");
        int tipoOp = lerInteiro();
        String tipo = switch (tipoOp) {
            case 1 -> "PRODUTOR";
            case 2 -> "EMPRESA";
            case 3 -> "ADMIN";
            default -> "PRODUTOR";
        };

        String resultado;
        if (!telefone.isEmpty()) {
            resultado = BlueSignalServico.cadastrarUsuario(nome, email, senha, telefone, tipo);
        } else {
            resultado = BlueSignalServico.cadastrarUsuario(nome, email, senha, tipo);
        }
        System.out.println("\n  " + resultado);
    }

    private static void listarUsuarios() {
        ArrayList<Usuario> lista = BlueSignalServico.listarUsuarios();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("\n  === LISTA DE USUÁRIOS (" + lista.size() + ") ===");
        for (Usuario u : lista) {
            System.out.println("\n" + u);
        }
    }

    private static void buscarUsuarioPorId() {
        System.out.print("\n  ID do usuário: ");
        int id = lerInteiro();
        Optional<Usuario> resultado = BlueSignalServico.buscarUsuario(id);
        if (resultado.isPresent()) {
            System.out.println("\n" + resultado.get());
        } else {
            System.out.println("  ⚠ Usuário com ID " + id + " não encontrado.");
        }
    }

    private static void removerUsuario() {
        System.out.print("\n  ID do usuário a remover: ");
        int id = lerInteiro();
        System.out.print("  Confirma remoção? (S/N): ");
        String conf = scanner.nextLine().trim();
        if (conf.equalsIgnoreCase("S")) {
            boolean ok = BlueSignalServico.removerUsuario(id);
            System.out.println(ok ? "  ✔ Usuário removido com sucesso." : "  ⚠ Usuário não encontrado.");
        } else {
            System.out.println("  Operação cancelada.");
        }
    }

    // ===================== MENU PROPRIEDADES =====================

    private static void menuPropriedades() {
        int opcao;
        do {
            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│       PROPRIEDADES RURAIS        │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│  1. Cadastrar propriedade        │");
            System.out.println("│  2. Listar todas                 │");
            System.out.println("│  3. Listar por usuário           │");
            System.out.println("│  4. Buscar por ID                │");
            System.out.println("│  5. Remover propriedade          │");
            System.out.println("│  0. Voltar                       │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarPropriedade();
                case 2 -> listarPropriedades();
                case 3 -> listarPropriedadesPorUsuario();
                case 4 -> buscarPropriedadePorId();
                case 5 -> removerPropriedade();
                case 0 -> {}
                default -> System.out.println("  ⚠ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarPropriedade() {
        System.out.println("\n  --- CADASTRO DE PROPRIEDADE RURAL ---");
        System.out.print("  Nome da propriedade  : ");
        String nome = scanner.nextLine().trim();

        System.out.print("  Localização          : ");
        String loc = scanner.nextLine().trim();

        System.out.print("  Área em hectares     : ");
        double area = lerDouble();

        System.out.print("  Tipo de cultivo (opcional, Enter para pular): ");
        String cultivo = scanner.nextLine().trim();

        System.out.print("  Coordenadas GPS (opcional, ex: -21.18,-47.82): ");
        String coord = scanner.nextLine().trim();

        System.out.print("  ID do usuário responsável: ");
        int idUsuario = lerInteiro();

        String resultado;
        if (!cultivo.isEmpty() || !coord.isEmpty()) {
            resultado = BlueSignalServico.cadastrarPropriedade(nome, loc, area,
                    cultivo.isEmpty() ? null : cultivo,
                    coord.isEmpty() ? null : coord, idUsuario);
        } else {
            resultado = BlueSignalServico.cadastrarPropriedade(nome, loc, area, idUsuario);
        }
        System.out.println("\n  " + resultado);
    }

    private static void listarPropriedades() {
        ArrayList<PropriedadeRural> lista = BlueSignalServico.listarPropriedades();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhuma propriedade cadastrada.");
            return;
        }
        System.out.println("\n  === PROPRIEDADES RURAIS (" + lista.size() + ") ===");
        for (PropriedadeRural p : lista) {
            System.out.println("\n" + p);
        }
    }

    private static void listarPropriedadesPorUsuario() {
        System.out.print("\n  ID do usuário: ");
        int idUsuario = lerInteiro();
        ArrayList<PropriedadeRural> lista = BlueSignalServico.listarPropriedadesPorUsuario(idUsuario);
        if (lista.isEmpty()) {
            System.out.println("  Nenhuma propriedade encontrada para este usuário.");
            return;
        }
        System.out.println("\n  Propriedades do usuário " + idUsuario + ":");
        lista.forEach(p -> System.out.println("\n" + p));
    }

    private static void buscarPropriedadePorId() {
        System.out.print("\n  ID da propriedade: ");
        int id = lerInteiro();
        Optional<PropriedadeRural> resultado = BlueSignalServico.buscarPropriedade(id);
        if (resultado.isPresent()) {
            System.out.println("\n" + resultado.get());
        } else {
            System.out.println("  ⚠ Propriedade com ID " + id + " não encontrada.");
        }
    }

    private static void removerPropriedade() {
        System.out.print("\n  ID da propriedade a remover: ");
        int id = lerInteiro();
        System.out.print("  Confirma remoção? (S/N): ");
        String conf = scanner.nextLine().trim();
        if (conf.equalsIgnoreCase("S")) {
            boolean ok = BlueSignalServico.removerPropriedade(id);
            System.out.println(ok ? "  ✔ Propriedade removida." : "  ⚠ Propriedade não encontrada.");
        }
    }

    // ===================== MENU MONITORAMENTO =====================

    private static void menuMonitoramento() {
        int opcao;
        do {
            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│      MONITORAMENTO HÍDRICO       │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│  1. Registrar monitoramento      │");
            System.out.println("│  2. Listar todos                 │");
            System.out.println("│  3. Listar por propriedade       │");
            System.out.println("│  4. Buscar por ID                │");
            System.out.println("│  5. Remover registro             │");
            System.out.println("│  0. Voltar                       │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarMonitoramento();
                case 2 -> listarMonitoramentos();
                case 3 -> listarMonitoramentosPorPropriedade();
                case 4 -> buscarMonitoramentoPorId();
                case 5 -> removerMonitoramento();
                case 0 -> {}
                default -> System.out.println("  ⚠ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarMonitoramento() {
        System.out.println("\n  --- REGISTRO DE MONITORAMENTO HÍDRICO ---");
        System.out.print("  ID da propriedade       : ");
        int idProp = lerInteiro();

        System.out.print("  Nível do reservatório % : ");
        double nivel = lerDouble();

        System.out.print("  Umidade do solo %       : ");
        double umidade = lerDouble();

        System.out.print("  Índice pluviométrico mm : ");
        double pluv = lerDouble();

        System.out.print("  Temperatura °C          : ");
        double temp = lerDouble();

        System.out.print("  Disponibilidade hídrica m³: ");
        double disp = lerDouble();

        String resultado = BlueSignalServico.cadastrarMonitoramento(nivel, umidade, pluv, temp, disp, idProp);
        System.out.println("\n  " + resultado);
    }

    private static void listarMonitoramentos() {
        ArrayList<MonitoramentoHidrico> lista = BlueSignalServico.listarMonitoramentos();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhum monitoramento registrado.");
            return;
        }
        System.out.println("\n  === MONITORAMENTOS HÍDRICOS (" + lista.size() + ") ===");
        lista.forEach(m -> System.out.println("\n" + m));
    }

    private static void listarMonitoramentosPorPropriedade() {
        System.out.print("\n  ID da propriedade: ");
        int id = lerInteiro();
        ArrayList<MonitoramentoHidrico> lista = BlueSignalServico.listarMonitoramentosPorPropriedade(id);
        if (lista.isEmpty()) {
            System.out.println("  Nenhum monitoramento para esta propriedade.");
        } else {
            lista.forEach(m -> System.out.println("\n" + m));
        }
    }

    private static void buscarMonitoramentoPorId() {
        System.out.print("\n  ID do monitoramento: ");
        int id = lerInteiro();
        Optional<MonitoramentoHidrico> resultado = BlueSignalServico.buscarMonitoramento(id);
        if (resultado.isPresent()) {
            System.out.println("\n" + resultado.get());
        } else {
            System.out.println("  ⚠ Monitoramento com ID " + id + " não encontrado.");
        }
    }

    private static void removerMonitoramento() {
        System.out.print("\n  ID do monitoramento a remover: ");
        int id = lerInteiro();
        System.out.print("  Confirma remoção? (S/N): ");
        String conf = scanner.nextLine().trim();
        if (conf.equalsIgnoreCase("S")) {
            boolean ok = BlueSignalServico.removerMonitoramento(id);
            System.out.println(ok ? "  ✔ Monitoramento removido." : "  ⚠ Registro não encontrado.");
        }
    }

    // ===================== MENU PERFURAÇÃO =====================

    private static void menuPerfuracao() {
        int opcao;
        do {
            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│      ANÁLISE DE PERFURAÇÃO       │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│  1. Cadastrar análise            │");
            System.out.println("│  2. Listar todas                 │");
            System.out.println("│  3. Listar por propriedade       │");
            System.out.println("│  4. Buscar por ID                │");
            System.out.println("│  5. Remover análise              │");
            System.out.println("│  0. Voltar                       │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarPerfuracao();
                case 2 -> listarPerfuracoes();
                case 3 -> listarPerfuracoesPorPropriedade();
                case 4 -> buscarPerfuracaoPorId();
                case 5 -> removerPerfuracao();
                case 0 -> {}
                default -> System.out.println("  ⚠ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarPerfuracao() {
        System.out.println("\n  --- CADASTRO DE ANÁLISE DE PERFURAÇÃO ---");
        System.out.print("  Local de perfuração       : ");
        String local = scanner.nextLine().trim();

        System.out.print("  Profundidade estimada (m) : ");
        double prof = lerDouble();

        System.out.print("  Nível de precisão (%)     : ");
        double precisao = lerDouble();

        System.out.println("  Status da análise:");
        System.out.println("    1 - APROVADO");
        System.out.println("    2 - EM ANÁLISE");
        System.out.println("    3 - REPROVADO");
        System.out.print("  Opção: ");
        int stOp = lerInteiro();
        String status = switch (stOp) {
            case 1 -> "APROVADO";
            case 2 -> "EM ANÁLISE";
            case 3 -> "REPROVADO";
            default -> "EM ANÁLISE";
        };

        System.out.print("  ID do monitoramento vinculado: ");
        int idMon = lerInteiro();

        System.out.print("  ID da propriedade            : ");
        int idProp = lerInteiro();

        String resultado = BlueSignalServico.cadastrarPerfuracao(local, prof, precisao, status, idMon, idProp);
        System.out.println("\n  " + resultado);
    }

    private static void listarPerfuracoes() {
        ArrayList<Perfuracao> lista = BlueSignalServico.listarPerfuracoes();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhuma análise de perfuração cadastrada.");
            return;
        }
        System.out.println("\n  === ANÁLISES DE PERFURAÇÃO (" + lista.size() + ") ===");
        lista.forEach(p -> System.out.println("\n" + p));
    }

    private static void listarPerfuracoesPorPropriedade() {
        System.out.print("\n  ID da propriedade: ");
        int id = lerInteiro();
        ArrayList<Perfuracao> lista = BlueSignalServico.listarPerfuracoesPorPropriedade(id);
        if (lista.isEmpty()) {
            System.out.println("  Nenhuma perfuração para esta propriedade.");
        } else {
            lista.forEach(p -> System.out.println("\n" + p));
        }
    }

    private static void buscarPerfuracaoPorId() {
        System.out.print("\n  ID da perfuração: ");
        int id = lerInteiro();
        Optional<Perfuracao> resultado = BlueSignalServico.buscarPerfuracao(id);
        if (resultado.isPresent()) {
            System.out.println("\n" + resultado.get());
        } else {
            System.out.println("  ⚠ Perfuração com ID " + id + " não encontrada.");
        }
    }

    private static void removerPerfuracao() {
        System.out.print("\n  ID da perfuração a remover: ");
        int id = lerInteiro();
        System.out.print("  Confirma remoção? (S/N): ");
        String conf = scanner.nextLine().trim();
        if (conf.equalsIgnoreCase("S")) {
            boolean ok = BlueSignalServico.removerPerfuracao(id);
            System.out.println(ok ? "  ✔ Perfuração removida." : "  ⚠ Registro não encontrado.");
        }
    }

    // ===================== MENU PLANTIO =====================

    private static void menuPlantio() {
        int opcao;
        do {
            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│       PLANTIO E IRRIGAÇÃO        │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│  1. Cadastrar plantio            │");
            System.out.println("│  2. Listar todos                 │");
            System.out.println("│  3. Listar por propriedade       │");
            System.out.println("│  4. Buscar por ID                │");
            System.out.println("│  5. Remover plantio              │");
            System.out.println("│  0. Voltar                       │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  Opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarPlantio();
                case 2 -> listarPlantios();
                case 3 -> listarPlantiosPorPropriedade();
                case 4 -> buscarPlantioPorId();
                case 5 -> removerPlantio();
                case 0 -> {}
                default -> System.out.println("  ⚠ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarPlantio() {
        System.out.println("\n  --- CADASTRO DE PLANTIO E IRRIGAÇÃO ---");
        System.out.print("  Tipo de cultura               : ");
        String cultura = scanner.nextLine().trim();

        System.out.print("  Período de plantio            : ");
        String periodo = scanner.nextLine().trim();

        System.out.print("  Necessidade de irrigação (%)  : ");
        double necIrr = lerDouble();

        System.out.print("  Quantidade de água (m³)       : ");
        double qtdAgua = lerDouble();

        System.out.print("  Situação climática            : ");
        String clima = scanner.nextLine().trim();

        System.out.print("  Recomendações                 : ");
        String rec = scanner.nextLine().trim();

        System.out.print("  ID do monitoramento vinculado : ");
        int idMon = lerInteiro();

        System.out.print("  ID da propriedade             : ");
        int idProp = lerInteiro();

        String resultado = BlueSignalServico.cadastrarPlantio(cultura, periodo, necIrr, qtdAgua,
                clima, rec, idMon, idProp);
        System.out.println("\n  " + resultado);
    }

    private static void listarPlantios() {
        ArrayList<PlantioIrrigacao> lista = BlueSignalServico.listarPlantios();
        if (lista.isEmpty()) {
            System.out.println("\n  Nenhum plantio cadastrado.");
            return;
        }
        System.out.println("\n  === PLANTIOS E IRRIGAÇÕES (" + lista.size() + ") ===");
        lista.forEach(p -> System.out.println("\n" + p));
    }

    private static void listarPlantiosPorPropriedade() {
        System.out.print("\n  ID da propriedade: ");
        int id = lerInteiro();
        ArrayList<PlantioIrrigacao> lista = BlueSignalServico.listarPlantiosPorPropriedade(id);
        if (lista.isEmpty()) {
            System.out.println("  Nenhum plantio para esta propriedade.");
        } else {
            lista.forEach(p -> System.out.println("\n" + p));
        }
    }

    private static void buscarPlantioPorId() {
        System.out.print("\n  ID do plantio: ");
        int id = lerInteiro();
        Optional<PlantioIrrigacao> resultado = BlueSignalServico.buscarPlantio(id);
        if (resultado.isPresent()) {
            System.out.println("\n" + resultado.get());
        } else {
            System.out.println("  ⚠ Plantio com ID " + id + " não encontrado.");
        }
    }

    private static void removerPlantio() {
        System.out.print("\n  ID do plantio a remover: ");
        int id = lerInteiro();
        System.out.print("  Confirma remoção? (S/N): ");
        String conf = scanner.nextLine().trim();
        if (conf.equalsIgnoreCase("S")) {
            boolean ok = BlueSignalServico.removerPlantio(id);
            System.out.println(ok ? "  ✔ Plantio removido." : "  ⚠ Registro não encontrado.");
        }
    }

    // ===================== UTILITÁRIOS =====================

    private static int lerInteiro() {
        while (true) {
            try {
                String linha = scanner.nextLine().trim();
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.print("  ⚠ Valor inválido. Digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                String linha = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.print("  ⚠ Valor inválido. Digite um número (ex: 25.5): ");
            }
        }
    }
}
