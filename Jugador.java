public class Jugador { // al hacer una clase jugador podemos usar este mismo objeto para hacerlo contra dos jugadores
	// no cambia mucho los contenidos pero mas para una distincion de nomenclatura que tenga sentido

	boolean turno = true;

	/*

 	public Jugador (boolean turnoParametro){
   		//System.out.println("-> Se invoca al constructor que recibe el numero y el palo");
   		turno = turnoParametro;
   		nombreJugador = nombreJugador;
   	}
	*/	
   	public int inputJugador(String nombreJ, Mazo mano,  Mazo basuraParametro, boolean esEspecial){ //! Hace cumplir las reglas para jugadores humanos
   		//por defecto la carta no va a ser valida pero lo vamos a especificar como un input que se puede cambiar
   		Interfaz interfaz = new Interfaz();   
   		int posicion=0;
   		boolean esCartaValida = false;

   		if(esEspecial){ // si viene de escogerPila puede escoger y jugar cualquier carta que quiera      
			posicion = interfaz.escogerCarta(mano, nombreJ); //devuelve posicion de la carta
   		} else {
			while(esCartaValida==false){ //mientras no seleccione una carta valida el loop va a seguir
				posicion = interfaz.escogerCarta(mano, nombreJ); //devuelve posicion de la carta

				esCartaValida = mano.esCartaValida(mano.getMazo()[posicion],basuraParametro.getUltimaCarta());
				if (esCartaValida==false){ //darle chance a la persona de que escoja
					interfaz.mostrarTexto("Favor use una carta valida","Atencion");
				}
		}

   		}
	   	
		return posicion;

   	}



	public void jugar(String nombreJ, Juego juego, Mazo mano, Mazo basuraParametro, Mazo pila,  boolean cartaEspecialActiva, int comerEspecial) {
		int posicion;
		turno = true;
		posicion = 0;
		boolean esCartaValida = false;
		boolean esCartaEspecial = false;
		// comer una carta al inicio del turno
		Interfaz interfaz = new Interfaz();   

		do { // seria bueno intentar separar esto en varios metodos

			if (mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) {

				posicion = inputJugador(nombreJ,mano,basuraParametro,false);
				basuraParametro.recibirCarta(mano.darCarta(1, posicion)); // aqui tiramos la carta a la basuraParametro

				esCartaEspecial = mano.esCartaEspecial(basuraParametro.getUltimaCarta());
				int id = basuraParametro.getUltimaCarta().getId();

				// si es especial vamos a invocar metodos de cartas especiales
				if(esCartaEspecial&& id != 12){ //12 es buscar en pila
					//ya que el color de la carta especial es arbitrario lo podemos cambiar 
					String color = interfaz.escogerColor(nombreJ);
					mano.cambiarColor(basuraParametro.getUltimaCarta(),color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

				} else if (esCartaEspecial&& id == 12){ //deberia correrse antes de ingresar a la basura
					posicion = inputJugador(nombreJ,basuraParametro,basuraParametro,true); //todo recursividad o bien quitar cosas de la pila
					//al hacer carta valida true cuando se juega buscar en pila se puede tomar cualquier carta 
					mano.recibirCarta(basuraParametro.darCarta(1, posicion));
					//maybe el jugador no deberia poder jugar la carta que quiera y deba pensar que carta escoger
					//p0osicion = inputJugador(nombreJ,mano,basuraParametro,true);
//					basuraParametro.recibirCarta(mano.darCarta(1, posicion)); //aunque todavia no hemos visto recursividad



				} else{ // si no, va a dar una carta a la pila de basura (juega la carta)
					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando
				}


			} else if (cartaEspecialActiva==false) { // parte 1 cuando no tiene cartas validas
				System.out.println( nombreJ+  " no tiene cartas validas para jugar, come 1");
				if(pila.getTamanio()==0){
					turno = false;
				} else {				
					mano.recibirCarta(pila.darCarta(1, 999));// en el caso de que la persona no tenga cartas validas para
															// jugar se le otorga una directamente hasta que pueda jugar

				}
													
			} else {

				System.out.println( nombreJ+  " no tiene cartas validas para jugar, come" + comerEspecial);
				mano.recibirCarta(pila.darCarta(comerEspecial, 999));// en el caso de que la persona no tenga cartas validas para
															// jugar se le otorga una directamente hasta que pueda jugar

			}
			

		} while (turno == true);




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
