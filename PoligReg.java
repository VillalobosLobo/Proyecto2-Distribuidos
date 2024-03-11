import javax.swing.*;
import java.awt.*;

public class PoligReg extends JPanel{
	//Variables que vamo a usar
	private Coordenada[] coordenada;
	private Polygon poligono = new Polygon();	

	//Constructor de la clase
	public PoligReg(int n){
		Coordenada[] coordenada=new Coordenada[n];
		coordenada[0]=new Coordenada(0,0);
		coordenada[1]=new Coordenada(100,0);
		coordenada[2]=new Coordenada(100,100);
		coordenada[3]=new Coordenada(0,100);

		Timer timer = new Timer(50, e -> {
			poligono.reset();

			//Con esto ponemos los puntos
			for(int i=0;i<n;i++){
				poligono.addPoint((int)coordenada[i].abcisa(),(int)coordenada[i].ordenada());
			}

			// Incrementa las coordenadas
			for(int i=0;i<n;i++){
				coordenada[i]=new Coordenada(coordenada[i].abcisa()+1,coordenada[i].ordenada()+1);
			}

			// Vuelve a dibujar
			repaint();
        	});
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawPolygon(poligono);
	}
} 
