package com.example.growith.admin;

import com.example.growith.supportservice.contact.ContactType;
import com.example.growith.supportservice.contact.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminContactTypeService {
    private final ContactTypeRepository contactTypeRepository;

    public void create(@ModelAttribute ContactType contactType) {
        contactTypeRepository.save(contactType);
    }

    public void deleteById(Integer id) {
        contactTypeRepository.deleteById(id);
    }

    public void update(@ModelAttribute ContactType contactType) {
        contactTypeRepository.save(contactType);
    }

    public ContactType getById(Integer id) {
        return contactTypeRepository.findById(id).orElse(null);
    }

    public List<ContactType> getAllContactTypes() {
        return contactTypeRepository.findAll();
    }
}
