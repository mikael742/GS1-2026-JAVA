package bluesignal.domain;

import java.time.LocalDate;

/** Classe base para herança - contém atributos comuns a todas as entidades */
public abstract class EntidadeBase {

    private int id;
    private LocalDate dataCadastro;

    // Construtor padrão
    public EntidadeBase() {
        this.dataCadastro = LocalDate.now();
    }

    // Construtor não padrão
    public EntidadeBase(int id) {
        this.id = id;
        this.dataCadastro = LocalDate.now();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Metodo que pode ser sobrescrito (polimorfismo override)
    public String exibirResumo() {
        return "ID: " + id + " | Cadastro: " + dataCadastro;
    }

    // Metodo toString abstrato para forçar implementação nas subclasses
    @Override
    public abstract String toString();
}
