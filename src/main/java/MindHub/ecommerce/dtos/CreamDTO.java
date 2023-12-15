package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Cream;
import MindHub.ecommerce.models.Type;

public class CreamDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer content;
    private Integer stock;
    private Type type;
    private String image;
    private Boolean active;

    public CreamDTO(Cream cream){
        id = cream.getId();
        name = cream.getName();
        description = cream.getDescription();
        price = cream.getPrice();
        content = cream.getContent();
        stock = cream.getStock();
        type = cream.getType();
        image = cream.getImage();
        active = cream.getActive();
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

    public Double getPrice() {
        return price;
    }

    public Integer getContent() {
        return content;
    }

    public Integer getStock() {
        return stock;
    }

    public Type getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public Boolean getActive() {
        return active;
    }
}
