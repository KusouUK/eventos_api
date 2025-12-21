package com.vitoruk.api.service;

import com.vitoruk.api.domain.address.Address;
import com.vitoruk.api.domain.event.Event;
import com.vitoruk.api.domain.event.EventRequestDTO;
import com.vitoruk.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        return this.addressRepository.save(address);
    }
}
