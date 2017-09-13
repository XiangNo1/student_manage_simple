import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.situ.student.util.JdbcUtil;

public class InitServlet extends HttpServlet {

	
	@Override
	public void init() throws ServletException {
		super.init();
		
		Integer count = 0;
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("count", count);
		JdbcUtil.init(servletContext);
	}


}
