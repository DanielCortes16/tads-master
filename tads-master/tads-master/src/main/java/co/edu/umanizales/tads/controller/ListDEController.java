package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
        @Autowired
        private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/promediopets")
    public ResponseEntity<ResponseDTO> promEdades() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.promAges(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPetDE(@RequestBody PetDTO PetDTO) {
        Location location = locationService.getLocationByCode(PetDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDEService.getPets().addDE(new Pet(PetDTO.getIdentification(), PetDTO.getName(), PetDTO.getAge(), PetDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el petacón", null), HttpStatus.OK);

    }

    @GetMapping(path = "/orderPetmsostart")
    public ResponseEntity<ResponseDTO> orderPetMToStartDE() {
        listDEService.orderPetMToStartDE();
        return new ResponseEntity<>(new ResponseDTO(200, "Se ordenaron los perros", null), HttpStatus.OK);
    }

    @GetMapping(path = "/addxposdE")
    public ResponseEntity<ResponseDTO> addxPosDE() {
        return new ResponseEntity<>(new ResponseDTO(200, addxPosDE(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/changeextremesde")
    public ResponseEntity<ResponseDTO> changeExtremesDE() {
        listDEService.changeExtremesDE();
        return new ResponseEntity<>(new ResponseDTO(200, "Se cambiaron los extremos", null), HttpStatus.OK);
    }

    @GetMapping(path = "/invertde")
    public ResponseEntity<ResponseDTO> invertDE() {
        listDEService.invertDE();
        return new ResponseEntity<>(new ResponseDTO(200, "Se invirtio la lista", null), HttpStatus.OK);
    }


    @GetMapping(path = "/addbynameatendde")
    public ResponseEntity<ResponseDTO> addByNameAtEndDE() {
        return new ResponseEntity<>(new ResponseDTO(200, addByNameAtEndDE(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/removepetbyage")
    public ResponseEntity<ResponseDTO> removePetByAge() {
        return new ResponseEntity<>(new ResponseDTO(200, removePetByAge(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/getpetmpetflist")
    public ResponseEntity<ResponseDTO> getPetMPetFList() {
        return new ResponseEntity<>(new ResponseDTO(200, getPetMPetFList(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/reportpetsbyage")
    public ResponseEntity<ResponseDTO> reportPetsByAgeDE() {
        return new ResponseEntity<>(new ResponseDTO(200, reportPetsByAgeDE(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/removepetxpos")
    public ResponseEntity<ResponseDTO> removePetXPos(){
        return new ResponseEntity<>(new ResponseDTO(200,removePetXPos(),null), HttpStatus.OK);
    }
}
