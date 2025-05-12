package com.resta.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.resta.web.model.Usuario;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {// registrar y autenticar usuarios
    private final File archivo = new File("usuarios.json"); // se va guardar los datos ingresados en el .json
    private final ObjectMapper mapper = new ObjectMapper();

    public void registrar(Usuario nuevoUsuario) {// registrar al usuario
        List<Usuario> usuarios = obtenerTodos();// carga la lista de usuarios actuales del archivo
        boolean correoRepetido = usuarios.stream()
                .anyMatch(u -> u.getCorreo().equals(nuevoUsuario.getCorreo()));
        if (correoRepetido) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        usuarios.add(nuevoUsuario);// agrega al nuevo usuario
        guardarTodos(usuarios);// guarda todo de nuevo en el archivo
    }

    public boolean autenticar(String correo, String password) {
        // Validamos si el correo ya existe para evitar duplicados
        return obtenerTodos().stream()
                .anyMatch(u -> u.getCorreo().equals(correo) && u.getPassword().equals(password));
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {// comprueba y brinda los datos del correo requerido
        return obtenerTodos().stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> obtenerTodos() {// lee la lista de usuarios
        try {
            if (!archivo.exists())// si no exite archivo de usuarios.json no va fallar el programa
                return new ArrayList<>();// va botar lista vacia
            ObjectReader reader = mapper.readerFor(new TypeReference<List<Usuario>>() {
            });// permite leer archivo .json
            return reader.readValue(archivo);// lee el archivo .json
        } catch (Exception e) {// si falla algo al leer
            e.printStackTrace();// lanza mensaje del error
            return new ArrayList<>();
        }
    }

    private void guardarTodos(List<Usuario> usuarios) {// guarda los usuarios en el archivo usuarios.json
        try (FileWriter writer = new FileWriter(archivo)) {// escribe los datos en el archivo y si no existe lo crea
            mapper.writeValue(writer, usuarios);
        } catch (Exception e) {// por si hay algun error lo muestre en la consola cual es
            e.printStackTrace();
        }
    }

    // metodo para obtener datos del usuario que inicio sesion
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {// lee lista
        return obtenerTodos().stream()// esa lista se convierte en flujo de datos
                .filter(u -> u.getUsuario().equals(nombreUsuario))// aqui se aplica el filtro
                .findFirst()// y se obtiene los datos del usuario que inicio sesion
                .orElse(null);
    }
}
