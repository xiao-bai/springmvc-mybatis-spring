package xiaobai.mybatis.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

@Controller
@RequestMapping("/fileController")
public class FileController {
	
	private static final int BUFFER_SIZE = 16 * 1024 ;
	/**
	 * http://localhost:8080/springmvc-mybatis-spring/authorityController/
	 * getAuthorityById/1.do
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request,
			HttpServletResponse response, BindException errors) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		String realFileName = file.getOriginalFilename();
		System.out.println(realFileName);
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")+"\\resources\\upload-file";
		
		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		File uploadFile = new File(ctxPath , realFileName);
		try {
			FileCopyUtils.copy(file.getBytes(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Parser parser =  new AutoDetectParser();
		InputStream is = null;
		try {
			Metadata metadata = new Metadata();
			is = new FileInputStream(uploadFile);
			ContentHandler handler = new BodyContentHandler();
			ParseContext context = new ParseContext();
			context.set(Parser.class,parser);
			parser.parse(is,handler, metadata,context);
			//输出文件的信息，包括作者
			for(String name:metadata.names()) {
				System.out.println(name+":"+metadata.get(name));
			}
			System.out.println("------------------------");
			System.out.println(handler.toString());
			//return handler.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}finally {
			try {
				if(is!=null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return "upload-success";
	}
	
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET) 
	public String download(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {  
		
		response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
		
		String downLoadPath = ctxPath + "\\resources\\upload-file\\action1.png";
		String realName ="action1.png";
		long fileLength = new File(downLoadPath).length();
		
		//response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "utf-8"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		OutputStream out  = response.getOutputStream();
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
        bos = new BufferedOutputStream(out); 
        out.close();
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bos.flush();
        bis.close();  
        bos.close();
        
        return null;
       
	}
	
	

}
