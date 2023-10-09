import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
/*! \class Interfaz ayuda para mostrar la interfaz 
 * muestra la interfaz al jugador cuando sea su turno
 * */
public class Interfaz {

	boolean estaActivo;
	/*! \brief muestra el texto
	 \param String texto
	 \param String titulo del cuadro
	 */
	public void mostrarTexto(String texto, String titulo) { // usando esto que vimos en clase
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

/*! \brief forma de que el jugador juegue
	\return boolean jugar de nuevo
	 */
	public boolean nuevaPartida(){

		String jugarDeNuevo = null;	
		int jugarDeNuevoInt = 2;
		boolean jugarDeNuevoBool = false;

		jugarDeNuevo = JOptionPane.showInputDialog(null,
				"Desea jugar de nuevo? Digite el numero segun la opcion: \n1) y\n2) n\n", 
				"Jugar de nuevo?", JOptionPane.QUESTION_MESSAGE); 

		try{
			jugarDeNuevoInt = Integer.parseInt(jugarDeNuevo);
		} catch(Exception e){
			System.out.println("Error al ingresar el digito. Asumiendo que no se quiere continuar");
		}

		if(  jugarDeNuevoInt >2 || jugarDeNuevoInt <1  ){
			System.out.println("Error al ingresar el digito. Asumiendo que no se quiere continuar");
		} else if(jugarDeNuevoInt == 1) { // cualquier cosa que no sea Y se va a tomar como que no desea continuars
			jugarDeNuevoBool = true;
		} 
		return jugarDeNuevoBool;

	}

	/*! \brief muestra sus cartas al jugador
	 \param Mazo mazo del jugador activo
	 \param String titulo del cuadro
	 */
	public int escogerCarta(Mazo mazoParametro, String tituloCuadro) {

		String cartas[] = mazoParametro.mazoToString(); // ahora esto recibe un mazo generico

		String opcion = null; // no creo que esto deba ser un while, ya que si alguien quiere cancelar no
								// puede
		do {
			opcion = String.valueOf(JOptionPane.showInputDialog(null, "seleccione una carta", tituloCuadro,
					JOptionPane.QUESTION_MESSAGE, null, cartas, cartas[0])); // cartas[0] es el valor por defecto
		} while (opcion == "null"); // el boton de cancelar no funciona porque esto esta dentro de un while CORREGIR

		int posicion = mazoParametro.getPosicionCarta(cartas, opcion);
		return posicion;
	}
	/*! \brief para cambiar el color del juego
	 \param String titulo del cuadro
	 */
	public String escogerColor(String tituloCuadro) {

		String[] colores = new String[] { "Rojo", "Verde", "Azul", "Naranja" }; // siempre van a ser los mismos

		String colorEscogido = null;
		do {
			// necesito que esto me devuelva un numero de 0 a n que represente la posicion
			// de la carta dentro del array
			// pero necesito que me imprima en si los valores de las cartas
			colorEscogido = String.valueOf(
					JOptionPane.showInputDialog(null, "Escoja un color para  jugar el siguiente turno", tituloCuadro,
							JOptionPane.QUESTION_MESSAGE, null, colores, colores[0])); // cartas[0] es el valor por
																						// defecto
		} while (colorEscogido == "null"); // el boton de cancelar no funciona porque esto esta dentro de un while
											// CORREGIR

		// System.out.println(tituloCuadro + " escoge el color: " + colorEscogido);

		// int posicion = mazoParametro.getPosicionCarta(cartas, opcion);
		return colorEscogido;
	}
	/*! \brief muestra el menu al jugador
	 /return int tipo de juego elegido
	 */
	public int menu() {
		String tipoDeJuego = null;	
		boolean rendirse = false;
		int tipoDeJuegoInt = 0;	

		do {
			try {
				tipoDeJuego = JOptionPane.showInputDialog(null,
						"Elige una opcion: \n1) jugador vs jugador\n2) jugador vs computadora\n", 
						"Menu Principal", JOptionPane.QUESTION_MESSAGE); 

				if(tipoDeJuego==null){ // boton cancelar
					System.out.println("Saliendo");
					rendirse = true;

				} else {
					tipoDeJuegoInt = Integer.parseInt(tipoDeJuego);

				}
																			// TRES
			} catch (Exception e) { // EL BOTONN DE CANCELAR NO HACE NADA POR ESTAR DENTRO DE UN CICLO // ARREGLAR
				JOptionPane.showMessageDialog(null, "Error al digitar el digito");
			}
		} while ( (tipoDeJuegoInt < 1 || tipoDeJuegoInt > 2) && rendirse==false);


		return tipoDeJuegoInt;

	}
	/*! \brief muestra un cuadro de si o no al jugador
	 \param String nombre del jugador
	 \return boolean si o no cancelar la carta
	 */
	public boolean cancelarBuscarPila(String nombreJugador) {
		boolean cancelar = true;
		int opcion = JOptionPane.showConfirmDialog(null, "desea cancelar la carta 'buscar en pila' jugada por su oponente", nombreJugador,
				JOptionPane.YES_NO_OPTION);
		if (opcion == 1) {
			cancelar = false;
		}
		return cancelar;
	}
}