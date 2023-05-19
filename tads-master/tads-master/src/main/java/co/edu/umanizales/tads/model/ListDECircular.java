package co.edu.umanizales.tads.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDECircular {
    private NodeDE head;

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (temp.getNext() != head) {
                pets.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return pets;
    }

    public void addPetToStart(Pet pet) {
/*
    verificamos si la lista no esta vacia
    nos ubicamos en la cabeza
    metemos el perro en un nuevo costal
    nuevo costal debe ser la cabeza y agarrar el ultimo (previo)

    si la lista esta vacia
    la cabeza seria el costal
*/
        if (head != null) {
            NodeDE temp = head;
            NodeDE newNode = new NodeDE(pet);
            head = newNode;
            newNode.getPrevious();
        } else {
            head = new NodeDE(pet);
        }
    }

    public void addPet(Pet pet) {
/*
    verificamos si la lista no esta vacia
    nos ubicamos en el ultimo perro (el previo de la cabeza)
    metemos el nuevo perro en un costal
    nuevo costal agarra la cabeza y el previo

    si la lista esta vacia
    la cabeza seria el costal
*/
        if (head != null) {
            NodeDE temp = head;
            temp = temp.getPrevious();
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head.setPrevious(newNode);
        } else {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        }
    }

    public void addPetXPos(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            int cont = 1;
            int poss = 0;
            while (cont < poss - 1) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.getNext().setPrevious(newNode);
            temp.setNext(newNode);
        } else {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        }
    }

}// fin de la clase
