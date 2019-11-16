package com.example.evaldo.firebase.activity.Administrador.Classes;

public class ResultadosOuvidoria {

    private String hora, idDispositivo , nomeFuncionario, emailFuncionario,resposta;

    public ResultadosOuvidoria(String hora, String idDispositivo, String nomeFuncionario, String emailFuncionario, String resposta) {
        this.hora = hora;
        this.idDispositivo = idDispositivo;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
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

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    @Override
    public String toString() {
        return "ResultadosOuvidoria{" +
                "hora='" + hora + '\'' +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", emailFuncionario='" + emailFuncionario + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }
}
