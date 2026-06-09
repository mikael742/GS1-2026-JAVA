package bluesignal.domain;

import java.time.LocalDate;

/** Entidade MonitoramentoHidrico - herda de EntidadeBase */
public class MonitoramentoHidrico extends EntidadeBase {

    private int idMonitoramento;
    private double nivelReservatorio;     // opcional
    private double umidadeSolo;           // opcional
    private double indicePluviometrico;   // opcional
    private double temperatura;           // opcional
    private double disponibilidadeHidrica; // opcional
    private LocalDate dataColeta;
    private int idPropriedade;

    // Construtor padrão
    public MonitoramentoHidrico() {
        super();
        this.dataColeta = LocalDate.now();
    }

    // Construtor não padrão básico
    public MonitoramentoHidrico(int idMonitoramento, LocalDate dataColeta, int idPropriedade) {
        super(idMonitoramento);
        this.idMonitoramento = idMonitoramento;
        this.dataColeta = dataColeta;
        this.idPropriedade = idPropriedade;
    }

    // Construtor não padrão completo (polimorfismo overload)
    public MonitoramentoHidrico(int idMonitoramento, double nivelReservatorio, double umidadeSolo,
                                 double indicePluviometrico, double temperatura,
                                 double disponibilidadeHidrica, LocalDate dataColeta, int idPropriedade) {
        super(idMonitoramento);
        this.idMonitoramento = idMonitoramento;
        this.nivelReservatorio = nivelReservatorio;
        this.umidadeSolo = umidadeSolo;
        this.indicePluviometrico = indicePluviometrico;
        this.temperatura = temperatura;
        this.disponibilidadeHidrica = disponibilidadeHidrica;
        this.dataColeta = dataColeta;
        this.idPropriedade = idPropriedade;
    }

    // Metodo de análise hídrica
    public String analisarSituacaoHidrica() {
        if (nivelReservatorio < 20) {
            return "CRÍTICO - Nível do reservatório muito baixo!";
        } else if (nivelReservatorio < 40) {
            return "ATENÇÃO - Nível do reservatório abaixo do ideal.";
        } else if (nivelReservatorio >= 70) {
            return "ÓTIMO - Nível do reservatório adequado.";
        } else {
            return "NORMAL - Situação hídrica estável.";
        }
    }

    // Getters e Setters
    public int getIdMonitoramento() { return idMonitoramento; }
    public void setIdMonitoramento(int idMonitoramento) {
        this.idMonitoramento = idMonitoramento;
        this.setId(idMonitoramento);
    }

    public double getNivelReservatorio() { return nivelReservatorio; }
    public void setNivelReservatorio(double nivelReservatorio) { this.nivelReservatorio = nivelReservatorio; }

    public double getUmidadeSolo() { return umidadeSolo; }
    public void setUmidadeSolo(double umidadeSolo) { this.umidadeSolo = umidadeSolo; }

    public double getIndicePluviometrico() { return indicePluviometrico; }
    public void setIndicePluviometrico(double indicePluviometrico) { this.indicePluviometrico = indicePluviometrico; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getDisponibilidadeHidrica() { return disponibilidadeHidrica; }
    public void setDisponibilidadeHidrica(double disponibilidadeHidrica) { this.disponibilidadeHidrica = disponibilidadeHidrica; }

    public LocalDate getDataColeta() { return dataColeta; }
    public void setDataColeta(LocalDate dataColeta) { this.dataColeta = dataColeta; }

    public int getIdPropriedade() { return idPropriedade; }
    public void setIdPropriedade(int idPropriedade) { this.idPropriedade = idPropriedade; }

    // Polimorfismo override
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Monit. Hídrico | " + analisarSituacaoHidrica();
    }

    @Override
    public String toString() {
        return "=== MONITORAMENTO HÍDRICO ===" +
               "\n  ID              : " + idMonitoramento +
               "\n  Nível Reserv.   : " + nivelReservatorio + "%" +
               "\n  Umidade Solo    : " + umidadeSolo + "%" +
               "\n  Índice Pluv.    : " + indicePluviometrico + " mm" +
               "\n  Temperatura     : " + temperatura + "°C" +
               "\n  Disp. Hídrica   : " + disponibilidadeHidrica + " m³" +
               "\n  Data Coleta     : " + dataColeta +
               "\n  ID Propriedade  : " + idPropriedade +
               "\n  Análise         : " + analisarSituacaoHidrica();
    }
}
