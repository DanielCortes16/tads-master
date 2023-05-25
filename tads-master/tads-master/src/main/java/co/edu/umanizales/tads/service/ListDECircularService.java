package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.ListDECircular;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDECircularService {
    private ListDECircular pets;

    ListDECircularService() {
        pets = new ListDECircular();
    }

    public void dirtyDogs() {
        pets.dirtyDogs();
    }

    public void cleanPet() throws ListDEException {
        pets.cleanPet();
    }

    public Pet getPetWithMoreFleas() {
        return pets.getPetWithMoreFleas();
    }
}
