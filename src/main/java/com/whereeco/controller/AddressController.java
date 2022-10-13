package com.whereeco.controller;

import com.whereeco.domain.address.entity.Address;
import com.whereeco.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    // todo 생성자 주입으로 변경. 순환참조 막아지는지 실험
    private final AddressService addressService;

    @GetMapping("addresslist")
    public String list(Model model) {
        List<Address> addressList = addressService.findAll();
        model.addAttribute("address", addressList);
        return "address/addresslist";
    }

    @GetMapping("column")
    public String column(Model model, @RequestParam("id") Long id) {
        Address address = addressService.findById(id);
        List<Address> addressList = addressService.findAll();
        model.addAttribute("address", address);
        model.addAttribute("addressList", addressList);
        return "address/addresslist";
    }
}
