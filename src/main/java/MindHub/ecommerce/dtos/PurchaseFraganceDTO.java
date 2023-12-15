package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Fragrance;
import MindHub.ecommerce.models.Purchase;
import MindHub.ecommerce.models.PurchaseFragrance;

public class PurchaseFraganceDTO {
    private Long id;
    private Purchase purchase;
    private Fragrance fragrance;
    private Integer quantity;
    private Double subtotal;

    public PurchaseFraganceDTO(PurchaseFragrance purchaseFragrance) {
        this.id = purchaseFragrance.getId();
        this.purchase = purchaseFragrance.getPurchase();
        this.fragrance = purchaseFragrance.getFragance();
        this.quantity = purchaseFragrance.getQuantity();
        this.subtotal = purchaseFragrance.getSubtotal();
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Fragrance getFragance() {
        return fragrance;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
