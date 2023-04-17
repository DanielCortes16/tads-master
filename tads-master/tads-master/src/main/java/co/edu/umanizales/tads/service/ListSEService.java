package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.CiudadDTO;
import co.edu.umanizales.tads.model.Ciudad;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
        kids.add(new Kid("123", "Carlos", (byte) 4, new Ciudad("Manizales", "1")));
        kids.add(new Kid("256", "Mariana", (byte) 3, new Ciudad("Armenia", "2")));
        kids.add(new Kid("89", "Daniel", (byte) 5, new Ciudad("Pereira", "3")));
        kids.add(new Kid("789", "Tom", (byte) 7, new Ciudad("Manizales", "1")));

        kids.add(new Kid("79", "Fede", (byte) 3, new Ciudad("Pereira", "3")));

        kids.addxPos(new Kid("999", "yo", (byte) 18, new Ciudad("Manizales", "1")), 3);
        kids.addToStart(new Kid("967", "Estefania", (byte) 6, new Ciudad("Manizales", "1")));
        kids.removeById("79");

    }

    public Node getKids() {
        return kids.getHead();
    }

    public float promEdades() {
        return kids.promedioEdades();
    }

    public List<Ciudad> getCiudadList() {
        List<Ciudad> ciudadList = new ArrayList<Ciudad>();
        if (kids.getHead() != null) {
            Node temp = kids.getHead();
            ciudadList.add(temp.getData().getCiudad());
            while (temp.getNext() != null) {
                temp = temp.getNext();
                ciudadList.add(temp.getData().getCiudad());
            }
        }
        return ciudadList;
    }
}//