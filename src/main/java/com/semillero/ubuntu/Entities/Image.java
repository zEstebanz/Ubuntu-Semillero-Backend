package com.semillero.ubuntu.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String secure_url;
    private String format;
    private String created_at;
    private String public_id;
    private Integer width;
    private Integer height;

    private Image(){}
    private Image(String secure_url, String format, String created_at, String public_id, Integer width, Integer height){
        this.secure_url = secure_url;
        this.format = format;
        this.created_at = created_at;
        this.public_id = public_id;
        this.width = width;
        this.height = height;
    }

    public static Image createImage(Map upload){
        return new Image(
                (String) upload.get("secure_url"),
                (String) upload.get("format"),
                (String) upload.get("created_at"),
                (String) upload.get("public_id"),
                (Integer) upload.get("width"),
                (Integer) upload.get("height")
        );
    }

}
