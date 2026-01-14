package com.edusantos.igreja.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_occasion")
public class Occasion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;//descrição
    private LocalDate start;//inicio
    private LocalDate exist;//fim ou saida finalizada

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Occasion occasion = (Occasion) o;
        return Objects.equals(id, occasion.id) && Objects.equals(name, occasion.name) && Objects.equals(description, occasion.description) && Objects.equals(start, occasion.start) && Objects.equals(exist, occasion.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, start, exist);
    }

    @Override
    public String toString() {
        return "Occasion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", exist=" + exist +
                '}';
    }
}
