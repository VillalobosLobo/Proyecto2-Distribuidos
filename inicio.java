import javax.swing.*;
import java.awt.*;

public class inicio extends JPanel{
	public static void main(String[]args){

		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Proyecto 2");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,600);
			frame.add(new PoligReg(4));
			frame.setVisible(true);
		});

	}
}
