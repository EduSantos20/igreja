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
@Table(name = "tb_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;//situação
    private String role;//cargo
    private LocalDate baptismDate;
    private String admission;//forma de admissão
    private String name;
    private String gender;//genero
    private LocalDate birthdate;
    private String address;
    private String state;
    private String occupation;// profissão

    @ManyToOne(fetch = FetchType.EAGER)// um membro tem muitas igrejas
    @JoinColumn(name = "church_id")
    private Church church;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(status, member.status) && Objects.equals(role, member.role) && Objects.equals(baptismDate, member.baptismDate) && Objects.equals(admission, member.admission) && Objects.equals(name, member.name) && Objects.equals(gender, member.gender) && Objects.equals(birthdate, member.birthdate) && Objects.equals(address, member.address) && Objects.equals(state, member.state) && Objects.equals(occupation, member.occupation) && Objects.equals(church, member.church);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, role, baptismDate, admission, name, gender, birthdate, address, state, occupation, church);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", baptismDate=" + baptismDate +
                ", admission='" + admission + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", occupation='" + occupation + '\'' +
                ", churchId=" + (church != null ? church.getId() : null)+ // se a igreja existe vai chamar o id da igreja se não retorna null
                '}';
    }
}
