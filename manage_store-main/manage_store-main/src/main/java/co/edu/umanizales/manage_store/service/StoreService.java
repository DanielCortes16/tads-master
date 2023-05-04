package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.StoreDTO;
import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class StoreService {

    private List<Store> stores;
    @Autowired
    private SaleService saleService;

    public StoreService() {

        stores = new ArrayList<>();
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public Store getStoreById(String code) {
        for (Store store : stores) {
            if (store.getCode().equalsIgnoreCase(code)) {
                return store;
            }
        }
        return null;
    }

    public List<StoreDTO> ventasXCantidad(int i) {
        List<StoreDTO> storesDTO = new ArrayList<>();
        for (Store store : stores) {
            StoreDTO storeDTO = new StoreDTO(new Store(store.getCode(),store.getName()), saleService.getTotalSalesByStore(store.getCode()));
            if (storeDTO.getCantidad() > i) {
                storesDTO.add(storeDTO);
            }
        }
        return storesDTO;
    }

}
