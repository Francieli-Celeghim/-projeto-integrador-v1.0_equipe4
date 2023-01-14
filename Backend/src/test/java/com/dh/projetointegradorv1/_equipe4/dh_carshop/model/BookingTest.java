package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookingTest {
    @Autowired
    BookingService bookingService;

    @Autowired
    ProductService productService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Test
    void createBooking() {
        //cenário
        BookingDto booking = new BookingDto();
        Booking entity = new Booking();

        // expectativas
        String inicioReserva = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fimReserva = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //Integer valorReserva = 200;

        /*booking.setProduto(getProduto());
        booking.setCidade(getCidade());
        booking.setUsuario(getUser());*/

        // teste de get e set de inicioReserva
        booking.setInicioReserva(inicioReserva);
        Assertions.assertEquals(inicioReserva, booking.getInicioReserva());

        // teste de get e set de fimReserva
        booking.setFimReserva(fimReserva);
        Assertions.assertEquals(fimReserva, booking.getFimReserva());

        // teste de get e set de valorReserva
        /*booking.setValorReserva(valorReserva);
        Assertions.assertEquals(valorReserva, booking.getValorReserva());*/

        // create
        booking = bookingService.createBooking(booking);

        // aqui esta sendo testado a existencia do id e inclusive que foi salvo no banco porque tem um id
        Assertions.assertInstanceOf(java.lang.Integer.class, booking.getId());

        booking.setId(0);
        Assertions.assertEquals(0, booking.getId());

    }

    /*private ProductDto getProduto() {
        return productService.findProductById(1);
    }

    private UserDto getUser() {
        return userService.findUserById(1);
    }

    private CityDto getCidade() {
        return cityService.findCityById(1);
    }*/

}