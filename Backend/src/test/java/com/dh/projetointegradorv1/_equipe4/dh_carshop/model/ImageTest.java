package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.ImageService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ImageTest {

    @Autowired
    ImageService imageService;

    @Autowired
    ProductService productService;

    @Test
    void createImage() {

        //cenário
        ImageDto image = new ImageDto();
        Image entity = new Image();

        // expectativas
        String titulo = "Imagem 1";
        String url = "url.com";
        String toStringOutput = "Images{" +
                "id=0, titulo='Imagem 1', " +
                "url='url.com', " +
                "criado=null, " +
                "atualizado=null" +
                "}";


        // teste de get e set de titulo
        image.setTitulo(titulo);
        Assertions.assertEquals(titulo, image.getTitulo());

        // teste de get e set da url
        image.setUrl(url);
        Assertions.assertEquals(url, image.getUrl());

        // create
        image = imageService.createImage(image);
        //user = bookingService.createBooking(user);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, image.getId());

        image.setId(0);
        Assertions.assertEquals(0, image.getId());

        image.setId(0);
        Assertions.assertEquals(0, image.getId());

        //Assertions.assertEquals(toStringOutput, image.toString());

    }


}