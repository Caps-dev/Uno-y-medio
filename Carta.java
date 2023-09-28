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


   	public int getId(){
   		return id;
   	}


   	// hacer metodos set y get
   	// por el momento solo vamos a usar el constructor

   	public String getNumeroSwitch(){ // deberiamos cambiar este nombre por uno mejor, al inicio lo hice con este nombre pero creo que no es el mejor
		String valorCarta = "";
		switch(this.id){
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

	
	public void setColor(String colorPar){ //principalmente hago este metodo para las cartas especiales donde la gente escoge el color que quiere que se juego
		color = colorPar;

	}

	public String getSimbolo(){
		return simbolo;
	}






}