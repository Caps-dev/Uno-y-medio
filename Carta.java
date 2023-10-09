//! \class Carta Carta de un mazo de uno
/*!
	Clase que crea las cartas con su respectivo valor y color o si es especial
   */

public class Carta {
	private int id; /* !< Identificador numerico para cada carta */
	private String color; /* !< Color de cada carta */
	private boolean esEspecial; /* !< Verifica si es especial o no */
	
	 /*! \brief Constructor de la carta
	  \param int id de la carta
	  \param String color de la carta
	  \param boolean si la carta es especial
	 */

	public Carta(int idParametro, String colorParametro, boolean esEspecialParametro) {
		id = idParametro;
		color = colorParametro;
		esEspecial = esEspecialParametro;
	}

	/*! \brief retorna el valor de la carta 
	 \return int el id de la carta 
	  */
	public int getId() {
		return id;
	}

	/*! \brief retorna si es especial 
	 \return boolean del atributo esEspecial; 
	 */
	public boolean getEsEspecial() {
		return esEspecial;
	}

	/*! \brief se otorgan las cartas especial dependiendo de su id 
	 \return String el valor de la carta
	 */
	public String getNumeroSwitch() {
		String valorCarta = "";
		switch (this.id) {
			case 9:
				valorCarta = "Come 2";
				break;
			case 10:
				valorCarta = "Come 3";
				break;
			case 11:
				valorCarta = "Cancelar";
				break;
			case 12:
				valorCarta = "Buscar dentro de la pila";
				break;
			default:
				valorCarta += this.id; // concatenar
		}
		return valorCarta;
	}

	/*! \brief se pasa a tipo String 
	 \return String carta en texto
	 */
	public String toString() {

		String texto = "";

		if (id < 9) {

			texto = "" + getNumeroSwitch() + " " + color + "\n";

		} else {
			texto = "" + getNumeroSwitch() + "\n";
		}

		return texto;
	}

	/*! \brief obtiene color 
	 \return String returna el color de la carta
	 */
	public String getColor() {
		return color;
	}

	/*! \brief otorga un color 
	 \param String color nuevo de la carta
	 */
	public void setColor(String colorPar) {
		color = colorPar;

	}

}