package com.salary.users.entity;

import com.salary.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@ToString
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String sub;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public Users(String name, String email, String sub, Role role, Provider provider) {
        this.name = name;
        this.email = email;
        this.sub = sub;
        this.role = role;
        this.provider = provider;
    }

    public String getRole(){
        return role.getKey();
    }
}
