package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

@Data
public class PetDTO {
    private String identification;
    private String name;
    private byte age;
    private int fleas;
    private char gender;

    private String codeLocation;
}
