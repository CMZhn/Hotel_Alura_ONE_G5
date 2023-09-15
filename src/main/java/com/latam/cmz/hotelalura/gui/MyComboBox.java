package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;


public class MyComboBox<E> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel Label;
	private JComboBox<E>  ComboBox;
	private JSeparator separator_1;
	private int MinWidth=250, MinHeight=70,
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
	public MyComboBox(String label){
		Mi_Margen=0;
		ConfigurarElementos();
		Label.setText(label);
	}
	public MyComboBox(){
		Mi_Margen=0;
		ConfigurarElementos();
	}
	
	public MyComboBox(String label, int Margen_Type){
		if(Margen_Type==0|Margen_Type==1|Margen_Type==2) {
			Mi_Margen=Margen_Type;
		} else {Mi_Margen=0;}
		ConfigurarElementos();
		Label.setText(label);
	}
	public MyComboBox(int Margen_Type){
		if(Margen_Type==0|Margen_Type==1|Margen_Type==2) {
			Mi_Margen=Margen_Type;
		} else {Mi_Margen=0;}
		ConfigurarElementos();
	}
	
	private void ConfigurarElementos() {

		setLayout(null);
		setPreferredSize(new Dimension(MinWidth,MinHeight));
		Label = new JLabel();
		ComboBox = new JComboBox<E>();
		separator_1 = new JSeparator();
		
		w0=MinWidth;
		ShapeParametresComponentsConf();
		
		NuevoHuespedDefaultTxtField ();
		
		this.add(Label);
		this.add(ComboBox);
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
		
		ComboBox.setBackground(SystemColor.text);
		ComboBox.setFont(new Font("Roboto", Font.PLAIN, 18));
		

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
		ComboBox.setLocation(x1,y1);
		ComboBox.setSize(w1, h1);
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
	public Object getCBSelectedItem(){
		
		return this.ComboBox.getSelectedItem();
	}
	public void CBgrabFocus() {
		this.ComboBox.grabFocus();
	}
	public void setCBSelectedItem(Object anObject) {
		this.ComboBox.setSelectedItem(anObject);
	}
	public void ComboBoxClear() {
		this.ComboBox.removeAllItems();
	}
	 
	/**
     * Selects the item at index <code>anIndex</code>.
     *
     * @param anIndex an integer specifying the list item to select,
     *                  where 0 specifies the first item in the list and -1 indicates no selection
     * @exception IllegalArgumentException if <code>anIndex</code> &lt; -1 or
     *                  <code>anIndex</code> is greater than or equal to size
     */
	public void setCBSelectedIndex(int anIndex) {
		this.ComboBox.setSelectedIndex(anIndex);
	}

	
	public void addCBItem(E item) {
		this.ComboBox.addItem(item);
	}
	
	public int getCBItemCount() {
		
		return this.ComboBox.getItemCount();
	}
	public E getCBItemAt(int index) {
        return this.ComboBox.getItemAt(index);
    }
	
	public int getCBSelectedIndex() {
		return this.ComboBox.getSelectedIndex();
	}
	
	public void CBremoveAllItems() {
		this.ComboBox.removeAllItems();
	}
	public void CBremoveItemAt(int anIndex) {
		this.ComboBox.removeItemAt(anIndex);
	}
	public void CBremoveItem(Object anObject) {
		this.ComboBox.removeItem(anObject);
	}
	public boolean CBisEditable() {
		return this.ComboBox.isEditable();
	}
	public void CBsetEditable(boolean aFlag) {
		this.ComboBox.setEditable(aFlag);
	}
	
	public void setEnabled(boolean b) {
		this.ComboBox.setEnabled(b);
	}
	
	public String getTextLabel(){
		String r=this.Label.getText();
		return r;
	}
	
	public void setLabelText(String label) {
		this.Label.setText(label);
	}
	
	public void setWhith(int width) {
		this.w0=width;
		int h= this.getSize().height;
		super.setSize(this.w0, h);
		ActualizarComponetesSize();
		
	}
	

}
