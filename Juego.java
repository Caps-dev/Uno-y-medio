public class Juego {
	Interfaz interfaz = new Interfaz();
	int opcion;
	boolean turno = true;

	public boolean finJuego(Mazo mano1Parametro, Mazo mano2Parametro){
		boolean activo = true;
		if(mano1Parametro.getTamanio() < 1 ){
				System.out.println("Jugador 1 gana");
				activo = false;
			}
			if(mano2Parametro.getTamanio() < 1 ){
				System.out.println("Jugador 2 gana");
				activo = false;
			}
		return activo;
	}


}