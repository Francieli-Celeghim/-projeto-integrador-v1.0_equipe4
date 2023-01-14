package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.CategoryDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.ImageDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.CategoryService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.ImageService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.ProductService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @Autowired
    ProductService productService;

    @Test
    void createCategory() {
        //cenário
        CategoryDto category = new CategoryDto();
        Category entity = new Category();

        // expectativas
        String titulo = "Econômico";
        String descricao = "Carros com baixo consumo";
        String descricaoAlterada = "Carros com alto consumo";
        String urlImagem = "url.com";
        String toStringOutput = "Categories{" +
                    "id=0, titulo='Econômico', " +
                    "descricao='Carros com baixo consumo', " +
                    "urlImagem='url.com', " +
                    "criado=null, " +
                    "atualizado=null" +
                "}";

        // teste de get e set de titulo
        category.setTitulo(titulo);
        Assertions.assertEquals(titulo, category.getTitulo());

        // teste de get e set de descricao
        category.setDescricao(descricao);
        Assertions.assertEquals(descricao, category.getDescricao());

        // teste de get e set de urlImagem
        category.setUrlImagem(urlImagem);
        Assertions.assertEquals(urlImagem, category.getUrlImagem());

        // create
        category = categoryService.createCategory(category);

        // verificar agora instanciar ja com os valores
        category = new CategoryDto(titulo, descricao, urlImagem);

        // testando se o initializer Category() funfou
        Assertions.assertEquals(titulo, category.getTitulo());
        Assertions.assertEquals(descricao, category.getDescricao());
        Assertions.assertEquals(urlImagem, category.getUrlImagem());

        category.setId(0);
        Assertions.assertEquals(0, category.getId());
    }
}
