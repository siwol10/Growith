package com.example.growith.admin;

import com.example.growith.supportservice.notice.Notice;
import com.example.growith.supportservice.notice.NoticeRepository;
import com.example.growith.supportservice.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminNoticeService {
    private final NoticeRepository noticeRepository;

    public void createNotice(Notice notice) {
        notice.setDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    public void deleteNotice(Integer id) {
        noticeRepository.deleteById(id);
    }

    public void updateNotice(Notice notice) {
        notice.setDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    public Notice getNotice(Integer id) {
        return noticeRepository.findById(id).orElse(null);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }
}
