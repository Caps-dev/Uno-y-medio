public class Main {
	public static void main(String args[]) {

		Mazo pila = new Mazo();
		Mazo mano1 = new Mazo();
		Mazo mano2 = new Mazo();
		Mazo basura = new Mazo();
		Interfaz interfaz = new Interfaz();
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
		mano1.recibirCarta(pila.darCarta(10, 999)); // cuando se da una carta esta carta se quita del objeto que da la
													// carta //999 significa que no importa el orden
		mano2.recibirCarta(pila.darCarta(10, 999)); // cuando se da manuna carta esta carta se quita del objeto que da la
													// carta
		basura.recibirCarta(pila.darCarta(15, 999));// se activa la basura para saber con cual carta inicia el juego // hacemos esto para no dar ventaja a un jugador

		System.out.println("mano1------------"); // for dev purposes
		System.out.println(mano1);

		System.out.println("mano2------------"); // for dev purposes
		System.out.println(mano2);

		System.out.println("basura------------"); // for dev purposes
		System.out.println(basura);

		//System.out.println(""+mano2.getTamanio());


		// int prueba2 = interfaz.botarRecoger(mano2, "Jugador 2",basura); // como esto
		// recibe un mazo generico entonces podemos buscar dentro de cualquier pila
		// basura.recibirCarta(mano2.darCarta(1,prueba2));
		// System.out.println(mano2);

		// int prueba1 = interfaz.botarRecoger(mano1, "Jugador 1", basura); // esto
		// recibe un mazo x entonces podemos
		// buscar dentro de cualquier pila, como
		// pila de basura para buscarcarta
		// basura.recibirCarta(mano1.darCarta(1, prueba1));
		// System.out.println(basura.getUltimaCarta());

		System.out.println("------------------------------");

		
		// Carta pruebaValida = basura.getUltimaCarta();

		// creo que solo pasa en el segundo ciclo

		// System.out.println(basura.getUltimaCarta());

		// mano1.convertirArraytoString(pruebaValida);

		// int test2 = interfaz.botarRecoger(basura, "Buscar en Pila",basura); // como
		// esto recibe un mazo generico entonces podemos buscar dentro de cualquier pila
		boolean activo = true;
		//buscar en pila probablemente necesita una condicion especial dentro de jugador

		do {
			boolean cartaEspecialActiva = false;
			int comerEspecial = 0;
			System.out.println("Ultima Carta Jugada------------"); 
			System.out.println(basura.getUltimaCarta());
			jugador1.jugar("Jugador 1",juego,mano1, basura, pila,cartaEspecialActiva,comerEspecial);
			System.out.println("Ultima Carta Jugada------------"); 
			System.out.println(basura.getUltimaCarta());
			computadora.jugar("Jugador 2",juego,mano2, basura, pila,cartaEspecialActiva,comerEspecial);
			activo = juego.finJuego(mano1,mano2,pila,basura);
			

		} while ( activo == true ); // si una de las dos manos se hace cero ya termina





	}
}