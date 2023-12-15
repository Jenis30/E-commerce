package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.*;

import java.util.Set;

public class FragranceDTO {
    private Long id;
    private String name;
    private String description;
    private Gender gender;
    private OlfactoryFamily olfactoryFamily;
    private String image;
    private Double price;
    private Presentation presentation;
    private Integer content;
    private Integer stock;
    private Set<PurchaseFragrance> purchesFragances;
    private Boolean active;

    public FragranceDTO(Fragrance fragrance)
    {
        this.id = fragrance.getId();
        this.name = fragrance.getName();
        this.description = fragrance.getDescription();
        this.gender = fragrance.getGender();
        this.olfactoryFamily = fragrance.getOlfactoryFamily();
        this.image = fragrance.getImage();
        this.price = fragrance.getPrice();
        this.presentation = fragrance.getPresentation();
        this.content = fragrance.getContent();
        this.stock = fragrance.getStock();
        this.purchesFragances = fragrance.getPurchesFragances();
        this.active = fragrance.getActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Gender getGender() {
        return gender;
    }

    public OlfactoryFamily getOlfactoryFamily() {
        return olfactoryFamily;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public Integer getContent() {
        return content;
    }

    public Integer getStock() {
        return stock;
    }

    public Set<PurchaseFragrance> getPurchesFragances() {
        return purchesFragances;
    }

    public Boolean getActive() {
        return active;
    }
}
