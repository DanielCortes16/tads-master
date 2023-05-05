package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;

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

    public void removePetByAge(int age) {
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getAge() == age) {
                    if (temp == head) {
                        head = temp.getNext();
                        if (head != null) {
                            head.setPrevious(null);
                        }
                    } else {
                        temp.getPrevious().setNext(temp.getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrevious(temp.getPrevious());
                        }
                    }
                }
                temp = temp.getNext();
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

    public void reportKidsByAgeDE(int ageMin, int ageMax) {
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
        if (head != null) {
            NodeDE temp = head;
            ListDE newList = new ListDE();
            while (temp != null) {
                if (temp.getData().getName().startsWith(initial)) {
                    newList.addDE(temp.getData());
                } else {
                    newList.addToStartDE(temp.getData());
                }
                temp = temp.getNext();
            }
        }
    }
}//Fin de clase
