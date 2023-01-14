package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FeatureTest {

    @Autowired
    FeatureService featureService;

    @Autowired
    ImageService imageService;

    @Autowired
    ProductService productService;

    @Test
    void createFeature() {
        //cenário
        FeatureDto feature = new FeatureDto();
        Feature entity = new Feature();

        // expectativas
        String nome = "Rafael";
        String icone = "url.com";
        String toStringOutput = "Features{" +
                "id=0, nome='Rafael', icone='url.com'" +
                "criado=null, " +
                "atualizado=null" +
                "}";

        feature.setProdutos(getProdutos());

        // teste de get e set de nome
        feature.setNome(nome);
        Assertions.assertEquals(nome, feature.getNome());

        // teste de get e set de icone
        feature.setIcone(icone);
        Assertions.assertEquals(icone, feature.getIcone());

        // create
        feature = featureService.createFeature(feature);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, feature.getId());

        feature.setId(0);
        Assertions.assertEquals(0, feature.getId());

    }

    private List<ProductDto> getProdutos() {
        return productService.findProductByCategory(1);
    }

    /*private ImageDto getImagem() {
        return imageService.findImageById(1);
    }*/


}