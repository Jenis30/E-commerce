package MindHub.ecommerce.dtos;

import java.util.List;
import java.util.Set;

public class BuyPurchaseDTO {

    private List<ProductsBuyDTO> productsDTO;

    public BuyPurchaseDTO() {
    }

    public BuyPurchaseDTO(List<ProductsBuyDTO> productsDTO) {
        this.productsDTO = productsDTO;
    }

    public List<ProductsBuyDTO> getProductsDTO() {
        return productsDTO;
    }

    @Override
    public String toString() {
        return "BuyPurchaseDTO{" + "productsDTO=" + productsDTO + '}';
    }
}
