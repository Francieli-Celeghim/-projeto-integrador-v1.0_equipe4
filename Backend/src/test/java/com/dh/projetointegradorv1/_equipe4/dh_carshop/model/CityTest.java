package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import static org.junit.jupiter.api.Assertions.*;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.CityDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CityTest {

    @Autowired
    CityService cityService;

    @Test
    void createCity() {
        //cenário
        CityDto city = new CityDto();
        City entity = new City();

        // expectativas
        String nome = "Curitiba";
        String estado = "Paraná";

        // teste de get e set de nome
        city.setNome(nome);
        Assertions.assertEquals(nome, city.getNome());

        // teste de get e set de Estado
        city.setEstado(estado);
        Assertions.assertEquals(estado, city.getEstado());

        // create
        city = cityService.createCity(city);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, city.getId());

        // testando se o initializer City() funfou
        Assertions.assertEquals(nome, city.getNome());
        Assertions.assertEquals(estado, city.getEstado());

        city.setId(0);
        Assertions.assertEquals(0, city.getId());


    }


}