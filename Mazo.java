public class Mazo {

	private Carta [] mazo ;

	public Carta[] getMazo() {
		return mazo;
	}

	public int getTamanio() {
		 int tamanio = mazo.length;
		 return tamanio;
	}


	public String obtenerColor(int numero){ 
		String color = "Rojo";

		if(numero == 0){
			color = "Rojo";
		} else if(numero == 1){
			color = "Verde"; 
		} else if (numero == 2){
			color = "Azul"; 
		} else if (numero == 3){
			color = "Naranja"; 
		} else {
			color = "Negro" ; //las cartas especiales, es un valor arbitrario
		}
	return color;
	}

	public void getColor(){
		for(int i = 0 ; i < mazo.length ; i++){
			System.out.println(mazo[i].getColor());

		}

	}



	
	public void generarMazo (){ //generar mazo
		mazo = new Carta[88];

		//mazo[0] = new Carta(1,"A");
		int celda = 0;
		for (int k= 0; k <2; k++){
			//System.out.println(k);
			for(int j = 0 ; j < 4 ; j++){ // 4 colores para las cartas basicas
				for(int i = 0; i <= 8; i++){
					//Carta carta = new Carta(i, obtenerPalo(j));
					mazo[celda] = new Carta(i, obtenerColor(j),false); //no son cartas especiales
					celda++;
					//System.out.println(celda);
				}
			}

		}
		
		celda = 72;
		for(int j = 0 ; j < 4 ; j++){ // 4 artas por tipo
			for(int i = 9; i <= 12; i++){
				//Carta carta = new Carta(i, obtenerPalo(j), true);
				mazo[celda] = new Carta(i, "Negro",true);
				celda++;
			}	
		}
		
	
	}


	public String toString(){

		String contenido = ""; 
		if(mazo==null || mazo.length == 0 ){
			contenido = ""; ;
		} else{
			for(int i = 0 ; i < mazo.length; i++){
				contenido += mazo[i];
			}
		}


		return contenido;
	}

	public void revolver (){
		for(int i = 0 ; i < mazo.length ; i++){
			int numeroAleatorio = (int) (Math.random()*mazo.length);
			Carta temporal = mazo[i];
			mazo[i] = mazo[numeroAleatorio];
			mazo[numeroAleatorio] = temporal;
		}
	}

	//primero se da la carta, luego se recorta el mazo
	public Carta[] darCarta(int n, int posicion){ //recibimos un arreglo de cartas como parametro

		Carta [] arreglo = new Carta [n];

		if(mazo.length>0){
			// hacemos una copia del arreglo // puede que no sea necesario
			Carta [] nuevoArreglo = new Carta [mazo.length-n]; // hacemos una copia del arreglo // puede que no sea necesario	


			if(posicion==999){
				// para repartir cartas del mazo
				for(int i = 0; i < n; i++){
					arreglo[i] = mazo[i]; // tomamos los primeros elementos
				}	

				for(int i = 0; i < mazo.length-n; i++){ // siempre hay que reducir el mazo
					nuevoArreglo[i] = mazo[i+n];
				}

			} else{
				// solo se puede jugar una carta a la vez
				arreglo[0] = mazo[posicion];
				int celda = 0;
				int i = 0;

				while(i<mazo.length){


					if(posicion!=i){
						nuevoArreglo[celda]= mazo[i];
						i++;
						celda++;

					} else{
						i++;
						//nuevoArreglo[celda]= mazo[i];
					}


				}
					
				}


			mazo = nuevoArreglo;

			} else {
				System.out.println("No hay cartas para dar");
			}



		return arreglo;
	}

	
	public void recibirCarta(Carta[] arregloParametro){
		//Carta [] arreglo = new Carta [n]

		if(mazo==null || mazo.length == 0 ){
			mazo = arregloParametro;
		} else {

			Carta [] nuevoArreglo = new Carta[mazo.length+arregloParametro.length]; // las cartas que vamos a recibir
		//int celda = 0;

			for (int i = 0; i < mazo.length; i++){// primero pongo los elementos que ya estaban en la mano

				nuevoArreglo[i] = mazo[i];

			}

			for (int i = 0; i < arregloParametro.length; i++){// primero pongo los elementos que ya estaban en la mano

				nuevoArreglo[i+mazo.length] = arregloParametro[i]; // empezamos en el tamanio de la mano original + i

			}

			mazo = nuevoArreglo;

		}


	}

	public int getPosicionCarta(String[] arregloParametro, String cartaElegida){

		int posicion = 0;
		if(cartaElegida != null){	
			for (int i = 0; i < arregloParametro.length; i++){// primero pongo los elementos que ya estaban en la mano
				if (arregloParametro[i] == cartaElegida){
					posicion = i; //si tengo dos voy a tomar el ultimo
				}
			}
		}

		return posicion;
	}

	public String[] mazoToString(){ // toma el mazo actual y devuelve arreglo de String

		String [] contenido = new String [mazo.length]; 
		for(int i = 0 ; i < mazo.length; i++){
			contenido[i] = ""+mazo[i];
		}
		return contenido;

	}

	public Carta getUltimaCarta(){ //cambiando el tipo de esto

		//String ultimaCarta ;

		Carta ultimaCarta;


		if(mazo==null || mazo.length == 0 ){
	//		ultimaCarta = "";
			ultimaCarta = null;
		} else {

			ultimaCarta = mazo[mazo.length-1];


		}

		return ultimaCarta;


	}


	public boolean esCartaValida(Carta carta1,Carta ultimaCarta){ //queremos ver si carta1 es valida

		String colorUltimaCarta = ultimaCarta.getColor();
		String colorCarta1 = carta1.getColor();
		boolean esEspecial = carta1.getEsEspecial();

		boolean test = ( 
			( ultimaCarta.getId()==12 ) || //si la ultima carta jugada fue el buscar pila se puede usar cualquier carta porque siempre va linkeada
			( esEspecial==true ) || 
			( colorUltimaCarta.equals(colorCarta1) ) || // descubri esto despues de muchos errores
			( ultimaCarta.getId()==carta1.getId() )  // no hace falta hacer todo el switch a string si ya tenemos el id
			) ;
		return test;

	}

	public boolean esCartaEspecial(Carta carta){ //queremos ver si una carta del mazo es especial

		boolean test = carta.getEsEspecial(); 
		return test;

	}

	public void cambiarColor(Carta carta, String color){ //queremos ver si una carta del mazo es especial

		carta.setColor(color); // no importa si le caemos encima al color de la carta especial
		System.out.println("La siguiente carta debe ser color: " + color);

	}


	public Carta [] getCartaValida(Carta ultimaCartaParam){ //comparamos el mazo actual contra una carta externa y vemos que cartas del mazo actual se pueden jugar

		int celda = 0; // hacemos un contador para la celda aparte para poder hacerle skip a las cartas no validas
		int i = 0 ;
		int cartasValidas = 0;

		while(i<mazo.length){ // un ciclo para contar las cartas validas

			if (   esCartaValida(mazo[i],ultimaCartaParam)  ){ 
				cartasValidas++;
				i++;

			} else{
				i++;
					//nuevoArreglo[celda]= mazo[i];
				}
		}

		Carta [] nuevoArreglo = new Carta [cartasValidas]; 

		i = 0 ;

		while(i<mazo.length){ // un ciclo para agregar las cartas validas

			if(    esCartaValida(mazo[i],ultimaCartaParam)   ){ //falta agregar la comparacion por numero y agregar la comparacion para las cartas especiales

				nuevoArreglo[celda]= mazo[i];
				i++;
				celda++;

			} else{
				i++;
					//nuevoArreglo[celda]= mazo[i];
				}
		}
		

		return nuevoArreglo;

//entender por que ya no funciona


	}


	public String convertirArraytoString(Carta [] arreglo){
		String contenido = ""; 
		if(arreglo==null || arreglo.length == 0 ){
			contenido = ""; ;
		} else{
			for(int i = 0 ; i < arreglo.length; i++){
				contenido += arreglo[i];
			}
		}


		return contenido;
	}


	





}