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
import entidades.Viaje;
import entidades.Reserva;

public class ConductorServ {
	

	public static void crearConductor(Scanner leerTeclado) {
		
		System.out.print("Introduce el nombre del conductor: ");
		String nombre = leerTeclado.nextLine();
		System.out.print("Introduce el modelo del vehiculo: ");
		String vehiculo = leerTeclado.nextLine();
		
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("Hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(Conductor.class)
                .addAnnotatedClass(Viaje.class)
                .addAnnotatedClass(Reserva.class)
                .addAnnotatedClass(Pasajero.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();    
        
        Session session = sessionFactory.openSession();
        
        try {
        	Conductor nuevoConductor = new Conductor (nombre, vehiculo);
        	
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
