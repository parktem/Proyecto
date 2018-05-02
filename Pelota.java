import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota extends Rectangle {

	static final int DIM = 20;
	public static final int DERECHA = 0;
	public static final int IZQUIERDA = 1;

	public Pelota() {
		super(280, 20, DIM, DIM);
	}

	public void dibujar(Graphics g) {
		g.fillOval(x, y, width, height);
	}

	public void caer() {
		if (!GeometryJump.colision) {
			y += 10;
		}

	}

	public void desplazarse(int direccion) {
		if (direccion == DERECHA) {
			x += 40;
		} else if (direccion == IZQUIERDA) {
			x -= 40;
		}
	}

}
