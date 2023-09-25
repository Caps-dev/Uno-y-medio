public class Carta {
	// atributos de clase
	// Declaracion de atributos:
   	private int id; // un identificador numerico para cada carta
   	private String color; // un atributo de tipo texto que contiene el color
   	private String simbolo;  // esto contiene el numero de la carta o el texto especial de la carta

   	public Carta (int idParametro, String colorParametro){
   		//System.out.println("-> Se invoca al constructor que recibe el numero y el palo");
   		id = idParametro;
   		color = colorParametro;
   	}


   	// hacer metodos set y get
   	// por el momento solo vamos a usar el constructor

   	public String getNumeroSwitch(){
		String valorCarta = "";
		switch(this.id){
			// 1 -> As, 11-> J, 12 -> Q, 13 -> K
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

	public String toString(){

		String texto = "";

		if (id<9){

			texto = "" +  getNumeroSwitch() + " " + color + "\n";

		} else {
			texto = "" +  getNumeroSwitch() + "\n";			
		}


		return texto;
	}

	public String getColor(){
		return color;
	}

	public String getSimbolo(){
		return simbolo;
	}






}