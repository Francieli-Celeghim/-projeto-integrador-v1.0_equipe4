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
public class CityDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String estado;
    private List<ProductDto> produtos = new ArrayList<>();
    private List<BookingDto> reservas = new ArrayList<>();

    public CityDto(Integer id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public CityDto(City entity) {
        id = entity.getId();
        nome = entity.getNome();
        estado = entity.getEstado();
    }

    public CityDto(City entity, Set<Product> produtos, Set<Booking> reservas) {
        this(entity);
        produtos.forEach(prod -> this.produtos.add(new ProductDto(prod)));
        reservas.forEach(book -> this.reservas.add(new BookingDto(book)));
    }

}
