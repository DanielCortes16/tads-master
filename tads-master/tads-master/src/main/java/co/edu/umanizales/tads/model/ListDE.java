package co.edu.umanizales.tads.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                pets.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return pets;
    }


    public void addDE(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(pet);
        }
    }

    public void addxPosDE(Pet pet, int pos) {
        if (head != null) {
            NodeDE temp = head;
            int cont = 1;
            while (cont < pos - 1) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        } else {
            head = new NodeDE(pet);
        }
    }

    public void addToStartDE(Pet pet) {
        if (head != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(head);
            head.setPrevious(newNodeDE);
            head = newNodeDE;
        } else {
            head = new NodeDE(pet);
        }
    }

    public void changeExtremesDE() {// creo que lo tengo que cambiar
        if (this.head != null && this.head.getNext() != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Pet newKid = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(newKid);
        }
    }

    public void invertDE() {
        if (this.head != null) {
            ListDE newList = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                newList.addToStartDE(temp.getData());
                temp = temp.getNext();
            }
            this.head = newList.getHead();
        }
    }

    public void orderPetMToStartDE() {
        if (this.head != null) {
            ListDE newList = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    newList.addToStartDE(temp.getData());
                } else {
                    newList.addDE(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = newList.getHead();
        }
    }

    public void getPetMPetFList() {
        if (head != null) {
            ListDE newListGirls = new ListDE();
            ListDE newListBoys = new ListDE();
            ListDE newListFinal = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    newListGirls.addDE(temp.getData());
                } else {
                    newListBoys.addDE(temp.getData());
                }
                temp = temp.getNext();
            }
            NodeDE tempBoys = newListBoys.getHead();
            NodeDE tempGirls = newListGirls.getHead();
            while (tempBoys != null || tempGirls != null) {
                if (tempBoys != null) {
                    newListFinal.addDE(tempBoys.getData());
                    tempBoys = tempBoys.getNext();
                }
                if (tempGirls != null) {
                    newListFinal.addDE(tempGirls.getData());
                    tempGirls = tempGirls.getNext();
                }
            }
            head = newListFinal.getHead();
        }
    }

    public void removePetByAge(Byte age) {
        NodeDE temp = head;
        while (temp != null) {
            NodeDE next = temp.getNext();
            if (temp.getData().getAge() == age) {
                NodeDE previous = temp.getPrevious();
                if (previous != null) {
                    previous.setNext(next);
                } else {
                    head = next;
                }
                if (next != null) {
                    next.setPrevious(previous);
                }
                temp = next;
            } else {
                temp = next;
            }
        }
    }
//      --------------------------------------------------------------------------------

    public int countPets() {
        int cPet = 1;
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                cPet++;
            }
        }
        return cPet;
    }

    public int addAgesPets() {
        int sAges = head.getData().getAge();
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                sAges = sAges + temp.getData().getAge();
            }
        }
        return sAges;
    }

    public float promAges() {
        float prom = 0;
        float metodo1 = countPets();
        float metodo2 = addAgesPets();
        prom = metodo2 / metodo1;
        return prom;
    }

    //      --------------------------------------------------------------------------------
    public int getCountPetsByCityCode(String code) {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByDepCode(String code) {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().contains(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void reportPetsByAgeDE(int ageMin, int ageMax) {
        if (head != null) {
            NodeDE temp = head;
            ListDE newList = new ListDE();
            while (temp != null) {
                if (ageMin >= temp.getData().getAge() && ageMax <= temp.getData().getAge()) {
                    newList.addDE(temp.getData());
                }
            }
        }
    }

    public void addByNameAtEndDE(String initial) {
        ListDE newListDE = new ListDE();
        if (head != null) {
            NodeDE temp = head;
            NodeDE previous = null;
            while (temp != null) {
                if (temp.getData().getName().startsWith(initial)) {
                    newListDE.addDE(temp.getData());
                } else {
                    newListDE.addToStartDE(temp.getData());
                }
                previous = temp;
                temp = temp.getNext();
            }
            head = newListDE.getHead();
            previous.setNext(newListDE.getHead());
            if (newListDE.getHead() != null) {
                newListDE.getHead().setPrevious(previous);
            }
        }
    }

    public void removePetByID(String id) {
                /*
        si la lista no esta vacia
            asignamos un ayudante a la cabeza
            si la cabeza contiene la identificacion dada
                la cabeza sera igual al siguiente y el anterior sera vacio.

        si no
                si el ayudante no es el ultimo nodo
                el nodo anterior al ayudante agarre al nodo siguiente del ayudante
                el nodo siguiente al ayudante agarra al nodo anterior del ayudante

                si no el nodo anterior al ayudante agarre al nodo siguiente del ayudante
         */
        if (head != null) {
            NodeDE temp = head;
            if (head.getData().getIdentification().equals(id)) {
                head = head.getNext();
                if (head != null) {
                    head.setPrevious(null);
                }
            } else {
                if (temp.getNext() != null) {
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                } else {
                    temp.getPrevious().setPrevious(null);
                }
                temp.getNext();
            }
        }
    }

    public void gainPositions() {
        /*
        si la cabeza tiene datos asignar un ayudante a la cabeza
        recorremos la lista hasta llegar al nodo indicado
        creamos una lista copia hasta el nodo indicado
        avanzamos la cantidad de nodos deseados
        lo insertamos en esa posicion
         */
    }
}//Fin de clase
