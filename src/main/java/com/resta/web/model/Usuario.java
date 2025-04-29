package com.resta.web.model;

public class Usuario {// en la clase usuario se guarda los tributos usuario, contraseña y dni
    private String usuario;
    private String password;
    private String dni;

    public Usuario() {
    }

    public Usuario(String usuario, String password, String dni) {// guarda los datos del usuario en las variables
        this.usuario = usuario;
        this.password = password;
        this.dni = dni;
    }

    public String getUsuario() {// acceder y cambiar el valor de usuario al igual que en password
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
