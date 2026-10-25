package com.bharatthymes.system.service;

import com.bharatthymes.system.entity.Client;
import com.bharatthymes.system.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Client not found with ID: " + id));
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = getClientById(id);

        existingClient.setCompanyName(updatedClient.getCompanyName());
        existingClient.setContactPerson(updatedClient.getContactPerson());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setGstin(updatedClient.getGstin());
        existingClient.setAddress(updatedClient.getAddress());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {
        Client existingClient = getClientById(id);
        clientRepository.delete(existingClient);
    }
}
