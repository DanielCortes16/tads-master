package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
    }

    public float promEdades() {
        return kids.promedioEdades();
    }

    public void orderBoysToStart() {
        kids.orderBoysToStart();
    }

    public void removeById(String id) {
        kids.removeById(id);
    }

    public void addxPos(Kid kid, int pos) {
        kids.addxPos(kid, pos);
    }

    public void changeExtremes() {
        kids.changeExtremes();
    }

    public void invert() {
        kids.invert();
    }

    public void getCountKidsByCityCode(String code) {
        kids.getCountKidsByCityCode(code);
    }

    public void getCountKidsByDepCode(String code) {
        kids.getCountKidsByDepCode(code);
    }

    public void addByNameAtEnd(String initial) {
        kids.addByNameAtEnd(initial);
    }

    public void removeKidByAge(int age) {
        kids.removeKidByAge(age);
    }

    public void getNinoNinaList() {
        kids.getNinoNinaList();
    }

    public void reportKidsByAge(int ageMin, int ageMax) {
        kids.reportKidsByAge(ageMin, ageMax);
    }
    public void gainXPos(String id, int pos){ kids.gainXPos(id, pos);}
    public void loseXPos(String id, int pos){ kids.loseXPos(id, pos);}
}