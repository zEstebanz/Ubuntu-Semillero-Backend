package com.semillero.ubuntu.ChatBot.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private Boolean isDeleted;
    private
    @OneToMany(mappedBy = "respuesta")
    private final List<Question> questionList = new ArrayList<>(); inicial,intermedia(3)

}
