import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       //1、创建磁盘文件项工厂 DiskFileItemFactory：--一些相关的配置的设置 : 缓存的大小 ,临时目录的位置
       DiskFileItemFactory factory = new DiskFileItemFactory();
       //1M=1024KB  1KB=1024B
       factory.setSizeThreshold(1024 * 1024);
       String tempPath = getServletContext().getRealPath("temp");
       factory.setRepository(new File(tempPath));
       
       //2、ServletFileUplaod：文件上传的一个核心类
       ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
       //设置上传的文件名的编码方式
        servletFileUpload.setHeaderEncoding("UTF-8");
       
       //判断这个表单是文件上传的表单
       if (servletFileUpload.isMultipartContent(req)) {
            //通过request获取请求体的内容。
            //根据分割符请请求体的文本内容分割成多个数组，数组中每一部分是一个表单项。
           List<FileItem> list = null;
           try {
               list = servletFileUpload.parseRequest(req);
           } catch (FileUploadException e) {
               e.printStackTrace();
           }
           
           if (list != null) {
               for (FileItem fileItem : list) {
                  //判断是不是普通表单项
                  if (fileItem.isFormField()) {
                      String fieldName = fileItem.getFieldName();
                      String fieldValue = fileItem.getString("utf-8");
                      System.out.println("fieldName: " + fieldName + ", fieldValue: " + fieldValue);
                  } else {
                      //文件上传项
                      //文件名字
                      String fileName = fileItem.getName();
                      //获得上传文件的内容
                      String uploadPath = getServletContext().getRealPath("upload");
                      InputStream inputStream = fileItem.getInputStream();
                      OutputStream outputStream = new FileOutputStream(uploadPath + "/" + fileName);
                      IOUtils.copy(inputStream, outputStream);
                      outputStream.close();
                      inputStream.close();
                      
                      //删除临时文件(删除临时文件（temp）下面的文件)
                      fileItem.delete();
                  }
               }
           }
       }
    }
}
