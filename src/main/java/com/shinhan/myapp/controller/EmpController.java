package com.shinhan.myapp.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpDTO;
import org.firstzone.myapp.emp.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.util.DateUtil;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	
	//Autowired는 타입이 같으면 자동으로 Injection
	@Autowired
	EmpService eService;
	
	@Autowired
	DeptService dService;
	
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	//deptSelect=40&jobSelect=AD_PRES&hdate=2005-01-01&salary=5001
	//deptSelect==0 => 모든 부서를 의미
	//jobSelect.equals("all") => 모든 직책을 의미
	@RequestMapping("/empAll2.do")
	public String empAll2(Model model, 
			HttpSession session,
			Integer deptSelect, String jobSelect, 
			@RequestParam(value="hdate", required=false, defaultValue="1900-01-01") Date hdate,
			Integer salary )  {
		
		if(deptSelect==null) deptSelect=0;
		if(salary==null) salary=0;
		//Date startDate = DateUtil.getSQLDate(hdate);		

		//선택한 값들을 session에 저장하기
		session.setAttribute("deptSelect", deptSelect);
		session.setAttribute("jobSelect", jobSelect);
		session.setAttribute("hdate", hdate);
		session.setAttribute("salary", salary);
		
		List<EmpDTO> emplist2 = eService.selectByCondition(deptSelect, jobSelect, hdate, salary);
		
		model.addAttribute("emplist", emplist2);
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());
		return "emp/emplist";
	}
	
	@RequestMapping("/empAll.do")
	public String empAll(Model model, Integer deptid, String jobid) {
		List<EmpDTO> emplist2 = null;
		
		if(deptid==null) deptid = 0;
		emplist2 = eService.selectByCondition(deptid, jobid, null, 0);
		
		model.addAttribute("emplist", emplist2);
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("joblist", eService.selectAllJob());
		//view 이름이 return된다.
		//ViewResolver가 접두사 + view 이름 + 접미사
		//view로 forward된다. (주소는 바뀌지 않음)
		return "emp/emplist";
	}
	
	@GetMapping("/empDetail.do")
	public void detail(Integer empid, Model model) {
		model.addAttribute("empInfo", eService.selectById(empid));
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("mlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());
		
	
	}
	
	@GetMapping("/empInsert.do")
	public void insert(Model model) {
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("mlist", eService.selectAllManager());
		model.addAttribute("joblist", eService.selectAllJob());
	};
	
	//DB 입력
	@PostMapping("/empInsert.do")
	public String insertDB(EmpDTO emp) {
		System.out.println("insert 확인(JavaBean) : " + emp);
		eService.empInsert(emp);
		return "redirect:empAll.do";
	}
	
	//DB 수정
	@PostMapping("/empDetail.do")
	public String updateDB(EmpDTO emp) {
		System.out.println("Update 확인(JavaBean) : " + emp);
		eService.empUpdate(emp);
		return "redirect:empAll.do";
	}
	
	@GetMapping("/empdelete.do")
	public String delete(Integer empid) {
		eService.empDelete(empid);
		
		return "redirect:empAll.do";
	}
	
	@GetMapping("/empIdCheck.do")
	@ResponseBody
	public String test(Integer empid) {
		EmpDTO emp = eService.selectById(empid);
		if(emp==null) return "0";
		return "1";
	}
	
}


