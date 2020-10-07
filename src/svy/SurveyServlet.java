package svy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SurveyServlet
 */
//@WebServlet("*.sv")
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServletContext context;
	String driver;
	String url;
	String user;
	String password;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		context = config.getServletContext();		//config객체를 활용해서 context를 받을 수 있다.
		
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		user = config.getInitParameter("user");
		password = config.getInitParameter("password");
		System.out.println("init driver : " + driver);
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet()");
		
		doProcess(request,response);
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("doPost()");
		
		doProcess(request,response);
			
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doProcess()");
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int len =  contextPath.length();
		
		System.out.println("uri : " +uri);
		System.out.println("len : " +len);
		
		String command = uri.substring(len);
		System.out.println("command : "+command);
		
		SurveyDao dao = new SurveyDao(driver,url,user,password); 
		String viewPage = null;
		
		if(command.equals("/insert.sv")) {
			System.out.println("insert 요청");
			
			String flag = (String)context.getAttribute("flag");
			System.out.println("insert flag : " + flag);
			
			String name = request.getParameter("name");
			String company = request.getParameter("company");
			String email = request.getParameter("email");
			String satisfaction = request.getParameter("satisfaction");
			String[] part_imsi = request.getParameterValues("part");
			String part = "";
			
			if(part_imsi == null) {
				part = "선택한 관심분야가 없습니다.";
			}
			else {
				for(int i=0;i<part_imsi.length;i++) {
					part += part_imsi[i];
						if(i != part_imsi.length-1) {
							part += ",";
						}
				}
			}
			
			String howto = request.getParameter("howto");
			
			String agree_imsi = request.getParameter("agree");
			int agree; 
						
			if(agree_imsi == null) {
				agree = 0;
			}else {
				agree = 1;
			}
		
			SurveyBean sb = new SurveyBean(0, name, company, email, satisfaction, part, howto, agree);
			
			if(flag.equals("false")) {
				int cnt = dao.insertSurveyBean(sb);
				System.out.println("insert cnt : " + cnt);
				
				context.setAttribute("flag","true"); 
				viewPage = "/list.sv";
			} else {
		
				viewPage = "/list.sv";
			}
		}
		
		
		else if(command.equals("/list.sv")) {
			System.out.println("list 요청");
			
			ArrayList<SurveyBean> lists = dao.getServeyList();
			
			request.setAttribute("lists", lists);
			
			viewPage = "Ex02_surveyList.jsp";
			
			
			
		}
		
		
		 else if(command.equals("/updateForm.sv")) {
			 System.out.println("updateForm 요청"); 
			 
			 int no = Integer.parseInt(request.getParameter("no"));
			 System.out.println("updateForm no : " + no);
			 
			 SurveyBean sb = dao.getSelectOne(no); 
			 
			 
			 
			 request.setAttribute("sb", sb);
			 viewPage = "Ex02_surveyUpdateForm.jsp";
		 }
		 
		 
		 else if(command.equals("/update.sv")) { 
			 System.out.println("update 요청");
			 
			 	int no = Integer.parseInt(request.getParameter("no"));
			 	String name = request.getParameter("name");
				String company = request.getParameter("company");
				String email = request.getParameter("email");
				String satisfaction = request.getParameter("satisfaction");
				String[] part_imsi = request.getParameterValues("part");
				String part = "";
				
				if(part_imsi == null) {
					part = "선택한 관심분야가 없습니다.";
				}
				else {
					for(int i=0;i<part_imsi.length;i++) {
						part += part_imsi[i];
							if(i != part_imsi.length-1) {
								part += ",";
							}
					}
				}
				
				String howto = request.getParameter("howto");
				
				String agree_imsi = request.getParameter("agree");
				int agree; 
							
				if(agree_imsi == null) {
					agree = 0;
				}else {
					agree = 1;
				}
			
				SurveyBean sb = new SurveyBean(no, name, company, email, satisfaction, part, howto, agree);
			 
				int cnt = dao.updateSurveyBean(sb);
				System.out.println("update cnt : "+ cnt);

				
				 viewPage = "/list.sv";
			 
		 }
		 
		  
		  else if(command.equals("/delete.sv")) { 
			  System.out.println("delete 요청"); 
			  
			  int no = Integer.parseInt(request.getParameter("no"));
			  System.out.println("delete.sv no : " + no);
			  
			  int cnt = dao.deleteSurvey(no);
			  System.out.println("delete cnt : "+ cnt);
			  
			  viewPage = "/list.sv";
		  }
		 
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
		
	} //doProcess
	

}
