package com.example.evaldo.firebase.activity.Administrador.Classes;

public class Usuario {

    private String uid;
    private String email;
    private String name;
    private String cellPhone;
    private String status;
    private String function;

    public Usuario(String uid, String email, String name, String cellPhone, String status, String function) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.cellPhone = cellPhone;
        this.status = status;
        this.function = function;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(Usuario data){
        email = data.email;
        name = data.name;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + '\'' +
                ", emailFuncionario='" + email + '\'' +
                ", name='" + name + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", status='" + status + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}
