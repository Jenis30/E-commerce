package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Fragrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FragranceRepository extends JpaRepository<Fragrance, Long> {
}
