package com.latam.cmz.hotelalura.gui;

import java.awt.Color;
import java.awt.Font;

import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.latam.cmz.hotelalura.dao.HuespedDAO;
import com.latam.cmz.hotelalura.dao.ReservaDAO;
import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.modelo.Huesped;
import com.latam.cmz.hotelalura.modelo.Reserva;
import com.latam.cmz.hotelalura.modelo.Habitacion;
import com.latam.cmz.hotelalura.mytools.MyDateConver;
import com.latam.cmz.hotelalura.utils.JPAUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JPReservaUpdate extends JPanel {
	private JLabel Titulo,TituloID, Logo;
	private MyTxtField Valor, Habitacion;
	private MyJDateChooser FechaCheckIn,FechaCheckOut;
	private MyComboBox<String> FormaPago;
	
	private MyBoton btnCancelar,
					btnActualizar,
					btnEditarFecha,
					btnEditarHuespedes,
					btnCerrarEdicion,
					btnAgregarH,
					btnQuitarH,
					btnValidarFecha;
	
	private List<Huesped> 	Huespedes,
							HuespedesSeleccionados=new ArrayList<>();
	
	
	
	private Reserva PReserva;
	private JPanel EstePanel=this;
	private JPtbHuespedes tbHuespedesR,tbHuespedes;
	private EntityManager em;
	private JPtbReservas tbReservasEntrelazadas;
	private List<Reserva> ReservasEntrelazadas;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPReservaUpdate() {
		
		setSize(890, 521);
		setLayout(null);
		setBackground(Color.WHITE);
		
		
		Titulo = new JLabel("SISTEMA DE EDICIÓN DE RESERVAS");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setForeground(new Color(12, 138, 199));
		Titulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		Titulo.setBounds(140, 22, 490, 42);
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
		
		int dby=20;
		int bx0=230;
		int by0=70;
		int bw0=180;
		btnEditarFecha = new MyBoton(bx0,by0,bw0);
		btnEditarFecha.setbtnText("EDITAR FECHA");
		this.add(btnEditarFecha);
		
		int bx1=bx0+bw0+dby;
		int by1=by0;
		int bw1=bw0;
		btnEditarHuespedes = new MyBoton(bx1,by1,bw1);
		btnEditarHuespedes.setbtnText("EDITAR HUÉSPEDES");
		this.add(btnEditarHuespedes);
		
		int bx2=bx1+bw1+(dby*3);
		int by2=by0;
		int bw2=bw0;
		btnCancelar = new MyBoton(bx2,by2,bw2);
		btnCancelar.setbtnText("CANCELAR");
		this.add(btnCancelar);
		
		int bx3=bx2;
		int by3=150;
		int bw3=bw2;
		btnActualizar = new MyBoton(bx3,by3,bw3);
		btnActualizar.setbtnText("ACTUALIZAR");
		this.add(btnActualizar);
		
		int bx4=bx2;
		int by4=230;
		int bw4=bw2;
		btnCerrarEdicion= new MyBoton(bx4,by4,bw4);
		btnCerrarEdicion.setbtnText("CERRAR EDICIÓN");
		btnCerrarEdicion.setVisible(false);
		this.add(btnCerrarEdicion);
		
		int bx5=bx2-220;
		int by5=230;
		int bw5=100;
		btnAgregarH= new MyBoton(bx5,by5,bw5);
		btnAgregarH.setbtnText("AGREGAR");
		btnAgregarH.setbtncolor(new Color(255,150,0), new Color(255,125,0));
		btnAgregarH.setVisible(false);
		this.add(btnAgregarH);
		
		int bx6=bx5+110;
		int by6=by5;
		int bw6=bw5;
		btnQuitarH= new MyBoton(bx6,by6,bw6);
		btnQuitarH.setbtnText("QUITAR");
		btnQuitarH.setbtncolor(new Color(255,150,0), new Color(255,125,0));
		btnQuitarH.setVisible(false);
		this.add(btnQuitarH);
		
		int bx7=bx5-5;
		int by7=by5;
		int bw7=215;
		btnValidarFecha= new MyBoton(bx7,by7,bw7);
		btnValidarFecha.setbtnText("VALIDAR DISPOSICIÓN");
		btnValidarFecha.setbtncolor(new Color(255,150,0), new Color(255,125,0));
		btnValidarFecha.setVisible(false);
		this.add(btnValidarFecha);
		
		int dy=85;
		
		int x0=10;
		int y0=132;
		int w0=200;
		
		int x1=x0+w0;
		int y1=y0;
		int w1=w0;
		
		int x2=x1+w1;
		int y2=y0;
		int w2=250;
		
		FechaCheckIn = new MyJDateChooser(MyTxtField.Margen_Medio);
		FechaCheckIn.setBackground(new Color(255, 255, 255));
		FechaCheckIn.setLabelText("CHECK IN");
		FechaCheckIn.setLocation(x0,y0);
		FechaCheckIn.setWhith(w0);
		FechaCheckIn.setDateEnabled(false);
		this.add(FechaCheckIn);
		
		FechaCheckOut = new MyJDateChooser(MyTxtField.Margen_Medio);
		FechaCheckOut.setBackground(new Color(255, 255, 255));
		FechaCheckOut.setLabelText("CHECK IN");
		FechaCheckOut.setLocation(x1,y1);
		FechaCheckOut.setWhith(w1);
		FechaCheckOut.setDateEnabled(false);
		this.add(FechaCheckOut);
		
		FormaPago = new MyComboBox<String>(MyTxtField.Margen_Medio);
		FormaPago.setBackground(new Color(255, 255, 255));
		FormaPago.setLabelText("FORMA DE PAGO");
		FormaPago.setLocation(x2,y2);
		FormaPago.setWhith(w2);
			FormaPago.addCBItem("Tarjeta de Crédito");
			FormaPago.addCBItem("Tarjeta de Débito");
			FormaPago.addCBItem("Dinero en efectivo");
		this.add(FormaPago);
		
		int x3=x0;
		int y3=y0+dy;
		int w3=w0+20;
		
		Valor= new MyTxtField(MyTxtField.Type_String,MyTxtField.Margen_Medio);
		Valor.setBackground(new Color(255, 255, 255));
		Valor.setLabelText("VALOR DE LA RESERVA");
		Valor.setLocation(x3,y3);
		Valor.setWhith(w3);
		Valor.setTextEditable(false);
		this.add(Valor);
		
		int x4=x3+w3+10;
		int y4=y3;
		int w4=w3-90;
		
		Habitacion= new MyTxtField(MyTxtField.Type_LongId,MyTxtField.Margen_Medio);
		Habitacion.setBackground(new Color(255, 255, 255));
		Habitacion.setLabelText("No HABITACION");
		Habitacion.setLocation(x4,y4);
		Habitacion.setWhith(w4);
		Habitacion.setTextEditable(false);
		this.add(Habitacion);
		
		
		tbHuespedesR=new JPtbHuespedes();
		tbHuespedesR.setBackground(new Color(255, 255, 255));
		tbHuespedesR.setLabelText("HUÉSPEDES ASIGNADOS");
		tbHuespedesR.setLocation(x0,y4+75);
		this.add(tbHuespedesR);
		
		tbHuespedes=new JPtbHuespedes();
		tbHuespedes.setBackground(Color.ORANGE);
		tbHuespedes.setLabelText("HUÉSPEDES");
		tbHuespedes.setLocation(x0+430,y4+75);
		tbHuespedes.setVisible(false);
		this.add(tbHuespedes);
		
		tbReservasEntrelazadas=new JPtbReservas();
		tbReservasEntrelazadas.setBackground(Color.ORANGE);
		tbReservasEntrelazadas.setLabelText("RESERVAS ENTRELAZADAS");
		tbReservasEntrelazadas.setLocation(x0+430,y4+75);
		tbReservasEntrelazadas.setVisible(false);
		this.add(tbReservasEntrelazadas);
		CargarAcciones();
		
	}
	
	private void CargarAcciones() {
		btnEditarFecha.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(!btnEditarFecha.getHabilitado()|
						btnCerrarEdicion.isVisible()) {return;}
				
				btnEditarFecha.setHabilitado(false);
				btnEditarHuespedes.setVisible(false);
				
				FechaCheckIn.setDateEnabled(true);
				FechaCheckOut.setDateEnabled(true);
				btnCerrarEdicion.setVisible(true);
				btnValidarFecha.setVisible(true);
				
			}
			
		});
		
		btnValidarFecha.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				tbReservasEntrelazadas.setVisible(false);
				tbReservasEntrelazadas.ValuesClear();
				if(PReserva==null) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Fecha no se puede validar \n"+
							"La entidad Reserva viculada es nula");
					return;}
				
				LocalDate FechaEntrada=FechaCheckIn.getLocalDateValue();
				if(FechaEntrada==null) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Check in invalido \n"+
							"Debe de seleccionar una fecha de entrada valida.");
					FechaCheckIn.DateFieldFocus();
					return;
				}
				
				LocalDate FechaSalida= FechaCheckOut.getLocalDateValue();
				if(FechaSalida==null) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Check out invalido \n"+
							"Debe de seleccionar una fecha de salida valida.");
					FechaCheckOut.DateFieldFocus();
					return;					
				}
				
				if (DiasFormulario()<=0) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Intervalo de fechas invalido \n"+
							"La fecha Check In debe de ser menor \n"
							+ "a la fecha Check Out.");
					FechaCheckIn.DateFieldFocus();
					return;
				}
				
				LocalDate FCIn=PReserva.getFecha_entrada();
				LocalDate FCout=PReserva.getFecha_salida();
				
				if(FechaEntrada.equals(FCIn)&&
						FechaSalida.equals(FCout)) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Nos se encotro cambios en las fechas");
					return;
				}
				
				try {
					Long id_Habitacion=PReserva.getHabitacion().getId();
					Long Id_Reserva=PReserva.getId();
					
					try {
						if (!em.isOpen()) {em = JPAUtils.getEntityManager();}
					} catch (NullPointerException e3) {
						em = JPAUtils.getEntityManager();
					}
						ReservaDAO  ReservaDAO=new ReservaDAO(em);
						ReservasEntrelazadas = ReservaDAO.ConsultarDisposicion(id_Habitacion,
																			   Id_Reserva,
																			   FechaEntrada,
																			   FechaSalida);
						em.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
					return;
				}
				
				if(ReservasEntrelazadas.size()<=0) {
					int n2 = JOptionPane.showConfirmDialog(
							EstePanel,
				            "Intervalo de fechas Valido."+"\n"
				            + "¿Desea cerrar la edicion?",
				            "FECHAS INVALIDAS",
				            JOptionPane.YES_NO_OPTION);
					if (n2==0) {
						PReserva.setFecha_entrada(FechaCheckIn.getLocalDateValue());
						PReserva.setFecha_salida(FechaCheckOut.getLocalDateValue());
						AsignarValorReserva();
						Valor.setTextValue(PReserva.getValor_Total().toString());
						CerrarEdicion();
					}
					return;
				} 
				
				int n = JOptionPane.showConfirmDialog(
						EstePanel,
			            "Se encontraron reservas entrelazadas."+"\n"
			            + "Hay "+ReservasEntrelazadas.size()+" reservas entrelazadas \n"
			            + "Por lo que las fechas no son validas \n"
			            + "¿Desea cambiar las fechas?",
			            "FECHAS INVALIDAS",
			            JOptionPane.YES_NO_OPTION);

				if(n!=0) {
					FechaCheckIn.setDateValue(PReserva.getFecha_entrada());
					FechaCheckOut.setDateValue(PReserva.getFecha_salida());
					CerrarEdicion();
					return;
				}
				
				tbReservasEntrelazadas.CargarValoresTbHuepedes(ReservasEntrelazadas);
				tbReservasEntrelazadas.setVisible(true);
				
			}
		});
		
		btnEditarHuespedes.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(!btnEditarHuespedes.getHabilitado()|
						btnCerrarEdicion.isVisible()) {return;}
				
				btnEditarHuespedes.setHabilitado(false);
				btnEditarFecha.setVisible(false);
				btnCerrarEdicion.setVisible(true);
				btnAgregarH.setVisible(true);
				btnQuitarH.setVisible(true);
				try {
					try {
						if (!em.isOpen()) {em = JPAUtils.getEntityManager();}
					} catch (NullPointerException e3) {
						em = JPAUtils.getEntityManager();
					}
						HuespedDAO  HuespedDAO=new HuespedDAO(em);
						Huespedes = HuespedDAO.ConsultarTodosOrdenporApellido();
						em.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					
					btnEditarHuespedes.setHabilitado(true);
					btnEditarFecha.setVisible(true);
					btnCerrarEdicion.setVisible(false);
					btnAgregarH.setVisible(false);
					btnQuitarH.setVisible(false);
					return;
				}
				if(Huespedes!=null&&Huespedes.size()>0) {
					tbHuespedes.CargarValoresTbHuepedes(Huespedes);
					tbHuespedes.setVisible(true);
				}
				
			}
			
		});
		
		btnAgregarH.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int rs =tbHuespedes.getTBSelectedRow();
				if (rs<0) {
					JOptionPane.showMessageDialog(EstePanel, 
						"Debe seleccionar un huésped de la lista de huéspedes");
					return;
				}
				
				if (Huespedes==null) {return;}
				
				if (Huespedes.size()==0) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Error: No se encontro huepedes en la Base de datos");
					return;}
				
				if(rs>=Huespedes.size()) {
					JOptionPane.showMessageDialog(EstePanel, 
							"Hueped seleccionado no se encuentra en la base de datos");
					return;
				}
				
				if (PReserva==null|PReserva.getHabitacion()==null) {return;}
				
				
				long id = Long.parseLong(tbHuespedes.getTBValueAt(rs, 1).toString());
				Huesped h = ObtenerHuespeddeID(id);
				if (h!=null) {
					AgregarHuepedAreserva(h);
				}
				
				
			}
		});
		
		btnQuitarH.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int rs =tbHuespedesR.getTBSelectedRow();
				if (rs<0) {
					JOptionPane.showMessageDialog(EstePanel, 
						"Debe seleccionar un huésped de los asignados");
					return;
				}
				
				if (HuespedesSeleccionados==null) {return;}
				if (HuespedesSeleccionados.size()==0) {return;}
				if (HuespedesSeleccionados.size()<=1) {
					JOptionPane.showMessageDialog(EstePanel, 
							"No se permite reserva sin Huespedes \n"
						  + "No se puede quitar el hueped seleccionado \n"
						  + "ya que solo hay un huesped");
						return;}
				if(rs>=HuespedesSeleccionados.size()) {return;}
				
				Huesped h =HuespedesSeleccionados.get(rs);
				QuitarHuepedAreserva(h);
			}
		});
	
		
		btnCerrarEdicion.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				LocalDate FechaEntrada=FechaCheckIn.getLocalDateValue();
				LocalDate FechaSalida=FechaCheckOut.getLocalDateValue();
				if (PReserva!=null&& FechaEntrada!=null && FechaSalida!=null) {
					LocalDate FCIn=PReserva.getFecha_entrada();
					LocalDate FCout=PReserva.getFecha_salida();
					
					if(FechaEntrada.equals(FCIn)&&
							FechaSalida.equals(FCout)) {
						CerrarEdicion();
						return;
					}
				}
				
				
				if (!btnEditarFecha.getHabilitado()) {
					int n2 = JOptionPane.showConfirmDialog(
							EstePanel,
				            "Advertencia: al cerrar la edicion "
		            		+ "por este boton, no se cambiara \n"
		            		+ "el intervalo de fechas. \n"
		            		+"Para cambiar las fechas debe "
		            		+ "usar el boton de Validacion. \n"
				            + "¿Desea cerrar la edicion?",
				            "CERRAR EDICION",
				            JOptionPane.YES_NO_OPTION);
					if (n2!=0) {return;}
					if(PReserva!=null) {
						FechaCheckIn.setDateValue(PReserva.getFecha_entrada());
						FechaCheckOut.setDateValue(PReserva.getFecha_salida());
					}
					CerrarEdicion();
					
				}else if(!btnEditarHuespedes.getHabilitado()) {
					CerrarEdicion();
					
				}
			}
			
		});
		
		
		
	}
	private void CerrarEdicion() {
		btnCerrarEdicion.setVisible(false);
		btnAgregarH.setVisible(false);
		btnQuitarH.setVisible(false);
		tbHuespedes.ValuesClear();
		Huespedes=null;
		tbHuespedes.setVisible(false);
		FechaCheckIn.setDateEnabled(false);
		FechaCheckOut.setDateEnabled(false);
		btnValidarFecha.setVisible(false);
		tbReservasEntrelazadas.ValuesClear();
		tbReservasEntrelazadas.setVisible(false);
		ReservasEntrelazadas=null;
		
		btnEditarHuespedes.setHabilitado(true);
		btnEditarHuespedes.setVisible(true);
		btnEditarFecha.setHabilitado(true);
		btnEditarFecha.setVisible(true);

	}

	public void addbtnActualizarMouseListener(MouseAdapter MouseAdapter) {
		btnActualizar.addMouseListener(MouseAdapter);
	}
	public void addbtnCancelarMouseListener(MouseAdapter MouseAdapter) {
		btnCancelar.addMouseListener(MouseAdapter);
	}

	public void LimpiarPanel() {
		this.PReserva=null;
		this.Huespedes=null;
		this.HuespedesSeleccionados=null;
		
		this.FechaCheckIn.DateValueclear();
		this.FechaCheckOut.DateValueclear();
		this.Valor.TextValueClear();
		this.Habitacion.TextValueClear();
		this.FormaPago.setCBSelectedIndex(0);
		this.TituloID.setText("");
		this.tbHuespedesR.ValuesClear();
		
		CerrarEdicion();
	}

	public void CargarReserva(Reserva r) {
		this.PReserva=r;
		this.FechaCheckIn.setDateValue(r.getFecha_entrada());
		this.FechaCheckOut.setDateValue(r.getFecha_salida());
		this.Valor.setTextValue(r.getValor_Total().toString());
		this.Habitacion.setTextValue(r.getHabitacion().getId().toString());
		String rfp = r.getForma_de_pago();
		for (int i=0;i<FormaPago.getCBItemCount();i++) {
			if(rfp.equals(FormaPago.getCBItemAt(i))) {
				this.FormaPago.setCBSelectedIndex(i);
				break;
			}
		}
		this.HuespedesSeleccionados=r.getHuespedes();
		this.tbHuespedesR.CargarValoresTbHuepedes(r.getHuespedes());
		this.TituloID.setText("ID: "+r.getId().toString());
		
	}
	
	
	public boolean ValidadarDatos(boolean MostrarMensaje) {
		if(FechaCheckIn.getDateValue()==null) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Fecha de entrada invalida \n"+
						"Debe de ingresar un checkin valido");
			}
			if(!FechaCheckIn.DCisEnabled()) {FechaCheckIn.setDateEnabled(true);}
			FechaCheckIn.DateFieldFocus();
			return false;
		}
		
		if(FechaCheckOut.getDateValue()==null) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Fecha de salida invalida \n"+
						"Debe de ingresar un checkout valido");
			}
			if(!FechaCheckOut.DCisEnabled()) {FechaCheckOut.setDateEnabled(true);}
			FechaCheckOut.DateFieldFocus();
			return false;
		}
		
		if(FechaCheckIn.DCisEnabled()|FechaCheckOut.DCisEnabled()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Edicion de fecha Abierta \n"+
						"Debe de cerrar la edicion de fechas");
			}
			FechaCheckOut.DateFieldFocus();
			return false;
		}
		
		if(btnCerrarEdicion.isVisible()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Edicion abierta"+
						"Debe de cerrar la edicion \n"
					   +"antes de actualizar");
			}
			return false;
		}
		
		if(Valor.getTextValue()==null|
				Valor.getTextValue().isBlank()|
				Valor.getTextValue().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Valor de la reserva esta en blanco"+
						"Ha ocurrido un error en el calculo del valor \n"+
						"No se puede actualizar la reserva");
			}
			return false;
		}
		if(Habitacion.getTextValue()==null|
				Habitacion.getTextValue().isBlank()|
				Habitacion.getTextValue().isEmpty()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Valor de la habitacion esta en blanco"+
						"Ha ocurrido un error en la obtencion del numero de habitacion \n"+
						"No se puede actualizar la reserva");
			}
			return false;
		}
		
		if(this.PReserva==null|
		   this.HuespedesSeleccionados==null|
		   this.PReserva.getHabitacion()==null|
		   this.PReserva.getHabitacion()==null) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"Valor de entidad de reserva nulla"+
						"Ha ocurrido un error con la reserva a actualizar \n"+
						"No se puede actualizar la reserva");
			}
			return false;
		}
		
		if(this.HuespedesSeleccionados==null|
				   this.HuespedesSeleccionados.size()<1) {
					if (MostrarMensaje){
						JOptionPane.showMessageDialog(EstePanel, 
								"No hay huespedes asignados a la Reserva"+
								"Sin asignacion de huespedes no se puede \n"+
								"actualizar la reserva");
					}
					return false;
				}
		if (this.HuespedesSeleccionados.size()>this.PReserva.getHabitacion().getCapacidad()) {
			if (MostrarMensaje){
				JOptionPane.showMessageDialog(EstePanel, 
						"La cantidad de uespedes asignados a la Reserva"+
						"es mayor a la capacidad maxima de la habitacion \n"+
						"No se puede actualizar la reserva");
			}
			return false;
		}
		
		
		return true;
	}
	
	public Reserva getReserva() {
		try {
			if (PReserva==null|HuespedesSeleccionados==null) {return null;}
			AsignarValorReserva();
			PReserva.setFecha_entrada(FechaCheckIn.getLocalDateValue());
			PReserva.setFecha_salida(FechaCheckOut.getLocalDateValue());
			PReserva.setForma_de_pago(FormaPago.getCBSelectedItem().toString());
			PReserva.setHuespedes(HuespedesSeleccionados);
			return PReserva;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private void AsignarValorReserva() {
		Date FE= MyDateConver.toDate(PReserva.getFecha_entrada());
		Date FS= MyDateConver.toDate(PReserva.getFecha_salida());
		BigDecimal Costo = CalcularCostoTotal(FE,FS,PReserva.getHabitacion());
		PReserva.setValor_Total(Costo);
	}
	private BigDecimal CalcularCostoTotal(Date CheckIn, Date CheckOut, Habitacion Habitacion) {
		int milisecondsByDay = 86400000;
		int dias = (int) (((CheckOut.getTime() - CheckIn.getTime())/milisecondsByDay)+1);
		BigDecimal diasBD= new BigDecimal(dias);
		Habitacion H=Habitacion;
		BigDecimal Costo = H.getValor_fijo().add(H.getValor_variable().multiply(diasBD));
		return Costo;
	}
	private int DiasFormulario() {
		Date FE= FechaCheckIn.getDateValue();
		Date FS= FechaCheckOut.getDateValue();
		if(FE==null|FS==null) {return -1;}
		
		int milisecondsByDay = 86400000;
		int dias = (int) (((FS.getTime() - FE.getTime())/milisecondsByDay)+1);
		return dias;
	}
	private Huesped ObtenerHuespeddeID(long id) {
		
		for (Huesped h: Huespedes) {
			if(h.getId()==id){
				return h;
			}
		}
		return null;
	}
	
	private void AgregarHuepedAreserva(Huesped huesped) {
		if(PReserva.getHabitacion().getCapacidad()==null) {
			JOptionPane.showMessageDialog(EstePanel,
					"Error capacidad maxima de habitacion no definida");
			return;
		}
		if(HuespedesSeleccionados.size()>=PReserva.getHabitacion().getCapacidad()) {
			JOptionPane.showMessageDialog(EstePanel,
					"No se asigno el huespedes \n"
				  + "capacidad maxima alcanzada");
			return;
		}
		
		for (Huesped h:HuespedesSeleccionados) {
			if (huesped.getId()==h.getId()) {
				JOptionPane.showMessageDialog(EstePanel, 
						"Este huesped ya ha sido agregado");
				return;
			}
		}
		
		HuespedesSeleccionados.add(huesped);
		tbHuespedesR.CargarValoresTbHuepedes(HuespedesSeleccionados);
	}
	private void QuitarHuepedAreserva(Huesped huesped) {
		if (HuespedesSeleccionados==null) {return;}
		if (HuespedesSeleccionados.size()==0) {return;}
		
		for (Huesped h:HuespedesSeleccionados) {
			if (huesped.getId()==h.getId()) {
				HuespedesSeleccionados.remove(h);
				tbHuespedesR.CargarValoresTbHuepedes(HuespedesSeleccionados);
				return;
			}
		}
	}
	
	
}
