package com.example.growith.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    //유저가 로그인하면 스프링 시큐리티는 UserDetailService를 호출하고 loadUserByUsername을 호출하여 로그인 정보와 DB의 정보 비교
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> tempmembers = memberRepository.findByEmail(email);
        if(tempmembers.isEmpty()){
            throw new UsernameNotFoundException("You need to signup first");

        }
        Member member  = tempmembers.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("ROLE_ADMIN".equals(member.getRole())){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(member.getEmail(), member.getPassword(), authorities);
    }

    public void create(Member member) {
        member.setEmail(member.getEmail());
        member.setRole("ROLE_ADMIN");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
}


