package com.example.evaldo.firebase.activity.Administrador.Classes;

public class DispositivoAdministrador {

    private String dispositivoId;
    private String nomeDispositivo;
    private String status;
    private String dataAtivacao;

    public DispositivoAdministrador(String dispositivoId, String nomeDispositivo, String status, String dataAtivacao) {
        this.dispositivoId = dispositivoId;
        this.nomeDispositivo = nomeDispositivo;
        this.status = status;
        this.dataAtivacao = dataAtivacao;
    }


    public String getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(String dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public DispositivoAdministrador(){}

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DispositivoAdministrador{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", nomeDispositivo='" + nomeDispositivo + '\'' +
                ", status='" + status + '\'' +
                ", dataAtivacao='" + dataAtivacao + '\'' +
                '}';
    }
}
