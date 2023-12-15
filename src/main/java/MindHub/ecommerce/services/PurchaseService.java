package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Purchase;
import com.itextpdf.text.DocumentException;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface PurchaseService {
    List<Purchase> findAllPurchases();
    Purchase findPurchaseById (Long id);
    void savePurchase (Purchase purchase);

    void createAndSendPDFMail(Authentication authentication, Purchase purchase) throws DocumentException, IOException, MessagingException;
}
