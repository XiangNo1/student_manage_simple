import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class FileServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp)
		       throws ServletException, IOException {
		/*ServletContext servletContext = getServletContext();
		    String aPath = servletContext.getRealPath("/WEB-INF/classes/a.txt");
		    System.out.println("aPath: " + aPath);
		    String bPath = servletContext.getRealPath("/WEB-INF/b.txt");
		    System.out.println("bPath: " + bPath);
		    String cPath = servletContext.getRealPath("/db.properties");
		    System.out.println("cPath: " + cPath);
		    InputStream inputStream = servletContext.getResourceAsStream("/db.properties");
		    String dbPath = servletContext.getRealPath("/db.properties");
		    System.out.println("cPath: " + dbPath);
		    Properties properties = new Properties();
		    properties.load(inputStream);
		    String url = properties.getProperty("url");
		    System.out.println(url);
		    */
		ServletContext servletContext = getServletContext();
		String aPath = servletContext.getRealPath("/WEB-INF/classes/a.txt");
		System.out.println("aPath" + aPath);
		String bPath = servletContext.getRealPath("/WEB-INF/b.txt");
		System.out.println("bPath" + bPath);
		String dbPath = servletContext.getRealPath("/db.properties");
		Properties properties = new Properties();
		InputStream inputStream = servletContext.getResourceAsStream("/db.properties");
		properties.load(inputStream);
		String url = properties.getProperty("url");
		System.out.println(url);
		    

		}

}
