package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.*;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.BDExcecao;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        Product entity = new Product();
        copyToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> listAllProducts(Integer pageSize) {
        List<ProductDto> listDto = new ArrayList<>();
        List<Product> list = productRepository.findAll();

        Integer currentSize = 0;
        for(Product prod : list) {
            ProductDto dto = new ProductDto(prod, prod.getCaracteristicas(),
                    prod.getImagens(), prod.getCategorias(), prod.getCidade(), prod.getReservas());
            listDto.add(dto);
            currentSize++;

            if (currentSize >= pageSize) {
                break;
            }
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public ProductDto findProductById(Integer id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new ProductDto(entity, entity.getCaracteristicas(), entity.getImagens(),
                entity.getCategorias(), entity.getCidade(), entity.getReservas());
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProductByCity(Integer cityId) {
        List<ProductDto> listDto = new ArrayList<>();
        List<Product> list = productRepository.findByCidade(cityId);
        for(Product prod : list) {
            ProductDto dto = new ProductDto(prod, prod.getCaracteristicas(),
                    prod.getImagens(), prod.getCategorias(), prod.getCidade(), prod.getReservas());
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProductByDates(String dataInicio, String dataTermino) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate termino = LocalDate.parse(dataTermino);
        List<ProductDto> listProductDto = new ArrayList<>();
        List<Product> listProduct = productRepository.findAll();
        for(Product prod : listProduct) {
            ProductDto prodDto = new ProductDto(prod, prod.getCaracteristicas(),
                    prod.getImagens(), prod.getCategorias(), prod.getCidade(), prod.getReservas());
            Integer disp = 1;
            List<BookingDto> listBooking = prodDto.getReservas();
            for(BookingDto bookDto : listBooking) {
                LocalDate inicioReserva = LocalDate.parse(bookDto.getInicioReserva(), DateTimeFormatter.ISO_DATE);
                LocalDate fimReserva = LocalDate.parse(bookDto.getFimReserva(), DateTimeFormatter.ISO_DATE);
                if(
                    inicio.isEqual(inicioReserva) ||
                    termino.isEqual(fimReserva) ||
                    (inicio.isAfter(inicioReserva) && inicio.isBefore(fimReserva)) ||
                    (termino.isAfter(inicioReserva) && termino.isBefore(fimReserva))
                ) {
                    disp = 0;
                }
            }
            if(disp == 1) {
                listProductDto.add(prodDto);
            }
        }
        return listProductDto;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProductByCityAndDates(Integer cityId, String dataInicio, String dataTermino) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate termino = LocalDate.parse(dataTermino);
        List<ProductDto> listProductDto = new ArrayList<>();
        List<Product> listProduct = productRepository.findByCidade(cityId);
        for(Product prod : listProduct) {
            ProductDto prodDto = new ProductDto(prod, prod.getCaracteristicas(),
                    prod.getImagens(), prod.getCategorias(), prod.getCidade(), prod.getReservas());
            Integer disp = 1;
            List<BookingDto> listBooking = prodDto.getReservas();
            for(BookingDto bookDto : listBooking) {
                LocalDate inicioReserva = LocalDate.parse(bookDto.getInicioReserva(), DateTimeFormatter.ISO_DATE);
                LocalDate fimReserva = LocalDate.parse(bookDto.getFimReserva(), DateTimeFormatter.ISO_DATE);
                if(
                    inicio.isEqual(inicioReserva) ||
                    termino.isEqual(fimReserva) ||
                    (inicio.isAfter(inicioReserva) && inicio.isBefore(fimReserva)) ||
                    (termino.isAfter(inicioReserva) && termino.isBefore(fimReserva))
                ) {
                    disp = 0;
                }
            }
            if(disp == 1) {
                listProductDto.add(prodDto);
            }
        }
        return listProductDto;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProductByCategory(Integer id) {
        List<ProductDto> list = new ArrayList<>();
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("CATEGORIA NÃO ENCONTRADA"));
        CategoryDto dto = new CategoryDto(entity, entity.getProdutos());
        for(ProductDto prod : dto.getProdutos()) {
            Optional<Product> objProd = productRepository.findById(prod.getId());
            Product entityProd = objProd.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            list.add(new ProductDto(entityProd, entityProd.getCaracteristicas(), entityProd.getImagens(),
                    entityProd.getCategorias(), entityProd.getCidade(), entityProd.getReservas()));
        }
        return list;
    }

    @Transactional
    public ProductDto editProductById(Integer id, ProductDto dto) {
        try {
            Optional<Product> obj = productRepository.findById(id);
            Product entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            copyToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado("ID NÃO ENCONTRADO: " + id);
        }
    }

    public void deleteProductById(Integer id) {
        try{
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado("ID NÃO ENCONTRADO: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BDExcecao("VIOLAÇÃO DE INTEGRIDADE");
        }
    }

    public void copyToEntity(ProductDto dto, Product entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setValorDiaria(dto.getValorDiaria());
        entity.getCaracteristicas().clear();
        for(FeatureDto featDto : dto.getCaracteristicas()) {
            Optional<Feature> obj = featureRepository.findById(featDto.getId());
            Feature feature = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getCaracteristicas().add(feature);
        }
        entity.getImagens().clear();
        for(ImageDto imgDto : dto.getImagens()) {
            Optional<Image> obj = imageRepository.findById(imgDto.getId());
            Image image = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getImagens().add(image);
        }
        entity.getCategorias().clear();
        for(CategoryDto catDto : dto.getCategorias()) {
            Optional<Category> obj = categoryRepository.findById(catDto.getId());
            Category category = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getCategorias().add(category);
        }
        for(BookingDto bookDto : dto.getReservas()) {
            Optional<Booking> obj = bookingRepository.findById(bookDto.getId());
            Booking booking = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getReservas().add(booking);
        }

        if (dto.getCidade() != null) {
            Optional<City> obj = cityRepository.findById(dto.getCidade().getId());
            City city = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.setCidade(city);
        }
    }

}
