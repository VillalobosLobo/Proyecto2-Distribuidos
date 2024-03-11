import javax.swing.*;
import java.awt.*;

public class PoligReg extends JPanel{
	//Variables que vamo a usar
	private int lados,radio=100;
	private Coordenada[] coordenada;
	private Polygon poligono = new Polygon();	

	//Constructor de la clase
	public PoligReg(int n){

		//Pa' que genere poligonos regulare de 3 a 10 vertices y radio
		this.lados = (int)(Math.random()*10+3);
		this.radio = (int)(Math.random()*120+10);

		Coordenada[] coordenada= new Coordenada[lados];
		//Para guardar las coordenadas
		for(int i=0;i<lados;i++){
			double angulo=2*Math.PI*i/lados;
			coordenada[i]=new Coordenada(400+radio * Math.cos(angulo), 300+radio * Math.sin(angulo));
		}

		Timer timer = new Timer(50, e -> {
			poligono.reset();

			//Con esto ponemos los puntos a nuestro poligono
			for(int i=0;i<lados;i++){
				poligono.addPoint((int)coordenada[i].abcisa(),(int)coordenada[i].ordenada());
			}

			// Incrementa las coordenadas
			for(int i=0;i<lados;i++){
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
