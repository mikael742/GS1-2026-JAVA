package bluesignal.domain;

/** Entidade PlantioIrrigacao - herda de EntidadeBase */
public class PlantioIrrigacao extends EntidadeBase {

    private int idPlantio;
    private String tipoCultura;
    private String periodoPlantio;           // opcional
    private double necessidadeIrrigacao;    // opcional
    private double quantidadeAgua;          // opcional
    private String situacaoClimatica;       // opcional
    private String recomendacoes;           // opcional
    private int idMonitoramento;
    private int idPropriedade;

    // Construtor padrão
    public PlantioIrrigacao() {
        super();
    }

    // Construtor não padrão básico
    public PlantioIrrigacao(int idPlantio, String tipoCultura, int idMonitoramento, int idPropriedade) {
        super(idPlantio);
        this.idPlantio = idPlantio;
        this.tipoCultura = tipoCultura;
        this.idMonitoramento = idMonitoramento;
        this.idPropriedade = idPropriedade;
    }

    // Construtor não padrão completo (polimorfismo overload)
    public PlantioIrrigacao(int idPlantio, String tipoCultura, String periodoPlantio,
                             double necessidadeIrrigacao, double quantidadeAgua,
                             String situacaoClimatica, String recomendacoes,
                             int idMonitoramento, int idPropriedade) {
        super(idPlantio);
        this.idPlantio = idPlantio;
        this.tipoCultura = tipoCultura;
        this.periodoPlantio = periodoPlantio;
        this.necessidadeIrrigacao = necessidadeIrrigacao;
        this.quantidadeAgua = quantidadeAgua;
        this.situacaoClimatica = situacaoClimatica;
        this.recomendacoes = recomendacoes;
        this.idMonitoramento = idMonitoramento;
        this.idPropriedade = idPropriedade;
    }

    // Metodo de geração de alerta de irrigação
    public String gerarAlertaIrrigacao() {
        if (necessidadeIrrigacao > 80) {
            return "URGENTE - Irrigação imediata necessária!";
        } else if (necessidadeIrrigacao > 50) {
            return "RECOMENDADO - Irrigação recomendada em breve.";
        } else {
            return "NORMAL - Sem necessidade imediata de irrigação.";
        }
    }

    // Getters e Setters
    public int getIdPlantio() { return idPlantio; }
    public void setIdPlantio(int idPlantio) {
        this.idPlantio = idPlantio;
        this.setId(idPlantio);
    }

    public String getTipoCultura() { return tipoCultura; }
    public void setTipoCultura(String tipoCultura) { this.tipoCultura = tipoCultura; }

    public String getPeriodoPlantio() { return periodoPlantio; }
    public void setPeriodoPlantio(String periodoPlantio) { this.periodoPlantio = periodoPlantio; }

    public double getNecessidadeIrrigacao() { return necessidadeIrrigacao; }
    public void setNecessidadeIrrigacao(double necessidadeIrrigacao) { this.necessidadeIrrigacao = necessidadeIrrigacao; }

    public double getQuantidadeAgua() { return quantidadeAgua; }
    public void setQuantidadeAgua(double quantidadeAgua) { this.quantidadeAgua = quantidadeAgua; }

    public String getSituacaoClimatica() { return situacaoClimatica; }
    public void setSituacaoClimatica(String situacaoClimatica) { this.situacaoClimatica = situacaoClimatica; }

    public String getRecomendacoes() { return recomendacoes; }
    public void setRecomendacoes(String recomendacoes) { this.recomendacoes = recomendacoes; }

    public int getIdMonitoramento() { return idMonitoramento; }
    public void setIdMonitoramento(int idMonitoramento) { this.idMonitoramento = idMonitoramento; }

    public int getIdPropriedade() { return idPropriedade; }
    public void setIdPropriedade(int idPropriedade) { this.idPropriedade = idPropriedade; }

    // Polimorfismo override
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Cultura: " + tipoCultura + " | " + gerarAlertaIrrigacao();
    }

    @Override
    public String toString() {
        return "=== PLANTIO E IRRIGAÇÃO ===" +
               "\n  ID              : " + idPlantio +
               "\n  Tipo Cultura    : " + tipoCultura +
               "\n  Período Plantio : " + (periodoPlantio != null ? periodoPlantio : "Não informado") +
               "\n  Nec. Irrigação  : " + necessidadeIrrigacao + "%" +
               "\n  Qtd. Água       : " + quantidadeAgua + " m³" +
               "\n  Sit. Climática  : " + (situacaoClimatica != null ? situacaoClimatica : "Não informado") +
               "\n  Recomendações   : " + (recomendacoes != null ? recomendacoes : "Sem recomendações") +
               "\n  ID Monitoramento: " + idMonitoramento +
               "\n  ID Propriedade  : " + idPropriedade +
               "\n  Alerta          : " + gerarAlertaIrrigacao();
    }
}
