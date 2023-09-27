public class Juego {
	Interfaz interfaz = new Interfaz();
	int opcion;
	boolean turno = true;

	public void jugador(Mazo mano, Mazo basuraParametro, Mazo pila) { // creo que esto deberia ir en una clase jugador
		Mazo manoValida = new Mazo(); // al declararlo dentro de cada uno no se comparte la memoria
		turno = true;
		opcion = 0;
		do {
			manoValida.recibirCarta(mano.getCartaValida(basuraParametro.getUltimaCarta()));// Arreglo de cartas validas
			if (manoValida.getMazo().length > 0) {
				try { // no recibimos inputs de texto del usuario entonces no se si sea necesario, preguntar

					opcion = interfaz.botarRecoger(manoValida, "", basuraParametro); // puedo jugar para selecionar
					for (int i = 0; i < mano.getMazo().length; i++) {
						if (mano.getMazo()[i] == manoValida.getMazo()[opcion]) { // hice que la interfaz solo nos
																					// muestre las cartas validas para
																					// que sea mas facil y a menor error
																					// para el usuario
							opcion = i; // el For nos sirve para encontrar la posicion de la carta valida en la mano
						}
					}
				} catch (Exception e) {
					System.out.println();
				}
				basuraParametro.recibirCarta(mano.darCarta(1, opcion)); // aqui tiramos la carta a la basuraParametro
				turno = false; // el turno cambia a 2 para que la siguiente persona siga jugando
			} else {
				mano.recibirCarta(pila.darCarta(1, 999));// en el caso de que la persona no tenga cartas validas para
															// jugar se le otorga una directamente hasta que pueda jugar
			}
			// System.out.println(mano);// prueba solo para ver si si se tira la carta
			// correcta
		} while (turno == true);


	}

	public void computadora(Mazo mano, Mazo basuraParametro, Mazo pila) {
		Mazo manoValida = new Mazo(); // ?
		turno = true;
		opcion = 0;
		do {
			manoValida.recibirCarta(mano.getCartaValida(basuraParametro.getUltimaCarta()));

			if (manoValida.getMazo().length > 0) {
				opcion = (int) (Math.random() * manoValida.getMazo().length);
				for (int i = 0; i < mano.getMazo().length; i++) {
					if (mano.getMazo()[i] == manoValida.getMazo()[opcion]) {
						opcion = i;
					}
				}

				basuraParametro.recibirCarta(mano.darCarta(1, 1));
				turno = false;
			} else {
				mano.recibirCarta(pila.darCarta(1, 999));
			}
		} while (turno == true);

	}

}