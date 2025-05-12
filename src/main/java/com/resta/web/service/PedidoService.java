package com.resta.web.service;

import com.resta.web.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedidoService {

    private Map<Integer, List<Pedido>> mesas = new HashMap<>();
    private Map<Long, Pedido> pedidosSimulados = new HashMap<>();

    public PedidoService() {// aqui se usa el buble for para las mesas 1 a 4
        for (int i = 1; i <= 4; i++) {
            mesas.put(i, new ArrayList<>());// cada mesa tiene su lista vacia
        }
    }

    public void asignarAMesa(int mesa, Long pedidoId) {// llama al numero demesa y el pedido
        Pedido pedido = obtenerPedidoPorId(pedidoId);// obtiene los datos del pedido
        mesas.get(mesa).add(pedido);// se agrega el pedido a la mesa asignada
    }

    public void eliminarPedido(Long pedidoId) {// elimina el pedido llamandolo primero
        for (List<Pedido> pedidos : mesas.values()) {
            pedidos.removeIf(p -> p.getId().equals(pedidoId));// elimina el pedido si cumple con el pedido indicado a
                                                              // eliminar
        }
    }

    public int obtenerMesaDePedido(Long pedidoId) {
        for (Map.Entry<Integer, List<Pedido>> entry : mesas.entrySet()) {// revisa las mesas y listas de pedidos
            if (entry.getValue().stream().anyMatch(p -> p.getId().equals(pedidoId))) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public List<Pedido> obtenerPedidosPorMesa(int mesa) {
        return mesas.get(mesa);// va mostrar lo que contiene la mesa (el pedido)
    }

    public Pedido obtenerPedidoPorId(Long pedidoId) {
        // Si ya hay un pedido lo muestra
        if (pedidosSimulados.containsKey(pedidoId)) {
            return pedidosSimulados.get(pedidoId);
        }
        // Si no, crea uno de prueba y lo guarda
        Pedido pedido = new Pedido(pedidoId, "Producto demo", 1, 20.0);// verifica si existe algun pedido
        pedidosSimulados.put(pedidoId, pedido);// guarda el pedido
        return pedido;// muestra el pedido
    }
}
