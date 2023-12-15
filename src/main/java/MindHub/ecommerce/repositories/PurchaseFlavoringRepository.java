package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.PurchaseFlavoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchaseFlavoringRepository extends JpaRepository<PurchaseFlavoring, Long> {
}
