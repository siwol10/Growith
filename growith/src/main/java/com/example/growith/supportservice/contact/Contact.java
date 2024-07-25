package com.example.growith.supportservice.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@ToString(exclude = {"type"})
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ContactType type;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String subject;


    private String content;
    private LocalDateTime date;
}
