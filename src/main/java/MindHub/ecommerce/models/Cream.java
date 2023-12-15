package MindHub.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cream {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer content;
    private Integer stock;
    private Type type;
    private String image;
    private Boolean active;
    @OneToMany(mappedBy = "cream", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PurchaseCream> purchaseCreams= new HashSet<>();

    public Cream() {
    }

    public Cream(String name, String description, Double price, Integer content, Integer stock, Type type,
                 String image, Boolean active)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.content = content;
        this.stock = stock;
        this.type = type;
        this.image = image;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<PurchaseCream> getPurchaseCreams() {
        return purchaseCreams;
    }

    public void setPurchaseCreams(Set<PurchaseCream> purchaseCreams) {
        this.purchaseCreams = purchaseCreams;
    }
}
