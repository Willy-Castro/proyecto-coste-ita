package com.resta.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Conecta las rutas
public class ProductoController {

    @GetMapping("/productos")
    public String productos() {
        return "productos"; // Va a productos.html
    }

    @GetMapping("/pago")
    public String pago() {
        return "pago"; // Va a pago.html
    }

}