package MindHub.ecommerce.repositories;

import MindHub.ecommerce.models.Flavoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlavoringRepository extends JpaRepository<Flavoring, Long> {


}
