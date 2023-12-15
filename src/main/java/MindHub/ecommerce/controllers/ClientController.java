package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.ClientDTO;
import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = clientService.findAllClients().stream().map(client -> new ClientDTO(client)).collect(
                Collectors.toList());
        return clients;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        ClientDTO client = new ClientDTO(clientService.findClientById(id));
        return client;
    }

    @GetMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {
        return new ClientDTO(clientService.findClientByEmail(authentication.getName()));
    }

    @PostMapping("/clients/register")
    public ResponseEntity<Object> registerClient(@RequestParam String firstName, @RequestParam String lastName,
                                                 @RequestParam String email, @RequestParam String password,
                                                 @RequestParam(required = false) String address)
    {
        if (firstName.isBlank()) {
            return new ResponseEntity<>("Please complete this field with your name", HttpStatus.FORBIDDEN);
        }
        if (lastName.isBlank()) {
            return new ResponseEntity<>("Please complete this field with your last name", HttpStatus.FORBIDDEN);
        }
        if (email.isBlank()) {
            return new ResponseEntity<>("Please complete this field with your email", HttpStatus.FORBIDDEN);
        }
        if (password.isBlank()) {
            return new ResponseEntity<>("Please complete this field with your password", HttpStatus.FORBIDDEN);
        }
        if (clientService.existsClientByEmail(email)) {
            return new ResponseEntity<>("Already exists a client with this email", HttpStatus.FORBIDDEN);
        }
        Client newClient;
        if (address == null || address.isBlank()) {
            newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        } else {
            newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password), address);
        }
        clientService.saveClient(newClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
