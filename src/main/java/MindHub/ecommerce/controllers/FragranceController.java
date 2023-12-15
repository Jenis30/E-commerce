package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.FragranceDTO;
import MindHub.ecommerce.models.Fragrance;
import MindHub.ecommerce.models.Gender;
import MindHub.ecommerce.models.OlfactoryFamily;
import MindHub.ecommerce.models.Presentation;
import MindHub.ecommerce.services.FragranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class FragranceController {
    @Autowired
    private FragranceService fragranceService;

    @GetMapping("/fragances")
    public List<FragranceDTO> getAllFragances() {
        List<Fragrance> fragancesActives = fragranceService.findAllFragances().stream().filter(fragance -> fragance.getActive()).collect(Collectors.toList());
        List<FragranceDTO> fragancesDTO = fragancesActives.stream().map(
                fragance -> new FragranceDTO(fragance)).collect(Collectors.toList());
        return fragancesDTO;
    }

    @GetMapping("/fragances/{id}")
    public FragranceDTO getFragance(@PathVariable Long id) {
        FragranceDTO fragance = new FragranceDTO(fragranceService.findFraganceById(id));
        return fragance;
    }

    @PostMapping("/fragances/create")
    public ResponseEntity<String> createNewFragance(@RequestParam String name, @RequestParam String description,
                                                    @RequestParam Gender gender,
                                                    @RequestParam OlfactoryFamily olfactoryFamily,
                                                    @RequestParam Double price, @RequestParam Presentation presentation,
                                                    @RequestParam Integer content, @RequestParam Integer stock,
                                                    @RequestParam String image)
    {
        List<String> fragancesName = fragranceService.findAllFragances().stream().map(
                fragance -> fragance.getName()).collect(Collectors.toList());

        if (name.isEmpty()) {
            return new ResponseEntity<>("The fragrance name is required.", HttpStatus.BAD_REQUEST);
        }
        if (description.isEmpty()) {
            return new ResponseEntity<>("The fragrance description is required.", HttpStatus.BAD_REQUEST);
        }
        if (price == null) {
            return new ResponseEntity<>("The fragrance price is required.", HttpStatus.BAD_REQUEST);
        }
        if (content == null) {
            return new ResponseEntity<>("The fragrance content is required.", HttpStatus.BAD_REQUEST);
        }
        if (stock == null) {
            return new ResponseEntity<>("The fragrance stock is required.", HttpStatus.BAD_REQUEST);
        }
        if (fragancesName.contains(name)) {
            return new ResponseEntity<>("The fragrance name already use", HttpStatus.BAD_REQUEST);
        }
        if (image.isEmpty()) {
            return new ResponseEntity<>("The fragrance image is required.", HttpStatus.BAD_REQUEST);
        } else {
            Fragrance newFragrance = new Fragrance(name, description, gender, olfactoryFamily, image, price, presentation,
                    content, stock, true);
            fragranceService.saveFragance(newFragrance);
            return new ResponseEntity<>("Fragance create successfully", HttpStatus.CREATED);
        }
    }

    @PatchMapping("/fragances/update")
    public ResponseEntity<String> updateFragance(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestParam(required = false) Gender gender,
                                                 @RequestParam(required = false) OlfactoryFamily olfactoryFamily,
                                                 @RequestParam(required = false) Double price,
                                                 @RequestParam(required = false) Presentation presentation,
                                                 @RequestParam(required = false) Integer content,
                                                 @RequestParam(required = false) Integer stock,
                                                 @RequestParam(required = false) String image, @RequestParam Long id)
    {
        Fragrance fragrance = fragranceService.findFraganceById(id);

            if(name != null){
                fragrance.setName(name);
            }
            if(description != null){
                fragrance.setDescription(description);
            }
            if(gender != null){
                fragrance.setGender(gender);
            }
            if(olfactoryFamily != null){
                fragrance.setOlfactoryFamily(olfactoryFamily);
            }
            if(price != null){
                fragrance.setPrice(price);
            }
            if(presentation != null){
                fragrance.setPresentation(presentation);
            }
            if(content != null){
                fragrance.setContent(content);
            }
            if(stock != null){
                fragrance.setStock(stock);
            }
            if(image != null){
                fragrance.setImage(image);
            }
            fragranceService.saveFragance(fragrance);
            return new ResponseEntity<>("Fragance update successfully", HttpStatus.OK);
        }



    @PatchMapping("/fragances/delete")
    public ResponseEntity<Object> deleteFragance(@RequestParam Long id) {
        Fragrance fragrance = fragranceService.findFraganceById(id);
        if (fragrance == null) {
            return new ResponseEntity<>("The fragance doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (!fragrance.getActive()) {
            return new ResponseEntity<>("The fragance is inactive", HttpStatus.FORBIDDEN);
        } else {
            fragrance.setActive(false);
            fragranceService.saveFragance(fragrance);
            return new ResponseEntity<>("Fragance delete successfully", HttpStatus.CREATED);
        }
    }
}
