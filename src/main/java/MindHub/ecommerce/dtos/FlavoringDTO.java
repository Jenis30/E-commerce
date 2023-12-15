package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.models.Presentation;

import java.awt.image.BufferedImage;

public class FlavoringDTO {
    private Long id;
    private String name;
    private String description;
    private Integer content;
    private Double price;
    private Integer stock;
    private Presentation presentation;
    private String image;
    private Boolean active;

    public FlavoringDTO(Flavoring flavoring){
        id = flavoring.getId();
        name = flavoring.getName();
        description = flavoring.getDescription();
        content = flavoring.getContent();
        price = flavoring.getPrice();
        stock = flavoring.getStock();
        presentation = flavoring.getPresentation();
        image = flavoring.getImage();
        active = flavoring.getActive();
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

    public Integer getContent() {
        return content;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public String getImage() {
        return image;
    }

    public Boolean getActive() {
        return active;
    }
}
