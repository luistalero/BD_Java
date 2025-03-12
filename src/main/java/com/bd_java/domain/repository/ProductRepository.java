package com.bd_java.domain.repository;

import java.util.List;

import com.bd_java.domain.entity.Product;

public interface ProductRepository {
    void guardar(Product product);
    Product buscarPorId(int id);
    List<Product> listarTodos();
    void actualizar(Product product);
    void eliminar(int id);
}
