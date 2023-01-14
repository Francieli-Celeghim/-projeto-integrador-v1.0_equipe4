package com.dh.projetointegradorv1._equipe4.dh_carshop.controller;

import com.dh.projetointegradorv1._equipe4.dh_carshop.dto.FeatureDto;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Category;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Feature;
import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import com.dh.projetointegradorv1._equipe4.dh_carshop.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/features")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeatureController {
    @Autowired
    FeatureService featureService;

    @PostMapping
    public ResponseEntity<FeatureDto> createFeature(@RequestBody FeatureDto dto) {
        return ResponseEntity.status(201).body(featureService.createFeature(dto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FeatureDto>> findAllFeatures() {
        return ResponseEntity.ok(featureService.findAllFeatures());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FeatureDto> findFeatureById(@PathVariable Integer id) {
        FeatureDto dto = featureService.findFeatureById(id);
        return ResponseEntity.ok().body(dto);

        /*return featureService.findFeatureById(id).map(feature -> {
            return ResponseEntity.ok().body(feature);
        }).orElseGet(() -> {
            return ResponseEntity.status(404).body(new Feature());
        });*/
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteFeatureById(@PathVariable(value = "id") Integer id) {
        featureService.deleteFeatureById(id);
        return ResponseEntity.status(204).body("");
    }
}
