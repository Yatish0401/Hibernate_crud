package org.jsp.userproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Product saveproduct(Product product, int user_id) {
		User u = manager.find(User.class, user_id);
		if(u!= null)
		{
			u.getProduct().add(product); // adding product to user
			product.setUser(u);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

	public Product findProductsByUserId(int id) {
		Query q = manager.createQuery("select u.product from User u where u.id=?1");
		q.setParameter(1, id);
		List<Product> pro = q.getResultList();
		for(Product p:pro) {
			System.out.println(p);
		}
		return null;
	}

	public Product viewproductBycategory(String category) {
		String qry = "select p from Product p where p.category =?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, category);
		try {
			return (Product)q.getSingleResult();
		}
		catch(NoResultException e)
		{
		return null;
	}
	
	}

	public Product viewproductByBrand(String Brand) {
		String qry = "select p from Product p where p.brand =?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, Brand);
		try {
			return (Product)q.getSingleResult();
		}
		catch(NoResultException e)
		{
		return null;
	}
}

	public Product findProduct(int id) {
		return manager.find(Product.class, id);
	}

	
	public boolean deleteProduct(int id) {
		Product  p = findProduct(id);
		if(p!= null)
		{
			manager.remove(p);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return true;
	
		}
		return false;
	}


	public Product UpdateProduct(Product product) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(product);
		transaction.begin();
		transaction.commit();
		return product;
	}


	
}
