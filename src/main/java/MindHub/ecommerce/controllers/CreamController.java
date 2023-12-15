package MindHub.ecommerce.controllers;

import MindHub.ecommerce.dtos.CreamDTO;

import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.services.CreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Position;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/velvet")
public class CreamController {
    @Autowired
    private CreamService creamService;

    @GetMapping("/creams")
    public List<CreamDTO> getAllCreams(){
        List<Cream> creamsActives = creamService.findAllCreams().stream().filter(creamDTO -> creamDTO.getActive()).collect(Collectors.toList());
        List<CreamDTO> creamsDTO = creamsActives.stream().map(creamActive -> new CreamDTO(creamActive)).collect(Collectors.toList());
        return  creamsDTO;
    }


    @GetMapping("/creams/{id}")
    public CreamDTO getCreamId(@PathVariable Long id){
         Cream cream =  creamService.findCreamById(id);

         if (cream != null){

             return  new CreamDTO(cream);
         }
         else {
             throw new ResourceNotFoundException("This Id Cream not exist");
         }
    }

    @PostMapping("/creams/create")
    public ResponseEntity<Object> createNewCream(@RequestParam String name, @RequestParam String description,
       @RequestParam Double price, @RequestParam Integer content, @RequestParam Integer stock,
       @RequestParam Type type, @RequestParam String image){

        if (name.isBlank()){
            return new ResponseEntity<>("complete the name", HttpStatus.FORBIDDEN);
        }
        if (description.isBlank()){
            return new ResponseEntity<>("complete the description", HttpStatus.FORBIDDEN);
        }
        if (image.isBlank()){
            return new ResponseEntity<>("complete the image URL", HttpStatus.FORBIDDEN);
        }
        if (price.isNaN()){
            return new ResponseEntity<>("complete the Price", HttpStatus.FORBIDDEN);
        }
        if (price <= 0){
            return new ResponseEntity<>("The price cant be 0 or less", HttpStatus.FORBIDDEN);
        }
        if (stock == null){
            return new ResponseEntity<>("complete the Stock", HttpStatus.FORBIDDEN);
        }
        if (stock < 0){
            return new ResponseEntity<>("The Stock cant be less to 0", HttpStatus.FORBIDDEN);
        }
        if (content == null){
            return new ResponseEntity<>("complete the content", HttpStatus.FORBIDDEN);
        }
        if (content <= 0){
            return new ResponseEntity<>("The Stock cant be 0 or less ", HttpStatus.FORBIDDEN);
        }

        Cream cream = new Cream(name,description,price,content,stock,type,image, true);

        creamService.saveCream(cream);
        return new ResponseEntity<>("cream created!", HttpStatus.CREATED);
    }

    @PatchMapping("/creams/update")
    public ResponseEntity<String> updateCream(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestParam(required = false) Double price,
                                                 @RequestParam(required = false) Type type,
                                                 @RequestParam(required = false) Integer content,
                                                 @RequestParam(required = false) Integer stock,
                                                 @RequestParam(required = false) String image, @RequestParam Long id)
    {
        Cream cream = creamService.findCreamById(id);

            if(name != null){
                cream.setName(name);
            }
            if(description != null){
                cream.setDescription(description);
            }
            if(price != null){
                cream.setPrice(price);
            }
            if(type != null){
                cream.setType(type);
            }
            if(content != null){
                cream.setContent(content);
            }
            if(stock != null){
                cream.setStock(stock);
            }
            if(image != null){
                cream.setImage(image);
            }
            creamService.saveCream(cream);
            return new ResponseEntity<>("cream update successfully", HttpStatus.OK);
        }



    @PatchMapping("/creams/delete")
    public ResponseEntity<Object> deleteCream(@RequestParam Long id){
        Cream cream = creamService.findCreamById(id);
        if (cream == null) {
            return new ResponseEntity<>("The cream doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (!cream.getActive()) {
            return new ResponseEntity<>("The cream is inactive", HttpStatus.FORBIDDEN);
        } else {
            cream.setActive(false);
            creamService.saveCream(cream);
            return new ResponseEntity<>("Cream delete successfully", HttpStatus.CREATED);
        }



}}
