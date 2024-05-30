package com.shinhan.myapp.controller;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.firstzone.myapp.dept.DeptDTO;
import org.firstzone.myapp.dept.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController		// @Controller + @ResponseBody
@RequestMapping("/dept/api/*")
public class DeptRestController {
	
	@Autowired
	DeptService dService;
	
	Logger logger = LoggerFactory.getLogger(DeptRestController.class);
	
	
	//����
	@DeleteMapping(value = "/delete/{deptid}", produces="text/plain;charset=utf-8")
	public String delete(@PathVariable("deptid") Integer deptid) {
	 	int result = dService.deptDelete(deptid);
	 	return result>0?"��������":"��������";
	}
	
	
	//����
	@PutMapping(value = "/update", 
			consumes = "application/json",
			produces="text/plain;charset=utf-8"
			)
	public String update(@RequestBody DeptDTO dept) {
		int result = dService.deptUpdate(dept);
		
		return "�����Ǽ� : " + result + "��";
	}
	
	
	//Post�� ��û������ body�� data�� ���´�.
	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces="text/plain;charset=utf-8")
	public String insert(@RequestBody DeptDTO dept) {
		logger.info("���� dept : " + dept);
		int result =  dService.deptInsert(dept);
		return "insert ��� : " +  result + "��";
	}
	
	//JSON���� ��������
	//jackson-databind�� �̿��ؼ� �ڹ��� ��ü�� JSON���� convert�ȴ�.
	//produces = MediaType.APPLICATION_JSON_VALUE
	//produces = "application/json"
	@GetMapping(value = "/deptAll", produces = "application/json")
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = dService.selectAll();
		
		return deptlist;
	}
	
	//Json���� ��������
	//jackson-databind�� �̿��ؼ� �ڹ��� ��ü�� JSON���� convert�ȴ�.
	@GetMapping(value = "/detail/{deptid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DeptDTO selectById(@PathVariable("deptid") Integer did) {
		DeptDTO dept = dService.selectById(did);
		return dept;
	}

	//�������� : consumes ����
	//�������� : produces ����
	@GetMapping(value = "/test1", produces="text/plain;charset=utf-8")
	public String test1() {
		return "Restful ��� ����";
	}
}