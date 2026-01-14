package com.edusantos.igreja.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_church")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String responsible;
    private String website;
    private String type;
    private String address;
    private String city;
    private String state;
    private LocalDate foundationdate;
    private String cnpj;
    private String phone;

    @OneToMany(mappedBy = "church")// significa que uma igreja tem vários membros 1 --> N (para muitos)
    @JsonIgnore // não deixa dar loop infinito se caso receber um json
    private List<Member> members = new ArrayList<>();


    public void addMember(Member member){
        members.add(member);
        member.setChurch(this);
    }

    public void removeMember(Member member){ // caso remover um membro da igreja ele vai desvincular e remover o membro da igraja
        members.remove(member);
        member.setChurch(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Church church = (Church) o;
        return Objects.equals(id, church.id) && Objects.equals(name, church.name) && Objects.equals(responsible, church.responsible) && Objects.equals(website, church.website) && Objects.equals(type, church.type) && Objects.equals(address, church.address) && Objects.equals(city, church.city) && Objects.equals(state, church.state) && Objects.equals(foundationdate, church.foundationdate) && Objects.equals(cnpj, church.cnpj) && Objects.equals(phone, church.phone) && Objects.equals(members, church.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, responsible, website, type, address, city, state, foundationdate, cnpj, phone, members);
    }

    @Override
    public String toString() {
        return "Church{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", responsible='" + responsible + '\'' +
                ", website='" + website + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", foundationdate=" + foundationdate +
                ", cnpj='" + cnpj + '\'' +
                ", phone='" + phone + '\'' +
                ", memberCount=" + members.size() +
                '}';
    }
}
