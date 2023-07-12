package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//or we can use @Data to import both @getter and @setter
public class User implements UserDetails {

        @Id
        @SequenceGenerator(name = "users_seq_generator",sequenceName ="users_id_seq",allocationSize  =1)
        @GeneratedValue(generator = "users_seq_generator",strategy=GenerationType.SEQUENCE)
        private Integer userId;
        @Column(name="fname",length=50,nullable=false)
        private String fname;
        @Column(name="lname",length=50,nullable=false)
        private String lname;
        @Column(name="email",length=50,nullable=false)
        private String email;
        @Column(name="password",length=500,nullable=false)
        private String password;
        @Column(name="role",length=50,nullable=false)
        private String role;

        @Column(name="status",length=50)
        private String status;

        @Column(name="citizenship_number",length=50)
        private String citizenshipNumber;

        @Column(name="pan_number",length=50)
        private String panNumber;

        @Column(name="phone_number",length=50)
        private String phoneNumber;

        @Column(name="address",length=500)
        private String address;

        @Column(name="sq",length=500)
        private String sq;

        @Column(name="image",length=500)
        private String image;

    @Transient
    private String imageBase64;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }




    @Override
    public String getUsername() {
        return (this.fname+" "+this.lname);
    }

    public String getUserEmail() {
        return this.email;
    }


    public String getUserPassword() {
        return this.password;
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
