===================================================
  BLUESIGNAL - Sistema de Inteligência Hídrica
===================================================

COMO COMPILAR:
  javac -d out/classes $(find src -name "*.java")

COMO EXECUTAR:
  java -cp out/classes bluesignal.presentation.BlueSignalApp

ESTRUTURA DO PROJETO:
  src/main/java/bluesignal/
  ├── presentation/
  │   └── BlueSignalApp.java         ← Menu/Interface com usuário
  ├── application/
  │   └── BlueSignalServico.java     ← Lógica de negócio e validações
  ├── domain/
  │   ├── EntidadeBase.java          ← Classe base (herança)
  │   ├── Usuario.java
  │   ├── PropriedadeRural.java
  │   ├── MonitoramentoHidrico.java
  │   ├── Perfuracao.java
  │   └── PlantioIrrigacao.java
  └── infrastructure/
      ├── UsuarioRepositorio.java
      ├── PropriedadeRuralRepositorio.java
      ├── MonitoramentoHidricoRepositorio.java
      ├── PerfuracaoRepositorio.java
      └── PlantioIrrigacaoRepositorio.java

REQUISITOS ATENDIDOS:
  ✔ Arquitetura em Camadas (presentation, application, domain, infrastructure)
  ✔ Herança: todas as entidades herdam de EntidadeBase
  ✔ Polimorfismo Override: exibirResumo() + toString() em cada entidade
  ✔ Polimorfismo Overload: construtores e métodos de cadastro com parâmetros variados
  ✔ ArrayList: todos os repositórios usam ArrayList em memória
  ✔ Menu de navegação completo com validações
  ✔ Identificador único em todas as entidades
  ✔ Getters/Setters + Construtores Padrão e Não Padrão

Integrantes:

Guilherme de Medeiros 
Victor Pucci
Mikael de Albuquerque
Otávio Magno
Felipe Ramalho