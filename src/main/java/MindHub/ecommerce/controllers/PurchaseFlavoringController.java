package MindHub.ecommerce.controllers;


import MindHub.ecommerce.dtos.PurchaseFlavoringDTO;

import MindHub.ecommerce.models.PurchaseFlavoring;
import MindHub.ecommerce.repositories.PurchaseFlavoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseFlavoringController {
    @Autowired
    private PurchaseFlavoringRepository purchaseFlavoringRepository;

    @GetMapping("/purchase/flavoring")
    public List<PurchaseFlavoringDTO> getAllPurchaseFlavorings(){
        List<PurchaseFlavoring> purchaseFlavorings = purchaseFlavoringRepository.findAll();
        return  purchaseFlavorings.stream()
                .map(PurchaseFlavoringDTO::new)
                .collect(Collectors.toList());
    }
}
