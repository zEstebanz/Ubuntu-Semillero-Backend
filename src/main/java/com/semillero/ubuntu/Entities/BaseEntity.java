package com.semillero.ubuntu.Entities;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    private Long id;
}
