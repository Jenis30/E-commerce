package MindHub.ecommerce.services.Implements;

import MindHub.ecommerce.models.Client;
import MindHub.ecommerce.models.Purchase;
import MindHub.ecommerce.models.PurchasePDF;
import MindHub.ecommerce.repositories.PurchaseRepository;
import MindHub.ecommerce.services.ClientService;
import MindHub.ecommerce.services.PurchaseService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PurchaseServiceImplements implements PurchaseService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ClientService clientService;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    @Override
    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public void createAndSendPDFMail(Authentication authentication, Purchase purchase) throws DocumentException, IOException, MessagingException {

        Client client = clientService.findClientByEmail(authentication.getName());

        PurchasePDF exporter = new PurchasePDF(purchase);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.usePDFExport(outputStream);

        // Create a new MimeMessage object
        MimeMessage message = mailSender.createMimeMessage();

        // Create a new MimeMessageHelper object
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set the email parameters
        helper.setTo(client.getEmail());
        helper.setSubject("Your Purchase PDF");
        helper.setText("Here is your purchase PDF!");

        // Create a ByteArrayResource from the PDF bytes
        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

        // Add the PDF as an attachment
        helper.addAttachment("Purchase ID:" + purchase.getId() + ".pdf", byteArrayResource);

        // Send the email
        mailSender.send(message);
    }
}
