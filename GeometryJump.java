import java.applet.Applet;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryJump extends Applet implements Runnable {

	Thread animacion;
	Pelota pelota;
	List<Plataforma> plataformas;
	Random r;
	int contador = 0;
	static boolean colision;

	public void init() {
		setSize(600, 600);
		r = new Random();
		plataformas = new ArrayList<Plataforma>();
		plataformas.add(new Plataforma(r.nextInt(600), 600, r.nextInt(50)));
		pelota = new Pelota();
	}

	public void start() {
		animacion = new Thread(this);
		animacion.start();
	}

	public void paint(Graphics g) {
		pelota.dibujar(g);
		for (int i = 0; i < plataformas.size(); i++) {
			plataformas.get(i).dibujar(g);
		}
	}

	public void run() {
		while (true) {
			for (int i = 0; i < plataformas.size(); i++) {
				plataformas.get(i).elevar();
			}
			for (int i = 0; i < plataformas.size(); i++) {
				if (pelota.intersects(plataformas.get(i))) {
					contacto(i);
				}
			}
			pelota.caer();
			repaint();
			try {
				Thread.sleep(50);
				contador++;
				if (contador % 20 == 0) {
					plataformas.add(new Plataforma(r.nextInt(600), 600, r.nextInt(50)));
				}
			} catch (InterruptedException e) {

			}
		}
	}

	public void contacto(int i) {
		pelota.y = plataformas.get(i).y;
		colision = true;
		repaint();
	}

}
