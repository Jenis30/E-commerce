package MindHub.ecommerce.services;

import MindHub.ecommerce.models.Flavoring;
import MindHub.ecommerce.models.Purchase;
import com.itextpdf.text.DocumentException;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface FlavoringService {
    List<Flavoring> findAllFlavorings ();
    Flavoring findFlavoringByID(Long id);
    void saveFlavoring(Flavoring flavoring);
    void deleteFlavoringById(Long id);
    boolean existFlavoringById(Long id);

}
