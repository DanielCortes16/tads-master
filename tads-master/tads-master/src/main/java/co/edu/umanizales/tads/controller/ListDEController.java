package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.PetsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/promediopets")
    public ResponseEntity<ResponseDTO> promAges(){
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.promAges(), null), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getPets().getPets(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPetDE(@RequestBody PetDTO PetDTO) {
        Location location = locationService.getLocationByCode(PetDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDEService.getPets().addDE(new Pet(PetDTO.getIdentification(), PetDTO.getName(), PetDTO.getAge(), PetDTO.getGender(), false, location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el petacón", null), HttpStatus.OK);

    }

    @GetMapping(path = "/orderpetmsostart")
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


    @GetMapping(path = "/addbynameatendde/{initial}")
    public ResponseEntity<ResponseDTO> addByNameAtEndDE(@PathVariable String initial) {
        listDEService.addByNameAtEndDE(initial);
        return new ResponseEntity<>(new ResponseDTO(200, "se ordenaron", null), HttpStatus.OK);
    }

    @GetMapping(path = "/removepetbyage/{age}")
    public ResponseEntity<ResponseDTO> removePetByAge(@PathVariable Byte age) {
        listDEService.removePetByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "se elimino la mascota", null), HttpStatus.OK);
    }

    @GetMapping(path = "/getpetmpetflist")
    public ResponseEntity<ResponseDTO> getPetMPetFList() {
        listDEService.getPetMPetFList();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercalaron las mascotas", null), HttpStatus.OK);
    }

    @GetMapping(path = "/reportpetsbyage")
    public ResponseEntity<ResponseDTO> reportPetsByAgeDE() {
        return new ResponseEntity<>(new ResponseDTO(200, reportPetsByAgeDE(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/removepetbyid/{id}")
    public ResponseEntity<ResponseDTO> removePetXPos(@PathVariable String id) {
        listDEService.removePetByID(id);
        return new ResponseEntity<>(new ResponseDTO(200,"se elimino esa chanda" , null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> getPetsByLocation() {
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listDEService.getPets().getCountPetsByCityCode(loc.getCode());
            if (count > 0) {
                petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbydep")
    public ResponseEntity<ResponseDTO> getPetsByDep() {
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listDEService.getPets().getCountPetsByDepCode(loc.getCode());
            if (count > 0) {
                petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsByLocationDTOList, null), HttpStatus.OK);
    }
    @GetMapping(path = "/gainxpos/{id}/{pos}")
    public ResponseEntity<ResponseDTO> gainXPos(@PathVariable String id, @PathVariable int pos) throws ListDEException {
        listDEService.gainXPos(id, pos);
        return  new ResponseEntity<>(new ResponseDTO(200, "el perro avanzo de posicion", null), HttpStatus.OK);
    }
}
