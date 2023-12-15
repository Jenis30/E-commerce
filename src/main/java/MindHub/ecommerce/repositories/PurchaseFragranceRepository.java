package MindHub.ecommerce.repositories;


import MindHub.ecommerce.models.PurchaseFragrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface PurchaseFragranceRepository extends JpaRepository<PurchaseFragrance, Long> {
}


