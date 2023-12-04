package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "eventos")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String data;
    private String hora;
    private String descricao;

    public Atividade(String nome, String data, String hora, String descricao) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    public Atividade() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}