package com.backpac.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="backpac_member")
public class Member implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Size(min = 10)
    @Column( nullable = false)
    private String password;

    @Column(length = 20, nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberRole memberRole = MemberRole.USER;

    @Builder.Default
    private boolean locked = false;

    @Builder.Default
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(memberRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
