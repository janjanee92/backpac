package com.backpac.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="backpac_member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Size(min = 10)
    @Column( nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private int phoneNumber;

    @Column(length = 100, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
