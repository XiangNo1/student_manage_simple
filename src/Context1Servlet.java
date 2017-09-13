import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

public class Context1Servlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*ServletContext servletContext = getServletContext();
		servletContext.setAttribute("name", "zhangsan");
	       IStudentService service = new StudentServiceImpl();
	       List<Student> list = service.findAll();
	       // 将list集合设置到ServletContext域中
	       servletContext.setAttribute("list", list);*/
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("name", "zhangsan");
		IStudentService iStudentService = new StudentServiceImpl();
		List<Student> list = iStudentService.findAll();
		servletContext.setAttribute("list", list);
	}
}
