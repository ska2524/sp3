package org.zerock.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.MemberVO;

@Controller
@RequestMapping
public class LoginController {

	private Map<String,MemberVO> map;
	
	public LoginController(){
		map = new HashMap<>();
	}
	
	//만들고 로그인 인터셉터
	
	
	public Map<String, MemberVO> getMap() {
		return map;
	}




	public void setMap(Map<String, MemberVO> map) {
		this.map = map;
	}




	@GetMapping("/login")
	public void loginGET(){
		
	}
	
	
	@PostMapping("/login")
	public void loginPost(@RequestParam("uid")String uid,@RequestParam("upw")String upw, Model model){
		//@ModelAttribute("member")MemberVO vo 자동 담긴다.
		if(uid.equals(upw)){
			
			MemberVO vo = new MemberVO();
			vo.setUid(uid);
			vo.setUpw(upw);
			vo.setUname(uid+"님");
			model.addAttribute("result",vo);
			//모델에 담가줬다.
		}
	}
	
}
