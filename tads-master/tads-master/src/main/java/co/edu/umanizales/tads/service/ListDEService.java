package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
public class ListDEService {
    private ListDE pets;
    public ListDEService() {
        pets = new ListDE();
    }
    public float promAges() {
        return pets.promAges();
    }
    public void addxPosDE(Pet pet, int pos){ pets.addxPosDE(pet, pos);}
    public void invertDE(){ pets.invertDE();}
    public void orderPetMToStartDE(){ pets.orderPetMToStartDE();}
    public void getPetMPetFList(){ pets.getPetMPetFList();}
    public void removePetByAge(Byte age){ pets.removePetByAge(age);}
    public void getCountPetsByCityCode(String code){ pets.getCountPetsByCityCode(code);}
    public void getCountPetsByDepCode(String code){ pets.getCountPetsByDepCode(code);}
    public void reportKidsByAgeDE(int ageMin, int ageMax){ pets.reportPetsByAgeDE(ageMin, ageMax);}
    public void addByNameAtEndDE(String initial){ pets.addByNameAtEndDE(initial);}
    public void changeExtremesDE(){ pets.changeExtremesDE();}
    public void removePetByID(String id){pets.removePetByID(id);}
    public void gainXPos(String id, int pos) throws ListDEException {pets.gainXPos(id, pos);}
}