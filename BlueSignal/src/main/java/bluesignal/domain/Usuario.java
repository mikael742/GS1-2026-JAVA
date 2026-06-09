package bluesignal.domain;

/** Entidade Usuario - herda de EntidadeBase */
public class Usuario extends EntidadeBase {

    private String nome;
    private String email;
    private String senha;
    private String telefone;      // opcional
    private String tipoUsuario;   // PRODUTOR, EMPRESA, ADMIN
    private int idUsuario;

    // Construtor padrão
    public Usuario() {
        super();
    }

    // Construtor não padrão completo
    public Usuario(int idUsuario, String nome, String email, String senha, String tipoUsuario) {
        super(idUsuario);
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Construtor não padrão com telefone (polimorfismo overload)
    public Usuario(int idUsuario, String nome, String email, String senha, String telefone, String tipoUsuario) {
        super(idUsuario);
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        this.setId(idUsuario);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    // Polimorfismo override - sobrescreve exibirResumo da classe base
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Usuário: " + nome + " [" + tipoUsuario + "]";
    }

    @Override
    public String toString() {
        return "=== USUÁRIO ===" +
               "\n  ID          : " + idUsuario +
               "\n  Nome        : " + nome +
               "\n  Email       : " + email +
               "\n  Telefone    : " + (telefone != null ? telefone : "Não informado") +
               "\n  Tipo        : " + tipoUsuario +
               "\n  Cadastro    : " + getDataCadastro();
    }
}
