import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeServlet extends HttpServlet {


	
	public LifeServlet() {

		System.out.println("LifeServlet.LifeServlet()");
	}
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("LifeServlet.init()");
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LifeServlet.service()");
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("LifeServlet.destroy()"); 
	}
		 /*public LifeServlet() {
		       System.out.println("构造方法");
		    }
		    
		    @Override
		    public void init(ServletConfig config) throws ServletException {
		       super.init(config);
		       System.out.println("init()");
		    }
		    
		    @Override
		    protected void service(HttpServletRequest req, HttpServletResponse resp)
		           throws ServletException, IOException {
		       System.out.println("service()");
		    }
		    
		    @Override
		    public void destroy() {
		       super.destroy();
		       System.out.println("destory()");
		    }*/

	
}
