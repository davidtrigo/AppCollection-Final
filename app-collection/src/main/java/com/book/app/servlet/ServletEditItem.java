package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.AppServices;

import entities.Item;

 


public class ServletEditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String title,author, description, imageFile ;
	
	/* referencia por inyección */
	@EJB
	private AppServices service; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		   String option = request.getParameter("option");  
		   Item item=null; 	 
		   
		 if(option.equals("show")){			   
		   String itemId = request.getParameter("itemId");
		 
		item = service.find(Item.class, itemId);
	
		
	if(item==null){/*TODO */}
		   }else if(option.equals("edit")){
			   
			   if ((title!=null)&& (author!=null)){   
				 
					 
					 item.setTitle(title);
					 	item.setAuthor(author);
					 	
					 	if (description!=null){
					 		item.setDescription(description);
					 	}
					 	
					 	byte[] image=null;
					 	if (imageFile!=null){ 
					 		 image= imageFile.getBytes();		
					 	}
					 	service.updateItem(item, image);
			
		

			 
			   	response.sendRedirect("../servlet/book?option=list"); 
			   	return; 
		   }		     
		   
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(HttpHelper.getStyleTable()); 
	        out.println("<title>Catalogo de libros</title>");
	        out.println("</head>");
	        out.println("<body>");
	      
	        
        	out.println("<body>");
        		
			out.println("<h1>Editar Libro</h1>");
	              
			 out.println("<form action='../servlet/edit?option=edit' method='Post'>");
			 out.println("Titulo:<br>");
			 out.println(" <input type='text' value='"+item.getTitle()+"' name='title'>");
			 out.println(" <br>");
			 out.println(" Autor:<br>");
			 out.println(" <input type='text' value='"+item.getAuthor()+"' name='author'>");
		     out.println(" <br>");
			 out.println(" Descripcion:<br>");
			 out.println(" <input type='text' value='"+item.getDescription()+ "' name='description'>");
			 out.println(" <input type='hidden' value='"+item.getId()+ "' name='itemId'>");
			 out.println(" <br><br>");
			 out.println(" <input type='submit' value='Editar'>");
			 out.println("</form> ");
	        
	
	        out.println("<p>");    
	        	out.println("<a href=../index.html> Menu </a>");
	        out.println("<p>");
	        
	        out.println("</body>");
	        out.println("</html>");
	}
}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
	 
		 String title = request.getParameter("title");
		 String author = request.getParameter("name");
		 String description = request.getParameter("description");
		 String imageFile = request.getParameter("imagefile");
		 
		doGet(request, response);
	}	
}