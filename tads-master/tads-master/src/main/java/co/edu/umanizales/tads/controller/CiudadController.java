package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.CiudadDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.umanizales.tads.service.CiudadService;

@RestController
@RequestMapping(path = "/ciudad")
public class CiudadController {
    @Autowired
    private  CiudadService ciudadService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getCiudades(){
        return new ResponseEntity<>(new ResponseDTO(200, ciudadService.getCiudadesFinal(), null), HttpStatus.OK);
    }
    @GetMapping(path = "/niñosxciudad")
    public ResponseEntity<ResponseDTO> ninosXCiudad(){
        return  new ResponseEntity<>(new ResponseDTO(200, ciudadService.ninosXCiudad(), null), HttpStatus.OK);
    }

}
