package fr.iut2.server;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: UploadFileServlet
 */
public class UploadFileServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 8305367618713715640L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		FileItem uploadItem = getFileItem(request);
		if (uploadItem == null) {
			response.getWriter().write("NO-SCRIPT-DATA");
			return;
		}

		try {
                       // System.out.println("context : " + this.getServletContext().getRealPath("images/" + uploadItem.getName()));
			File saveTo = new File(this.getServletContext().getRealPath("images/" + uploadItem.getName()));
			response.getWriter().write("images/" + uploadItem.getName());
			uploadItem.write(saveTo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FileItem getFileItem(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			@SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);
			@SuppressWarnings("rawtypes")
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (!item.isFormField() && "uploadFormElement".equals(item.getFieldName())) {
					return item;
				}
			}
		} catch (FileUploadException e) {
			return null;
		}
		return null;
	}
}