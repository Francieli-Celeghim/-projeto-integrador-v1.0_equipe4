package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.BookingDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Booking;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.City;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.User;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.BookingRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.CityRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.ProductRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.UserRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BookingDto createBooking(BookingDto dto) {
        Booking entity = new Booking();
        System.out.println("====dto.getProduto()");
        System.out.println(dto.getProduto());
        copyToEntity(dto, entity);
        entity = bookingRepository.save(entity);
        return new BookingDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BookingDto> listAllBookings() {
        List<BookingDto> listDto = new ArrayList<>();
        List<Booking> list = bookingRepository.findAll();
        for(Booking book : list) {
            BookingDto dto = new BookingDto(book, book.getProduto(), book.getCidade(), book.getUsuario());
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public BookingDto findBookingById(Integer id) {
        Optional<Booking> obj = bookingRepository.findById(id);
        Booking entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new BookingDto(entity, entity.getProduto(), entity.getCidade(), entity.getUsuario());
    }

    @Transactional(readOnly = true)
    public List<BookingDto> findBookingByUser(Integer userId) {
        List<BookingDto> listDto = new ArrayList<>();
        List<Booking> list = bookingRepository.findByUser(userId);
        for(Booking book : list) {
            BookingDto dto = new BookingDto(book, book.getProduto(), book.getCidade(), book.getUsuario());
            listDto.add(dto);
        }
        return listDto;
    }

    public void copyToEntity(BookingDto dto, Booking entity) {
        entity.setInicioReserva(LocalDate.parse(dto.getInicioReserva(), DateTimeFormatter.ISO_DATE));
        entity.setFimReserva(LocalDate.parse(dto.getFimReserva(), DateTimeFormatter.ISO_DATE));
        System.out.println("====dto.getProduto()");
        System.out.println(dto.getProduto());
        if (dto.getProduto() != null) {
            Optional<Product> obj = productRepository.findById(dto.getProduto().getId());
            Product product = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.setProduto(product);
            entity.setValorReserva(product.getValorDiaria() * Math.toIntExact(ChronoUnit.DAYS.between(entity.getInicioReserva(), entity.getFimReserva())+1));
        }
        if (dto.getCidade() != null) {
            Optional<City> obj = cityRepository.findById(dto.getCidade().getId());
            City city = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.setCidade(city);
        }
        if (dto.getUsuario() != null) {
            Optional<User> obj = userRepository.findById(dto.getUsuario().getId());
            User user = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.setUsuario(user);
        }
    }

}
