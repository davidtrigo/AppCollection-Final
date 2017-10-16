package com.book.test;

import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.book.app.business.InfAppServices;
import com.book.test.tools.MockHelper;
import com.book.test.tools.TestEjbHelper;

import entities.Collection;
import entities.Item;
import entities.User;

public class ItemServicesTest {
	
	
	@Inject
	private InfAppServices service; 
 


   @Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 service.removeAll(User.class);	
    }
   
   
   
	@Test
	public void addItemTest(){
	
	User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
   	 	service.signUpUser(user); 
   	  
   	  Collection collection = MockHelper.mockCollection("Collection test"); 
   	  	service.addCollection(user.getId(),collection); 
		  
	Item item = MockHelper.mockItem("Author test", "item test", "Description test");  	
		service.addItem(collection.getId(), item, null);    	
		
		
    	  List<Item> list = service.getAll(Item.class);
    	  Assert.assertEquals(1, list.size());         
		
	}

}
