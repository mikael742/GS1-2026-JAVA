package bluesignal.domain;

/** Entidade PropriedadeRural - herda de EntidadeBase */
public class PropriedadeRural extends EntidadeBase {

    private int idPropriedade;
    private String nomePropriedade;
    private String localizacao;
    private double areaHectares;
    private String tipoCultivo;    // opcional
    private String coordenadas;   // opcional
    private int idUsuario;

    // Construtor padrão
    public PropriedadeRural() {
        super();
    }

    // Construtor não padrão básico
    public PropriedadeRural(int idPropriedade, String nomePropriedade, String localizacao,
                             double areaHectares, int idUsuario) {
        super(idPropriedade);
        this.idPropriedade = idPropriedade;
        this.nomePropriedade = nomePropriedade;
        this.localizacao = localizacao;
        this.areaHectares = areaHectares;
        this.idUsuario = idUsuario;
    }

    // Construtor não padrão completo (polimorfismo overload)
    public PropriedadeRural(int idPropriedade, String nomePropriedade, String localizacao,
                             double areaHectares, String tipoCultivo, String coordenadas, int idUsuario) {
        super(idPropriedade);
        this.idPropriedade = idPropriedade;
        this.nomePropriedade = nomePropriedade;
        this.localizacao = localizacao;
        this.areaHectares = areaHectares;
        this.tipoCultivo = tipoCultivo;
        this.coordenadas = coordenadas;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getIdPropriedade() { return idPropriedade; }
    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
        this.setId(idPropriedade);
    }

    public String getNomePropriedade() { return nomePropriedade; }
    public void setNomePropriedade(String nomePropriedade) { this.nomePropriedade = nomePropriedade; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public double getAreaHectares() { return areaHectares; }
    public void setAreaHectares(double areaHectares) { this.areaHectares = areaHectares; }

    public String getTipoCultivo() { return tipoCultivo; }
    public void setTipoCultivo(String tipoCultivo) { this.tipoCultivo = tipoCultivo; }

    public String getCoordenadas() { return coordenadas; }
    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    // Polimorfismo override
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Propriedade: " + nomePropriedade + " | " + areaHectares + " ha";
    }

    @Override
    public String toString() {
        return "=== PROPRIEDADE RURAL ===" +
               "\n  ID          : " + idPropriedade +
               "\n  Nome        : " + nomePropriedade +
               "\n  Localização : " + localizacao +
               "\n  Área        : " + areaHectares + " ha" +
               "\n  Tipo Cultivo: " + (tipoCultivo != null ? tipoCultivo : "Não informado") +
               "\n  Coordenadas : " + (coordenadas != null ? coordenadas : "Não informado") +
               "\n  ID Usuário  : " + idUsuario;
    }
}
