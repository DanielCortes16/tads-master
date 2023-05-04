package co.edu.umanizales.manage_store.model;

import lombok.Data;

@Data
public class Store {
    private String code;
    private String name;

    public Store() {
    }
    public Store(String code, String name){
        this.code = code;
        this.name = name;
    }
}
