package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Fragrance;

import java.util.List;

public interface FragranceService {
    void saveFragance(Fragrance fragrance);
    List<Fragrance> findAllFragances();
    Fragrance findFraganceById (Long id);
}
