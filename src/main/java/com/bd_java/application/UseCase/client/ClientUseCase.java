package com.bd_java.application.UseCase.client;

import java.util.List;

import com.bd_java.domain.entity.Client;
import com.bd_java.domain.repository.ClientRepository;

public class ClientUseCase {
    private final ClientRepository repository;

    public ClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public void registrarCliente(int id, String nombre, String email) {
        Client cliente = new Client(id, nombre, email);
        repository.guardar(cliente);
    }

    public Client obtenerCliente(int id) {
        return repository.buscarPorId(id);
    }

    public List<Client> listarClientes() {
        return repository.listarTodos();
    }

    public void actualizarCliente(int id, String nombre, String email) {
        Client cliente = new Client(id, nombre, email);
        repository.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        repository.eliminar(id);
    }
}
