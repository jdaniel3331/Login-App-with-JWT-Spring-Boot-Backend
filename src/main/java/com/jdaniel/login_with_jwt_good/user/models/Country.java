package com.jdaniel.login_with_jwt_good.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries", schema = "users")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "iso_code", nullable = false, unique = true)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.CHAR) // Forzar a Hibernate a tratar la columna como CHAR(2)
    private String isoCode;
    @Column(name = "phone_code", nullable = false, length = 6)
    private String phoneCode;
}
