package com.edusantos.igreja.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data

@Entity
@Table(name = "tb_study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate theDate;
    private String subject; //assunto
    private String description;//descrição
    private String notes; //observação


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equals(id, study.id) && Objects.equals(theDate, study.theDate) && Objects.equals(subject, study.subject) && Objects.equals(description, study.description) && Objects.equals(notes, study.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theDate, subject, description, notes);
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", theDate=" + theDate +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
