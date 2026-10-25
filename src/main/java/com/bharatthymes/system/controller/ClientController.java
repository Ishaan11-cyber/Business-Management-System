package com.bharatthymes.system.controller;

import com.bharatthymes.system.entity.Client;
import com.bharatthymes.system.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create a new client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Get a client by ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // Update a client
    @PutMapping("/{id}")
    public Client updateClient(
            @PathVariable Long id,
            @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
