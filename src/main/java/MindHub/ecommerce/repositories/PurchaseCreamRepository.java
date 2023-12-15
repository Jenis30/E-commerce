package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.PurchaseCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchaseCreamRepository extends JpaRepository<PurchaseCream, Long> {
}
