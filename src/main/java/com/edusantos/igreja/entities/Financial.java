package com.edusantos.igreja.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_financial")
public class Financial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;//tipo - entrada /saida
    private BigDecimal amount;//valor
    private LocalDate theDate;//data
    private String paymentMethod;//forma de pagamento - pix, cartão
    private String description;//descrição


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Financial financial = (Financial) o;
        return Objects.equals(id, financial.id) && Objects.equals(type, financial.type) && Objects.equals(amount, financial.amount) && Objects.equals(theDate, financial.theDate) && Objects.equals(paymentMethod, financial.paymentMethod) && Objects.equals(description, financial.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount, theDate, paymentMethod, description);
    }

    @Override
    public String toString() {
        return "Financial{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", theDate=" + theDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
