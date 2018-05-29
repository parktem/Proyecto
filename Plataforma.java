import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plataforma extends Rectangle {

	static final int ALTURA = 20;
	int posX, posY, anchura;
	Color color = new Color(171, 53, 28);
	int velY;

	public Plataforma(int posX, int posY, int anchura) {
		super(posX, posY - ALTURA, anchura + 50, ALTURA);
		if (x > 550) {
			x -= 50;
		}
		velY = (int) (Math.random() * 15) + 3;
		this.anchura = anchura;
	}

	public void dibujar(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	public void elevar() {
		y -= velY;

	}

}
