package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import lombok.Data;

@Data
public class ListSE {
    private Node head;

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE newList = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    newList.addToStart(temp.getData());
                } else {
                    newList.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = newList.getHead();
        }
    }

    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }

    public void removeById(String id) {
        if (head != null) {
            Node temp = head;
            if (head.getData().getIdentification().equals(id)) {
                head = head.getNext();
            } else {
                while (!temp.getNext().getData().getIdentification().equals(id)) {
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext().getNext());
            }
        }
    }

    public void addxPos(Kid kid, int pos) {
        if (head != null) {
            Node temp = head;
            int cont = 1;
            while (cont < pos - 1) {
                temp = temp.getNext();
                cont++;
            }
            Node newNode = new Node(kid);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    public void changeExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public void invert() {
        if (this.head != null) {
            ListSE newList = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                newList.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = newList.getHead();
        }
    }

//      --------------------------------------------------------------------------------

    public int contarNiños() {
        int cNiños = 1;
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                cNiños++;
            }
        }
        return cNiños;
    }

    public int sumarEdadesNiños() {
        int sEdades = head.getData().getAge();
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                sEdades = sEdades + temp.getData().getAge();
            }
        }
        return sEdades;
    }

    public float promedioEdades() {
        float prom = 0;
        float metodo1 = contarNiños();
        float metodo2 = sumarEdadesNiños();
        prom = metodo2 / metodo1;
        return prom;
    }

//      --------------------------------------------------------------------------------

    public int getCountKidsByCityCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountKidsByDepCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().contains(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report) {
        if (head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getAge() > age) {
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }

//      --------------------------------------------------------------------------------

    public void addByNameAtEnd(String initial) {
        if (head != null) {
            Node temp = head;
            ListSE newList = new ListSE();
            while (temp != null) {
                if (temp.getData().getName().startsWith(initial)) {
                    newList.add(temp.getData());
                } else {
                    newList.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
        }
    }

    public void removeKidByAge(int age) {
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getAge() == age) {
                    temp.setNext(temp.getNext().getNext());
                }
                temp = temp.getNext();
            }
        }
    }

    public void getNinoNinaList() {
        if (head != null) {
            ListSE newListGirls = new ListSE();
            ListSE newListBoys = new ListSE();
            ListSE newListFinal = new ListSE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    newListGirls.add(temp.getData());
                } else {
                    newListBoys.add(temp.getData());
                }
                temp = temp.getNext();
            }
            Node tempBoys = newListBoys.getHead();
            Node tempGirls = newListGirls.getHead();
            while (tempBoys != null || tempGirls != null) {
                if (tempBoys != null) {
                    newListFinal.add(tempBoys.getData());
                    tempBoys = tempBoys.getNext();
                }
                if (tempGirls != null) {
                    newListFinal.add(tempGirls.getData());
                    tempGirls = tempGirls.getNext();
                }
            }
            head = newListFinal.getHead();
        }
    }

    public void advancePoss(int pos, Kid code) {//esta mal pero no tan mal
        if (head != null) {
            Node temp = head;
            int cont = 1;
            ListSE newList = new ListSE();
            while (temp != null) {
                if (temp.getData().getIdentification().equals(code)) {
                    newList.addToStart(temp.getData());
                } else {
                    newList.add(temp.getData());
                }
                temp = temp.getNext();
                cont = cont + 1;
            }
            int posF = cont - pos;
        }
    }

    public void reportKidsByAge(int ageMin, int ageMax) {
        if (head != null) {
            Node temp = head;
            ListSE newList = new ListSE();
            while (temp != null) {
                if(ageMin >= temp.getData().getAge() && ageMax <= temp.getData().getAge() ){
                    newList.add(temp.getData());
                }
            }
        }
    }
} // Fin de la clase
