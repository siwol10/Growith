package com.example.growith.supportservice.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
	private final NoticeRepository noticeRepository;
	// db에 있는 board 전체 갖고 오기
//	public List<Notice> readList(){
//		return noticeRepository.findAll();
//	}
	 public Page<Notice> getList(int page) {
	        Pageable pageable = PageRequest.of(page, 5);
	        return this.noticeRepository.findAll(pageable);
	    }
	 public Page<Notice> findAll(Pageable pageable) {
	        return noticeRepository.findAll(pageable);
	    }
}
