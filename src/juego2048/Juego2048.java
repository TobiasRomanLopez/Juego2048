package juego2048;

import java.awt.Point;
import java.util.Random;

public class Juego2048 {
	
	private int[][] cuadricula;
	private Random aleatorio;
	private int tamanio;
	private boolean quedaLugar;

	public Juego2048() {
		tamanio = 4;
		aleatorio = new Random(); 
		cuadricula=new int [tamanio][tamanio];
		
		for (int i = 0; i < cuadricula.length; i++) {
            for (int j = 0; j < cuadricula[i].length; j++) {
                cuadricula[i][j]=0;
            }
        }
	}
	
	public void moverArriba() {
		for (int columna = 0; columna < 4; columna++) {
			for (int i = 0; i < 4; i++) {
				for (int fila = 1; fila < 4; fila++) {
					if (cuadricula[fila - 1][columna] == 0) {
						conmutarFilas(fila -1, fila, columna);
	                } 
					else if (sigFilaEsIgual(fila -1, fila, columna)) {
	                   sumarEntreFilas(fila -1, fila, columna);
	                   break; // Evita que se combine dos veces en un mismo movimiento
	                }
	             }
	         }
	    }
	}

    public void moverAbajo() {
    }

    public void moverIzquierda() {
    }

    public void moverDerecha() {
    }
    
    public int[][] getCuadricula(){
    	return cuadricula;
    }
    
    //Las posiciones para agregar un numero van del 0 al 3
    public void agregarNumeroRandom() {
        int fila, columna;   
        do {
	        fila = aleatorio.nextInt(tamanio);
	        columna = aleatorio.nextInt(tamanio);
	    } 
	    while (cuadricula[fila][columna] != 0);
	
	    cuadricula[fila][columna] = (aleatorio.nextInt(2) + 1) * 2; // Puede ser 2 o 4, lo que hace es elegir un numero entre 0 y 1 al eso le suma uno y lo multiplica por dos 
	      
	 }
    
    private boolean sigFilaEsIgual(int fila1, int fila2,  int colum) {	//Verifica si el contenido de la fila1 es igual al de la fila2 siempre en la misma columna
    	if (cuadricula[fila1][colum] == cuadricula[fila2][colum]) {
    		return true;
    	}
    	return false;
    }
    
    private void sumarEntreFilas(int fila1, int fila2, int columna) {	//Hace la suma entre fila1 y fila2, en este caso como deben ser igual solo multiplica y al resultado lo almacena en fila1 luego en fila2 pone un "0"
    	 cuadricula[fila1][columna] *= 2;
         cuadricula[fila2][columna] = 0;  
    }
     
    private void conmutarFilas(int fila1, int fila2, int columna) {		//Conmuta el contenido de fila2 a fila1 f2-->f1 // y en f2 pone un cero ya que anteriormente verificamos con otro metodo que en f1 habia un 0
    	cuadricula[fila1][columna] = cuadricula[fila2][columna];
        cuadricula[fila2][columna] = 0;
    	
    }
    
    public boolean quedanLugares() {
    	quedaLugar = false;
    	for (int i = 0; i < cuadricula.length; i++) {
    		for (int j = 0; j < cuadricula[i].length; j++) {
    			quedaLugar = quedaLugar || cuadricula[i][j] ==0 ;
    		}
    	}
    	return quedaLugar;
    }
    
    private void mostrarMatriz() {
    	 for (int i = 0; i < cuadricula.length; i++) {
             for (int j = 0; j < cuadricula[i].length; j++) {
                 System.out.print(cuadricula[i][j] + " ");
             }
             System.out.println(); // Salto de línea después de cada fila
         }
    	 System.out.println();     // Salto de línea despues de cuatro filas
    	 
    }
    
    public static void main(String[] args) {
    	Juego2048 juego =new Juego2048();
 
    	 juego.mostrarMatriz();
    	 
    	 juego.agregarNumeroRandom();
    	 
    	 juego.mostrarMatriz();
    	 
    	 juego.agregarNumeroRandom();
    	 
    	 juego.mostrarMatriz();

    	 juego.agregarNumeroRandom();
    	
    	 juego.mostrarMatriz();
    	 
    	 juego.moverArriba();
    	 
    	 juego.mostrarMatriz();
    	 
    }

}
