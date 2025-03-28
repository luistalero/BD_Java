package com.bd_java.application.UseCase.product;

import java.util.List;

import com.bd_java.domain.entity.Product;
import com.bd_java.domain.repository.ProductRepository;

public class ProductUseCase {
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registrarproducto(int id, String nombre, int stock) {
        Product producto = new Product(id, nombre, stock);
        repository.guardar(producto);
    }

    public Product obtenerproducto(int id) {
        return repository.buscarPorId(id);
    }

    public List<Product> listarproductos() {
        return repository.listarTodos();
    }

    public void actualizarproducto(int id, String nombre, int Stock) {
        Product producto = new Product(id, nombre, Stock);
        repository.actualizar(producto);
    }

    public void eliminarproducto(int id) {
        repository.eliminar(id);
    }
}
