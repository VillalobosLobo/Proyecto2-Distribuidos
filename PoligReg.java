import javax.swing.*;
import java.awt.*;

public class PoligReg extends JPanel {
    private int n;
    private Coordenada[][] coordenadas;
    private Polygon[] poligonos;
    private double[] angulos;
    private Coordenada[] centros;

    public PoligReg(int n) {
        this.n = n;

        // Inicializa los arrays
        this.coordenadas = new Coordenada[n][];
        this.poligonos = new Polygon[n];
        this.angulos = new double[n];
        this.centros = new Coordenada[n];

        // Inicializa cada polígono y sus coordenadas
        for (int j = 0; j < n; j++) {
            int lados = (int) (Math.random() * 8 + 3); // Número aleatorio de lados entre 3 y 10
            this.coordenadas[j] = new Coordenada[lados];
            int centroX = (int) (Math.random() * 650 + 1);
            int centroY = (int) (Math.random() * 450 + 1);
            int radio = (int) (Math.random() * 80 + 10);
            this.poligonos[j] = new Polygon();
            this.angulos[j] = 0; // Inicialmente, no hay rotación
            this.centros[j] = new Coordenada(centroX, centroY);

            for (int i = 0; i < lados; i++) {
                double angulo = 2 * Math.PI * i / lados;
                coordenadas[j][i] = new Coordenada(centroX + radio * Math.cos(angulo), centroY + radio * Math.sin(angulo));
                poligonos[j].addPoint((int) coordenadas[j][i].abcisa(), (int) coordenadas[j][i].ordenada());
            }
        }

        Timer timer = new Timer(50, e -> {
            // Actualiza los ángulos de rotación para cada figura
            for (int j = 0; j < n; j++) {
                angulos[j] += Math.random() * 0.1; // Cambio aleatorio en la velocidad de rotación
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
            Polygon poligono = new Polygon();
            for (int i = 0; i < coordenadas[j].length; i++) {
                double x = coordenadas[j][i].abcisa() - centros[j].abcisa();
                double y = coordenadas[j][i].ordenada() - centros[j].ordenada();
                double xRotado = x * Math.cos(angulos[j]) - y * Math.sin(angulos[j]) + centros[j].abcisa();
                double yRotado = x * Math.sin(angulos[j]) + y * Math.cos(angulos[j]) + centros[j].ordenada();
                poligono.addPoint((int) xRotado, (int) yRotado);
            }
            g2d.drawPolygon(poligono);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polígonos Aleatorios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PoligReg(5)); // Puedes ajustar el número de polígonos aquí
        frame.setVisible(true);
    }
}



