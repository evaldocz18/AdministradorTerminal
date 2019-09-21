package com.example.evaldo.firebase.activity.Administrador.util;

import java.util.List;

public class DadoQuantitativo {

    private String pergunta;
    private List<String> respostas;

    public DadoQuantitativo(String pergunta, List<String> respostas) {
        this.pergunta = pergunta;
        this.respostas = respostas;
    }


    public String getPergunta() {
        return pergunta;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    @Override
    public String toString() {
        return "DadoQuantitativo{" +
                "pergunta='" + pergunta + '\'' +
                ", respostas=" + respostas +
                '}';
    }
}
