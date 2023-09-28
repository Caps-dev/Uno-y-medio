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
   	public int inputJugador(String nombreJ, Mazo mano, Mazo basuraParametro){ //! Hace cumplir las reglas para jugadores humanos

   		Interfaz interfaz = new Interfaz();   
   		int posicion=0;			
	   	boolean esCartaValida = false;

		while(esCartaValida==false){ //mientras no seleccione una carta valida el loop va a seguir
			posicion = interfaz.escogerCarta(mano, nombreJ, basuraParametro); //devuelve posicion de la carta

			esCartaValida = mano.esCartaValida(mano.getMazo()[posicion],basuraParametro.getUltimaCarta());
			if (esCartaValida==false){ //darle chance a la persona de que escoja
				interfaz.mostrarTexto("Favor use una carta valida","Atencion");
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

		do {

			if (mano.getCartaValida(basuraParametro.getUltimaCarta()).length > 0) {

				posicion = inputJugador(nombreJ,mano,basuraParametro);
				basuraParametro.recibirCarta(mano.darCarta(1, posicion)); // aqui tiramos la carta a la basuraParametro


				//aqui verificamos si la carta valida dada por el usuario es especial

				esCartaEspecial = mano.esCartaEspecial(basuraParametro.getUltimaCarta());

				// si es especial vamos a invocar metodos de cartas especiales
				if(esCartaEspecial){
					//ya que el color de la carta especial es arbitrario lo podemos cambiar 
					String color = interfaz.escogerColor(nombreJ);
					mano.cambiarColor(basuraParametro.getUltimaCarta(),color);

					turno = false; // el turno cambia a falso para que la siguiente persona siga jugando

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
