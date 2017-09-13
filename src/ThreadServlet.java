import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThreadServlet extends HttpServlet{

private int money = 1000;
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       synchronized (this) {
           //模拟取钱
           if (money >= 1000) {
               //网络延迟
               try {
                  Thread.sleep(8000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               money = money - 1000;
           }
           //显示数据
           resp.setContentType("text/html;charset=utf-8");
           PrintWriter printWriter = resp.getWriter();
           printWriter.println("剩余： " + money);
           
       }
    }

}
