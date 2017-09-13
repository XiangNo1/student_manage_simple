import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitCookie extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		String getLastDate = null;
		if(cookies != null){
			for (Cookie cookie : cookies) {
				getLastDate = cookie.getValue();
			}
		}
		if (cookies != null) {
			System.out.println("上次访问的时间为： " + getLastDate);
		}else {
			System.out.println("您是第一次访问！么么哒！！");
		}
		
		
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String lastDate = simpleDateFormat.format(date);
		Cookie cookie = new Cookie("lastDate", lastDate);
		cookie.setMaxAge(60);
		resp.addCookie(cookie);
	}
}
