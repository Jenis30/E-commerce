package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail (String Email);
    boolean existsClientByEmail(String Email);
    boolean existsClientById (Long id);
}
