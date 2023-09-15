package com.latam.cmz.hotelalura.gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import com.latam.cmz.hotelalura.dao.HabitacionDAO;
import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.modelo.Habitacion;
import com.latam.cmz.hotelalura.modelo.Reserva;
import com.latam.cmz.hotelalura.modelo.Usuario;
import com.latam.cmz.hotelalura.mytools.MyDateConver;
import com.latam.cmz.hotelalura.tests.EntydadesTest;
import com.latam.cmz.hotelalura.utils.JPAUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	private static JTextField txtValor;
	private static JDateChooser txtFechaEntrada;
	private static JDateChooser txtFechaSalida;
	private static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private Usuario Usuario;
	
	// Elementos de Acciones.
	private JPanel btnsiguiente, btnAtras, btnBuscarH,
					btnexit, btnCambiarFecha;
	private JLabel lblsiguiente, labelAtras, lblBuscarH,
					labelExit, lblCambiarFecha, lblT_Resultado;
	private JPanel panel_1, header, panel;
	private JFrame EstaVentana=this;
	
	
	// Consultas BD
	private List<Habitacion> Habitaciones, HabitacionesNoDispobibles;
	private EntityManager em;
	private JTable tbHabitaciones;
	private DefaultTableModel tbmodeloHab;
	private JTextArea txtA_DesH;
	private JLabel lblSiguiente, lblT_P1, lblT_D;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView(EntydadesTest.u());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservasView(Usuario usuario) {
		super("Reserva");
		this.Usuario=usuario;
		setIconImage(DirectorioImg.img("aH-40px.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(910, 560);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 289, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(32, 40, 363, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(150, 150, 230));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(10, 47, 104, 107);
		panel_1.add(logo);
		logo.setIcon(DirectorioImg.ico("Ha-100px.png"));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 285, 24);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
		
		lblT_P1 = new JLabel("DISPONIBILIDAD DE HABITACIONES");
		lblT_P1.setHorizontalAlignment(SwingConstants.CENTER);
		lblT_P1.setForeground(new Color(3, 60, 86));
		lblT_P1.setFont(new Font("Roboto", Font.BOLD, 20));
		lblT_P1.setBounds(117, 60, 355, 42);
		panel_1.add(lblT_P1);
		
		lblT_D = new JLabel("DESCRIPCIÓN DE HABITACION SELECCIONADA");
		lblT_D.setHorizontalAlignment(SwingConstants.CENTER);
		lblT_D.setForeground(new Color(3, 60, 86));
		lblT_D.setFont(new Font("Roboto", Font.BOLD, 17));
		lblT_D.setBounds(10, 408, 462, 28);
		lblT_D.setVisible(false);
		panel_1.add(lblT_D);
		
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		
		lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		
		
		//Campos que guardaremos en la base de datos 
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(DirectorioImg.ico("icon-reservas.png"));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaEntrada.setMinSelectableDate(new Date());
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(DirectorioImg.ico("icon-reservas.png"));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.setMinSelectableDate(new Date());
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(68, 328, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);


		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 164, 462, 208);
		scrollPane.setVisible(false);
		panel_1.add(scrollPane);
		
		tbHabitaciones = new JTable();					
		tbHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHabitaciones.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		String t[]= {"No","Habitacion","Calificación","Capacidad","Precio por dia (USD)"};
		tbmodeloHab = new DefaultTableModel(null, t) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
		tbHabitaciones.setModel(tbmodeloHab);
		
		for (int i = 0; i < t.length; i++) {
			tbHabitaciones.getColumnModel().getColumn(i).setResizable(false);
		}
		tbHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(52);
		tbHabitaciones.getColumnModel().getColumn(1).setPreferredWidth(90);
		tbHabitaciones.getColumnModel().getColumn(2).setPreferredWidth(90);
		tbHabitaciones.getColumnModel().getColumn(3).setPreferredWidth(90);
		tbHabitaciones.getColumnModel().getColumn(4).setPreferredWidth(160);
		tbHabitaciones.setRowHeight(30);
	
		scrollPane.setViewportView(tbHabitaciones);
		
		txtA_DesH = new JTextArea();
		txtA_DesH.setBounds(10, 446, 462, 104);
		txtA_DesH.setFont(tbHabitaciones.getFont());
		txtA_DesH.setDisabledTextColor(tbHabitaciones.getForeground());
		txtA_DesH.setLineWrap(true);
		txtA_DesH.setWrapStyleWord(true);
		txtA_DesH.setVisible(false);
		panel_1.add(txtA_DesH);
		txtA_DesH.setEnabled(false);

		CofigurarBotones ();
		
		CofigurarAcciones ();

	}
	
	private void CofigurarBotones () {
		//-BOTONES----------------------------------------------------------------
				//--------| CAMBIAR FECHA-------------------------------------------------
					btnCambiarFecha= new JPanel();
					btnCambiarFecha.setLayout(null);
					btnCambiarFecha.setBackground(SystemColor.textHighlight);
					btnCambiarFecha.setBounds(140, 112, 300, 42);
					panel_1.add(btnCambiarFecha);
					
					lblCambiarFecha = new JLabel("CAMBIAR FECHA");
					lblCambiarFecha.setHorizontalAlignment(SwingConstants.CENTER);
					lblCambiarFecha.setForeground(SystemColor.controlLtHighlight);
					lblCambiarFecha.setFont(new Font("Roboto", Font.PLAIN, 18));
					lblCambiarFecha.setBounds(0, 0, 289, 42);
					btnCambiarFecha.add(lblCambiarFecha);
					
					btnCambiarFecha.setVisible(false);
				
				//--------| EXIT----------------------------------------------------------
					btnexit = new JPanel();
					btnexit.setLayout(null);
					btnexit.setBackground(panel_1.getBackground());
					btnexit.setBounds(429, 0, 53, 36);
					panel_1.add(btnexit);
					labelExit = new JLabel("X");
					labelExit.setForeground(Color.WHITE);
					labelExit.setBounds(0, 0, 53, 36);
					btnexit.add(labelExit);
					labelExit.setHorizontalAlignment(SwingConstants.CENTER);
					labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
				//--------| BUSCAR--------------------------------------------------------
					btnBuscarH = new JPanel();
					btnBuscarH.setLayout(null);
					btnBuscarH.setBackground(SystemColor.textHighlight);
					btnBuscarH.setBounds(140, 112, 300, 42);
					panel_1.add(btnBuscarH);
					
					lblBuscarH = new JLabel("BUSCAR DISPONIBILIDAD");
					lblBuscarH.setHorizontalAlignment(SwingConstants.CENTER);
					lblBuscarH.setForeground(SystemColor.controlLtHighlight);
					lblBuscarH.setFont(new Font("Roboto", Font.PLAIN, 18));
					lblBuscarH.setBounds(0, 0, 300, 42);
					btnBuscarH.add(lblBuscarH);
					
					lblT_Resultado = new JLabel("Total de habitaciones disponibles: ");
					lblT_Resultado.setHorizontalAlignment(SwingConstants.LEFT);
					lblT_Resultado.setForeground(new Color(3, 60, 86));
					lblT_Resultado.setFont(new Font("Roboto", Font.BOLD, 18));
					lblT_Resultado.setBounds(10, 375, 462, 28);
					lblT_Resultado.setVisible(false);
					panel_1.add(lblT_Resultado);

				//--------| ATRAS---------------------------------------------------------
					btnAtras = new JPanel();
					btnAtras.setLayout(null);
					btnAtras.setBackground(Color.WHITE);
					btnAtras.setBounds(0, 0, 127, 36);
					header.add(btnAtras);
					
					labelAtras = new JLabel("Regresar");
					labelAtras.setBounds(10, 0, 117, 36);
					btnAtras.add(labelAtras);
					labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
					labelAtras.setFont(new Font("Roboto", Font.BOLD, 19));
					labelAtras.setForeground(new Color(12, 138, 199));
				//--------| SIGUIENTE-----------------------------------------------------
						
					btnsiguiente = new JPanel();
					btnsiguiente.setLayout(null);
					btnsiguiente.setBackground(Color.GRAY);
					btnsiguiente.setBounds(150, 500, 150, 42);
					btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					panel.add(btnsiguiente);
					
					lblsiguiente = new JLabel("SIGUIENTE");
					lblsiguiente.setBounds(0, 0, 150, 42);
					lblsiguiente.setForeground(SystemColor.controlLtHighlight);
					lblsiguiente.setHorizontalAlignment(SwingConstants.CENTER);
					lblsiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
					btnsiguiente.add(lblsiguiente);
	}
	
	private void CofigurarAcciones () {
		//--------| TABLA DE HABITACIONES-----------------------------------------
		tbHabitaciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP) {
					CargarDescripcion();				
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP) {
					txtA_DesH.setText("");				
				}
			}
	
		});
		tbHabitaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CargarDescripcion();
			}
		});
	//--------| CAMBIAR FECHA-------------------------------------------------
		btnCambiarFecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CambiarFecha();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCambiarFecha.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnCambiarFecha.setBackground(SystemColor.textHighlight);
			}
		});
	
	//--------| EXIT----------------------------------------------------------
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int n = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "Datos sin Guradar."+"\n"
			            +"Al cerrar la sesión perderá la información ingresada"+"\n"
			            + "¿Desea cerrar su sesión?",
			            "CERRAR SESIÓN",
			            JOptionPane.YES_NO_OPTION);

				if(n==0) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				CerrarVentana();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(panel_1.getBackground());
			     labelExit.setForeground(Color.white);
			}
		});
	//--------| BUSCAR--------------------------------------------------------
		btnBuscarH.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (ReservasView.txtFechaEntrada.getDate() == null) {
					ReservasView.txtFechaEntrada.getDateEditor().setDate(null);
					ReservasView.txtFechaSalida.getDateEditor().setDate(null);
					ReservasView.txtFechaEntrada.getDateEditor().getUiComponent().requestFocusInWindow();
					JOptionPane.showMessageDialog(EstaVentana, "Debes de seleccionar una fecha valida de Check In");
					return;
				}
				
				if(ReservasView.txtFechaSalida.getDate() == null ) {
					ReservasView.txtFechaSalida.getDateEditor().setDate(null);
					ReservasView.txtFechaSalida.getDateEditor().getUiComponent().requestFocusInWindow();
					JOptionPane.showMessageDialog(EstaVentana, "Debes de seleccionar una fecha valida de Check Out");
					return;
				}
				
				int s = ReservasView.txtFechaSalida.getDate().compareTo(ReservasView.txtFechaEntrada.getDate());
				
				if (s<0) {
					ReservasView.txtFechaSalida.getDateEditor().setDate(null);
					ReservasView.txtFechaSalida.getDateEditor().getUiComponent().requestFocusInWindow();
					JOptionPane.showMessageDialog(EstaVentana, "Fecha de Check Out debe ser mayor o igual a la Check In");
					return;
				}
				
				Date FE= ReservasView.txtFechaEntrada.getDate();
				Date FS= ReservasView.txtFechaSalida.getDate();
								
				ReservasView.txtFechaEntrada.setEnabled(false);
				ReservasView.txtFechaSalida.setEnabled(false);
				btnBuscarH.setVisible(false);
				
				em = JPAUtils.getEntityManager();
				HabitacionDAO  HabitacionDAO=new HabitacionDAO(em);
				Habitaciones = HabitacionDAO.ConsultarTodos();
				HabitacionesNoDispobibles = HabitacionDAO.ConsultarDisponibles(
						MyDateConver.toLocalDate(FE),MyDateConver.toLocalDate(FS));
				em.clear();
				
				if(HabitacionesNoDispobibles.size()>0) {
					
					for (int i = 0; i < Habitaciones.size(); i++) {
						
						for (int i2 = 0; i2 < HabitacionesNoDispobibles.size(); i2++) {
							if (Habitaciones.get(i).getId()==HabitacionesNoDispobibles.get(i2).getId()){
								Habitaciones.remove(i);
								HabitacionesNoDispobibles.remove(i2);
								i--;
								break;
							}
						}
					}
				}
				
				if (Habitaciones.size()<=0) {
					JOptionPane.showMessageDialog(EstaVentana, "No hay habitaciones disponibles, cambie las fechas");
					CambiarFecha();
					return;
				}
				
				
				int milisecondsByDay = 86400000;
				int dias = (int) (((FS.getTime() - FE.getTime())/milisecondsByDay)+1);
				BigDecimal diasBD= new BigDecimal(dias);
				BigDecimal Costo;				
				System.out.println(diasBD.toString());
				int x=1;
				for (Habitacion H:Habitaciones) {
					Object[] fila = new Object[5];
					fila [0]=x;
					fila [1]=H.getId().toString();
					fila [2]=H.getCalificacion();
					fila [3]=H.getCapacidad();

					Costo= H.getValor_fijo().add(H.getValor_variable().multiply(diasBD));
					Costo=Costo.divide(diasBD,2,RoundingMode.HALF_UP);
					fila [4]= Costo;
					tbmodeloHab.addRow(fila);
					x++;
				}
				
				scrollPane.setVisible(true);
				lblT_Resultado.setText(lblT_Resultado.getText()+Habitaciones.size());
				lblT_Resultado.setVisible(true);
				txtA_DesH.setVisible(true);
				lblT_D.setVisible(true);
				btnCambiarFecha.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscarH.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscarH.setBackground(SystemColor.textHighlight);
			}
		});
	//--------| ATRAS---------------------------------------------------------
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "Datos sin Guradar."+"\n"
			            +"AL regresar se perderá la información ingresada"+"\n"
			            + "¿Desea regresar al Menú de Usuario?",
			            "REGRESAR",
			            JOptionPane.YES_NO_OPTION);

				if(n==0) {
					MenuUsuario usuario = new MenuUsuario(Usuario);
					usuario.setVisible(true);
					CerrarVentana();
				}
								
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
				
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
				 labelAtras.setForeground(new Color(12, 138, 199));
			     
			}
		});
		
	//--------| SIGUIENTE-----------------------------------------------------
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (btnsiguiente.getBackground()==Color.GRAY || btnsiguiente.getBackground() == Color.LIGHT_GRAY) {
					JOptionPane.showMessageDialog(EstaVentana, "Formulario incompleto, no se puede seguir");
					return;
				}
				
				int rs =tbHabitaciones.getSelectedRow();

				if (rs<0) {
					JOptionPane.showMessageDialog(EstaVentana, "Debe selecionar una habitacion");
					return;
				}
				
				if (Habitaciones.size()<0) {
					JOptionPane.showMessageDialog(EstaVentana, "Debe selecionar una habitacion");
					return;
				}
				
				
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					
					Reserva reserva = new Reserva(Usuario, 
							Habitaciones.get(rs), 
							MyDateConver.toLocalDate(ReservasView.txtFechaEntrada.getDate()), 
							MyDateConver.toLocalDate(ReservasView.txtFechaSalida.getDate()),
							new BigDecimal(txtValor.getText()),
							txtFormaPago.getSelectedItem().toString());
					
					RegistroHuesped registro =new RegistroHuesped(reserva);
					registro.setVisible(true);
					CerrarVentana();
					
				} else {
					JOptionPane.showMessageDialog(EstaVentana, "Debes llenar todos los campos.");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnsiguiente.getBackground()==Color.GRAY) {
					btnsiguiente.setBackground(Color.LIGHT_GRAY);					
					return;
				}
				btnsiguiente.setBackground(new Color(0, 156, 223));
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				if (btnsiguiente.getBackground()==Color.LIGHT_GRAY) {
					btnsiguiente.setBackground(Color.GRAY);
					return;
				}
				btnsiguiente.setBackground(SystemColor.textHighlight);
			}
		});
	
	}
	
	private void CerrarVentana() {
		try {
			em.close();
		} catch (Exception e) {
		}
		
		dispose();
	}
		
	private void CargarDescripcion() {
		int rs =tbHabitaciones.getSelectedRow();
		if (rs>=0 && Habitaciones.size()>0 && rs<Habitaciones.size()) {
			Habitacion H=Habitaciones.get(rs);
			txtA_DesH.setText(H.getDescripcion());
			btnsiguiente.setBackground(SystemColor.textHighlight);
			
			if(ReservasView.txtFechaEntrada.getDate()!=null &&
					ReservasView.txtFechaSalida.getDate()!=null) {
				Date FE= ReservasView.txtFechaEntrada.getDate();
				Date FS= ReservasView.txtFechaSalida.getDate();
				int milisecondsByDay = 86400000;
				int dias = (int) (((FS.getTime() - FE.getTime())/milisecondsByDay)+1);
				BigDecimal diasBD= new BigDecimal(dias);
				BigDecimal Costo = H.getValor_fijo().add(H.getValor_variable().multiply(diasBD));
				txtValor.setText(Costo.toString());
			} else {txtValor.setText("");}

		} else {txtA_DesH.setText("");btnsiguiente.setBackground(Color.GRAY);txtValor.setText("");}

	}
	private void CambiarFecha() {
		txtFechaEntrada.setEnabled(true);;
		txtFechaSalida.setEnabled(true);
		btnBuscarH.setVisible(true);
		
		btnCambiarFecha.setVisible(false);
		txtValor.setText("");
		txtA_DesH.setText("");
		txtA_DesH.setVisible(false);
		lblT_D.setVisible(false);
		scrollPane.setVisible(false);
		lblT_Resultado.setVisible(false);
		lblT_Resultado.setText("Total de habitaciones disponibles: ");
		btnsiguiente.setBackground(Color.GRAY);
		btnCambiarFecha.setBackground(SystemColor.textHighlight);
		tbmodeloHab.setRowCount(0);	
	}
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	        this.setSize(this.getMinimumSize());
}
}
