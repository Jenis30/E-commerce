package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.PurchaseCream;

import java.awt.image.BufferedImage;

public class PurchaseCreamDTO {
    private Long id;

    private Integer quantity;
    private Double subtotal;
    private Long creamId;

    private  Long purchaseId;
    private BufferedImage image;


    public PurchaseCreamDTO(PurchaseCream purchaseCream) {
        id = purchaseCream.getId();
        quantity = purchaseCream.getQuantity();
        subtotal = purchaseCream.getSubtotal();
        creamId = purchaseCream.getCream().getId();
        purchaseId = purchaseCream.getPurchase().getId();
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

    public Long getCreamId() {
        return creamId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }
}
