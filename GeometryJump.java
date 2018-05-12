import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryJump extends Applet implements Runnable {

	int puntos = 0;
	Thread animacion;
	Pelota pelota;
	List<Plataforma> plataformas;
	Random r;
	int contador = 0;
	static boolean colision;
	int indice;
	int indice2 = 0;
	static boolean movimiento = false;
	Image[] imagenes;
	int contimg = 0;
	int puntuacion = 1;

	public void init() {
		setSize(600, 600);
		r = new Random();
		plataformas = new ArrayList<Plataforma>();
		imagenes = new Image[2];
		for (int i = 0; i < imagenes.length; i++) {
			imagenes[i] = getImage(getCodeBase(), "imagenesProyecto/img" + (i + 1) + ".jpg");
		}
		pelota = new Pelota();
		plataformas.add(new Plataforma(r.nextInt(600), 600, r.nextInt(50)));
	}

	public void start() {
		animacion = new Thread(this);
		animacion.start();
	}

	public void paint(Graphics g) {
		if (puntuacion % 10 == 0) {
			if (contimg == imagenes.length - 1) {
				contimg = 0;
			} else {
				contimg++;
			}
		}
		g.drawImage(imagenes[contimg], 0, 0, this);
		pelota.dibujar(g);
		for (int i = 0; i < plataformas.size(); i++) {
			plataformas.get(i).dibujar(g);
		}
		if (pelota.y == 600) {
			animacion.stop();
			g.drawString("GAME OVER", 280, 300);
		}
		g.drawString("" + puntuacion, 500, 20);
	}

	public void run() {
		while (true) {
			for (int i = 0; i < plataformas.size(); i++) {
				plataformas.get(i).elevar();
			}
			for (int i = 0; i < plataformas.size(); i++) {
				if (pelota.intersects(plataformas.get(i))) {
					colision = true;
					if (indice2 == 0) {
						puntuacion++;
						indice2++;
					}
					if (colision && i > indice) {
						indice2 = 0;
					}
					indice = i;
					contacto(i);
				}
			}
			if (!pelota.intersects(plataformas.get(indice))) {
				movimiento = false;
				colision = false;
			}

			pelota.caer();
			repaint();
			try {
				Thread.sleep(70);

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
		if (tecla == 1006) {
			pelota.desplazarse(Pelota.IZQUIERDA);
		}
		if (tecla == 1007) {
			pelota.desplazarse(Pelota.DERECHA);
		}
		return true;
	}

}
