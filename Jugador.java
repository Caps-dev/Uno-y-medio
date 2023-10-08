public class Jugador { // al hacer una clase jugador podemos usar este mismo objeto para hacerlo contra
						// dos jugadores
	// no cambia mucho los contenidos pero mas para una distincion de nomenclatura
	// que tenga sentido

	private boolean turno;
	private String nombre;

	public Jugador(String nombreParametro){
		turno = true;
		nombre = nombreParametro;
	}

	public String getNombre(){
		return nombre;
	}

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
			boolean cartaEspecialActiva, int comerEspecial, // TODO: CREO QUE ESTOS NO ESTAN HACIENDO NADA
			Juego juegoParametro, Mazo manoRival, String nombreRival) { // meter boolean rendirse
		int posicion;
		turno = true;
		posicion = 0;
		boolean esCartaEspecial = false;

		// comer una carta al inicio del turno
		Interfaz interfaz = new Interfaz();
		int id = basuraParametro.getUltimaCarta().getId();

		do {

			if (mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) { // si tiene cartas validas

				posicion = inputJugador(nombreJ, mano, basuraParametro, false);
				basuraParametro.recibirCarta(mano.darCarta(1, posicion)); // aqui tiramos la carta a la basuraParametro

				esCartaEspecial = mano.esCartaEspecial(basuraParametro.getUltimaCarta());
				id = basuraParametro.getUltimaCarta().getId();

				// si es especial vamos a invocar metodos de cartas especiales
				if (esCartaEspecial && id != 12) { // 12 es buscar en pila

					juegoParametro.sumarCartasComer(id); // TODO CREO QUE AQUI SE PODRIA HACER  LA ASIGNACION A COMERESPECIAL?

					// el color de la carta especial es arbitrario entonces lo podemos cambiar
					String color = interfaz.escogerColor(nombreJ);
					mano.setColor(basuraParametro.getUltimaCarta(), color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

			} else if (esCartaEspecial && id == 12) { // buscar pila
					
				boolean cancelarBP = false; // BP = Buscar en Pila
				posicion = inputJugador(nombreJ, basuraParametro, basuraParametro, true); 

				if(manoRival.posicionCartaCancelar()>=0){ // si el rival tiene una cartaCancelar
					cancelarBP = interfaz.cancelarBuscarPila(nombreRival);
					if (cancelarBP) {
							basuraParametro.recibirCarta(manoRival.darCarta(1, manoRival.posicionCartaCancelar()));
						}
				}

				if (cancelarBP == false) {
					mano.recibirCarta(basuraParametro.darCarta(1, posicion));
				}

				// TODO: AGREGAR UNA EXPLICACION DE ESTO
				if ( (basuraParametro.getTamanio() > 3 ) && basuraParametro.getMazo()[basuraParametro.getTamanio() - 2].getEsEspecial() == false) {

					basuraParametro.recibirCarta(basuraParametro.darCarta(1, basuraParametro.getMazo().length - 3)); // ????

				} else if ( (basuraParametro.getTamanio() >= 2) && basuraParametro.getMazo()[basuraParametro.getTamanio() - 2].getEsEspecial() == false) {

					basuraParametro.recibirCarta(basuraParametro.darCarta(1, basuraParametro.getMazo().length - 2)); // ???

				} else {
					mano.setColor(basuraParametro.getUltimaCarta(), interfaz.escogerColor(nombreJ));
				}


			} else { // si no juega una carta especial, la carta normal se pone en la basura
				if (juegoParametro.cartasComer > 0) { //todo hacer esto un metodo
					System.out.println(
							nombreJ + " come " + juegoParametro.cartasComer + "\n ----------------------");
					mano.recibirCarta(pila.darCarta(juegoParametro.cartasComer, 999));
					juegoParametro.resetCartasComer();
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

			} else { // TODO: creo que esto se podria meter arriba con algunas condiciones para que la persona coma si no tiene una carta que pueda jugar

				System.out.println(nombreJ + " no tiene cartas validas para jugar, come" + comerEspecial);
				mano.recibirCarta(pila.darCarta(comerEspecial, 999));// en el caso de que la persona no tenga cartas
																		// validas para
				// jugar se le otorga una directamente hasta que pueda jugar

			}

		} while (turno == true);

	}

	public void jugarComputadora(String nombreJ, Mazo mano, Mazo basuraParametro, Mazo pila,
			boolean cartaEspecialActiva, int comerEspecial, Juego juegoParametro) { // podriamos poner
																					// dos compus a
		// jugar
		int posicion;
		turno = true;
		posicion = 0;
		boolean esCartaEspecial = false;
		int opcion = 0;
		String color = "";
		// comer una carta al inicio del turno
		int id = basuraParametro.getUltimaCarta().getId();

		do {

			if (  mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) {
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
				id = basuraParametro.getUltimaCarta().getId();

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

					mano.setColor(basuraParametro.getUltimaCarta(), color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

				} else if (esCartaEspecial && id == 12) { // deberia correrse antes de ingresar a la basura

					opcion = (int) (Math.random() * basuraParametro.getMazo().length);// Seleccionamos y numero al azar
																						// de la basura para jugar la
																						// carta.
					mano.recibirCarta(basuraParametro.darCarta(1, posicion)); //TODO: PROBLEMA AQUI

				} else { // si no, va a dar una carta a la pila de basura (juega la carta)
					if (juegoParametro.cartasComer > 0) {
						System.out.println(
								nombreJ + " come " + juegoParametro.cartasComer + "\n ----------------------");
						mano.recibirCarta(pila.darCarta(juegoParametro.cartasComer, 999));
						juegoParametro.resetCartasComer();
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
