package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyBoton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel Label = new JLabel();
	private JPanel EstePanel=this;
	private boolean Habilitado;
	private Color 	mouseEnteredColor = new Color(0, 156, 223),
					mouseExitedColor = SystemColor.textHighlight,
					mouseEnteredDeshabilitadoColor = Color.LIGHT_GRAY,
					mouseExitedDeshabilitadoColor = Color.GRAY;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param Width
	 */
	public MyBoton(int x, int y, int Width) {
		
		setLocation(x, y);
		setSize(Width, 40);;
		setLayout(null);
		Habilitado=true;
		setBackground(mouseExitedColor);
		setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		Label.setForeground(SystemColor.controlLtHighlight);
		Label.setFont(new Font("Roboto", Font.PLAIN, 18));
		Label.setBounds(0, 0, Width, 42);
		this.add(Label);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Habilitado==false) {
					EstePanel.setBackground(mouseEnteredDeshabilitadoColor);					
					return;
				}
				EstePanel.setBackground(mouseEnteredColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (Habilitado==false) {
					EstePanel.setBackground(mouseExitedDeshabilitadoColor);					
					return;
				}
				EstePanel.setBackground(mouseExitedColor);
			}
		});
	}
	public void setTextFont(Font font) {
		this.Label.setFont(font);
	}
	public Font getTextFont() {
		return this.Label.getFont();
	}
	public String getbtnText () {
		return this.Label.getText();
	}
	public void setbtnText (String text) {
		this.Label.setText(text);;
	}

	public boolean getHabilitado() {
		return this.Habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.Habilitado = habilitado;
		ActualizarColores();
	}
	
	public void setbtncolor(Color Mouse_Entered,Color Mouse_Exited) {
		this.mouseEnteredColor=Mouse_Entered;
		this.mouseExitedColor=Mouse_Exited;
		ActualizarColores();
	}
	
	public void setDeshabilidadobtncolor(Color Mouse_Entered,Color Mouse_Exited) {
		this.mouseEnteredDeshabilitadoColor=Mouse_Entered;
		this.mouseExitedDeshabilitadoColor=Mouse_Exited;
		ActualizarColores();
	}
	
	@Override
	public void setSize(int width, int height) {
		Label.setSize(width,height);
		super.setSize(width,height);
       
    }
	@Override
	public void setSize(Dimension d) {
	 	Label.setSize(d);
		super.setSize(d);
    }
	@Override
	public void setBounds(int x, int y, int width, int height) {
		Label.setBounds(0, 0, width, height);
		super.setBounds(x, y, width, height);
    }
	
	@Override
	public void setBounds(Rectangle r) {
		Label.setBounds(0, 0, r.width, r.height);
        super.setBounds(r.x, r.y, r.width, r.height);
    }
	private void ActualizarColores() {
		if (this.Habilitado) {
			setBackground(mouseExitedColor);
		}else{
			setBackground(mouseExitedDeshabilitadoColor);
		}
		
	}
	
	

}
