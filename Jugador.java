public class Jugador { // al hacer una clase jugador podemos usar este mismo objeto para hacerlo contra dos jugadores
	// no cambia mucho los contenidos pero mas para una distincion de nomenclatura que tenga sentido

	String nombreJugador;
	boolean turno = true;

 	public Jugador (boolean turnoParametro, String nombreJugador){
   		//System.out.println("-> Se invoca al constructor que recibe el numero y el palo");
   		turno = turnoParametro;
   		nombreJugador = nombreJugador;
   	}




	public void jugar(Mazo mano, Mazo basuraParametro, Mazo pila, String nombreJ) { // me gustaria descomponer esto en mas metodos
		int opcion;
		Interfaz interfaz = new Interfaz();
		Mazo manoValida = new Mazo(); // al declararlo dentro de cada uno no se comparte la memoria
		turno = true;
		opcion = 0;
		do {
			manoValida.recibirCarta(mano.getCartaValida(basuraParametro.getUltimaCarta()));// Aqui recibo solo las
			if (manoValida.getMazo().length > 0) {
				try {
					// me gustaria mostrar todas las cartas disponibles y que se le de la opcion de tirar cualquier carta
					//pero que luego se le diga que no puede jugar una carta especifica
					opcion = interfaz.botarRecoger(manoValida, nombreJ, basuraParametro); // puedo jugar para selecionar



					
					for (int i = 0; i < mano.getMazo().length; i++) {
						if (mano.getMazo()[i] == manoValida.getMazo()[opcion]) { // hice que la interfaz solo nos
																					// muestre las cartas validas para
																					// que sea mas facil y a menor error
																					// para el usuario
							opcion = i; // el For nos sirve para encontrar la posion de la carta valida en la mano
						}
					}
				} catch (Exception e) {
					System.out.println();
				}
				basuraParametro.recibirCarta(mano.darCarta(1, opcion)); // aqui tiramos la carta a la basuraParametro
				turno = false; // el turno cambia a 2 para que la siguiente persona siga jugando
			} else {
				System.out.println( nombreJ+  " no tiene cartas validas para jugar, se come una");
				mano.recibirCarta(pila.darCarta(1, 999));// en el caso de que la persona no tenga cartas validas para
															// jugar se le otorga una directamente hasta que pueda jugar
			}
			// System.out.println(mano);// prueba solo para ver si si se tira la carta
			// correcta
		} while (turno == true);

		// Carta[] pruebaValidaComputadora =
		// mano2.getCartaValida(basuraParametro.getUltimaCarta());
		// int cartaAleatorio = (int) Math.random() * pruebaValidaComputadora.length;

		// System.out.println(basuraParametro);// quitar despues de prueba
	}

	public void jugarComputadora(Mazo mano, Mazo basuraParametro, Mazo pila) { //podriamos poner dos compus a jugar entre si
		boolean turno = true;
		int opcion;
		Mazo manoValida = new Mazo(); // ?
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
