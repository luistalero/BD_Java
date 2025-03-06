package com.bd_java.domain.repository;

import java.util.List;

import com.bd_java.domain.entity.Client;

public class ClientRespository {
    void guardar(Client cliente);
    Client buscarPorId(int id);
    List<Client> listarTodos();
    void actualizar(Client cliente);
    void eliminar(int id);
}
