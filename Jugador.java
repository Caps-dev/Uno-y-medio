public class Jugador { // al hacer una clase jugador podemos usar este mismo objeto para hacerlo contra
						// dos jugadores
	// no cambia mucho los contenidos pero mas para una distincion de nomenclatura
	// que tenga sentido

	boolean turno = true;

	/*
	 * 
	 * public Jugador (boolean turnoParametro){
	 * //System.out.
	 * println("-> Se invoca al constructor que recibe el numero y el palo");
	 * turno = turnoParametro;
	 * nombreJugador = nombreJugador;
	 * }
	 */
	public int inputJugador(String nombreJ, Mazo mano, Mazo basuraParametro, boolean esEspecial) { // ! Hace cumplir las
																									// reglas para
																									// jugadores humanos
		// por defecto la carta no va a ser valida pero lo vamos a especificar como un
		// input que se puede cambiar

		Interfaz interfaz = new Interfaz();
		int posicion = 0;
		boolean esCartaValida = false;

		if (esEspecial) { // si viene de escogerPila puede escoger y jugar cualquier carta que quiera
			posicion = interfaz.escogerCarta(mano, nombreJ); // devuelve posicion de la carta
		} else {
			while (esCartaValida == false) { // mientras no seleccione una carta valida el loop va a seguir
				posicion = interfaz.escogerCarta(mano, nombreJ); // devuelve posicion de la carta

				esCartaValida = mano.esCartaValida(mano.getMazo()[posicion], basuraParametro.getUltimaCarta());
				if (esCartaValida == false) { // darle chance a la persona de que escoja
					interfaz.mostrarTexto("Favor use una carta valida", "Atencion");
				}
			}

		}

		return posicion;

	}

	public void jugar(String nombreJ, Mazo mano, Mazo basuraParametro, Mazo pila,
			boolean cartaEspecialActiva, int comerEspecial, Juego juegoParametro) {
		int posicion;
		turno = true;
		posicion = 0;
		boolean esCartaEspecial = false;
		// comer una carta al inicio del turno
		Interfaz interfaz = new Interfaz();

		do { // seria bueno intentar separar esto en varios metodos
			if (mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) {

				posicion = inputJugador(nombreJ, mano, basuraParametro, false);
				basuraParametro.recibirCarta(mano.darCarta(1, posicion)); // aqui tiramos la carta a la basuraParametro

				esCartaEspecial = mano.esCartaEspecial(basuraParametro.getUltimaCarta());
				int id = basuraParametro.getUltimaCarta().getId();

				// si es especial vamos a invocar metodos de cartas especiales
				if (esCartaEspecial && id != 12) { // 12 es buscar en pila
					// ya que el color de la carta especial es arbitrario lo podemos cambiar
					juegoParametro.sumarCartasComer(id);
					String color = interfaz.escogerColor(nombreJ);
					mano.cambiarColor(basuraParametro.getUltimaCarta(), color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

				} else if (esCartaEspecial && id == 12) { // deberia correrse antes de ingresar a la basura
					posicion = inputJugador(nombreJ, basuraParametro, basuraParametro, true); // todo recursividad o
																								// bien quitar cosas de
																								// la pila
					// al hacer carta valida true cuando se juega buscar en pila se puede tomar
					// cualquier carta
					mano.recibirCarta(basuraParametro.darCarta(1, posicion));
					// maybe el jugador no deberia poder jugar la carta que quiera y deba pensar que
					// carta escoger
					// p0osicion = inputJugador(nombreJ,mano,basuraParametro,true);
					// basuraParametro.recibirCarta(mano.darCarta(1, posicion)); //aunque todavia no
					// hemos visto recursividad

				} else { // si no, va a dar una carta a la pila de basura (juega la carta)
					if (juegoParametro.cartasComer > 0) {
						System.out.println(
								nombreJ + " comer " + juegoParametro.cartasComer + "\n ----------------------");
						mano.recibirCarta(pila.darCarta(juegoParametro.cartasComer, 999));
						juegoParametro.setCartasComer();
					}
					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando
				}

			} else if (cartaEspecialActiva == false) { // parte 1 cuando no tiene cartas validas
				System.out.println(nombreJ + " no tiene cartas validas para jugar, come 1");
				if (pila.getTamanio() == 0) {
					turno = false;
				} else {
					mano.recibirCarta(pila.darCarta(1, 999));// en el caso de que la persona no tenga cartas validas
																// para
																// jugar se le otorga una directamente hasta que pueda
																// jugar

				}

			} else {

				System.out.println(nombreJ + " no tiene cartas validas para jugar, come" + comerEspecial);
				mano.recibirCarta(pila.darCarta(comerEspecial, 999));// en el caso de que la persona no tenga cartas
																		// validas para
				// jugar se le otorga una directamente hasta que pueda jugar

			}

		} while (turno == true);

	}

	public void jugarComputadora(String nombreJ, Mazo mano, Mazo basuraParametro, Mazo pila,
			boolean cartaEspecialActiva, int comerEspecial, Juego juegoParametro) { // podriamos poner dos compus a
																					// jugar
		int posicion;
		turno = true;
		posicion = 0;
		boolean esCartaEspecial = false;
		int opcion = 0;
		String color = "";
		// comer una carta al inicio del turno

		do {

			if (mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) {
				opcion = (int) (Math.random() * mano.getCartaValida(basuraParametro.getUltimaCarta()).length); // sacamos
																												// una
																												// carta
																												// valida
																												// aleatoria
				for (int i = 0; i < mano.getMazo().length; i++) { // tenemos que hacer un for para encontrar la carta
																	// ramdon en el mazo y pasamos la i como posicion
					if (mano.getCartaValida(basuraParametro.getUltimaCarta())[opcion] == mano.getMazo()[i]) {
						posicion = i;
					}
				}
				basuraParametro.recibirCarta(mano.darCarta(1, posicion)); // aqui tiramos la carta a la basuraParametro

				esCartaEspecial = mano.esCartaEspecial(basuraParametro.getUltimaCarta());
				int id = basuraParametro.getUltimaCarta().getId();

				// si es especial vamos a invocar metodos de cartas especiales
				if (esCartaEspecial && id != 12) { // 12 es buscar en pila
					// ya que el color de la carta especial es arbitrario lo podemos cambiar
					juegoParametro.sumarCartasComer(id);
					opcion = (int) (Math.random() * 4); // esto sustituye con el switch sustituye el input para asi la
														// maquina selecione un color al azar
					switch (opcion) {
						case 0:
							color = "Rojo";
							break;
						case 1:
							color = "Verde";
							break;
						case 2:
							color = "Azul";
							break;
						case 3:
							color = "Naranja";
							break;
					}// el switch hace que se elija un color aleatorio

					mano.cambiarColor(basuraParametro.getUltimaCarta(), color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

				} else if (esCartaEspecial && id == 12) { // deberia correrse antes de ingresar a la basura

					opcion = (int) (Math.random() * basuraParametro.getMazo().length);// Seleccionamos y numero al azar
																						// de la basura para jugar la
																						// carta.
					// al hacer carta valida true cuando se juega buscar en pila se puede tomar
					// cualquier carta
					mano.recibirCarta(basuraParametro.darCarta(1, posicion));
					// maybe el jugador no deberia poder jugar la carta que quiera y deba pensar que
					// carta escoger
					// p0osicion = inputJugador(nombreJ,mano,basuraParametro,true);
					// basuraParametro.recibirCarta(mano.darCarta(1, posicion)); //aunque todavia no
					// hemos visto recursividad

				} else { // si no, va a dar una carta a la pila de basura (juega la carta)
					if (juegoParametro.cartasComer > 0) {
						System.out.println(
								nombreJ + " comer " + juegoParametro.cartasComer + "\n ----------------------");
						mano.recibirCarta(pila.darCarta(juegoParametro.cartasComer, 999));
						juegoParametro.setCartasComer();
					}
					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando
				}

			} else if (cartaEspecialActiva == false) { // parte 1 cuando no tiene cartas validas
				System.out.println(nombreJ + " no tiene cartas validas para jugar, come 1");
				if (pila.getTamanio() == 0) {
					turno = false;
				} else {
					mano.recibirCarta(pila.darCarta(1, 999));// en el caso de que la persona no tenga cartas validas
																// para
																// jugar se le otorga una directamente hasta que pueda
																// jugar

				}

			} else {

				System.out.println(nombreJ + " no tiene cartas validas para jugar, come " + comerEspecial);
				mano.recibirCarta(pila.darCarta(comerEspecial, 999));// en el caso de que la persona no tenga cartas
																		// validas para
				// jugar se le otorga una directamente hasta que pueda jugar

			}

		} while (turno == true);

	}

}
