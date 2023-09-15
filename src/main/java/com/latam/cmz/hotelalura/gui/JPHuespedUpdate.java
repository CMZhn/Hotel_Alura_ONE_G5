package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.modelo.Huesped;
import com.latam.cmz.hotelalura.modelo.Nacionalidad;
import com.latam.cmz.hotelalura.mytools.MyDateConver;

import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JPHuespedUpdate extends JPanel {
	private JLabel Titulo,TituloID, Logo;
	private MyTxtField Nombre, Apellido, Telefono;
	private MyJDateChooser FechaN;
	private MyComboBox<String> CBNacionalidad;
	private MyBoton btnActualizar, btnCancelar;
	private List<Nacionalidad> Nacionalidades;
	private Huesped PHuesped;
	private JPanel EstePanel=this;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPHuespedUpdate(List<Nacionalidad> nacionalidades) {
		this.Nacionalidades=nacionalidades;
		setSize(890, 521);
		setLayout(null);
		setBackground(Color.WHITE);
		int dy=92;
		int y0=142;
		int y1=y0+dy+2;
		int y2=y1+dy+2;
		int xL=45;
		int wL=400;
		
		int xR=xL+wL;
		int wR=wL;
		
		Titulo = new JLabel("SISTEMA DE EDICIÓN DE HUÉSPED");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setForeground(new Color(12, 138, 199));
		Titulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		Titulo.setBounds(150, 22, 480, 42);
		this.add(Titulo);
		TituloID = new JLabel("");
		TituloID.setHorizontalAlignment(SwingConstants.LEFT);
		TituloID.setForeground(new Color(12, 138, 199));
		TituloID.setFont(new Font("Roboto Black", Font.BOLD, 24));
		TituloID.setBounds(640, 22, 230, 42);
		this.add(TituloID);
		
		Logo = new JLabel("");
		Logo.setIcon(DirectorioImg.ico("Ha-100px.png"));
		Logo.setBounds(36, 11, 104, 107);
		this.add(Logo);
		
		Nombre= new MyTxtField(MyTxtField.Type_Name);
		Nombre.setBackground(new Color(255, 255, 255));
		Nombre.setLabelText("NOMBRE");
		Nombre.setLocation(xL,y0);
		Nombre.setWhith(wL);
		this.add(Nombre);
		
		Apellido= new MyTxtField(MyTxtField.Type_Name);
		Apellido.setBackground(new Color(255, 255, 255));
		Apellido.setLabelText("APELLIDO");
		Apellido.setLocation(xR,y0);
		Apellido.setWhith(wR);
		this.add(Apellido);

		FechaN= new MyJDateChooser();
		FechaN.setBackground(new Color(255, 255, 255));
		FechaN.setLabelText("FECHA DE NACIMIENTO");
		FechaN.setLocation(xL,y1);
		FechaN.setWhith(wL);
		this.add(FechaN);
		
		CBNacionalidad = new MyComboBox<String>();
		CBNacionalidad.setBackground(new Color(255, 255, 255));
		CBNacionalidad.setLabelText("NACIONALIDAD");
		CBNacionalidad.setLocation(xR,y1);
		CBNacionalidad.setWhith(wR);
		for (Nacionalidad N:Nacionalidades) {
			CBNacionalidad.addCBItem(N.getGentilicio());
		}
		this.add(CBNacionalidad);
		
		
		Telefono= new MyTxtField(MyTxtField.Type_Phone);
		Telefono.setBackground(new Color(255, 255, 255));
		Telefono.setLabelText("TELEFONO");
		Telefono.setLocation(xL,y2);
		Telefono.setWhith(wL);
		this.add(Telefono);
		
		int bx0=300;
		int by0=y2+dy+20;
		int bw0=200;
		int bx1=bx0+bw0+100;
		int by1=by0;
		int bw1=bw0;
	
		btnActualizar = new MyBoton(bx0,by0,bw0);
		btnActualizar.setbtnText("ACTUALIZAR");
		this.add(btnActualizar);
		
		btnCancelar = new MyBoton(bx1,by1,bw1);
		btnCancelar.setbtnText("CANCELAR");
		this.add(btnCancelar);

	}

	public void addbtnActualizarMouseListener(MouseAdapter MouseAdapter) {
		btnActualizar.addMouseListener(MouseAdapter);
	}
	public void addbtnCancelarMouseListener(MouseAdapter MouseAdapter) {
		btnCancelar.addMouseListener(MouseAdapter);
	}
	
	public boolean ValidadarDatos(boolean MostrarMensaje) {
		if(Nombre.getTextValue()==null|Nombre.getTextValue().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Nombre invalido \n"+
						"Debe de ingresar un nombre valido");
			}
			
			Nombre.TextFieldFocus();
			return false;
		}
		if(Nombre.DeletefinalSpace()) {
			Nombre.setTFforeground(Color.BLACK);		
		}
		
		if(Apellido.getTextValue()==null|Apellido.getTextValue().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Apellido invalido \n"+
						"Debe de ingresar un apellido valido");
			}
			Apellido.TextFieldFocus();
			return false;
		}
		if(Apellido.DeletefinalSpace()) {
			Apellido.setTFforeground(Color.BLACK);		
		}
		if(FechaN.getDateValue()==null) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Fecha de nacimiento invalida \n"+
						"Debe de ingresar una fecha valida");
			}
			FechaN.DateValueclear();
			FechaN.DateFieldFocus();
			return false;
		}
		
		if(Telefono.getTextValue()==null|Telefono.getTextValue().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Telefono invalido \n"+
						"Debe de ingresar un telefono valido");
			}
			Telefono.TextFieldFocus();
			return false;
		}
		if(Telefono.DeletefinalSpace()) {
			Telefono.setTFforeground(Color.BLACK);		
		}
		
		
		if (CBNacionalidad.getCBSelectedItem().toString()==null|| 
				CBNacionalidad.getCBSelectedItem().toString().isBlank()|| 
				CBNacionalidad.getCBSelectedItem().toString().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Nacionalidad invalida \n"+
						"Debe de seleccionar una nacionalidad");
			}
			CBNacionalidad.CBgrabFocus();
			return false;
		}

		return true;
	}
	public Huesped getHuesped() {
		try {
			String nombre=this.Nombre.getTextValue();
			String apellido=this.Apellido.getTextValue();
			String telefono=this.Telefono.getTextValue();
			LocalDate fechan=MyDateConver.toLocalDate(FechaN.getDateValue());
			int ns=CBNacionalidad.getCBSelectedIndex();
			Nacionalidad nacionalidad=Nacionalidades.get(ns);
			
			if(nombre.equals(PHuesped.getDatoPersonal().getNombre())&&
			   apellido.equals(PHuesped.getDatoPersonal().getApellido())&&
			   telefono.equals(PHuesped.getDatoPersonal().getTelefono())&&
			   fechan.equals(PHuesped.getDatoPersonal().getFecha_de_nacimiento())&&
			   nacionalidad.getId()==PHuesped.getDatoPersonal().getId_Nacionalidad().getId()) 
			{return null;}
			
			PHuesped.getDatoPersonal().setNombre(nombre);
			PHuesped.getDatoPersonal().setApellido(apellido);
			PHuesped.getDatoPersonal().setTelefono(telefono);
			PHuesped.getDatoPersonal().setFecha_de_nacimiento(fechan);
			PHuesped.getDatoPersonal().setId_Nacionalidad(nacionalidad);
					
			return PHuesped;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	 public void CargarHuesped(Huesped huesped) {
		 this.PHuesped=huesped;
		 this.Nombre.setTextValue(huesped.getDatoPersonal().getNombre());
		 this.Apellido.setTextValue(huesped.getDatoPersonal().getApellido());
		 this.Telefono.setTextValue(huesped.getDatoPersonal().getTelefono());
		 this.TituloID.setText("ID: "+huesped.getId().toString());
		 Date f = MyDateConver.toDate(huesped.getDatoPersonal().getFecha_de_nacimiento());
		 this.FechaN.setDateValue(f);
		 this.CBNacionalidad.setCBSelectedIndex(0);
		 Long NI=huesped.getDatoPersonal().getId_Nacionalidad().getId();
		 int i=0;
		 for (Nacionalidad n:Nacionalidades) {
			 if (n.getId()==NI) {
				 this.CBNacionalidad.setCBSelectedIndex(i);
				 break;
			 }
			 i++;
		 }		 
	 }
	 
	 public void LimpiarPanel () {
		 this.PHuesped=null;
		 this.Nombre.TextValueClear();
		 this.Apellido.TextValueClear();
		 this.Telefono.TextValueClear();
		 this.FechaN.DateValueclear();
		 this.CBNacionalidad.setCBSelectedIndex(0);
		 this.TituloID.setText("");
	 }

}
