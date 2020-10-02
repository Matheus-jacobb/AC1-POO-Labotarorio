package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {
    
    @Autowired
    PedidoRepository repository;
            
    @GetMapping()
    public List<Pedido> getPedido(){
        return repository.getPedidos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int codigo){
        Pedido pedido = repository.getPedidosByCodigo(codigo);

        if (pedido == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(pedido);
    }

    @PostMapping()
    public ResponseEntity<Pedido> salvar (@RequestBody Pedido pedido){
        pedido = repository.save(pedido);
        URI uri = URI.create("http://localhost:8080/pedidos/"+pedido.getCodigo());
        return ResponseEntity.created(uri).build();
        }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover (@PathVariable int codigo ){
        Pedido pedido = repository.getPedidosByCodigo(codigo);

        if( pedido == null ){
            return ResponseEntity.notFound().build();
        }
        else {
            repository.delete(pedido);
            return ResponseEntity.noContent().build();
        }

    }

    @PutMapping  ("/{codigo}")
    public ResponseEntity<Pedido> atualizar (@RequestBody Pedido pedido, @PathVariable int codigo){
            Pedido aux = repository.getPedidosByCodigo(codigo);
            if ( aux != null){
                pedido.setCodigo(codigo);
                pedido = repository.update(pedido);
                return ResponseEntity.ok(pedido);
            }
            else   
                return ResponseEntity.notFound().build();

    }



}
