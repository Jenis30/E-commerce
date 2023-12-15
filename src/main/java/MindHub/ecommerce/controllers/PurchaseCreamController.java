package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;
import MindHub.ecommerce.dtos.PurchaseCreamDTO;
import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.models.PurchaseCream;
import MindHub.ecommerce.repositories.PurchaseCreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseCreamController {
    @Autowired
    private PurchaseCreamRepository purchaseCreamRepository;

    @GetMapping("/purchase/cream")
    public List<PurchaseCreamDTO> getAllPurchaseCreams(){
        List<PurchaseCream> purchaseCreams = purchaseCreamRepository.findAll();
        return  purchaseCreams.stream()
                .map(PurchaseCreamDTO::new)
                .collect(Collectors.toList());
    }
}
