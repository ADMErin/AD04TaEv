package Servicios;

import java.util.Date;
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

public class ReservaServ {
	
	public static void crearReserva(Scanner leerTeclado) {
		Date fecha = new Date();
		System.out.print("Introduce el número de plazas: ");
		int plazas = leerTeclado.nextInt();
		System.out.print("Introduce el Id del viaje: ");
		int viajeId = leerTeclado.nextInt();
		System.out.print("Introduce el Id del pasajero: ");
		int pasajeroId = leerTeclado.nextInt();
		
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
        	
        	Pasajero pasajero= session.get(Pasajero.class, pasajeroId);
        	System.out.println(pasajero);
        	
        	Viaje viaje = session.get(Viaje.class, viajeId);
        	System.out.println(pasajero);
        	
        	Reserva nuevaReserva = new Reserva (fecha, plazas, pasajero, viaje );
        	
        	session.beginTransaction();

        	session.persist(nuevaReserva);
        	
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
	public static void borrarReserva(Scanner leerTeclado) {
		Date fecha = new Date();
		System.out.print("Introduce el ID de la reserva: ");
		int reservaId = leerTeclado.nextInt();
				
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
        	
        	Reserva estaReserva= session.get(Reserva.class, reservaId);
        	System.out.println(estaReserva);
        	
        	
        	session.beginTransaction();

        	session.delete(estaReserva);
        	
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
