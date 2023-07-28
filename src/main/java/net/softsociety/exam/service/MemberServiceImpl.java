package net.softsociety.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.softsociety.exam.dao.MemberDAO;
import net.softsociety.exam.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void insertMember(Member member) {
		String pw = passwordEncoder.encode(member.getMemberpw());
		member.setMemberpw(pw);
		dao.insertMember(member);
	}
   
}
