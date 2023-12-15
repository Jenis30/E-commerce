package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.repositories.CreamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CreamService {
   List<Cream> findAllCreams ();
   Cream findCreamById(Long id);
   boolean creamExistById(Long id);
   void saveCream (Cream cream);
   void deleteCreamById(Long id);
}
