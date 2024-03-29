
package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    
    private List<Pedido> pedidos;
    private int nextId;

    @PostConstruct
    public void inicial() {

        Pedido p1 = new Pedido();
        p1.setCodigo(1);
        p1.setCliente("Facens");
        p1.setValor(1500);
        p1.setDescricao("Lenovo-T480");
        p1.setData("13/07/1980");

        pedidos = new ArrayList<Pedido>();
        pedidos.add(p1);

        nextId=2;
    }
    public Pedido save(Pedido pedido){
        pedido.setCodigo(nextId);
        pedidos.add(pedido);
        nextId++;
        return pedido;
    }

    public List<Pedido> getPedidos(){
        return pedidos;
    }

    public Pedido getPedidosByCodigo(int codigo){

        for(Pedido aux : pedidos){
            if (aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }

    public void delete (Pedido pedido){
        pedidos.remove(pedido);
    }

    public Pedido update (Pedido pedido){

        Pedido aux = getPedidosByCodigo(pedido.getCodigo());
        if( aux != null){
            aux.setCodigo(pedido.getCodigo());
            aux.setData(pedido.getData());
            aux.setDescricao(pedido.getDescricao());
            aux.setValor(pedido.getValor());
        }
            return aux;
    }

}
