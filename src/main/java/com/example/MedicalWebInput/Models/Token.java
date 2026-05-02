package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Token {
        @Id
        @SequenceGenerator(sequenceName = "token_sequence", name = "token_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
        private Long id;
        private String registrationToken;
        @ManyToOne
        private Patient patient;

        public Token() {
        }

}
