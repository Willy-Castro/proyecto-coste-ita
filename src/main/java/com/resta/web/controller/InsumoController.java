package com.resta.web.controller;

import com.resta.web.model.Insumo;
import com.resta.web.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public String verInsumos(Model model) {
        model.addAttribute("insumos", insumoService.obtenerTodos());
        return "insumos";
    }

    @PostMapping("/agregar")
    public String agregarInsumo(@ModelAttribute Insumo insumo) {
        insumoService.agregar(insumo);
        return "redirect:/insumos";
    }

    @PostMapping("/editar")
    public String editarInsumo(@ModelAttribute Insumo insumo) {
        insumoService.editar(insumo);
        return "redirect:/insumos";
    }

    @PostMapping("/eliminar")
    public String eliminarInsumo(@RequestParam String nombre) {
        insumoService.eliminar(nombre);
        return "redirect:/insumos";
    }
}
