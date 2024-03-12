import javax.swing.*;
import java.awt.*;

public class PoligReg extends JPanel {
    private int lados, radio, centroX, centroY, n;
    private Coordenada[][] coordenadas;
    private Polygon[] poligonos;

    public PoligReg(int n) {
        this.n = n;
        this.lados = (int) (Math.random() * 10 + 3);
        this.radio = (int) (Math.random() * 100 + 10);

        // Inicializa los arrays
        this.coordenadas = new Coordenada[n][lados];
        this.poligonos = new Polygon[n];

        // Inicializa cada polígono y sus coordenadas
        for (int j = 0; j < n; j++) {
	    centroX = (int) (Math.random() * 650 + 1);
            centroY = (int) (Math.random() * 450 + 1);

            poligonos[j] = new Polygon();

            for (int i = 0; i < lados; i++) {
                double angulo = 2 * Math.PI * i / lados;
                coordenadas[j][i] = new Coordenada(
                        centroX + radio * Math.cos(angulo) + j * 30, // Añadido un desplazamiento en X
                        centroY + radio * Math.sin(angulo)
                );
            }
        }

        Timer timer = new Timer(50, e -> {
            // Actualiza las coordenadas de cada polígono
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < lados; i++) {
                    coordenadas[j][i] = new Coordenada(
                            coordenadas[j][i].abcisa() + 1,
                            coordenadas[j][i].ordenada() + 1
                    );
                }
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

        // Dibuja todos los polígonos
        for (int j = 0; j < n; j++) {
            poligonos[j].reset();

            for (int i = 0; i < lados; i++) {
                poligonos[j].addPoint((int) coordenadas[j][i].abcisa(), (int) coordenadas[j][i].ordenada());
            }

            g2d.drawPolygon(poligonos[j]);
        }
    }
}

