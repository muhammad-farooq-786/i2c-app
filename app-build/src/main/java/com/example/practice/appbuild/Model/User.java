package com.example.practice.appbuild.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String city;
}
