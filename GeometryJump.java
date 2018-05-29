import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryJump extends Applet implements Runnable {

	String puntuacion;
	String canciones[] = { "INTERSTELLAR(BSO)", "AMELIE(BSO)", "PACMAN FEVER", "Starwars", "ARCADE3" };
	String[] path = { "canciones/Interstellar.wav", "canciones/Amelie.wav", "canciones/Pacman.wav",
			"canciones/Starwars.wav" };
	int puntos = 1;
	int poscancion;
	int contador = 0;
	int indice;
	int indice2 = 0;
	int contimg = 0;
	int vidas = 4;
	AudioClip cancion;
	Choice desplegable;
	Button boton;
	Thread animacion;
	Pelota pelota;
	List<Plataforma> plataformas;
	Random r;
	boolean start = false;
	static boolean colision;
	static boolean movimiento = false;
	boolean pause = false;
	Graphics noseve;
	Image imagen;
	Image imgvidas;
	Image[] imagenes;
	Image imgvictoria;
	Image imgderrota;
	Image imgpause;

	public void init() {
		setSize(600, 600);
		imagen = createImage(650, 650);
		noseve = imagen.getGraphics();
		Panel principal = new Panel();
		desplegable = new Choice();
		boton = new Button("Jugar");
		for (int i = 0; i < canciones.length; i++) {
			desplegable.add(canciones[i]);
		}
		principal.setLayout(new GridLayout(1, 2));
		principal.add(new Label("Elige la melodía : "));
		principal.add(desplegable);
		add(principal, "Center");
		add(boton, "South");
		r = new Random();
		plataformas = new ArrayList<Plataforma>();
		imagenes = new Image[4];
		imgvidas = getImage(getCodeBase(), "imagenesProyecto/vida.png");
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
		if (start) {
			noseve.fillRect(0, 0, 650, 650);
			imgpause = getImage(getCodeBase(), "imagenesProyecto/pause.png");
			if (puntos % 12 == 0) {
				if (contimg == imagenes.length - 1) {
					contimg = 0;
				} else {
					contimg++;
				}
			}
			noseve.drawImage(imagenes[contimg], 0, 0, 600, 600, this);
			pelota.dibujar(noseve);
			for (int i = 0; i < plataformas.size(); i++) {
				plataformas.get(i).dibujar(noseve);
			}
			for (int i = 0; i < vidas; i++) {
				noseve.drawImage(imgvidas, 20 * i + 20, 20, 20, 20, this);
			}
			if (pelota.y > 600 || pelota.y < 0) {
				animacion.stop();
				imgderrota = getImage(getCodeBase(), "imagenesProyecto/gameover.jpg");
				noseve.drawImage(imgderrota, 0, 0, 600, 600, this);
			} else if (puntos == 500) {
				animacion.stop();
				imgvictoria = getImage(getCodeBase(), "imagenesProyecto/victoria.jpg");
				noseve.drawImage(imgvictoria, 0, 0, 600, 600, this);
			}
			if (vidas == 0) {
				animacion.stop();
				imgderrota = getImage(getCodeBase(), "imagenesProyecto/gameover.jpg");
				noseve.drawImage(imgderrota, 0, 0, 600, 600, this);
			}
			puntuacion = String.valueOf(puntos);
			noseve.setColor(Color.BLUE);
			noseve.setFont(new Font("Arial", Font.BOLD, 22));
			noseve.drawString(puntuacion, 560, 20);
			g.drawImage(imagen, 0, 0, this);
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		while (true) {
			if (start) {
				for (int i = 0; i < plataformas.size(); i++) {
					plataformas.get(i).elevar();
				}
				for (int i = 0; i < plataformas.size(); i++) {
					if (plataformas.get(i).y < 0) {
						plataformas.remove(i);
						puntos++;
					}
				}
				for (int i = 0; i < plataformas.size(); i++) {
					if (pelota.intersects(plataformas.get(i))) {
						plataformas.remove(i);
						vidas--;
					}
				}
				repaint();
			}
			try {
				Thread.sleep(70);

				contador++;
				if (contador % 6 == 0) {
					plataformas.add(new Plataforma(r.nextInt(600), 600, r.nextInt(50)));
				}
			} catch (InterruptedException e) {

			}
		}
	}

	public boolean keyDown(Event e, int tecla) {
		if (tecla == 1006) {
			pelota.desplazarse(Pelota.IZQUIERDA);
		}
		if (tecla == 1007) {
			pelota.desplazarse(Pelota.DERECHA);
		}
		if (tecla == 1004) {
			pelota.desplazarse(Pelota.ARRIBA);
		}
		if (tecla == 1005) {
			pelota.desplazarse(Pelota.ABAJO);
		}
		return true;
	}

	public boolean action(Event ev, Object obj) {
		if (ev.target instanceof Choice) {
			poscancion = desplegable.getSelectedIndex();
		} else {
			try {
				cancion = getAudioClip(new URL(getDocumentBase(), path[poscancion]));
			} catch (MalformedURLException e) {

			}
			cancion.play();
			this.removeAll();
			start = true;

		}
		return true;
	}

}
