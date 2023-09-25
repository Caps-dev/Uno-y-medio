public class Main {
	public static void main(String args[]) {

		Mazo pila = new Mazo();
		Mazo mano1 = new Mazo();
		Mazo mano2 = new Mazo();
		Mazo basura = new Mazo();
		// Interfaz interfaz = new Interfaz();
		Juego juego = new Juego();

		// hay que encontrar una forma de hacer esto un metodo

		pila.generarMazo();
		pila.revolver(); // esto se podria meter al metodo de generar mazo cuando ya estemos as seguros
							// de todo
		// System.out.println(pila);
		System.out.println("----------------");
		// voy a empezar dando 10 cartas para probar el funcionamiento del juego
		mano1.recibirCarta(pila.darCarta(5, 999)); // cuando se da una carta esta carta se quita del objeto que da la
													// carta //999 significa que no importa el orden
		mano2.recibirCarta(pila.darCarta(5, 999)); // cuando se da una carta esta carta se quita del objeto que da la
													// carta
		basura.recibirCarta(pila.darCarta(1, 999));// se activa la basura para saber con cual carta inicia el juego
		// System.out.println(mano1);
		// System.out.println(mano2);

		// int prueba1 = interfaz.botarRecoger(mano1, "Jugador 1", basura); // esto
		// recibe un mazo x entonces podemos
		// buscar dentro de cualquier pila, como
		// pila de basura para buscarcarta
		// basura.recibirCarta(mano1.darCarta(1, prueba1));
		// System.out.println(basura.getUltimaCarta());

		System.out.println("---------   Cartas Validas para Mano 2 abajo  -----------------\n");

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
		do {
			System.out.println(basura.getUltimaCarta());
			juego.jugador(mano1, basura, pila);
			System.out.println(basura);
			juego.computadora(mano2, basura, pila);
		} while (mano1.getMazo().length != 0);
	}
}