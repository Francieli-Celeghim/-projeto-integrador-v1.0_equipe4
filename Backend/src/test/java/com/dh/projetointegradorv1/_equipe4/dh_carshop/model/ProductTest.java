package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.ProductDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.CategoryService;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ProductTest {
    @Autowired
    ProductService productService;

    @Test
    void createProduct() {
        ProductDto product = new ProductDto();
        Product entity = new Product();

        // expectativas
        String nome = "Fiat Argo";
        String descricao = "Com ar";
        String descricaoAlterada = "Sem ar";
        Integer valorDiaria = 300;
        String toStringOutput = "";


        // teste de get e set de titulo
        product.setNome(nome);
        Assertions.assertEquals(nome, product.getNome());

        // teste de get e set de descricao
        product.setDescricao(descricao);
        Assertions.assertEquals(descricao, product.getDescricao());

        product.setValorDiaria(valorDiaria);
        Assertions.assertEquals(valorDiaria, product.getValorDiaria());

        // create
        product = productService.createProduct(product);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, product.getId());

        product.setId(0);
        Assertions.assertEquals(0, product.getId());
    }
}
