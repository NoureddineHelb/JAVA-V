package be.helb.controller;

import be.helb.DAO.ImageDao;
import be.helb.model.Image;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private final ImageDao imageDao;


    public ImageController(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @ApiOperation(value = "Opération pour enregister une image en base64")
    @PostMapping
    public void createImage(@RequestBody String base64Image) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        Image image = new Image();
        image.setData(imageBytes);
        imageDao.save(image);
    }

}
