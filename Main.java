public class Main {
	public static void main(String args[]) {

		Mazo pila = new Mazo();
		Mazo mano1 = new Mazo();
		Mazo mano2 = new Mazo();
		Mazo basura = new Mazo();
		// Interfaz interfaz = new Interfaz();
		Juego juego = new Juego();
		Jugador jugador1 = new Jugador();
		Jugador computadora = new Jugador();

		//seria bueno tener una forma de terminar el juego de manera anticipada
		// rendirse pues
		// maybe tratarlo como una carta especial para que siempre se pueda jugar?
		// falta hacer la logica de las cartas especiales

		pila.generarMazo();
		pila.revolver(); // esto se podria meter al metodo de generar mazo cuando ya estemos as seguros
							// de todo
		// System.out.println(pila);
		System.out.println("----------------");
		// buscar la forma de quitar cosas de main y meterlas en la clase juego
		mano1.recibirCarta(pila.darCarta(5, 999)); // cuando se da una carta esta carta se quita del objeto que da la
													// carta //999 significa que no importa el orden
		mano2.recibirCarta(pila.darCarta(5, 999)); // cuando se da una carta esta carta se quita del objeto que da la
													// carta
		basura.recibirCarta(pila.darCarta(1, 999));// se activa la basura para saber con cual carta inicia el juego // hacemos esto para no dar ventaja a un jugador

		System.out.println("mano1------------"); // for dev purposes

		System.out.println(mano1);
		System.out.println("mano2------------"); // for dev purposes

		System.out.println(mano2);

		System.out.println(""+mano2.getTamanio());



		// int prueba1 = interfaz.botarRecoger(mano1, "Jugador 1", basura); // esto
		// recibe un mazo x entonces podemos
		// buscar dentro de cualquier pila, como
		// pila de basura para buscarcarta
		// basura.recibirCarta(mano1.darCarta(1, prueba1));
		// System.out.println(basura.getUltimaCarta());

		//System.out.println("---------   Cartas Validas para Mano 2 abajo  -----------------\n");

		// Carta[] pruebaValida = mano2.getCartaValida(basura.getUltimaCarta());
		// System.out.println(mano2.convertirArraytoString(pruebaValida)); // me
		// devuelve las cartas validas que puede
		// jugar el que tiene la mano2
		// a partir de ahi podemos pasarle eso al JOptionPane, el jugador selecciona una
		// de las validas y se quitan de su mano original
		// alternativamente se podria decirle al JOptionPane que muestre toda la mano y
		// que en otra caja le muestre solo las validas
		// lo importante es que con esto ya se puede hacer la mayor parte del juego

		System.out.println("------------------------------");

		// int prueba2 = interfaz.botarRecoger(mano2, "Jugador 2",basura); // como esto
		// recibe un mazo generico entonces podemos buscar dentro de cualquier pila
		// basura.recibirCarta(mano2.darCarta(1,prueba2));
		// System.out.println(mano2);

		// Carta pruebaValida = basura.getUltimaCarta();

		// creo que solo pasa en el segundo ciclo

		// System.out.println(basura.getUltimaCarta());

		// mano1.convertirArraytoString(pruebaValida);

		// int test2 = interfaz.botarRecoger(basura, "Buscar en Pila",basura); // como
		// esto recibe un mazo generico entonces podemos buscar dentro de cualquier pila
		int turnos  = 10;
		boolean activo = true;

		
		do {
			System.out.println("Ultima Carta Jugada------------"); // for dev purposes
			System.out.println(basura.getUltimaCarta());
			jugador1.jugar(mano1, basura, pila,"Jugador 1");
			System.out.println("Ultima Carta Jugada------------"); // for dev purposes
			System.out.println(basura.getUltimaCarta());
//			System.out.println("mano2------------"); // for dev purposes
			//System.out.println(mano2); //for dev purposes
			computadora.jugar(mano2, basura, pila,"Jugador 2");

			activo = juego.finJuego(mano1,mano2);
			

		} while ( activo == true ); // si una de las dos manos se hace cero ya termina



	}
}