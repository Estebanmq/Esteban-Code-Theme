package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Director;
import vista.DialogoDirectorAlta;

public class CtrlDirectorAlta implements ActionListener {
	
	private DialogoDirectorAlta dialogoDirectorAlta;
	private Director Director;
	
	public CtrlDirectorAlta() {
		
		this.setDialogoDirectorAlta(new DialogoDirectorAlta());
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		this.getDialogoDirectorAlta().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "btnAceptar" :
				System.out.format("Ha pulsado: %s\n", e.getActionCommand());
				break;
			case "btnCancelar" :
				this.getDialogoDirectorAlta().dispose();
				break;
		}
	}

	public DialogoDirectorAlta getDialogoDirectorAlta() {
		return dialogoDirectorAlta;
	}

	public void setDialogoDirectorAlta(DialogoDirectorAlta dialogoDirectorAlta) {
		this.dialogoDirectorAlta = dialogoDirectorAlta;
	}

	public Director getDirector() {
		return Director;
	}

	public void setDirector(Director director) {
		Director = director;
	}

}
