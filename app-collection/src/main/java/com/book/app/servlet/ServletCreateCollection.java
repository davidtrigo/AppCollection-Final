package com.book.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.AppServices;

import entities.Collection;
import entities.User;


@MultipartConfig
public class ServletCreateCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	/* referencia por inyección */
	@EJB
	private  AppServices service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
          
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		Collection collection = HttpHelper.getParametesCollection(request); 		   
		checkParameters(); 
		
		
		
		
		
		try{
			
			service.addCollection(user.getId(), collection);

	    }catch(EJBException e){
	    	
	    	//TODO control error
	    	
	    	return; 
	    }
		
		
		   
		HttpHelper.saveSessionUser(request, user);
		response.sendRedirect("/ServletHome");
		
	}
	


	private void checkParameters() {
		// TODO Auto-generated method stub
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	

	
	
	private static byte[] inputStreamToByte(InputStream is){
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			
			buffer.flush();
		} catch (IOException e) {
			
		}

		return buffer.toByteArray();
	}
	
	


}