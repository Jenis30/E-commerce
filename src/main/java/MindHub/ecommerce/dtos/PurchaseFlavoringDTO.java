package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.PurchaseFlavoring;

import java.awt.image.BufferedImage;

public class PurchaseFlavoringDTO {
    private Long id;
    private  Integer quantity;
    private  Double subtotal;
    private Long flavoringId;

    private Long purchaseId;
    private BufferedImage image;


    public PurchaseFlavoringDTO(PurchaseFlavoring purchaseFlavoring) {
        id = purchaseFlavoring.getId();
        quantity = purchaseFlavoring.getQuantity();
        subtotal= purchaseFlavoring.getSubtotal();
        flavoringId = purchaseFlavoring.getFlavoring().getId();
        purchaseId = purchaseFlavoring.getPurchase().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Long getFlavoringId() {
        return flavoringId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }
}
