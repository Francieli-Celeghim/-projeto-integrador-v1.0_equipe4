package com.dh.projetointegradorv1._equipe4.dh_carshop.repository;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products p WHERE p.id_cidade = ?1", nativeQuery = true)
    List<Product> findByCidade(Integer cityId);

    /*@Query(value = "SELECT * FROM products p " +
            "INNER JOIN bookings b ON ((p.id <> b.id_produto) OR (p.id = b.id_produto " +
            "WHERE (CONVERT(?1, DATE) < b.inicio_reserva AND CONVERT(?2, DATE) < b.inicio_reserva) OR " +
            "(CONVERT(?1, DATE) > b.fim_reserva AND CONVERT(?2, DATE) > b.fim_reserva)))",
            nativeQuery = true)
    List<Product> findByDatas(String dataInicio, String dataFim);*/


    /*@Query(value = "SELECT * FROM products p" +
            "INNER JOIN categories_products cp ON p.id_produto = cp.id_produto" +
            "WHERE cp.id_categoria = ?1", nativeQuery = true)
    List<Product> findByCategoria(Integer id);*/
}
