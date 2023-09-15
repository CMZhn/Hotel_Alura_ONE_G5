package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.mytools.MyDateConver;
import com.toedter.calendar.JDateChooser;

public class MyJDateChooser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel Label;
	private JDateChooser DateChooser;
	private JSeparator separator_1;
	private int MinWidth=200, MinHeight=70,
				dx, h0, h1, h2, x0, y0,
				x1, y1, x2, y2, w0, w1;
	
	public static final int Margen_Default = 0,
							Margen_Medio = 1,
							Margen_Ajustado = 2;
	private int Mi_Margen;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public MyJDateChooser(String label){
		Mi_Margen=0;
		ConfigurarElementos();
		Label.setText(label);
	}
	public MyJDateChooser(){
		Mi_Margen=0;
		ConfigurarElementos();
	}
	public MyJDateChooser(int type){
		if(type==0|type==1|type==2) {
			Mi_Margen=type;
		} else {Mi_Margen=0;}
		ConfigurarElementos();
	}
	public MyJDateChooser(String label, int type){
		if(type==0|type==1|type==2) {
			Mi_Margen=type;
		} else {Mi_Margen=0;}
		
		ConfigurarElementos();
		Label.setText(label);
	}
	
	private void ConfigurarElementos() {

		setLayout(null);
		setPreferredSize(new Dimension(MinWidth,MinHeight));
		Label = new JLabel();
		DateChooser = new JDateChooser();
		separator_1 = new JSeparator();
		
		w0=MinWidth;
		ShapeParametresComponentsConf();
		
		NuevoHuespedDefaultTxtField ();
		
		this.add(Label);
		this.add(DateChooser);
		this.add(separator_1);
		
		ConfigurarAcciones();
	}
	

	private void ShapeParametresComponentsConf() {
		switch (Mi_Margen) {
		case Margen_Medio:
			dx =25;
			break;
		case Margen_Ajustado:
			dx =10;
			break;
		default:
			dx =50;
			break;
		}
		
		w1=(w0-(dx*2));
		h0= 18;
		x0=0;
		y0=0;
		x1=x0+dx;
		y1=(y0+h0+3);
		h1= 35;
		x2=x1;
		y2=y1+h1+4;
		h2=2;
	}
	
	private void ConfigurarAcciones() {	}



	private void NuevoHuespedDefaultTxtField() {
		
		Label.setForeground(SystemColor.textInactiveText);
		Label.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		
		DateChooser.getCalendarButton().setIcon(DirectorioImg.ico("icon-reservas.png"));
		DateChooser.getCalendarButton().setBackground(SystemColor.textHighlight);
		DateChooser.setDateFormatString("yyyy-MM-dd");
		DateChooser.setFont(new Font("Roboto", Font.PLAIN, 18));

		separator_1.setForeground(new Color(12, 138, 199));
		separator_1.setBackground(new Color(12, 138, 199));
		ActualizarComponetesSize();
	}
	
	private void ActualizarComponetesSize() {
		
		if (w0<MinWidth) {
			w0=MinWidth;
		}
		ShapeParametresComponentsConf();
		Label.setLocation(x0, y0);
		Label.setSize(w0, h0);
		DateChooser.setLocation(x1,y1);
		DateChooser.setSize(w1, h1);
		separator_1.setLocation(x2,y2);
		separator_1.setSize(w1,h2);
	}


	@Override
	public void setSize(int width, int height) {
		if(width>=MinWidth&&height>=MinHeight) {
			super.setSize(width, height);
			w0=width;
		} else {super.setSize(MinWidth, MinHeight);}
		
		ActualizarComponetesSize();
	
	}

	@Override
	public void setSize(Dimension d) {
		if(d.getWidth()>=MinWidth&&d.height>=MinHeight) {
			super.setSize(d);
			w0=(int) d.getWidth();
		} else {super.setSize(MinWidth, MinHeight);}
		ActualizarComponetesSize();
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		if(width>=MinWidth&&height>=MinHeight) {
			super.setBounds(x, y, width, height);
			w0=width;
		}else {
			super.setBounds(x, y, MinWidth, MinHeight);
		}
		ActualizarComponetesSize();
	}

	@Override
	public void setBounds(Rectangle r) {
		if(r.getWidth()>=MinWidth&&r.getHeight()>=MinHeight) {
			super.setBounds(r);
			w0=(int) r.getWidth();
		} else {
			super.setBounds(r.x, r.y, MinWidth, MinHeight);
		}
		ActualizarComponetesSize();
	}


	
	//---------------------------------------PUBLIC-------------------------
	public Date getDateValue(){
		return DateChooser.getDate();
	}
	public void setDateEnabled(boolean enabled) {
		this.DateChooser.setEnabled(enabled);;
	}
	
	public LocalDate getLocalDateValue(){
		return MyDateConver.toLocalDate(getDateValue());
	}
	
	public void setDateValue(Date date){
		this.DateChooser.getDateEditor().setDate(date);;
	}
	public void setDateValue(LocalDate date){
		Date f = MyDateConver.toDate(date);
		this.setDateValue(f);
	}
	public void DateValueclear(){
		this.DateChooser.getDateEditor().setDate(null);;
	}
	
	public void DateFieldFocus() {
		this.DateChooser.getDateEditor().getUiComponent().requestFocusInWindow();
	}
	public void setDCEnabled(boolean enabled) {
		this.DateChooser.setEnabled(enabled);
	}
	
	public void setDCMaxSelectableDate(Date max) {
		this.DateChooser.setMaxSelectableDate(max);
	}
	public void setDCMinSelectableDate(Date min) {
		this.DateChooser.setMinSelectableDate(min);
	}
	
	public boolean DCisEnabled() {
		return this.DateChooser.isEnabled();
	}
	public String getTextLabel(){
		String r=Label.getText();
		return r;
	}
	
	public void setLabelText(String label) {
		Label.setText(label);
	}
	
	public void setWhith(int width) {
		this.w0=width;
		int h= this.getSize().height;
		super.setSize(w0, h);
		ActualizarComponetesSize();
		
	}
	
	
	

}
