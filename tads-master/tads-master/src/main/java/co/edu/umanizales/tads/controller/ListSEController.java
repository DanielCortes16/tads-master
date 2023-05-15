package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/promedio")
    public ResponseEntity<ResponseDTO> promEdades() {
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.promEdades(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listSEService.getKids().add(new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el petacón", null), HttpStatus.OK);

    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByCityCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydep")
    public ResponseEntity<ResponseDTO> getKidsByDep() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listSEService.getKids().getCountKidsByDepCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsByLocationDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/orderboystostart")
    public ResponseEntity<ResponseDTO> orderBoysToStart() {
        listSEService.orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(200, "Se ordenaron los niños", null), HttpStatus.OK);
    }

    @GetMapping(path = "/removebyid/{code}")
    public ResponseEntity<ResponseDTO> removeById(@PathVariable String code) {
        listSEService.removeById(code);
        return new ResponseEntity<>(new ResponseDTO(200, "Se ah eliminado el niño", null), HttpStatus.OK);
    }

    @GetMapping(path = "/addxpos/{code}")
    public ResponseEntity<ResponseDTO> addxPos(@PathVariable String code) {
        return new ResponseEntity<>(new ResponseDTO(200, addxPos(code), null), HttpStatus.OK);
    }

    @GetMapping(path = "/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "Se cambiaron los extremos", null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200, "Se invirtio la lista", null), HttpStatus.OK);
    }


    @GetMapping(path = "/addbynameatend/{initial}")
    public ResponseEntity<ResponseDTO> addByNameAtEnd(@PathVariable String initial) {
        listSEService.addByNameAtEnd(initial);
        return new ResponseEntity<>(new ResponseDTO(200, "se ordeno re melo jaja", null), HttpStatus.OK);
    }

    @GetMapping(path = "/removekidbyage/{age}")
    public ResponseEntity<ResponseDTO> removeKidByAge(@PathVariable int age) {
        listSEService.removeKidByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "se elimino el niño", null), HttpStatus.OK);
    }

    @GetMapping(path = "/getninoninalist")
    public ResponseEntity<ResponseDTO> getNinoNinaList() {
        listSEService.getNinoNinaList();
        return new ResponseEntity<>(new ResponseDTO(200, "se intercalaron jaja", null), HttpStatus.OK);
    }

    @GetMapping(path = "/reportkidsbyage")
    public ResponseEntity<ResponseDTO> reportKidsByAge() {
        return new ResponseEntity<>(new ResponseDTO(200, reportKidsByAge(), null), HttpStatus.OK);
    }
}