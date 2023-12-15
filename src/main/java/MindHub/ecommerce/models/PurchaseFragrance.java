package MindHub.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseFragrance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Purchase purchase;
    @ManyToOne
    @JsonIgnore
    private Fragrance fragrance;
    private Integer quantity;
    private Double subtotal;


    public PurchaseFragrance() {
    }

    public PurchaseFragrance(Integer quantity, Double subtotal) {
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Fragrance getFragance() {
        return fragrance;
    }

    public void setFragance(Fragrance fragrance) {
        this.fragrance = fragrance;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
