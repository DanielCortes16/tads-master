package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.CiudadDTO;
import co.edu.umanizales.tads.model.Ciudad;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
@Data
public class CiudadService {
    private ListSEService listSEService = new ListSEService();
    private List<Ciudad> ciudades;
    private List<Ciudad> ciudadesFinal;

    public CiudadService() {
        ciudades = listSEService.getCiudadList();
        ciudadesFinal = new ArrayList<>();
    }

    public List<Ciudad> getCiudadesFinal() {
        for (Ciudad ciudad : ciudades) {
            if (!ciudadesFinal.contains(ciudad)) {
                ciudadesFinal.add(ciudad);
            }
        }
        return ciudadesFinal;
    }

    public int getCiudadByID(String id) {
        int cont = 0;
        if (ciudades != null) {
            for (Ciudad ciudad : ciudades) {
                if (ciudad.getCID().equals(id)) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public List<CiudadDTO> ninosXCiudad() {
        List<CiudadDTO> ciudadesDTO = new ArrayList<>();

        for (Ciudad ciudad : ciudadesFinal) {
            CiudadDTO ciudadDTO = new CiudadDTO(new Ciudad(ciudad.getNombre(),ciudad.getCID()),getCiudadByID(ciudad.getCID()));
            ciudadesDTO.add(ciudadDTO);
        }

        return ciudadesDTO;
    }
}
