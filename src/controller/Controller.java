package controller;

import java.io.IOException;
import java.util.Scanner;
import model.logic.Modelo;
import view.View;

public class Controller<T> {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
	}

	private void cargaDatos(){
		view.printMessage("--------- \nCargar datos");
		modelo = new Modelo(1);
		try
		{
			modelo.cargar();
		} catch (IOException e) {

			e.printStackTrace();
		}
		view.printModelo(modelo);
	}

	private void connectedComponents(Scanner lector){
		view.printMessage("--------- \nIngrese el nombre del primer punto de conexión");
		String punto1= lector.next();
		lector.nextLine();

		view.printMessage("--------- \nIngrese el nombre del segundo punto de conexión");
		String punto2= lector.next();
		lector.nextLine();

		String res1=modelo.req1String(punto1, punto2);
		view.printMessage(res1);
	}

	private void interconnectionLandings(){
		String res2= modelo.req2String();
		view.printMessage(res2);
	}

	private void minRoute(Scanner lector){
		view.printMessage("--------- \nIngrese el nombre del primer país");
		String pais1= lector.next();
		lector.nextLine();

		view.printMessage("--------- \nIngrese el nombre del segundo país");
		String pais2= lector.next();
		lector.nextLine();

		String res3= modelo.req3String(pais1, pais2);
		view.printMessage(res3);
	}
	private void minExpansion(){
		String res4= modelo.req4String();
		view.printMessage(res4);
	}

	private void conexionErrors(Scanner lector){
		view.printMessage("--------- \nIngrese el nombre del punto de conexión");
		String landing= lector.next();
		lector.nextLine();
		String res5= modelo.req5String(landing);
		view.printMessage(res5);
	}

	private void exit(){
		view.printMessage("--------- \n Hasta pronto !! \n---------");
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in).useDelimiter("\n");
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				cargaDatos();
				break;	
			case 2:
				connectedComponents(lector);
				break;	
			case 3:
				interconnectionLandings();
				break;
			case 4:
				minRoute(lector);
				break;
			case 5:
				minExpansion();
				break;
			case 6:
				conexionErrors(lector);
				break;
			case 7:
				exit();
				lector.close();
				fin = true;
				break;
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
