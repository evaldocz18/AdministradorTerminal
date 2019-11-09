package com.example.evaldo.firebase.activity.Administrador.Classes;

public class TerminalPesquisa {
    String nomeDispositivo,idDispositivo, status,key, dataAtivacao, questionarioAtual, imagemDivulgacaoEPergunta[], ultimaVezOnline[], imagemDivulgacao[] ;

    public TerminalPesquisa(String nomeDispositivo, String idDispositivo, String status, String key, String dataAtivacao, String questionarioAtual) {
        this.nomeDispositivo = nomeDispositivo;
        this.idDispositivo = idDispositivo;
        this.status = status;
        this.key = key;
        this.dataAtivacao = dataAtivacao;
        this.questionarioAtual = questionarioAtual;
    }

    public TerminalPesquisa(String nomeDispositivo, String idDispositivo, String status, String key, String dataAtivacao, String questionarioAtual, String[] imagemDivulgacaoEPergunta, String[] ultimaVezOnline, String[] imagemDivulgacao) {
        this.nomeDispositivo = nomeDispositivo;
        this.idDispositivo = idDispositivo;
        this.status = status;
        this.key = key;
        this.dataAtivacao = dataAtivacao;
        this.questionarioAtual = questionarioAtual;
        this.imagemDivulgacaoEPergunta = imagemDivulgacaoEPergunta;
        this.ultimaVezOnline = ultimaVezOnline;
        this.imagemDivulgacao = imagemDivulgacao;
    }

    public TerminalPesquisa(){}

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(String dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public String getQuestionarioAtual() {
        return questionarioAtual;
    }

    public void setQuestionarioAtual(String questionarioAtual) {
        this.questionarioAtual = questionarioAtual;
    }

    @Override
    public String toString() {
        return "TerminalPesquisa{" +
                "nomeDispositivo='" + nomeDispositivo + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", status='" + status + '\'' +
                ", key='" + key + '\'' +
                ", dataAtivacao='" + dataAtivacao + '\'' +
                ", questionarioAtual='" + questionarioAtual + '\'' +
                '}';
    }
}