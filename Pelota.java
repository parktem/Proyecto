import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota extends Rectangle {

	static final int DIM = 20;

	public Pelota() {
		super(280, 20, DIM, DIM);
	}

	public void dibujar(Graphics g) {
		g.fillOval(x, y, width, height);
	}

	public void caer() {
		if (GeometryJump.colision) {

		} else {
			y += 10;
		}
	}

}
