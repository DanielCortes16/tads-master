package co.edu.umanizales.tads.model;

import lombok.Data;
import co.edu.umanizales.tads.exception.ListDEException;

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

            // Verificar si la cabeza contiene la identificación dada
            if (head.getData().getIdentification().equals(id)) {
                if (head.getNext() != null) {
                    temp.getNext().setPrevious(null);
                    head = head.getNext();
                } else {
                    head = null;
                }
            } else {
                while (temp != null) {
                    if (temp.getData().getIdentification().equals(id)) {
                        if (temp.getNext() != null) {
                            // El nodo a eliminar no es el último nodo
                            temp.getPrevious().setNext(temp.getNext());
                            temp.getNext().setPrevious(temp.getPrevious());
                        } else {
                            // El nodo a eliminar es el último nodo
                            temp.getPrevious().setNext(null);
                        }
                        return; // Se encontró y eliminó el nodo, se sale del método
                    }
                    temp = temp.getNext();
                }
            }
        }
    }


    public void gainXPos(String id, int pos) throws ListDEException {

        NodeDE temp = head;
        int posList = 1;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
                posList++;
            }
            // temp estar en el que hay adelantar

            if (temp == head) {
                throw new ListDEException("la cabeza no puede avanzar posiciones");
            } else {

                int posFinal = posList - pos;
                if (posFinal >= 1) {
                    Pet petcop = temp.getData();
                    removePetByID(id);
                    addxPosDE(petcop, posFinal);
                } else {
                    throw new ListDEException("no puede avanzar");
                }
            }
        }
            /*
    verificamos si la lista esta vacia
    ubicamos el nodo con el id indicado
    restamos la cantidad de posiciones avanzadas con la posiciones ingresadas
    si el resultado es menor a 1 no se puede

    creamos una copia de la mascota .getData
    usamos removePetById

    insertamos la copia de la mascota en el resultaddo de la resta de las posiciones avanzadas con la posiciones ingresadas
     */
    }

    public void loseXPos(String id, int pos) throws ListDEException {
        NodeDE temp = head;
        int posList = 1;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
                posList++;
            }// temp esta en el id
            if (temp.getNext() == null) {
                throw new ListDEException("el ultimo no puede perder posiciones");
            }else {
                int posFinal = posList + pos;
                if (posFinal >= 1) {
                    Pet petcop = temp.getData();
                    removePetByID(id);
                    addxPosDE(petcop, posFinal);
                }else{
                    throw new ListDEException("no puede avanzar");
                }
            }
        }
    }

}//Fin de clase
