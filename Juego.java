public class Juego { // ! Clase juego almacena variables y metodos necesarios para el funcionamiento
						// de juego
	Interfaz interfaz = new Interfaz(); // ! Necesario para pedir datos del usuario
	int cartasComer = 0; // ! Almacen de cartas a comer cuando se invoca una carta especial
	boolean activo = true;
	boolean nuevaPartida = false;

	public boolean getNuevaPartida(){
		return nuevaPartida;
	}

	public boolean getEstado(){
		return activo;
	}

	public void setNuevaPartida(boolean estado){
		nuevaPartida = estado;
	}


	public boolean finJuego(Mazo mano1Parametro, Mazo mano2Parametro, Mazo pila, Mazo basura) {
		boolean activo = true;
		if (mano1Parametro.getTamanio() < 1) {
			System.out.println("Jugador 1 gana");
			activo = false;
		} else if (mano2Parametro.getTamanio() < 1) {
			System.out.println("Jugador 2 gana");
			activo = false;
		} else if (pila.getTamanio() == 0) {
			System.out.println("No hay cartas en la pila");
			activo = false;

			if (mano1Parametro.getTamanio() == mano2Parametro.getTamanio()) {
				System.out.println("Ambos jugadores tienen el mismo numero de cartas. Es un empate");

			} else if (mano1Parametro.getTamanio() < mano2Parametro.getTamanio()) {
				System.out.println("Jugador 1 tiene menos cartas. Jugador 1 gana");

			} else {
				System.out.println("Jugador 2 tiene menos cartas. Jugador 2 gana");
			}

		}
		if (activo == false){
			nuevaPartida = interfaz.nuevaPartida();
		}


		return activo;
	}

	public int sumarCartasComer(int id) {
		if (id == 9) {
			cartasComer += 2;
		} else if (id == 10) {
			cartasComer += 3;
		} else if (id == 11) {
			cartasComer = 0;
		}

		return cartasComer;

	}

	public void resetCartasComer() {
		cartasComer = 0;
	}

	public void jugarContraHumano(Mazo basura, Mazo mano1, Mazo mano2, Mazo pila, Jugador jugador1, Jugador jugador2, Juego juego){
		boolean cancelar = false;
		int ultimaCarta;

		do {
			boolean cartaEspecialActiva = false;
			int comerEspecial = 0;
			System.out.println("Ultima Carta Jugada------------");
			System.out.println(basura.getUltimaCarta());
			// TODO: HACE FALTA UN COMMENT AQUI
			cancelar = basura.getUltimaCarta().getId() == 11 && juego.cartasComer == 0
					&& (basura.getMazo()[(basura.getMazo().length - 2)].getId() != 10
							&& basura.getMazo()[(basura.getMazo().length - 2)].getId() != 9);

			if (cancelar) {
				System.out.println(jugador1.getNombre() + " tu turno fue cancelado");
				ultimaCarta = basura.getMazo().length - 2;
				basura.recibirCarta(basura.darCarta(1, ultimaCarta));
				cancelar = false;
			} else {
				jugador1.jugar(jugador1.getNombre(), mano1, basura, pila, cartaEspecialActiva, comerEspecial, juego,
						mano2, jugador2.getNombre());
			}

			System.out.println("Ultima Carta Jugada------------");
			System.out.println(basura.getUltimaCarta());
			
			cancelar = basura.getUltimaCarta().getId() == 11 && juego.cartasComer == 0;

			if (cancelar) {
				System.out.println(jugador2.getNombre()+" tu turno fue cancelado");
				ultimaCarta = basura.getMazo().length - 2;
				basura.recibirCarta(basura.darCarta(1, ultimaCarta));
				cancelar = false;
			} else {
				jugador2.jugar(jugador2.getNombre(), mano2, basura, pila, cartaEspecialActiva, comerEspecial, juego,
						mano1,jugador1.getNombre());
			}

			activo = juego.finJuego(mano1, mano2, pila, basura);
		} while (activo == true); // si una de las dos manos se hace cero ya termina

	}

	public void jugarContraComputadora(Mazo basura, Mazo mano1, Mazo mano2, Mazo pila, Jugador jugador1, Jugador jugador2, Juego juego){
		boolean cancelar = false;
		int ultimaCarta;

		do {
			boolean cartaEspecialActiva = false;
			int comerEspecial = 0;
			System.out.println("Ultima Carta Jugada------------");
			System.out.println(basura.getUltimaCarta());
			cancelar = basura.getUltimaCarta().getId() == 11 && juego.cartasComer == 0
					&& (basura.getMazo()[(basura.getMazo().length - 2)].getId() != 10
							&& basura.getMazo()[(basura.getMazo().length - 2)].getId() != 9);
			if (cancelar) {
				System.out.println(jugador1.getNombre()+" tu turno fue cancelado");
				ultimaCarta = basura.getMazo().length - 2;
				basura.recibirCarta(basura.darCarta(1, ultimaCarta));
				cancelar = false;
			} else {
				jugador1.jugar(jugador1.getNombre() ,mano1, basura, pila, cartaEspecialActiva, comerEspecial, juego,
						mano2, "Computadora");
			}
			System.out.println("Ultima Carta Jugada------------");
			System.out.println(basura.getUltimaCarta());
			cancelar = basura.getUltimaCarta().getId() == 11 && juego.cartasComer == 0;
			if (cancelar) {
				System.out.println("Computadora tu turno fue cancelado");
				ultimaCarta = basura.getMazo().length - 2;
				basura.recibirCarta(basura.darCarta(1, ultimaCarta));
				cancelar = false;
			} else {
				jugador2.jugarComputadora("Computadora", mano2, basura, pila, cartaEspecialActiva,
						comerEspecial, juego);
			}

			activo = juego.finJuego(mano1, mano2, pila, basura);

	} while (activo == true); // si una de las dos manos se hace cero ya termina

	}


						
}