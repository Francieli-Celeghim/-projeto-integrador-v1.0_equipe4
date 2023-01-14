package com.dh.projetointegradorv1._equipe4.dh_carshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table (name = "bookings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDate inicioReserva;
    @Column(nullable = false)
    private LocalDate fimReserva;
    @Column
    private Integer valorReserva;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Product produto;
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private City cidade;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    // Timestamps Automáticos
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime criado;
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private OffsetDateTime atualizado;

    @PrePersist
    public void antesDeSalvar() {
        criado = OffsetDateTime.now();
    }

    @PreUpdate
    public void antesDeAtualizar() {
        atualizado = OffsetDateTime.now();
    }

}
