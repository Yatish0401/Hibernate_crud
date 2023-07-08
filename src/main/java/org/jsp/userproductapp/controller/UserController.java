package org.jsp.userproductapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproductapp.dao.ProductDao;
import org.jsp.userproductapp.dao.UserDao;
import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;

public class UserController {
	 static Scanner sc  = new Scanner(System.in);
	 static ProductDao pDao = new ProductDao();
	 static UserDao uDao = new UserDao();
	 
	 public static void main(String[] args) {
		System.out.println("1. Regesiter");
		System.out.println("2. Update");
		System.out.println("3. Find");
		System.out.println("4. Delete");
		System.out.println("5. Verify user by phone and password");
		System.out.println("6. verify user by email and paasword");
		System.out.println("7. Add Product");
		System.out.println("8. Vies product by user id");
		System.out.println("9. view product by category");
		System.out.println("10.view product by brand");
		System.out.println("11. Find product");
		System.out.println("12. update product");
		System.out.println("13. delete product");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1:{
			save();
			break;
		}
		case 2:{
			update();
			break;
		}
		case 3:{
			find();
			break;
		}
		case 4:{
			delete();
			break;
		}
		case 5:{
			FindUserByPhoneAndPassword();
			break;
		}
		 
		case 6:{
			FindUserByEmailAndPassword();
			break;
		}
		case 7:{
			addProduct();
			break;
		}
		case 8:{
			ViewProductByUserId();
			break;
		}
		case 9:{
			ViewProductByCategory();
			break;
		}
		case 10:{
			viewProductByBrand();
			break;
		}
		case 11:{
			FindProduct();
			break;
		}
		case 12:{
			updateProduct();
		}
		case 13:{
			deleteProduct();
		}
		}
	}

	

	public static void save() { 
		System.out.println("enter the name , email , phone , and password to register");
		String name  = sc.next();
		String email = sc.next();
		long phone = sc.nextLong();
		String password = sc.next();
		 
	     User u = new User();
	     u.setName(name);
	     u.setEmail(email);
	     u.setPhone(phone);
	     u.setPassword(password);
	     u = uDao.saveUser(u);
	     System.out.println(" you are registerd with id"+ u.getId());
	     
		
	}

	public static void update() {
		System.out.println("enter the id ");
		int id = sc.nextInt();
		System.out.println("enter the name, email , phone and password to register");
		String name  = sc.next();
		String email = sc.next();
		long phone = sc.nextLong();
		String password = sc.next();
		 
	     User u = new User();
	     u.setId(id);
	     u.setName(name);
	     u.setEmail(email);
	     u.setPassword(password);
	     u.setPhone(phone);
	     u = uDao.updateUser(u);
	     System.out.println("your account is update");
		
	}


	public static void find() {
		System.out.println("enter your id");
		int id = sc.nextInt();
		User u = new User();
		u = uDao.findUserById(id);
		System.out.println(u.getName());
		System.out.println(u.getEmail());
		System.out.println(u.getPassword());
		System.out.println(u.getPhone());
	}	

	public static void delete() {
		System.out.println("enter the id to delete the details");
		int id = sc.nextInt();
		User u = new User();
	     uDao.DeleteUser(id);
		System.out.println("you delete details of this id");
		
		
	}

	private static void FindUserByPhoneAndPassword() {
		System.out.println("enter your phone and password fetch details of user");
		long phone = sc.nextLong();
		String  password   = sc.next();
		User u = new User();
		u = uDao.findUserByPhoneAndPassword(phone , password);
		System.out.println(u);
		
	}


	private static void FindUserByEmailAndPassword() {
		System.out.println(" enter your email and password fetch details of user");
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u = uDao.FindUserByEmailAndPassword(email ,password);
		System.out.println(u);
	}



	private static void addProduct() {
		System.out.println("enter your id to add the product");
		int uid = sc.nextInt();
		System.out.println("enter your name , brand , category and price ");
		String name  = sc.next();
		String brand  = sc.next();
		String category = sc.next();
		double price = sc.nextDouble();
		
		Product p  = new Product();
		p.setName(name);
		p.setBrand(brand);
		p.setCategory(category);
		p.setPrice(price);
		p = pDao.saveproduct(p,uid);
		if(p!= null)
		{
			System.out.println("product added to the cart with id:"+p.getId());
		}
		else {
			System.out.println("User with the enterd id not persent");
		}
		
	}

	private static void ViewProductByUserId() {
	 System.out.println("enter your user id and view product");
	 int id = sc.nextInt();
	Product p = new Product();
	p = pDao.findProductsByUserId(id);
      
	}

	private static void ViewProductByCategory() {
      System.out.println("enter your category and view details");
      String category =sc.next();
      Product p = new Product();
      p = pDao.viewproductBycategory(category);
      System.out.println(p);
	}


	private static void viewProductByBrand() {
		System.out.println("enter your Brand and view details");
		String Brand = sc.next();
		Product p = new Product();
		p = pDao.viewproductByBrand(Brand);
		System.out.println(p);
		
	}

	private static void FindProduct() {
		System.out.println("enter your id");
		int id = sc.nextInt();
		Product p = new Product();
		p = pDao.findProduct(id);
		System.out.println(p.getName());
		System.out.println(p.getPrice());
		System.out.println(p.getCategory());
		System.out.println(p.getBrand());
		
	}
	private static void updateProduct() {
		System.out.println(" enter the id and update the details");
		int id = sc.nextInt();
		System.out.println(" enter the name , brand , price, category");
		String name = sc.next();
		String brand = sc.next();
		double price = sc.nextDouble();
		String category = sc.next();
		
		Product p = new Product();
		p.setId(id);
		p.setName(name);
		p.setBrand(brand);
		p.setPrice(price);
		p.setCategory(category);
		p = pDao.UpdateProduct(p);
		System.out.println(" your product is update");
	}


	private static void deleteProduct() {
		System.out.println("enter the id delete the details of product");
		int id= sc.nextInt();
		Product p = new Product();
		pDao.deleteProduct(id);
		System.out.println(" your product has been deleted");
	}



	

}
