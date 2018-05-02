import java.applet.Applet;
import java.awt.Event;
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
	int indice;
	static boolean movimiento = false;

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
					colision = true;
					indice = i;
					movimiento = true;
					contacto(i);
				} else {
				}
			}
			if (!pelota.intersects(plataformas.get(indice))) {
				movimiento = false;
				colision = false;
			}

			pelota.caer();
			repaint();
			try {
				Thread.sleep(50);
				contador++;
				if (contador % 10 == 0) {
					plataformas.add(new Plataforma(r.nextInt(600), 600, r.nextInt(50)));
				}
			} catch (InterruptedException e) {

			}
		}
	}

	public void contacto(int i) {
		pelota.y = plataformas.get(i).y - Plataforma.ALTURA;
		repaint();
	}

	public boolean keyDown(Event e, int tecla) {
		if (movimiento) {
			if (tecla == 1006) {
				pelota.desplazarse(Pelota.IZQUIERDA);
			}
			if (tecla == 1007) {
				pelota.desplazarse(Pelota.DERECHA);
			}
		}
		return true;
	}

}
