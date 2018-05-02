import java.awt.Graphics;
import java.awt.Rectangle;

public class Plataforma extends Rectangle {

	static final int ALTURA = 20;
	int posX, posY, anchura;

	public Plataforma(int posX, int posY, int anchura) {
		super(posX, posY - ALTURA, anchura + 50, ALTURA);
		if (posX > 550) {
			posX -= 50;
		}
		this.anchura = anchura;
		this.posX = posX;
		this.posY = posY;
	}

	public void dibujar(Graphics g) {
		g.fillRect(x, y, width, height);
	}

	public void elevar() {
		y -= 10;

	}

}
