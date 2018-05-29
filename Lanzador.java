import java.awt.Button;
import java.awt.Choice;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;

public class Lanzador extends Frame {

	List actual;
	Choice desplegable;
	String[] path = { "canciones/Interstellar.mp3" };
	String canciones[] = { "INTERSTELLAR(BSO)", "AMELIE(BSO)", "ARCADE1", "ARCADE2", "ARCADE3" };
	Button boton;

	public static void main(String[] args) {
		Lanzador lanzador = new Lanzador();

	}

	public Lanzador() {
		super();
		setup();
		pack();
		setSize(400, 300);
		show();
	}

	public void setup() {
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
	}

	public boolean handleEvent(Event ev) {
		if (ev.id == Event.WINDOW_DESTROY) {
			System.exit(0);
			return true;
		} else if (ev.id == Event.ACTION_EVENT) {
			if (ev.target instanceof Choice) {
				GeometryJump juego;
				int prueba = desplegable.getSelectedIndex();
				System.out.println(prueba);
			} else {
				System.out.println("hola");
			}
		}
		return false;
	}

}
