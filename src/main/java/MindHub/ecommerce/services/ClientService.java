package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAllClients();
    Client findClientById (Long id);
    Client findClientByEmail (String email);
    void saveClient (Client client);
    Boolean existsClientByEmail(String email);
}
