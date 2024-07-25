package com.example.growith;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

// 유효하지 않은 html 태그 작성 내용 필터링

@Service
public class HtmlSanitizerService {
    public String sanitizeHtml(String html) {
        return Jsoup.clean(html, Safelist.basic());
    }
}
