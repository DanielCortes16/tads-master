package co.edu.umanizales.tads.model;

public class ListDECircular {
    private NodeDE head;

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
        }
    }

    public void addPetXPos(Pet pet){

    }

}// fin de la clase
