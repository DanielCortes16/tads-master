package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListDECircular {
    private NodeDE head;

    public List<Pet> getPets() {
        /*
        Se crea una nueva lista pets
        Se asigna la cabeza de la lista a el ayudante
        Se verifica si la lista no esta vacia
        Se entra en el bucle que recorrerá la lista
        Se agrega cada perrro a la otra lista
        Si el ayudante llega a la cabeza es porque recorrio toda la lista
        Se devuelve el ArrayList pets que contiene todos los elementos de la lista
        */
        List<Pet> pets = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (true) {
                pets.add(temp.getData());
                temp = temp.getNext();
                if (temp == head) {
                    break;
                }
            }
        }
        return pets;
    }

    public void addPet(Pet pet) {
        /*
        introducimos el perro en un costal
        Se verifica si la lista no esta vacia
        Se establece el siguiente nodo de newNode como la cabeza
        Se establece el nodo anterior de newNode como el nodo anterior de la cabeza
        Se establece que el nodo anterior de la cabeza su siguiente sea el newNode
        Se establece el el nodeo anterior a la cabeza como newNode
        Si la lista está vacia
        Se establece newNode como la cabeza
        Se establce el siguiente de la cabeza como la cabeza
        Se establce el anterior de la cabeza como la cabeza
        */
        NodeDE newNode = new NodeDE(pet);

        if (head != null) {
            newNode.setNext(head);
            newNode.setPrevious(head.getPrevious());
            head.getPrevious().setNext(newNode);
            head.setPrevious(newNode);
            newNode.getData().setLimpieza(true);
            newNode.getData().setFleas(putFleas());
        } else {
            head = newNode;
            head.setNext(newNode);
            head.setPrevious(newNode);
            newNode.getData().setLimpieza(true);
            newNode.getData().setFleas(putFleas());
        }
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
            temp = temp.getPrevious();
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            head = new NodeDE(pet);
            head.setPrevious(head);
            head.setNext(head);
        }
    }

    public void dirtyDogs() {
        NodeDE temp = head;
        if (head != null) {
            while (true) {
                temp.getData().setLimpieza(false);
                temp = temp.getNext();
                if (temp == head) {
                    break;
                }
            }
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

    public static int randomNum() {
        Random random = new Random();
        int num = random.nextInt(50);
        return num;
    }

    public void cleanPet() throws ListDEException {
        /*
        Se crea un numero aleatorio y se crea un contador
        Se verifica si la lsta esta vacia
        Se verifica si hay 2 o mas mascotas
        Se crea un ayudante en la cabeza
        Mientras el contador sea menor al numero aleatorio
        Recorremos la lista y sumamos al contador
        Se verifica si la mascota esta limpia
        Se verifica si hay suficientes mascotas
         */
        int rNum = randomNum();
        int cont = 1;
        if (head != null) {
            if (head.getNext() != null) {
                NodeDE temp = head;

                while (cont < rNum) {
                    temp = temp.getNext();
                    cont++;
                }
                if (temp.getData().isLimpieza()) {
                    throw new ListDEException("la mascota se limpio" + rNum);
                }
                temp.getData().setLimpieza(true);
            } else {
                throw new ListDEException("no hay mascotas suficientes");
            }
        }
    }

    public int putFleas() {
        Random fleas = new Random();
        int rFleas = fleas.nextInt(50);
        return rFleas;// cantidada de pulgas random
    }


    public Pet getPetWithMoreFleas() {
     /*
    Se verifica si la lista tiene datos
    Asignamos un ayudante a la cabeza
    Creamos una mascota copia
    Mientras hayan datos
    Si el ayudante tiene mas pulgas que la cabeza
    Asignamos los datos a la copia
    El ayudante avanza
    si no
    El ayudante avanza
    Retornamos la amsocta con mas pulgas
     */
        Pet pet = null;
        if (head != null) {
            NodeDE temp = head;
            Pet petCop = head.getData();
            while (true) {
                if (temp.getData().getFleas() > petCop.getFleas()) {
                    petCop = temp.getData();
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
                if (temp == head) {
                    break;
                }
                pet = petCop;
            }
        }
        return pet;
    }


}// fin de la clase
