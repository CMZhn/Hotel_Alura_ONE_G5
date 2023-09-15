package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import com.latam.cmz.hotelalura.mytools.MyValidation;

public class MyTxtField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel Label;
	private JTextField TextField;
	private JSeparator separator_1;
	
	private int MinWidth=200, MinHeight=60,
				dx, h0, h1, h2, x0, y0,
				x1, y1, x2, y2, w0, w1;
	public static final int Type_Name=0,
							Type_Phone=1,
							Type_LongId=2,
							Type_String=3;
	private int EstePanelType;
	public static final int Margen_Default = 0,
			Margen_Medio = 1,
			Margen_Ajustado = 2;
	private int Mi_Margen;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public MyTxtField(int Type,String label){
		Mi_Margen=0;
		ConfigurarElementos(Type);
		Label.setText(label);
	}
	public MyTxtField(int Type,String label, int Margen_Type){
		if(Margen_Type==0|Margen_Type==1|Margen_Type==2) {
			Mi_Margen=Margen_Type;
		} else {Mi_Margen=0;}
		
		ConfigurarElementos(Type);
		Label.setText(label);
	}
	
	
	public MyTxtField(int Type) {
		Mi_Margen=0;
		ConfigurarElementos(Type);
	}
	
	public MyTxtField(int Type, int Margen_Type) {
		
		if(Margen_Type==0|Margen_Type==1|Margen_Type==2) {
			Mi_Margen=Margen_Type;
		} else {Mi_Margen=0;}
		
		ConfigurarElementos(Type);
	}
	
	
	
	private static void checkOptionType(int Type) {
        if (Type != Type_Name && Type != Type_Phone
          &&Type != Type_LongId && Type != Type_String) {
            throw new RuntimeException("MyTxtFieldType: debe de ser una de las"
                    + " MyTxtField.Type_Name, MyTxtField.Type_Phone, Type_Long o Type_String");
        }
	}

	private void ConfigurarElementos(int Type) {
		checkOptionType(Type);
		EstePanelType=Type;
		setLayout(null);
		setPreferredSize(new Dimension(MinWidth,MinHeight));
		Label = new JLabel();
		TextField = new JTextField();
		separator_1 = new JSeparator();
		
		
		w0=MinWidth;
		ShapeParametresComponentsConf();
		
		ConfigurarComponentes ();
		
		this.add(Label);
		this.add(TextField);
		this.add(separator_1);
		
		ConfigurarAcciones(Type);
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
		h1= 25;
		x2=x1;
		y2=y1+h1+4;
		h2=2;
	}
	
	private void ConfigurarAcciones(int Type) {
		
		switch (Type) {
		case MyTxtField.Type_Name:
			TextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					MyValidation.KeyTypedNameValidation(e,TextField);}
				@Override
				public void keyReleased(KeyEvent e) {
					MyValidation.KeyReleaseNameValidation(TextField);}
			});
			break;
		case MyTxtField.Type_Phone:
			TextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					MyValidation.KeyTypedTelephoNumberValidation(e, TextField);}
				@Override
				public void keyReleased(KeyEvent e) {
					MyValidation.KeyReleaseTelephoNumberValidation(TextField);}
			});
			break;
		case MyTxtField.Type_LongId:
			TextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					MyValidation.KeyTypedLongIdValidation(e, TextField);}
			});
			
			break;
		case MyTxtField.Type_String:
			break;
		default:
			break;
		}
		
	}



	private void ConfigurarComponentes() {
		
		Label.setForeground(SystemColor.textInactiveText);
		Label.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		
		TextField.setFont(new Font("Roboto", Font.PLAIN, 18));
		TextField.setBackground(Color.WHITE);
		TextField.setColumns(10);
		TextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

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
		TextField.setLocation(x1,y1);
		TextField.setSize(w1, h1);
		
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
	public String getTextValue(){
		String r=TextField.getText();
		
		switch (EstePanelType) {
		case MyTxtField.Type_Name:
			if(!MyValidation.Name(r)) {r=null;}
			break;
		case MyTxtField.Type_Phone:
			if(r.length()>3) {
				if (MyValidation.finalSpace(r)) {
					MyValidation.DeletefinalSpace(TextField);
					r=TextField.getText();
				}
			}
			if(!MyValidation.Telephonumber(r)){r=null;}
			break;
		case MyTxtField.Type_LongId:
			if(!MyValidation.EsNumeroEntero(r)) {r=null;}
			break;
		case MyTxtField.Type_String:
			break;
		default:
			r=null;
			break;
		}
		return r;
	}
	/*public long getLongValue() {
		if ()
	}*/
	
	public void setTextValue(String t) {
		this.TextField.setText(t);
	}
	public void TextValueClear() {
		this.TextField.setText("");
	}
	
	public void TextFieldFocus() {
		this.TextField.grabFocus();
	}
	public void setTFforeground(Color fg) {
		this.TextField.setForeground(fg);
	}
	
	
	public String getTextLabel(){
		String r=Label.getText();
		return r;
	}
	
	public void setTextLabel(String text){
		this.Label.setText(text);
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
	
	public boolean DeletefinalSpace() {
		try {
			return MyValidation.DeletefinalSpace(TextField);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void setTextEditable(boolean b) {
		TextField.setEditable(b);
	}
	public boolean isTextEditable(boolean b) {
		return TextField.isEditable();
	}
	
	

}
