package com.edusantos.igreja.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_pray")
public class Pray {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reason;// motivo ou razão
    private String description; //descrição
    private String priority; //prioridade - urgente, alta, media, baixa
    private String status;// situação: feita, não feita


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pray pray = (Pray) o;
        return Objects.equals(Id, pray.Id) && Objects.equals(reason, pray.reason) && Objects.equals(description, pray.description) && Objects.equals(priority, pray.priority) && Objects.equals(status, pray.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, reason, description, priority, status);
    }

    @Override
    public String toString() {
        return "Pray{" +
                "Id=" + Id +
                ", reason='" + reason + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
