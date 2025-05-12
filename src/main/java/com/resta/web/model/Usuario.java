package com.resta.web.model;

public class Usuario {// en la clase usuario se guarda los tributos usuario, contraseña y dni
    private String usuario;
    private String apellido;
    private String correo;
    private String dni;
    private String password;

    public Usuario() {
    }

    public Usuario(String usuario, String apellido, String correo, String dni, String password) {
        // guarda los datos del usuario en las variables
        this.usuario = usuario;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.password = password;
    }

    public String getUsuario() {// acceder y cambiar el valor de usuario al igual que en password
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
