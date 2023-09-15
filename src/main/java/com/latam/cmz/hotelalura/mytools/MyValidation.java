package com.latam.cmz.hotelalura.mytools;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class MyValidation {
	
	
	public static boolean Telephonumber(String telephone) {
		String Regex="^[\\(]?[\\+]?(\\d{2}|\\d{3})[\\)]?[\\s]?(((\\d{4}[\\s]\\d{4})|\\d{6}|\\d{8})|(\\d{3}[\\*\\.\\-\\s]){3}|(\\d{2}[\\*\\.\\-\\s]){4}|(\\d{4}[\\*\\.\\-\\s]){2})|(\\d{4}[\\s]\\d{4})|\\d{8}|\\d{10}|\\d{12}$";
		Pattern pat = Pattern.compile(Regex,Pattern.MULTILINE);
		Matcher mat = pat.matcher(telephone);
		return mat.matches();
	}
		
	public static boolean Email(String email) {
		String Regex="^[\\w­]+(\\.[\\w­]+)*@[A­Za­z0­9]+(\\.[A­Za­z0­9]+)*(\\.[A­Za­z]{2,})$";
		Pattern pat = Pattern.compile(Regex);
		Matcher mat = pat.matcher(email);
		
		return mat.matches();
	}
	
	public static boolean Name(String name) {
		String Regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$";
		Pattern pat = Pattern.compile(Regex,Pattern.MULTILINE);
		Matcher mat = pat.matcher(name);
		return mat.matches();	
	}
	
	
	public static boolean finalSpace(String string) {
		boolean r=false;
		char Espacio=32;
		if(string.length()>0 && string.charAt(string.length()-1)== Espacio) {
			r=true;
		}
		return r;	
	}
	
	private static boolean finalCharEquals(String string, char character) {
		boolean r=false;
		if(string.length()>0 && string.charAt(string.length()-1)== character) {
			r=true;
		}
		return r;
	}
	
	private static boolean finalCharIsNumber(String string) {
		boolean r=false;
		if(string.length()>0) {
			r=EsNumeroEntero(string.charAt(string.length()-1));
		}
		return r;
	}
	private static String intoString(String textoReal, String textoInsert, int pos){
        StringBuilder stringBuilder= new StringBuilder(textoReal);
        stringBuilder.insert(pos,textoInsert);
        return stringBuilder.toString();
    }
	
	private static String intoString(String textoReal, char charaterInsert, int pos){
        String Schar=String.valueOf(charaterInsert);
        return intoString(textoReal,Schar,pos);
    }
	
	public static boolean EsNumeroEntero(String string) {
		String Regex = "^[0-9]+$";
		Pattern pat = Pattern.compile(Regex,Pattern.MULTILINE);
		Matcher mat = pat.matcher(string);
		return mat.matches();	
	}
	public static boolean EsNumeroEntero(char character) {
		return EsNumeroEntero(String.valueOf(character));	
	}
	
	public static boolean TelephoNumberValidChar(char telephoneChar) {
		String CharT=String.valueOf(telephoneChar);
		String Regex="^[0-9\\(\\+\\)\\s\\*\\.\\-]+$";
		Pattern pat = Pattern.compile(Regex,Pattern.MULTILINE);
		Matcher mat = pat.matcher(CharT);
		return mat.matches();
	}
	
	public static void KeyTypedTelephoNumberValidation(KeyEvent e, JTextField textfield) {
		char key = e.getKeyChar();
			//0Espacio=32,1ParentesisA=40,2ParentesisC=41;
			//3Asterisco=42, 4Mas=43, 5Menos=45, 6Punto=46
		char[] CharP= {32,40,41,42,43,45,46};
		char[] CharP2= {32,41,42,45,46};
		String t= textfield.getText();
		int tp = textfield.getCaretPosition();
		
		if(!MyValidation.TelephoNumberValidChar(key)) {
			e.consume(); return;
		}else if (t.length()==0) {			
			for (char c:CharP2) {
				if (key==c) {
					e.consume(); return;
				}
			}
		}else if (t.length()>0) {
			
			if (key==CharP[2]&&t.contains(String.valueOf(CharP[2]))) {
				e.consume(); return;
			}
			if (key==CharP[1]) {
				if (tp!=0) {e.consume(); return;}
				if (t.contains(String.valueOf(CharP[1]))) {e.consume(); return;}
			}
			
			if(key==CharP[4]) {
				if (tp>2) {e.consume(); return;}
				if (t.contains(String.valueOf(CharP[4]))) {e.consume(); return;}
				if (tp==0){
					if (t.charAt(0)==CharP[1]) {e.consume(); return;}
					}
				if (tp==1){
					if (t.charAt(0)!=CharP[1]) {e.consume(); return;}
					}
			}

			if(tp==(t.length())){
				
				if (t.length()==3|t.length()==4|t.length()==5) {
					if(t.charAt(0)!=CharP[1]&&key==CharP[2]) {
						e.consume(); return;
					} else if(t.charAt(0)==CharP[1]&&key==CharP[0]) {
						if(finalCharIsNumber(t)) {
							t=t+String.valueOf(CharP[2]);
							textfield.setText(t);
							return;
						}	
					}	
				}

				for (char c:CharP) {
					if (key==c&&finalCharEquals(t,c)) {
						e.consume(); return;
					}
				}
			} else {
				if (tp==0) {
					
					if ((t.charAt(0)==CharP[1]|t.charAt(0)==CharP[4])&&
							EsNumeroEntero(key)) {e.consume(); return;}
					
					for (char c:CharP2) {
						if (key==c) {
							e.consume(); return;
						}
					}	
				}else if (tp==3|tp==4|tp==5) {
					if(key==CharP[2]&&(t.charAt(0)!=CharP[1]|t.charAt(tp)==CharP[2])) {
						e.consume(); return;
					} else if(key==CharP[0]&&
							  t.charAt(0)==CharP[1]&&
							  EsNumeroEntero(t.charAt(tp-1))&&
							  EsNumeroEntero(t.charAt(tp))&&
							  !t.contains(String.valueOf(CharP[2]))) {
						
						t=intoString(t,CharP[2],tp);
						textfield.setText(t);
						textfield.setCaretPosition(tp+1);
						return;				
					} else if(key==CharP[0]&&
								(t.charAt(tp)==CharP[2]|
								 t.charAt(tp-1)==CharP[0]|
								 t.charAt(tp)==CharP[0])) {
						e.consume(); return;						
					}
				}

				if(tp>0) {
					if(tp<=5) {
						if(t.charAt(0)==CharP[1]&&t.contains(String.valueOf(CharP[2]))) {
							if(key==CharP[0]|key==CharP[3]|key==CharP[5]|key==CharP[6]) {
								boolean b = true;
								for (int i=1;i<tp;i++) {
									if (t.charAt(i)==CharP[2]) { b= false; break;}}
								if (b) {e.consume(); return;}
							}
		
						}
					}
					
					for (char c:CharP) {
						if (key==c&&(t.charAt(tp)==c|t.charAt(tp-1)==c)) {
							e.consume(); return;
						}
					}
					

				}
				
				
			}

		} 
		
	}
	
	
	public static void KeyTypedNameValidation(KeyEvent e, JTextField textfield) {
		char key = e.getKeyChar();
		int keyint = e.getKeyChar();
		
		String t= textfield.getText();
		if(!MyValidation.Name(String.valueOf(key))) {
			e.consume();
		}else if (t.length()==0&&Character.isAlphabetic(key)) {
			e.consume();
			textfield.setText(String.valueOf(key).toUpperCase());
		} else if (keyint==32 && (MyValidation.finalSpace(t)|t.length()==0)) {
			e.consume();
		} else if (MyValidation.finalSpace(t) && Character.isAlphabetic(key)) {
			e.consume();
			t=t+String.valueOf(key).toUpperCase();
			textfield.setText(t);
		}
	}
	
	public static void KeyTypedLongIdValidation(KeyEvent e, JTextField textfield) {
		char key = e.getKeyChar();
		if(!MyValidation.EsNumeroEntero(key)) {
			e.consume();
		}
	}
	
	public static void KeyReleaseTelephoNumberValidation(JTextField textfield) {
		String t= textfield.getText();
		boolean Valido=MyValidation.Telephonumber(t);
		if (!Valido) {
			textfield.setForeground(Color.RED);
		} else {
			textfield.setForeground(Color.BLACK);
		}
	}
	
	public static void KeyReleaseNameValidation(JTextField textfield) {
		String t= textfield.getText();
		
		boolean Valido=MyValidation.Name(t);
		boolean Espaciofinal=MyValidation.finalSpace(t);
		if (!Valido||Espaciofinal) {
			textfield.setForeground(Color.RED);
		} else {
			textfield.setForeground(Color.BLACK);
		}
	}
	
	public static boolean DeletefinalSpace(JTextField textfield) {
		try {
			if (MyValidation.finalSpace(textfield.getText())) {
				if (textfield.getText()!=null &&textfield.getText().length()>0) {
					String t= textfield.getText();
					t=t.substring(0, t.length() - 1);
					textfield.setText(t);
					return true;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
}
