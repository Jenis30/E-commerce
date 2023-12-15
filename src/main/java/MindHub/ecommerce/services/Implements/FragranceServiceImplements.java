package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Fragrance;
import MindHub.ecommerce.repositories.FragranceRepository;
import MindHub.ecommerce.services.FragranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FragranceServiceImplements implements FragranceService {
    @Autowired
    private FragranceRepository fragranceRepository;
    @Override
    public void saveFragance(Fragrance fragrance){
        fragranceRepository.save(fragrance);
    }

    @Override
    public List<Fragrance> findAllFragances() {
        return fragranceRepository.findAll();
    }
    @Override
    public Fragrance findFraganceById(Long id) {
        return fragranceRepository.findById(id).orElse(null);
    }
}
