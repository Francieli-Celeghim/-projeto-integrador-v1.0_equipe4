package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.BookingDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.CityDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.ProductDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Booking;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Category;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.City;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.BookingRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.CityRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.ProductRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public CityDto createCity(CityDto dto) {
        City entity = new City();
        copyToEntity(dto, entity);
        entity = cityRepository.save(entity);
        return new CityDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CityDto> listAllCities() {
        List<CityDto> listDto = new ArrayList<>();
        List<City> list = cityRepository.findAll();
        for(City city : list) {
            CityDto dto = new CityDto(city);
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public CityDto findCityById(Integer id) {
        Optional<City> obj = cityRepository.findById(id);
        City entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new CityDto(entity, entity.getProdutos(), entity.getReservas());
    }


    public void copyToEntity(CityDto dto, City entity) {
        entity.setNome(dto.getNome());
        entity.setEstado(dto.getEstado());
        entity.getProdutos().clear();
        for(ProductDto prodDto : dto.getProdutos()) {
            Optional<Product> obj = productRepository.findById(prodDto.getId());
            Product product = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getProdutos().add(product);
        }
        for(BookingDto bookDto : dto.getReservas()) {
            Optional<Booking> obj = bookingRepository.findById(bookDto.getId());
            Booking booking = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getReservas().add(booking);
        }
    }


}
