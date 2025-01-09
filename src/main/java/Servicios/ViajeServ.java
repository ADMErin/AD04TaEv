package Servicios;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


public class ViajeServ {
	
	public static void crearViaje(Scanner leerTeclado) {
		
		System.out.print("Introduce el destino: ");
		String destino = leerTeclado.nextLine();
		System.out.print("Introduce el origen: ");
		String origen = leerTeclado.nextLine();
		
		Date fecha = pedirFecha(leerTeclado);
		
		System.out.print("Introduce el número de plazas: ");
		int plazas = leerTeclado.nextInt();
		System.out.print("Introduce el ID del conductor: ");
		int conductorId = leerTeclado.nextInt();
		leerTeclado.nextLine();
		
		
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
        	Conductor conductor = session.get(Conductor.class, conductorId);
        	Viaje nuevoViaje = new Viaje (destino, origen, fecha, plazas, conductor);
        	
        	session.beginTransaction();

        	session.persist(nuevoViaje);
        	
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
	
	public static void listarViajes() {
		
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
        	session.beginTransaction();
        	
        	List<Viaje> viajes = session.createQuery("from Viaje").getResultList();
        	mostrarViajes(viajes);

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
	

	
	public static void buscarViaje(Scanner leerTeclado) {
		
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
        	
        	session.beginTransaction();
        	
        	List<Viaje> viajesConReserva = session.createNativeQuery("Select v.id, v.destino, v.origen, v.fechaHora, (v.plazas - reservas.numPlazasReservadas) plazas, v.conductor_id  from viaje v inner join reservas on reservas.viaje_id = v.id where v.id not in  (Select v.id from viaje as v inner join reservas on reservas.viaje_id = v.id where (v.plazas - reservas.numPlazasReservadas)=0) \r\n"
        			+ "", Viaje.class).getResultList();
        	
        	List<Viaje> viajesSinReserva = session.createNativeQuery("Select v.id, v.destino, v.origen, v.fechaHora, v.plazas, v.conductor_id  from viaje v where v.id not in  (Select v.id from viaje as v inner join reservas on reservas.viaje_id = v.id ) \r\n"
        			+ "", Viaje.class).getResultList();
        			 

        	mostrarViajes(viajesConReserva);
        	mostrarViajes(viajesSinReserva);

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
	
	public static Date pedirFecha(Scanner leerTeclado) {
		
		System.out.print("Introduce fecha y hora(YYYY-MM-DD hh:mm:ss): ");
		String fecha = leerTeclado.nextLine();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaDate = null;
		
		try {
			fechaDate = formato.parse(fecha);
		} catch(ParseException e) {
			e.printStackTrace();
			System.out.println("Formato de fecha incorrecto");
		}
		return fechaDate;
	}
	
	private static void mostrarViajes(List<Viaje> viajes) {
		for(Viaje tempViaje : viajes) {
			System.out.println(tempViaje);
		}
	}
}


