package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.AppServices;

import entities.Collection;
import entities.Item;


public class ServletItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String title,author, description, imageFile ;
	
	/* referencia por inyección */
	@EJB
	private AppServices service; 
	

	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		   String option = request.getParameter("option");  
		   	   
		   if(option.equals("insert")){			   		 
				
				 if ((title!=null)&& (author!=null)){   
					 Item item = new Item(); 
					 
					 item.setTitle(title);
					 	item.setAuthor(author);
					 	
					 	if (description!=null){
					 		item.setDescription(description);
					 	}
					 	
					 	byte[] image=null;
					 	if (imageFile!=null){ 
					 		 image= imageFile.getBytes();		
					 	}
					 	service.addItem(null, item, image);	
				 }
			 
			 return;
			 
			 
			   
		   }else if(option.equals("delete")){
			   String itemId = request.getParameter("itemId");
			   if(itemId!=null && !itemId.equals(""))
				   service.removeItem(itemId); 
			   
			  return;
		   } 
			 else{ //(option.equals("edit"))
				 
				 if ((title!=null)&& (author!=null)){   
					 Item item = new Item(); 
					 
					 item.setTitle(title);
					 	item.setAuthor(author);
					 	
					 	if (description!=null){
					 		item.setDescription(description);
					 	}
					 	
					 	byte[] image=null;
					 	if (imageFile!=null){ 
					 		 image= imageFile.getBytes();		
					 	}
					 	service.addItem(null, item, image);	
			
			return;

			 }
		   
		
		   
	   List<Item> list = service.getAll(Item.class);
		   
	   
	   response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println(HttpHelper.getStyleTable()); 
	        out.println("<title>Catalogo de libros</title>");
	        out.println("</head>");
	        out.println("<body>");
	       
	        
	        out.println("<h1>Catalogo de libros</h1>");
	        
	        out.println("<table>"); 
	        out.println("<tr><th>ID </th>"
	        		+ "<th>Titulo </th>"
	        		+ "<th>Autor </th>"
	        		+ "<th>Descripcion</th>"
	        		+ "<th>Editar</th>"
	        		+ "<th>Eliminar</th>"
	        		+ "</tr>");      
	        
//	      for (Collection item : list) {
//		        out.println("<tr>"); 
//		         out.println("<td> " +item.getId() +" </td>"); 
//		         out.println("<td> "+title+" </td>"); 
//		         out.println("<td> "+author+" </td>");
//		         out.println("<td> "+description+" </td>"); 
//		         out.println("<td> <a href=../servlet/edit?option=show&itemId="
//		         		 +      item.getId() + ">Editar</a>"
//		        		 +     "</td>"); 
//		         out.println("<td> <a href=../servlet/book?option=delete&itemId="
//		         		 +      item.getId() + ">Eliminar</a>"
//		        		 +     "</td>"); 
//		         
//		         
//		         
//		         
//		        out.println("</tr> "); 
//	        }
	        out.println("</table>"); 
	        
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