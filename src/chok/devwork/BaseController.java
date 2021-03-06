package chok.devwork;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.gson.Gson;

public class BaseController<T>
{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected MyReq req;
	protected Result result;
	private PrintWriter out;
	protected static Logger log = LoggerFactory.getLogger(BaseController.class.getName());
	
	@ModelAttribute
	public void BaseInitialization(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.response.setCharacterEncoding("UTF-8");
		this.session = request.getSession();
		this.req = new MyReq(request);
		this.result = new Result();
	}
	
	public void put(String key, Object value)
	{
		request.setAttribute(key, value);
	}
	
	public void print(Object o)
	{
		try
		{
			if(out == null)
			{
				out = this.response.getWriter();
			}
			out.print(o);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void printJson(Object o)
	{
		response.setContentType("application/json");
		try
		{
			if(out == null)
			{
				out = this.response.getWriter();
			}
			out.print(new Gson().toJson(o));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
