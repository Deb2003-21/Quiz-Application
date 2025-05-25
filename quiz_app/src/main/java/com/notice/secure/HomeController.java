package com.notice.secure;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notice.secure.Repo.LeadRepo;
import com.notice.secure.model.LeaderBoard;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	LeadRepo lr;
	@RequestMapping("")
	public String hom() {
		return "home";
	}
	@RequestMapping("create")
	public String first(HttpSession hs)
	{
		
		String title= "player"+ThreadLocalRandom.current().nextInt(1,1500);
		hs.setAttribute("player",title);
		return "first";
	}
	@RequestMapping("lead")
	public String lead(Model m)
	{
		List<LeaderBoard> lrb= lr.findAll(Sort.by(Sort.Direction.DESC,"acc"));
		m.addAttribute("data",lrb);
		return "lead";
	}
	@RequestMapping("third")
	public String third(@RequestParam String score,@RequestParam String total,@RequestParam int time,HttpSession hs)
	{	
		
		String p= (String) hs.getAttribute("player");
		Optional<LeaderBoard> sn=lr.findById(p);
		if(sn.isPresent()==true) {
			return "error";
		}	
		else {
		int sr=Integer.parseInt(score);
		int tr=Integer.parseInt(total);
		int acc = (int) (((double) sr / tr) * 100);
		int time_new = (int)(((double) tr / 4) * 60 - time);

		LeaderBoard lb= new LeaderBoard();
		lb.setId(p);
		lb.setAcc(acc);
		lb.setTime(time_new);
		lr.save(lb);}
		return "third";
	}
}
