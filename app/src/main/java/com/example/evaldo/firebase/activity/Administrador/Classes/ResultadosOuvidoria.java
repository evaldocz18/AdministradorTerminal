package com.example.evaldo.firebase.activity.Administrador.Classes;

public class ResultadosOuvidoria {

    private String hora, idDispositivo ,administradorResponsavel, resposta;

    public ResultadosOuvidoria(String hora, String idDispositivo, String administradorResponsavel, String resposta) {
        this.hora = hora;
        this.idDispositivo = idDispositivo;
        this.administradorResponsavel = administradorResponsavel;
        this.resposta = resposta;
    }

    public ResultadosOuvidoria() {}

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getAdministradorResponsavel() {
        return administradorResponsavel;
    }

    public void setAdministradorResponsavel(String administradorResponsavel) {
        this.administradorResponsavel = administradorResponsavel;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "ResultadosOuvidoria{" +
                "hora='" + hora + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", administradorResponsavel='" + administradorResponsavel + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }
}
