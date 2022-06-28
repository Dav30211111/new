package com.members.model;

import java.sql.Blob;
import java.util.List;



public class MemberService {
	
	private MembersDAO_interface dao;
	
	public MemberService() {
		dao = new MembersJDBCDAO();
	}
	
	public MemberVO addMember(String member_email, String member_password,
			String member_name, String member_phone, 
			String member_address, Integer member_status) {
	
	MemberVO memberVO = new MemberVO();
	
	memberVO.setMember_email(member_email);
	memberVO.setMember_password(member_password);
	memberVO.setMember_name(member_name);
	memberVO.setMember_phone(member_phone);
	memberVO.setMember_address(member_address);
	memberVO.setMember_status(member_status);
	dao.insert(memberVO);
	
	return memberVO;
	}
	
	public MemberVO updateMember(Integer member_id, String member_email, String member_password,
			String member_name, String member_phone, 
			String member_address, Integer member_status) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_id(member_id);
		memberVO.setMember_email(member_email);
		memberVO.setMember_password(member_password);
		memberVO.setMember_name(member_name);
		memberVO.setMember_phone(member_phone);
		memberVO.setMember_address(member_address);
		memberVO.setMember_status(member_status);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void deleteMember(Integer member_id) {
		dao.delete(member_id);
	}

	public MemberVO getOneMember(Integer member_id) {
		return dao.findByPrimaryKey(member_id);
	}

    public MemberVO getfindByAccountAndPassword(String account, String password) {
    	return dao.findByAccountAndPassword(account, password);
    }
	
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
