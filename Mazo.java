//! \class Mazo lleno de cartas de uno y medio.
/* !
	Clase encargado de representar un arreglo de cartas. 
	Puede dar y recibir cartas, achicandose o alargandose respectivamente.
	Dado que solo representa un arreglo de cartas puede usarse como pila de cartas para agarrar, pila de basura y las manos de los jugadores.
*/
public class Mazo {

	private Carta[] mazo; /*! <Arreglo de tipo Carta */

	// Metodos de retorno
	/*! \brief Retorna el mazo. */
	public Carta[] getMazo() {
		return mazo;
	}

	/*! \brief Retorna el tamanio del mazo. */
	public int getTamanio() {
		int tamanio = mazo.length;
		return tamanio;
	}

	/*!/brief convierte un id numerico en una representación de un color(Rojo, Verde, Azul, Naranja). Retorna el color como String.
	 * \param int id del color
	 */
	public String obtenerColor(int numero) {
		String color = "Rojo";

		if (numero == 0) {
			color = "Rojo";
		} else if (numero == 1) {
			color = "Verde";
		} else if (numero == 2) {
			color = "Azul";
		} else if (numero == 3) {
			color = "Naranja";
		} else {
			color = "Negro"; // las cartas especiales, es un valor arbitrario
		}
		return color;
	}

	/*!/brief Llama al método getColor de Carta para cada carta dentro del arreglo y lo imprime. */
	public void getColor() {
		for (int i = 0; i < mazo.length; i++) {
			System.out.println(mazo[i].getColor());

		}

	}

	/*! \brief Genera el mazo.
	 * 
	 * Llama al constructor de Carta, donde le asigna un numero, un color mediante el metodo obtenerColor y una categoria (especial o normal.)
	 * Para las cartas especiales se les asigna el color Negro
	 */	
	public void generarMazo() { // generar mazo
		mazo = new Carta[88];
		int celda = 0;

		for (int k = 0; k < 2; k++) {
			for (int j = 0; j < 4; j++) { // 4 colores para las cartas basicas
				for (int i = 0; i <= 8; i++) {
					// Carta carta = new Carta(i, obtenerPalo(j));
					mazo[celda] = new Carta(i, obtenerColor(j), false); // no son cartas especiales
					celda++;
					// System.out.println(celda);
				}
			}

		}

		celda = 72;
		for (int j = 0; j < 4; j++) { // 4 artas por tipo
			for (int i = 9; i <= 12; i++) {
				// Carta carta = new Carta(i, obtenerPalo(j), true);
				mazo[celda] = new Carta(i, "Negro", true);
				celda++;
			}
		}

	}

	/*! \brief Revuelve el mazo.
	 * 
	 * Mediante numeros aleatorios generados por Math.random() se intercambian posiciones de cartas en el mazo.
	 */	
	public void revolver() {
		for (int i = 0; i < mazo.length; i++) {
			int numeroAleatorio = (int) (Math.random() * mazo.length);
			Carta temporal = mazo[i];
			mazo[i] = mazo[numeroAleatorio];
			mazo[numeroAleatorio] = temporal;
		}
	}


	/*! \brief Imprime el contenido del mazo
	 *
	 * Imprime en consola el número en formato numérico y el color. Llama al metodo toString de la clase Carta.
	 */
	public String toString() {

		String contenido = "";
		if (mazo == null || mazo.length == 0) {
			contenido = "";
			;
		} else {
			for (int i = 0; i < mazo.length; i++) {
				contenido += mazo[i];
			}
		}

		return contenido;
	}

	/*! \brief Retorna el mazo como un arreglo de Strings*/
	public String[] mazoToString() { // toma el mazo actual y devuelve arreglo de String

		String[] contenido = new String[mazo.length];
		for (int i = 0; i < mazo.length; i++) {
			contenido[i] = "" + mazo[i];
		}
		return contenido;

	}


	/*! \brief Da una carta en una posicion dada o bien da las n primeras cartas. Acortando el arreglo de cartas acorde al numero de cartas dadas.
	 * 
	 * Para dar n cartas es necesario indicar posicion 999.
	 * Si no hay cartas para dar imprime un mensaje en consola indicandolo.
	 * Junto al metodo recibirCarta es responsable del intercambio de cartas entre jugadores, pila y basura
	 * \param int numero de cartas a dar.
	 * \param int posicion de la carta.
	 */
	public Carta[] darCarta(int n, int posicion) { 

		Carta[] arreglo = new Carta[n];

		if (mazo.length > 0) {
			Carta[] nuevoArreglo = new Carta[mazo.length - n]; 
			if (posicion == 999) {
				// para repartir cartas del mazo
				for (int i = 0; i < n; i++) {
					arreglo[i] = mazo[i]; // tomamos los primeros elementos
				}

				for (int i = 0; i < mazo.length - n; i++) { // siempre hay que reducir el mazo
					nuevoArreglo[i] = mazo[i + n];
				}

			} else {
				arreglo[0] = mazo[posicion]; // solo se puede jugar una carta a la vez

				int celda = 0;
				int i = 0;

				while (i < mazo.length) {

					if (posicion != i) {
						nuevoArreglo[celda] = mazo[i];
						i++;
						celda++;

					} else {
						i++;
					}
				}
			}

			mazo = nuevoArreglo;

		} else {
			System.out.println("No hay cartas para dar");
		}

		return arreglo;
	}

