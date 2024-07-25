package com.example.growith.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.growith.supportservice.faq.Faq;
import com.example.growith.supportservice.faq.FaqRepository;
import com.example.growith.supportservice.faq.FaqService;
import com.example.growith.supportservice.notice.Notice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminFaqService {
    private final FaqRepository faqRepository;
    
    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }
    
    public void createFaq(Faq faq) {
        faq.setDate(LocalDateTime.now());
        faqRepository.save(faq);
    }

    
	public void deleteFaq(Integer id) {
		faqRepository.deleteById(id);
	}

	public void updateFaq(Faq faq) {
		faqRepository.save(faq);
	}
	
	public Faq getFaq(Integer id) {
        return faqRepository.findById(id).orElse(null);
    }
}
