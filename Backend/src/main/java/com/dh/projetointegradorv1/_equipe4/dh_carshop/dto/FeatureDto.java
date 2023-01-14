package com.dh.projetointegradorv1._equipe4.dh_carshop.dto;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Feature;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Image;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class FeatureDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String icone;
    private List<ProductDto> produtos = new ArrayList<>();

    public FeatureDto(Integer id, String nome, String icone) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
    }

    public FeatureDto(Feature entity) {
        id = entity.getId();
        nome = entity.getNome();
        icone = entity.getIcone();
    }

    public FeatureDto(Feature entity, Set<Product> produtos) {
        this(entity);
        produtos.forEach(prod -> this.produtos.add(new ProductDto(prod)));
    }

}
