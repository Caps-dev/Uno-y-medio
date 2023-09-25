import javax.swing.JOptionPane;

public class Interfaz {
	boolean estaActivo;
	int tipoDeJuego = 0;

	public int botarRecoger(Mazo mazoParametro, String tituloCuadro, Mazo pilaBasura) { // funcion es un mal nombre
																						// porque el argumento es el
																						// titulo no un metodo

		String cartas[] = mazoParametro.mazoToString(); // ahora esto recibe un mazo generico

		String opcion = null; // no creo que esto deba ser un while, ya que si alguien quiere cancelar no
								// puede
		do {
			// necesito que esto me devuelva un numero de 0 a n que represente la posicion
			// de la carta dentro del array
			// pero necesito que me imprima en si los valores de las cartas
			opcion = String.valueOf(JOptionPane.showInputDialog(null, "seleccione una carta", tituloCuadro, // quit el
																											// concatenado
																											// que no es
																											// util
					JOptionPane.QUESTION_MESSAGE, null, cartas, cartas[0])); // cartas[0] es el valor por defecto
		} while (opcion == "null"); // el boton de cancelar no funciona porque esto esta dentro de un while CORREGIR

		int posicion = mazoParametro.getPosicionCarta(cartas, opcion);
		return posicion;
	}

	public int menu() {
		do {
			try {
				tipoDeJuego = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Elige una opcion: \n1) jugador vs jugador\n2) jugador vs computadora\n3) salir", // ESTE 3 NO
																											// HACE
																											// FALTA
																											// PORQUE YA
																											// HAY UN
																											// BOTON DE
																											// CANCELAR
						"Menu Principal", JOptionPane.QUESTION_MESSAGE)); // ADEMAS EL TRES NO HACE NADA //QUITAR EL
																			// TRES
			} catch (Exception e) { // EL BOTONN DE CANCELAR NO HACE NADA POR ESTAR DENTRO DE UN CICLO // ARREGLAR
				JOptionPane.showMessageDialog(null, "Error al digitar el digito");
			}
		} while (tipoDeJuego < 1 || tipoDeJuego > 3);

		return tipoDeJuego;

	}

}