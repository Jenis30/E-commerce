package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsPurchaseById (Long id);
}
