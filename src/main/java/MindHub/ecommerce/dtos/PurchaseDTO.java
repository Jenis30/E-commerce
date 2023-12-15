package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.*;

import java.util.Set;

public class PurchaseDTO {
    private Long id;
    private Long client;
    private Double totalPurchases;
    private Set<PurchaseFragrance> purchaseFragrances;
    private Set<PurchaseFlavoring> purchaseFlavorings;
    private Set<PurchaseCream> purchaseCreams;


    public PurchaseDTO(Purchase purchase)
    {
        id = purchase.getId();
        client = purchase.getClient().getId();
        totalPurchases = purchase.getTotalPurchases();
        purchaseFragrances = purchase.getPurchaseFragances();
        purchaseFlavorings = purchase.getPurchaseFlavorings();
        purchaseCreams = purchase.getPurchaseCreams();
    }

    public Long getId() {
        return id;
    }

    public Long getClient() {
        return client;
    }

    public Double getTotalPurchases() {
        return totalPurchases;
    }

    public Set<PurchaseFragrance> getPurchaseFragances() {
        return purchaseFragrances;
    }

    public Set<PurchaseFlavoring> getPurchaseFlavorings() {
        return purchaseFlavorings;
    }

    public Set<PurchaseCream> getPurchaseCreams() {
        return purchaseCreams;
    }
}
