package MindHub.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Client client;
    private Double totalPurchases;
    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PurchaseFragrance> purchaseFragrances = new HashSet<>();
    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PurchaseFlavoring> purchaseFlavorings = new HashSet<>();
    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PurchaseCream> purchaseCreams = new HashSet<>();

    public Purchase() {
    }

    public Purchase(Double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(Double totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public Set<PurchaseFragrance> getPurchaseFragances() {
        return purchaseFragrances;
    }

    public void setPurchaseFragances(Set<PurchaseFragrance> purchaseFragrances) {
        this.purchaseFragrances = purchaseFragrances;
    }

    public Set<PurchaseFlavoring> getPurchaseFlavorings() {
        return purchaseFlavorings;
    }

    public void setPurchaseFlavorings(Set<PurchaseFlavoring> purchaseFlavorings) {
        this.purchaseFlavorings = purchaseFlavorings;
    }

    public Set<PurchaseCream> getPurchaseCreams() {
        return purchaseCreams;
    }

    public void setPurchaseCreams(Set<PurchaseCream> purchaseCreams) {
        this.purchaseCreams = purchaseCreams;
    }

    public void addPurchaseFragance (PurchaseFragrance purchaseFragrance){
        purchaseFragrance.setPurchase(this);
        purchaseFragrances.add(purchaseFragrance);
    }

    public void addPurchaseFlavoring (PurchaseFlavoring purchaseFlavoring){
        purchaseFlavoring.setPurchase(this);
        purchaseFlavorings.add(purchaseFlavoring);
    }
    public void addPurchaseCream (PurchaseCream purchaseCream){
        purchaseCream.setPurchase(this);
        purchaseCreams.add(purchaseCream);
    }
}
