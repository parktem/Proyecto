import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota extends Rectangle {

	static final int DIM = 20;
	static final int DERECHA = 0;
	static final int IZQUIERDA = 1;
	static final int ARRIBA = 2;
	static final int ABAJO = 3;
	Color color = new Color(99, 9, 165);

	public Pelota() {
		super(280, 20, DIM, DIM);
	}

	public void dibujar(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}

	public void desplazarse(int direccion) {
		if (direccion == DERECHA) {
			x += 20;
		} else if (direccion == IZQUIERDA) {
			x -= 20;
		} else if (direccion == ARRIBA) {
			y -= 20;
		} else if (direccion == ABAJO) {
			y += 20;
		}
	}

}
