package com.whereeco.domain.address.service;

import com.whereeco.domain.address.entity.Address;
import com.whereeco.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 id"));
    }
}
























