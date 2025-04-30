// ApiRestController.java
package com.resta.web.api;

import com.resta.web.model.Insumo;
import com.resta.web.model.Usuario;
import com.resta.web.service.InsumoService;
import com.resta.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private UsuarioService usuarioService;

    // --- INSUMOS ---

    @GetMapping("/insumos") // ver la lista de insumos
    public List<Insumo> listarInsumos() {
        return insumoService.obtenerTodos();
    }

    @PostMapping("/insumos") // agregar insumo
    public void agregarInsumo(@RequestBody Insumo insumo) {
        insumoService.agregar(insumo);
    }

    @PutMapping("/insumos") // actualizar un insumo
    public void editarInsumo(@RequestBody Insumo insumo) {
        insumoService.editar(insumo);
    }

    @DeleteMapping("/insumos/{nombre}") // elimina un insumo
    public void eliminarInsumo(@PathVariable String nombre) {
        insumoService.eliminar(nombre);
    }

    // --- USUARIOS ---

    @GetMapping("/usuarios") // ver los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping("/usuarios") // agrega un usuario
    public void registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.registrar(usuario);
    }

    @PostMapping("/usuarios/login") // verificar si el usuario existe
    public boolean loginUsuario(@RequestBody Usuario datos) {
        return usuarioService.autenticar(datos.getUsuario(), datos.getPassword());
    }

    @GetMapping("/usuarios/{nombre}") // ver un solo usuario especificado
    public Usuario obtenerUsuario(@PathVariable String nombre) {
        return usuarioService.obtenerUsuarioPorNombre(nombre);
    }
}