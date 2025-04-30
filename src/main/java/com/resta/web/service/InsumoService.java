package com.resta.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resta.web.model.Insumo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsumoService {
    private final File archivo = new File("insumos.json"); // Archivo donde se guardan los insumos
    private final ObjectMapper mapper = new ObjectMapper(); // Se usa para leer los JSON

    public List<Insumo> obtenerTodos() { // Método para obtener todos los insumos desde el archivo
        try {
            if (!archivo.exists()) // Si el archivo no existe, devuelve una lista vacía
                return new ArrayList<>();
            return mapper.readValue(archivo, new TypeReference<List<Insumo>>() {
            }); // Si existe, lo lee y lo convierte en una lista de insumos
        } catch (Exception e) { // Si algo falla al leer el archivo
            e.printStackTrace(); // Muestra el error en consola
            return new ArrayList<>(); // Devuelve una lista vacía si hay error
        }
    }

    public void guardarTodos(List<Insumo> insumos) { // Guarda toda la lista de insumos en el archivo JSON
        try (FileWriter writer = new FileWriter(archivo)) { // Escribe en el archivo pero lo crea si no existe
            mapper.writeValue(writer, insumos); // Convierte la lista a JSON y lo guarda
        } catch (Exception e) { // Si algo falla al guardar
            e.printStackTrace(); // Muestra el error
        }
    }

    public void agregar(Insumo nuevo) { // Método para agregar un nuevo insumo
        List<Insumo> insumos = obtenerTodos(); // Obtiene la lista actual
        insumos.add(nuevo); // Agrega el nuevo insumo
        guardarTodos(insumos); // Guarda la lista actualizada
    }

    public void eliminar(String nombre) { // Elimina un insumo según su nombre
        List<Insumo> insumos = obtenerTodos(); // Carga la lista
        insumos.removeIf(i -> i.getNombre().equals(nombre)); // Quita el insumo que tenga ese nombre
        guardarTodos(insumos); // Guarda la lista actualizada
    }

    public void editar(Insumo actualizado) { // Edita los datos de un insumo ya existente
        List<Insumo> insumos = obtenerTodos(); // Carga la lista
        for (int i = 0; i < insumos.size(); i++) {
            if (insumos.get(i).getNombre().equals(actualizado.getNombre())) { // Si el nombre coincide
                insumos.set(i, actualizado); // Reemplaza el insumo viejo por el actualizado
                break;
            }
        }
        guardarTodos(insumos); // Guarda la lista con los cambios
    }
}
