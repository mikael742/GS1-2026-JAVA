package bluesignal.domain;

import java.time.LocalDate;

/** Entidade Perfuracao - herda de EntidadeBase */
public class Perfuracao extends EntidadeBase {

    private int idPerfuracao;
    private String localPerfuracao;
    private double profundidadeEstimada;  // opcional
    private double nivelPrecisao;         // opcional
    private String statusAnalise;
    private LocalDate dataRecomendacao;
    private int idMonitoramento;
    private int idPropriedade;

    // Construtor padrão
    public Perfuracao() {
        super();
    }

    // Construtor não padrão básico
    public Perfuracao(int idPerfuracao, String localPerfuracao, String statusAnalise,
                      LocalDate dataRecomendacao, int idMonitoramento, int idPropriedade) {
        super(idPerfuracao);
        this.idPerfuracao = idPerfuracao;
        this.localPerfuracao = localPerfuracao;
        this.statusAnalise = statusAnalise;
        this.dataRecomendacao = dataRecomendacao;
        this.idMonitoramento = idMonitoramento;
        this.idPropriedade = idPropriedade;
    }

    // Construtor não padrão completo (polimorfismo overload)
    public Perfuracao(int idPerfuracao, String localPerfuracao, double profundidadeEstimada,
                      double nivelPrecisao, String statusAnalise, LocalDate dataRecomendacao,
                      int idMonitoramento, int idPropriedade) {
        super(idPerfuracao);
        this.idPerfuracao = idPerfuracao;
        this.localPerfuracao = localPerfuracao;
        this.profundidadeEstimada = profundidadeEstimada;
        this.nivelPrecisao = nivelPrecisao;
        this.statusAnalise = statusAnalise;
        this.dataRecomendacao = dataRecomendacao;
        this.idMonitoramento = idMonitoramento;
        this.idPropriedade = idPropriedade;
    }

    // Getters e Setters
    public int getIdPerfuracao() { return idPerfuracao; }
    public void setIdPerfuracao(int idPerfuracao) {
        this.idPerfuracao = idPerfuracao;
        this.setId(idPerfuracao);
    }

    public String getLocalPerfuracao() { return localPerfuracao; }
    public void setLocalPerfuracao(String localPerfuracao) { this.localPerfuracao = localPerfuracao; }

    public double getProfundidadeEstimada() { return profundidadeEstimada; }
    public void setProfundidadeEstimada(double profundidadeEstimada) { this.profundidadeEstimada = profundidadeEstimada; }

    public double getNivelPrecisao() { return nivelPrecisao; }
    public void setNivelPrecisao(double nivelPrecisao) { this.nivelPrecisao = nivelPrecisao; }

    public String getStatusAnalise() { return statusAnalise; }
    public void setStatusAnalise(String statusAnalise) { this.statusAnalise = statusAnalise; }

    public LocalDate getDataRecomendacao() { return dataRecomendacao; }
    public void setDataRecomendacao(LocalDate dataRecomendacao) { this.dataRecomendacao = dataRecomendacao; }

    public int getIdMonitoramento() { return idMonitoramento; }
    public void setIdMonitoramento(int idMonitoramento) { this.idMonitoramento = idMonitoramento; }

    public int getIdPropriedade() { return idPropriedade; }
    public void setIdPropriedade(int idPropriedade) { this.idPropriedade = idPropriedade; }

    // Polimorfismo override
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Perfuração em " + localPerfuracao + " | Status: " + statusAnalise;
    }

    @Override
    public String toString() {
        return "=== PERFURAÇÃO ===" +
               "\n  ID              : " + idPerfuracao +
               "\n  Local           : " + localPerfuracao +
               "\n  Prof. Estimada  : " + profundidadeEstimada + " m" +
               "\n  Nível Precisão  : " + nivelPrecisao + "%" +
               "\n  Status          : " + statusAnalise +
               "\n  Data Recom.     : " + dataRecomendacao +
               "\n  ID Monitoramento: " + idMonitoramento +
               "\n  ID Propriedade  : " + idPropriedade;
    }
}
