package com.resta.web.controller;

import com.resta.web.model.Pedido;
import com.resta.web.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/enviarAMesa")
    public String enviarAMesa(@RequestParam int mesa, @RequestParam Long pedidoId) {
        pedidoService.asignarAMesa(mesa, pedidoId);
        return "redirect:/mesa" + mesa;
    }

    @PostMapping("/eliminarPedido")
    public String eliminarPedido(@RequestParam Long pedidoId) {
        int mesa = pedidoService.obtenerMesaDePedido(pedidoId);
        pedidoService.eliminarPedido(pedidoId);
        return "redirect:/mesa" + mesa;
    }

    @GetMapping("/mesa/{numero}")
    public String verMesa(@PathVariable int numero, Model model) {
        model.addAttribute("pedidos", pedidoService.obtenerPedidosPorMesa(numero));
        return "mesa" + numero;
    }

    @GetMapping("/pedido/{pedidoId}")
    public String mostrarPedido(@PathVariable Long pedidoId, Model model) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(pedidoId);
        List<Pedido> pedidoList = Arrays.asList(pedido);
        model.addAttribute("pedido", pedidoList);
        model.addAttribute("pedidoId", pedidoId);
        model.addAttribute("total", pedido.getTotal());
        return "pedido";
    }
}
