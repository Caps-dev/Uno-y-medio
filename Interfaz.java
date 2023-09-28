import javax.swing.JOptionPane;

public class Interfaz {
	boolean estaActivo;
	int tipoDeJuego = 0;

	public void mostrarTexto(String texto, String titulo){ // usando esto que vimos en clase
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public int escogerCarta(Mazo mazoParametro, String tituloCuadro, Mazo pilaBasura) { 

		String cartas[] = mazoParametro.mazoToString(); // ahora esto recibe un mazo generico

		String opcion = null; // no creo que esto deba ser un while, ya que si alguien quiere cancelar no
								// puede
		do {
			// necesito que esto me devuelva un numero de 0 a n que represente la posicion
			// de la carta dentro del array
			// pero necesito que me imprima en si los valores de las cartas
			opcion = String.valueOf(JOptionPane.showInputDialog(null, "seleccione una carta", tituloCuadro,
					JOptionPane.QUESTION_MESSAGE, null, cartas, cartas[0])); // cartas[0] es el valor por defecto
		} while (opcion == "null"); // el boton de cancelar no funciona porque esto esta dentro de un while CORREGIR

		int posicion = mazoParametro.getPosicionCarta(cartas, opcion);
		return posicion;
	}

	public String escogerColor(String tituloCuadro) { 

		String[] colores = new String[]{"Rojo", "Verde", "Azul","Naranja"}; // siempre van a ser los mismos

		String colorEscogido = null; 
		do {
			// necesito que esto me devuelva un numero de 0 a n que represente la posicion
			// de la carta dentro del array
			// pero necesito que me imprima en si los valores de las cartas
			colorEscogido = String.valueOf(JOptionPane.showInputDialog(null, "seleccione un color", tituloCuadro,
					JOptionPane.QUESTION_MESSAGE, null, colores, colores[0])); // cartas[0] es el valor por defecto
		} while (colorEscogido == "null"); // el boton de cancelar no funciona porque esto esta dentro de un while CORREGIR

		System.out.println(tituloCuadro + " escoge el color: " + colorEscogido);

		//int posicion = mazoParametro.getPosicionCarta(cartas, opcion);
		return colorEscogido;
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