package com.example.evaldo.firebase.activity.Administrador.Classes;

import java.util.HashMap;

public class Resposta {

    private String pergunta;

    private HashMap<String, Integer> respostas = new HashMap<>();


    public Resposta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void count(String resposta){

        if (resposta == null) resposta = "vazio";

        if (!respostas.containsKey(resposta))respostas.put(resposta, 1);
        else {
            respostas.put(resposta, respostas.get(resposta) + 1);
        }

    }


}
