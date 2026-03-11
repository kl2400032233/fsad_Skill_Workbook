package com.skill.hibernate_crud;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.skill.entity.Product;
import com.skill.util.HibernateUtil;

public class App {

 static Scanner sc = new Scanner(System.in);

 public static void main(String[] args) {

  while (true) {
   System.out.println("\n1.Insert  2.View  3.Update  4.Delete  5.Exit");
   int ch = sc.nextInt();

   switch (ch) {
   case 1: insert(); break;
   case 2: view(); break;
   case 3: update(); break;
   case 4: delete(); break;
   case 5: System.exit(0);
   default: System.out.println("Invalid choice");
   }
  }
 }

 public static void insert() {

	    Scanner sc = new Scanner(System.in);

	    System.out.print("Name: ");
	    String name = sc.nextLine();

	    System.out.print("Description: ");
	    String desc = sc.nextLine();

	    System.out.print("Price: ");
	    double price = sc.nextDouble();

	    System.out.print("Quantity: ");
	    int qty = sc.nextInt();

	    Product p = new Product();
	    p.setName(name);
	    p.setDescription(desc);
	    p.setPrice(price);
	    p.setQuantity(qty);

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    session.save(p);
	    tx.commit();

	    System.out.println("Inserted Successfully!");
	    System.out.println("Generated Product ID is: " + p.getId()); // ⭐ IMPORTANT

	    session.close();
	}

   // ⭐ IMPORTANT

 public static void view() {

     Session session = HibernateUtil.getSessionFactory().openSession();

     List<Product> list = session
             .createQuery("from Product", Product.class)
             .list();

     if (list.isEmpty()) {
         System.out.println("No products found!");
     } else {
         System.out.println("ID | Name | Price | Quantity");
         for (Product p : list) {
             System.out.println(
                 p.getId() + " | " +
                 p.getName() + " | " +
                 p.getPrice() + " | " +
                 p.getQuantity()
             );
         }
     }

     session.close();
     sc.close();
 }


 public static void update() {

	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter Product ID to Update: ");
	    int id = sc.nextInt();

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        System.out.print("Enter New Price: ");
	        double price = sc.nextDouble();

	        System.out.print("Enter New Quantity: ");
	        int qty = sc.nextInt();

	        p.setPrice(price);
	        p.setQuantity(qty);

	        session.update(p);
	        tx.commit();

	        System.out.println("Product Updated Successfully!");
	    } else {
	        System.out.println("Product not found!");
	        tx.rollback();
	    }

	    session.close();
	}


 public static void delete() {

	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter Product ID to Delete: ");
	    int id = sc.nextInt();

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        session.delete(p);
	        tx.commit();
	        System.out.println("Product Deleted Successfully!");
	    } else {
	        System.out.println("Product not found!");
	        tx.rollback();
	    }

	    session.close();
	    sc.close();
	}
}