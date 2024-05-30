package com.shinhan.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.emp.EmpDTO;


public class LoginCheckFilter implements Filter {
       
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//�α������� ������ �������� ���� ����
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse rep = (HttpServletResponse)response;
		 HttpSession session = req.getSession(); 
		 
		 //webshop/auth/login.do
		 if(!req.getRequestURI().endsWith("login.do")) {
			 
			 System.out.println("!!getQueryString : " + req.getQueryString() );		 
			 session.setAttribute("lastRequest", req.getRequestURI());
			 session.setAttribute("queryString", req.getQueryString());
			 EmpDTO emp = (EmpDTO) session.getAttribute("emp"); 
			 if(emp == null) { 
				 //�α��� �Ǿ����� ������ �������� �������� �α��� â���� ���û(sendRedirect) 
				 String path = req.getContextPath();
				 rep.sendRedirect(path + "/auth/login.do"); 
				 return;
			 }
		 }
	
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
