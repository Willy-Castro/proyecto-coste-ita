package com.resta.web.controller;

import com.resta.web.model.Usuario;
import com.resta.web.service.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;

@Controller // maneja las rutas registro y login
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil") // para ejecutar cuando se acceda al perfil
    public String mostrarPerfil(HttpSession session, Model model) {// accede a los datos guardados de session
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        // saca los datos del usuario que inicio sesion
        if (usuario == null) {
            return "redirect:/login.html"; // Por si entra sin haber iniciado sesion
        }

        model.addAttribute("usuario", usuario);// los datos que contiene usuario se pueden usar
        return "perfil"; // se visualiza el nombre en el html perfil
    }

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodos(); // obtenemos la lista
        model.addAttribute("usuarios", usuarios); // pasamos al HTML
        return "trabajadores"; // este será el nombr e del HTML a crear
    }

    @GetMapping("/logout") // para que el perfil de usuario no se quede con el mismo usuario
    public String cerrarSesion(HttpSession session) {// los datos de session los busca
        session.invalidate(); // Elimina todos los datos de sesión
        return "redirect:/login.html"; // Redirige al login
    }

    @PostMapping("/register") // Registramos un usuario con todos los datos nuevos
    public void registrar(@RequestParam String usuario,
            @RequestParam String apellido,
            @RequestParam String correo,
            @RequestParam String dni,
            @RequestParam String password,
            HttpServletResponse response) throws IOException {
        // llama a usuarioservice para guardar esos datos
        Usuario nuevoUsuario = new Usuario(usuario, apellido, correo, dni, password);
        try {
            usuarioService.registrar(nuevoUsuario);
            response.sendRedirect("/login.html");// dirige al html de login
        } catch (IllegalArgumentException e) {
            response.sendRedirect("/nore.html");
        }
    }

    @PostMapping("/login")
    public void login(@RequestParam String correo,
            @RequestParam String password, // recibe los datos del login.html
            HttpServletResponse response,
            HttpSession session) throws IOException {
        if (usuarioService.autenticar(correo, password)) {// llama usuarioservice verifica si existen los datos
            // busca y guarda los datos para mostrarlos en perfil del usuario cuando se
            // necesite
            Usuario usuarioAutenticado = usuarioService.obtenerUsuarioPorCorreo(correo);
            session.setAttribute("usuarioLogueado", usuarioAutenticado);
            response.sendRedirect("/index_rest.html");// dirige al index que es la pagina inicial
        } else {
            response.sendRedirect("/no.html");// dirige a la pantalla de fallo que es cuando no estas registrado
        }
    }
}
