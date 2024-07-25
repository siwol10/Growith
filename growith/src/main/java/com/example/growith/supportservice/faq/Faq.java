package com.example.growith.supportservice.faq;

import java.time.LocalDateTime;


import com.example.growith.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Faq {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	@ManyToOne
	private Member author;
	
	private LocalDateTime date;
}
