package net.codejava.hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BooksManager {

	protected SessionFactory sessionFactory;
	 
    protected void setup() {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() 
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch(HibernateException exception){
    	     System.out.println("Problem creating session factory");
    	     exception.printStackTrace();
    	}
    }
 
    protected void exit() {
    	sessionFactory.close();

    }
    protected void create() {
    	Book book = new Book();
        book.setTitle("Spring Boot");
        book.setAuthor("Rod Johnson");
        book.setPrice(45.22f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(book);
     
        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
    	Session session = sessionFactory.openSession();
    	 
        long bookId = 2;
        Book book = session.get(Book.class, bookId);
     
        if (book !=null) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());
        }else {
        	System.out.println("Book could not be found");
        }
     
        session.close();
    }
    
    protected void update() {
    	Book book = new Book();
        book.setId(3);
        book.setTitle("Pyhton");
        book.setAuthor("Guido van Rossum");
        book.setPrice(20.23f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.update(book);
     
        session.getTransaction().commit();
        session.close();
            }
    
    protected void delete() {
    	Book book = new Book();
        book.setId(1);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(book);
     
        session.getTransaction().commit();
        session.close();
    }

        public static void main(String[] args) {
    	BooksManager manager = new BooksManager();
        manager.setup();
        
        manager.update();
        
        manager.exit();
    }
}

