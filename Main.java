public class Main {
	public static void main(String args[]) {

		Mazo pila = new Mazo();
		Mazo mano1 = new Mazo();
		Mazo mano2 = new Mazo();
		Mazo basura = new Mazo();
		Interfaz interfaz = new Interfaz();
		Juego juego = new Juego();
		Jugador jugador1 = new Jugador("Juan");
		Jugador jugador2 = new Jugador("Pedro");
		int menu;
		//boolean cancelar = false;
		int ultimaCarta;

		pila.generarMazo();

		do {
			juego.setNuevaPartida(false);
			menu = interfaz.menu();


			pila.revolver();
			System.out.println("----------------");
			mano1.recibirCarta(pila.darCarta(5, 999)); // cuando se da una carta esta carta se quita del objeto que da la carta 999 significa que no importa el orden
			mano2.recibirCarta(pila.darCarta(5, 999)); // cuando se da manuna carta esta carta se quita del objeto que da la carta
			basura.recibirCarta(pila.darCarta(1, 999));// se activa la basura para saber con cual carta inicia el juego hacemos esto para no dar ventaja a un jugador
			System.out.println("mano1------------"); // for dev purpojugarDeNuevoBoolses
			System.out.println(mano1);

			System.out.println("mano2------------"); // for dev purposes
			System.out.println(mano2);

			System.out.println("basura------------"); // for dev purposes
			System.out.println(basura);

			if (basura.getUltimaCarta().getId() > 8) {
				basura.recibirCarta(pila.darCarta(1, 999));
			}

			switch (menu) {
				case 1:
					juego.jugarContraHumano(basura, mano1, mano2,pila, jugador1, jugador2, juego);
					break;
				case 2:
					juego.jugarContraHumano(basura, mano1, mano2,pila, jugador1, jugador2, juego);			
					break;
				default:
					break;
			}


			try { // para que esto?
				Thread.sleep(2 * 1000);
			} catch (Exception e) {
				System.out.println();
			}

		} while ((menu !=0 && juego.getEstado() == true) ||   juego.getNuevaPartida() ) ;




	}
}
