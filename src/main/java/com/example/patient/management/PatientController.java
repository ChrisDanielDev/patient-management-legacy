package com.example.patient.management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @GetMapping
    public List<AddressDTO> getPatients() {
        AddressDTO address1 = new AddressDTO("null", "null", null, null);
        AddressDTO address2 = new AddressDTO("null", "null", null, null);
        AddressDTO address3 = new AddressDTO("null", "null", null, null);

        List<AddressDTO> addressList = new ArrayList<AddressDTO>();

        // Add some strings to the list
        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);

        return addressList;
    }
}