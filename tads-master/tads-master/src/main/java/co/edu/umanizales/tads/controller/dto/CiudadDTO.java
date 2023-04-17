package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CiudadDTO {
    private Ciudad ciudad;
    private int quantity;

}
