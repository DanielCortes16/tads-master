package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.FleasDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECircularService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(path = "/listdec")
public class ListDECircularController {
    @Autowired
    private ListDECircularService listDECircularService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(200, listDECircularService.getPets().getPets(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO PetDTO) {
        Location location = locationService.getLocationByCode(PetDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDECircularService.getPets().addPet(new Pet(PetDTO.getIdentification(), PetDTO.getName(), PetDTO.getAge(), PetDTO.getGender(), false, PetDTO.getFleas(), location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado la mascota", null), HttpStatus.OK);

    }

    @PostMapping(path = "/addpettostart")
    public ResponseEntity<ResponseDTO> addPetToStart(@RequestBody PetDTO PetDTO) {
        Location location = locationService.getLocationByCode(PetDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDECircularService.getPets().addPetToStart(new Pet(PetDTO.getIdentification(), PetDTO.getName(), PetDTO.getAge(), PetDTO.getGender(), false, PetDTO.getFleas(), location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el petacón", null), HttpStatus.OK);

    }

    @GetMapping(path = "/dirtydogs")
    public ResponseEntity<ResponseDTO> dirtyDogs() {
        listDECircularService.dirtyDogs();
        return new ResponseEntity<>(new ResponseDTO(200, "los perros se ensuciaron", null), HttpStatus.OK);
    }

    @GetMapping(path = "/cleanpet")
    public ResponseEntity<ResponseDTO> cleanPet() {
        try {
            listDECircularService.cleanPet();
            return new ResponseEntity<>(new ResponseDTO(200, "los perros se lavaron", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(409, e.getMessage(), null), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/petwithmorefleas")
    public ResponseEntity<ResponseDTO> getpetWithMoreFleas() {
        Pet pet = listDECircularService.getPetWithMoreFleas();
        FleasDTO fleasDTO = new FleasDTO();
        fleasDTO.setName(pet.getName());
        fleasDTO.setFleas(pet.getFleas());
        return new ResponseEntity<>(new ResponseDTO(200, fleasDTO, null), HttpStatus.OK);
    }
}
