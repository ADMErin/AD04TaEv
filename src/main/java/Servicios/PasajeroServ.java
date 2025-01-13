package Servicios;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Conductor;
import entidades.Pasajero;
import entidades.Reserva;
import entidades.Viaje;

public class PasajeroServ {
	

	public static void crearPasajero(Scanner leerTeclado) {
		
		System.out.print("Introduce el nombre del pasajero: ");
		String nombre = leerTeclado.nextLine();
		System.out.print("Introduce el email: ");
		String email = leerTeclado.nextLine();
		
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("Hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(Pasajero.class)
                .addAnnotatedClass(Reserva.class)
                .addAnnotatedClass(Viaje.class)
                .addAnnotatedClass(Conductor.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();    
        
        Session session = sessionFactory.openSession();
        
        try {
        	Pasajero nuevoConductor = new Pasajero (nombre, email);
        	
        	session.beginTransaction();

        	session.persist(nuevoConductor);
        	
        	session.getTransaction().commit();

        }
        catch(Exception e) {
        	System.out.println("Realizado Rollback");
        	session.getTransaction().rollback();
        	e.printStackTrace();
        }
        finally {
        	session.close();
        	sessionFactory.close();
        }
	}
}

