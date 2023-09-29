public class Juego { // ! Clase juego almacena variables y metodos necesarios para el funcionamiento
						// de juego
	Interfaz interfaz = new Interfaz(); // ! Necesario para pedir datos del usuario
	int cartasComer = 0; // ! Almacen de cartas a comer cuando se invoca una carta especial

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

	public void setCartasComer() {
		cartasComer = 0;
	}

}