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
    private final File archivo = new File("insumos.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Insumo> obtenerTodos() {
        try {
            if (!archivo.exists())
                return new ArrayList<>();
            return mapper.readValue(archivo, new TypeReference<List<Insumo>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarTodos(List<Insumo> insumos) {
        try (FileWriter writer = new FileWriter(archivo)) {
            mapper.writeValue(writer, insumos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregar(Insumo nuevo) {
        List<Insumo> insumos = obtenerTodos();
        insumos.add(nuevo);
        guardarTodos(insumos);
    }

    public void eliminar(String nombre) {
        List<Insumo> insumos = obtenerTodos();
        insumos.removeIf(i -> i.getNombre().equals(nombre));
        guardarTodos(insumos);
    }

    public void editar(Insumo actualizado) {
        List<Insumo> insumos = obtenerTodos();
        for (int i = 0; i < insumos.size(); i++) {
            if (insumos.get(i).getNombre().equals(actualizado.getNombre())) {
                insumos.set(i, actualizado);
                break;
            }
        }
        guardarTodos(insumos);
    }
}