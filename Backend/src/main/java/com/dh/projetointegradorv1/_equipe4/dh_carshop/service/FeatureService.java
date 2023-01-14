package com.dh.projetointegradorv1._equipe4.dh_carshop.service;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.FeatureDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.ProductDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Feature;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Image;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.FeatureRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.ImageRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.repository.ProductRepository;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.BDExcecao;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public FeatureDto createFeature(FeatureDto dto) {
        Feature entity = new Feature();
        copyToEntity(dto, entity);
        entity = featureRepository.save(entity);
        return new FeatureDto(entity);
    }

    @Transactional(readOnly = true)
    public List<FeatureDto> findAllFeatures() {
        List<FeatureDto> listDto = new ArrayList<>();
        List<Feature> list = featureRepository.findAll();
        for(Feature feat : list) {
            FeatureDto dto = new FeatureDto(feat, feat.getProdutos());
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public FeatureDto findFeatureById(Integer id) {
        Optional<Feature> obj = featureRepository.findById(id);
        Feature entity = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
        return new FeatureDto(entity, entity.getProdutos());
    }

    public void deleteFeatureById(Integer id) {
        try{
            featureRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado("ID NÃO ENCONTRADO: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BDExcecao("VIOLAÇÃO DE INTEGRIDADE");
        }
    }

    public void copyToEntity(FeatureDto dto, Feature entity) {
        entity.setNome(dto.getNome());
        entity.setIcone(dto.getIcone());
        entity.getProdutos().clear();
        for(ProductDto prodDto : dto.getProdutos()) {
            Optional<Product> obj = productRepository.findById(prodDto.getId());
            Product product = obj.orElseThrow(() -> new RecursoNaoEncontrado("ENTIDADE NÃO ENCONTRADA"));
            entity.getProdutos().add(product);
        }
    }
}