	/*! \brief Recibe un arreglo de carta. Alargando el arreglo de cartas acorde al numero de cartas dadas.
	 * 
	 * Junto al metodo recibirCarta es responsable del intercambio de cartas entre jugadores, pila y basura
	 */
	public void recibirCarta(Carta[] arregloParametro) {

		if (mazo == null || mazo.length == 0) {
			mazo = arregloParametro;
		} else {

			Carta[] nuevoArreglo = new Carta[mazo.length + arregloParametro.length]; // las cartas que vamos a recibir
			// int celda = 0;

			for (int i = 0; i < mazo.length; i++) {// primero pongo los elementos que ya estaban en la mano

				nuevoArreglo[i] = mazo[i];

			}

			for (int i = 0; i < arregloParametro.length; i++) {// primero pongo los elementos que ya estaban en la mano

				nuevoArreglo[i + mazo.length] = arregloParametro[i]; // empezamos en el tamanio de la mano original + i

			}

			mazo = nuevoArreglo;

		}

	}

	/*! \brief Dados un arreglo de Strings y un String, retorna la posicion de este String dentro del arreglo.
	 * 
	 * Permite obtener la posicion de una carta seleccionada por el jugador, mediante interfaz.
	 * El chequeo de si la carta escogida es valida se hace posterior a la escogencia de la carta en el metodo
	 * Junto al metodo recibirCarta es responsable del intercambio de cartas entre jugadores, pila y basura
	 * \param int numero de cartas a dar.
	 * \param int posicion de la carta.
	 */
	public int getPosicionCarta(String[] arregloParametro, String cartaElegida) {

		int posicion = 0;
		if (cartaElegida != null) {
			for (int i = 0; i < arregloParametro.length; i++) {// primero pongo los elementos que ya estaban en la mano
				if (arregloParametro[i] == cartaElegida) {
					posicion = i; // si tengo dos voy a tomar el ultimo
				}
			}
		}

		return posicion;
	}

	/*! \brief retorna la ultima carta dentro del mazo.
	 * 
	 * Si no hay mazo o el mazo no tiene cartas se retorna un null.
	 */
	public Carta getUltimaCarta() { 


		Carta ultimaCarta;

		if (mazo == null || mazo.length == 0) {
			ultimaCarta = null;
		} else {

			ultimaCarta = mazo[mazo.length - 1];

		}

		return ultimaCarta;

	}

	/*! \brief Dada la ultima carta jugada verifica si una carta es valida para jugar. Retorna true si la carta es valida, false si no.
	 * \param Carta carta que se quiere jugar.
	 * \param Carta ultima carta jugada.
	 */
	public boolean esCartaValida(Carta carta1, Carta ultimaCarta) {

		String colorUltimaCarta = ultimaCarta.getColor();
		String colorCarta1 = carta1.getColor();
		boolean esEspecial = carta1.getEsEspecial();

		boolean test = (
		((esEspecial == true) && (esEspecial && ultimaCarta.getId() <= carta1.getId())) ||
				(colorUltimaCarta.equals(colorCarta1)) || 
				(ultimaCarta.getId() == carta1.getId()) 
		);
		return test;

	}

	/*! \brief Dada una carta verifica si es una carta especial o no (Come2, Come3, Cancelar, Buscar en Pila) 
	 * 
	 * \param Carta carta que se quiere verificar si es especial o no.
	 * */
	public boolean esCartaEspecial(Carta carta) {

		boolean test = carta.getEsEspecial();
		return test;

	}

	/*! \brief Cambia el color a una carta
	 * 
	 * Este metodo esta diseñado para ser usado con cartas especiales.Imprime un mensaje indicando que la siguiente carta debe ser del color al que se cambió la carta.
	 * 
	 * \param Carta carta que se le quiere cambiar el color.
	 * \param String color que se le quiere dar a la carta.
	 * */
	public void setColor(Carta carta, String color) {

		carta.setColor(color); // no importa si le caemos encima al color de la carta especial
		System.out.println("La siguiente carta debe ser color: " + color);

	}

	/*! \brief Retorna un array de Cartas que se pueden jugar en el turno actual.
	 *
	 *  Metodo usado por la computadora para jugar cartas e internamente para determinar si el jugador tiene cartas para jugar.
	 * 
	 * \param Carta Ultima carta jugada.
	 * */
	public Carta[] getCartaValida(Carta ultimaCartaParam) { // comparamos el mazo actual contra una carta externa y
															// vemos que cartas del mazo actual se pueden jugar

		int celda = 0; //hacemos un contador para la celda aparte para poder hacerle skip a las cartas no validas
		int i = 0;
		int cartasValidas = 0;

		while (i < mazo.length) { //un ciclo para contar las cartas validas.
			if (esCartaValida(mazo[i], ultimaCartaParam)) {

				cartasValidas++;
				i++;

			} else {
				i++;
			}
		}

		Carta[] nuevoArreglo = new Carta[cartasValidas];

		i = 0;

		while (i < mazo.length) { //un ciclo para agregar las cartas validas.
			if (esCartaValida(mazo[i], ultimaCartaParam)) {

				nuevoArreglo[celda] = mazo[i];
				i++;
				celda++;

			} else {
				i++;
			}
		}

		return nuevoArreglo;

	}

	/*! \brief Dado un arreglo de Cartas lo convierte a un string de cartas.
	 *
	 * \param Carta[]  Arreglo de Cartas Ultima carta jugada.
	 * */
	public String convertirArraytoString(Carta[] arreglo) {
		String contenido = "";
		if (arreglo == null || arreglo.length == 0) {
			contenido = "";
			;
		} else {
			for (int i = 0; i < arreglo.length; i++) {
				contenido += arreglo[i];
			}
		}

		return contenido;
	}

	/*! \brief retorna la posicion de la Carta Cancelar dentro del mazo. */
	public int posicionCartaCancelar(){

		int tieneCartaCancelar = -1;

		for (int i=0;i<mazo.length; i++ ){
			if(mazo[i].getId()==11){
				tieneCartaCancelar = i;
				break;
			}
		}
		return tieneCartaCancelar;
	}



}