package com.dh.projetointegradorv1._equipe4.dh_carshop.dto;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;
    private Integer valorDiaria;
    private List<FeatureDto> caracteristicas = new ArrayList<>();
    private List<ImageDto> imagens = new ArrayList<>();
    private List<CategoryDto> categorias = new ArrayList<>();
    private CityDto cidade;
    private List<BookingDto> reservas = new ArrayList<>();

    public ProductDto(Integer id, String nome, String descricao, Integer valorDiaria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
    }

    public ProductDto(Product entity) {
        id = entity.getId();
        nome = entity.getNome();
        descricao = entity.getDescricao();
        valorDiaria = entity.getValorDiaria();
        if (entity.getCidade() != null) {
            cidade = new CityDto(entity.getCidade());
        }
        for (Image image : entity.getImagens()) {
            imagens.add(new ImageDto(image));
            break;
        }
    }

    public ProductDto(Product entity, Set<Feature> caracteristicas, Set<Image> imagens,
                      Set<Category> categorias, City cidade, Set<Booking> reservas) {
        this(entity);
        caracteristicas.forEach(car -> this.caracteristicas.add(new FeatureDto(car)));
        categorias.forEach(cat -> this.categorias.add(new CategoryDto(cat)));
        if (cidade != null) {
            this.cidade = new CityDto(cidade);
        }
        reservas.forEach(book -> this.reservas.add(new BookingDto(book)));
    }

}
