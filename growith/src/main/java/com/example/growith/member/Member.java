package com.example.growith.member;

import com.example.growith.supportservice.faq.Faq;
import com.example.growith.supportservice.notice.Notice;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//관리자 entity
@Data
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String role;

	@OneToMany(mappedBy = "author")
	private List<Notice> noticeList;

	@OneToMany(mappedBy = "author")
	private List<Faq> faqList;
}
