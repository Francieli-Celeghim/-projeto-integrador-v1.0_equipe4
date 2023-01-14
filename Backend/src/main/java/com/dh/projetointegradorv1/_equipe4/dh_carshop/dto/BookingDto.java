package com.dh.projetointegradorv1._equipe4.dh_carshop.dto;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class BookingDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String inicioReserva;
    private String fimReserva;
    private Integer valorReserva;
    private ProductDto produto;
    private CityDto cidade;
    private UserDto usuario;

    public BookingDto(Integer id, LocalDate inicioReserva, LocalDate fimReserva) {
        this.id = id;
        this.inicioReserva = inicioReserva.format(DateTimeFormatter.ISO_DATE);
        this.fimReserva = fimReserva.format(DateTimeFormatter.ISO_DATE);
    }

    public BookingDto(Booking entity) {
        id = entity.getId();
        inicioReserva = entity.getInicioReserva().format(DateTimeFormatter.ISO_DATE);
        fimReserva = entity.getFimReserva().format(DateTimeFormatter.ISO_DATE);
        if (entity.getProduto() != null) {
            produto = new ProductDto(entity.getProduto());
            valorReserva = entity.getProduto().getValorDiaria() * Math.toIntExact(ChronoUnit.DAYS
                    .between(entity.getInicioReserva(), entity.getFimReserva())+1);
        }
        if (entity.getCidade() != null) {
            cidade = new CityDto(entity.getCidade());
        }
        if (entity.getUsuario() != null) {
            usuario = new UserDto(entity.getUsuario());
        }
    }

    public BookingDto(Booking entity, Product produto, City cidade, User usuario) {
        this(entity);
        this.produto = new ProductDto(produto);
        this.valorReserva = produto.getValorDiaria() * Math.toIntExact(ChronoUnit.DAYS
                .between(entity.getInicioReserva(), entity.getFimReserva())+1);
        this.cidade = new CityDto(cidade);
        this.usuario = new UserDto(usuario);
    }

}
