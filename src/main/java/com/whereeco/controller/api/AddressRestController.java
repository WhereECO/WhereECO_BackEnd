package com.whereeco.controller.api;

import com.whereeco.domain.address.entity.Address;
import com.whereeco.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddressInfo(){
        List<Address> addressList = addressService.findAll();
        return ResponseEntity.ok(addressList);
    }
}
