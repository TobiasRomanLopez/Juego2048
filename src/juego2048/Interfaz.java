package juego2048;

import javax.swing.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import java.awt.*;
import java.awt.event.*;

public class Interfaz extends JFrame{
	
    private JButton[][] cuadrados;
    private JLabel[][] etiquetasNumeros;
	private Juego2048 juego;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.setVisible(true);   //anteriormente windows.frame.setvisible(true) no permitia centrar la ventana
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void actualizarInterfaz() {
        int[][] cuadricula = juego.getCuadricula();											//creo una matriz auxiliar
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cuadricula[i][j] == 0) {												//comparo si en la posicion i,j hay un 0
                    etiquetasNumeros[i][j].setText("");										// si es asi en la etiqueta pongo el texto vacio
                } else {
                    etiquetasNumeros[i][j].setText(String.valueOf(cuadricula[i][j]));		//sino pongo en la etiqueta el numero que quedo en la matriz
                }
            }
        }
    }
	
	private boolean noPerdio() {
		return juego.quedanLugares();
	}
	
	private void leerTeclado() {
		
		addKeyListener(new KeyListener() {	

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				juego.moverDerecha();
				System.out.println("Se presiono Derecha"); //a modo de prueba
			}
				
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				juego.moverIzquierda();;
				System.out.println("Se presiono Izquierda");
			}
				
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				juego.moverArriba();
				System.out.println("Se presiono Arriba");
			}
				
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				juego.moverAbajo();
				System.out.println("Se presiono Abajo");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
		
		});
	}	
	
	private void initialize() {
		this.setBounds(100, 100, 400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Configura el tamaño de la ventana (ancho, alto)
        setSize(400, 400);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);		
		
		 // Inicializar los cuadrados y las etiquetas que mostraran los numeros
        cuadrados = new JButton[4][4];
        etiquetasNumeros = new JLabel[4][4];
        juego = new Juego2048();
		
        //Se divide en 4x4 el JPanel
		JPanel panelJuego = new JPanel(new GridLayout(4, 4));
        panelJuego.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Inicializar los cuadrados y las etiquetas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cuadrados[i][j] = new JButton();
                cuadrados[i][j].setPreferredSize(new Dimension(80, 80));
                cuadrados[i][j].setEnabled(false);
                etiquetasNumeros[i][j] = new JLabel("", SwingConstants.CENTER);
                etiquetasNumeros[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                etiquetasNumeros[i][j].setHorizontalAlignment(SwingConstants.CENTER);//intento de centrar el texot de la etiqueta
                
             // Añade el JLabel a cada JButton
                cuadrados[i][j].add(etiquetasNumeros[i][j]);
                
             //Añade los cuadrados(la grilla) al JPanel   
                panelJuego.add(cuadrados[i][j]);
            }
        }
        
        this.add(panelJuego, BorderLayout.CENTER);  
        
        this.setVisible(true);
        
        //El la situacion inicial del juego se debe agregar dos numeros random para que el usuario posteriormente elija hacia que direccion quiere ir
        juego.agregarNumeroRandom();
        juego.agregarNumeroRandom();
        actualizarInterfaz();
        
        //Aca quizas deberia un while(noPerdio) que deberia estar leyendo el teclado, 
        //llamar a los metodos de juego mover arriba, etc, posteriormente agregar un numeroRamdom y actualizarInterfaz
        //falta hacer que espere a que el jugador toque una flecha para seguir agregando numeros y actualizando la interfaz
        
      
        
//        while (noPerdio()) {
//        	leerTeclado();
//        	juego.agregarNumeroRandom();
//			actualizarInterfaz();
			
//			actualizarInterfaz();
//		}
      
	}

}
