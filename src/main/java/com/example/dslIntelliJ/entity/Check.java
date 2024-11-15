package com.example.dslIntelliJ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="check_id")
    private Long id;

    private String msg;
}
