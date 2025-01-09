package eus.birt.adavila.main;

import java.util.Scanner;

import Servicios.PasajeroServ;
import Servicios.ConductorServ;


public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leerTeclado = new Scanner(System.in);
		
		int opcion = 0;
		do {
			opcion = opcionMenu(leerTeclado);
			switch(opcion) {
				case 1:{
					System.out.println("Has elegido Crear conductor");
					ConductorServ.crearConductor(leerTeclado);
					break;
				}
				case 2:{
					System.out.println("Has elegido Crear viaje");
					break;
				}
				case 3:{
					System.out.println("Has elegido Buscar viajes");
					break;
				}
				case 4:{
					System.out.println("Has elegido Crear pasajero");
					PasajeroServ.crearPasajero(leerTeclado);
					break;
				}
				case 5:{
					System.out.println("Has elegido Crear reserva");
					break;
				}
				case 6:{
					System.out.println("Has elegido Cancelar reserva");
					break;
				}
				case 7:{
					System.out.println("Has elegido Listar viajes");
					break;
				}
				case 8:{
					System.out.println("Has elegido Salir de la aplicación");
					break;
				}
			}
			
		} while (opcion != 8);
	}
	public static int opcionMenu(Scanner leerTeclado) {
		System.out.println("=== Menú de Gestion de Viajes Compartidos ===");
		System.out.println("1. Crear conductor\n2. Crear viaje\n3. Buscar viajes disponibles\n4. Crear pasajero\n5. Crear reserva\n6. Cancelar reserva\n7. Listar viajes\n8. salir");
		System.out.print("Introduce el número de tu opción: ");
		int opcion = leerTeclado.nextInt();
		leerTeclado.nextLine();
		return opcion;
	}

}
