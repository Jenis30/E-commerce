package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.models.Presentation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateFlavoringDTO {


    @JsonProperty(required = false)
    private String name;
    @JsonProperty (required = false)
    private String description;
    @JsonProperty (required = false)
    private Integer content;
    @JsonProperty (required = false)
    private Double price;
    @JsonProperty (required = false)
    private Integer stock;
    @JsonProperty (required = false)
    private Presentation presentation;
    @JsonProperty (required = false)
    private String image;


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

    public String getImage() {return image;}
}
