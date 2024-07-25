package com.example.growith.supportservice.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactTypeService {
    private final ContactTypeRepository contactTypeRepository;

    public List<ContactType> getAllContactTypes() {
        return contactTypeRepository.findAll();
    }
}
