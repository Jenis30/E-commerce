package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.services.CreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreamServiceImplement implements CreamService {
    @Autowired
    private CreamRepository creamRepository;
    @Override
    public List<Cream> findAllCreams() {
        return creamRepository.findAll();
    }

    @Override
    public Cream findCreamById(Long id) {
        return creamRepository.findById(id).orElseThrow(null);
    }

    @Override
    public boolean creamExistById(Long id) {
        return creamRepository.existsById(id);
    }

    @Override
    public void saveCream(Cream cream) {
        creamRepository.save(cream);
    }

    @Override
    public void deleteCreamById(Long id) {
        creamRepository.deleteById(id);
    }


}
