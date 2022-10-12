package com.WhereECO.controller.api;

import com.WhereECO.dto.Address;
import com.WhereECO.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressRestController {

    @Autowired AddressMapper addressMapper;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddressInfo(){
        List<Address> addressList = addressMapper.findAll();

        return ResponseEntity.ok(addressList);
    }
}
