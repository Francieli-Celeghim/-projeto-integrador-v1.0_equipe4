package com.dh.projetointegradorv1._equipe4.dh_carshop.repository;

import com.dh.projetointegradorv1._equipe4.dh_carshop.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM bookings b WHERE b.id_usuario = ?1", nativeQuery = true)
    List<Booking> findByUser(Integer userId);
}
