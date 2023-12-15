package MindHub.ecommerce.dtos;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;
import com.fasterxml.jackson.annotation.JsonBackReference;


import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<PurchaseDTO> totalPurchases;

    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        address = client.getAddress();
        totalPurchases = client.getTotalPurchases().stream().map(purchase -> new PurchaseDTO(purchase)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Set<PurchaseDTO> getTotalPurchases() {
        return totalPurchases;
    }
}
