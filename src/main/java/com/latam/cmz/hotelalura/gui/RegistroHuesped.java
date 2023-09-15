package com.latam.cmz.hotelalura.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import com.latam.cmz.hotelalura.dao.DatoPersonalDAO;
import com.latam.cmz.hotelalura.dao.HuespedDAO;
import com.latam.cmz.hotelalura.dao.NacionalidadDAO;
import com.latam.cmz.hotelalura.dao.ReservaDAO;
import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.modelo.DatoPersonal;
import com.latam.cmz.hotelalura.modelo.Huesped;
import com.latam.cmz.hotelalura.modelo.Nacionalidad;
import com.latam.cmz.hotelalura.modelo.Reserva;
import com.latam.cmz.hotelalura.modelo.Usuario;
import com.latam.cmz.hotelalura.mytools.MyDateConver;
import com.latam.cmz.hotelalura.mytools.MyValidation;
import com.latam.cmz.hotelalura.tests.EntydadesTest;
import com.latam.cmz.hotelalura.utils.JPAUtils;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane,
					header,
					btnAtras,
					btnexit,
					panelLh,
					panelNuevoHuesped,
					btnNuevoHuesped,
					btnCancelarNuevoHuesped,
					panelFiltro,
					btnFiltro,
					btnAddHuesped,
					btnAsignarHuesped,
					btnQuitarHuesped,
					btnGenerarReserva;
	
	private JScrollPane scrollPaneLH,scrollPaneLH2;
	
	private JLabel labelAtras,labelExit, lblNombre, lblApellido,
					lblFechaN, lblNacionalidad, lblTelefono,
					lblNuevoHuesped, lblCancelarNuevoHuesped,
					lblNuevoHuesp_T, lblTituloLH_1, lblTituloLH_2,
					lblFiltro_T, lblFiltroNombre, lblFiltroApellido,
					lblFiltro, lblAddHuesped, lblFiltro_T2,
					lblAsignarHuesped, lblQuitarHuesped,
					lblGenerarReserva, lblTituloLH_3;
	
	private JTextField txtNombre, txtApellido, txtTelefono, txtFiltroNombre, txtFiltroApellido;
	
	private JDateChooser txtFechaN;
	
	private JSeparator separator_1, separator_2, separator_3, separator_4, separator_5,
						separator_6, separator_7, separator_8, separator_9, separator_10;
	
	private JComboBox<String> txtNacionalidad;
	
	private JTable tbHuespedesSeleccionados, tbHuespedes;
	private DefaultTableModel tbHuespedesSeleccionadosModelo,tbHuespedesModelo;
	
	private final static Color 
		txtHeaderbtn= new Color(3, 60, 86),
		backgroundPanellh=new Color(150, 150, 230),
		backgroundBtnMouseEntered=new Color(12, 138, 199);
	
	int xMouse, yMouse, maxHuespedes;
	//
	private Usuario Usuario;
	private Reserva Reserva;
	private List<Huesped> Huespedes, HuespedesSeleccionados=new ArrayList<>();
	private List<Nacionalidad> Nacionalidades;
	private EntityManager em;
	private JFrame EstaVentana=this;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped(EntydadesTest.re());
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
	public RegistroHuesped(Reserva reserva) {
		this.Usuario=reserva.getUsuario();
		this.Reserva=reserva;
		this.maxHuespedes=reserva.getHabitacion().getCapacidad();
		
		setIconImage(DirectorioImg.img("lOGO-50PX.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(910, 560);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		em = JPAUtils.getEntityManager();
		HuespedDAO  HuespedDAO=new HuespedDAO(em);
		NacionalidadDAO  NacionalidadDAO=new NacionalidadDAO(em);
		
		Huespedes = HuespedDAO.ConsultarTodos();
		Nacionalidades = NacionalidadDAO.ConsultarTodos();
		em.clear();
		
		
		IniciarComponentes();
		HuepedSeleccionadosEst();
		ConfigurarAcciones();
		
		
	}

	private void IniciarComponentes() {
		//--| ENCABEZADO |----------------------------------------------------
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
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
		
		contentPane.add(header);
		
		//--| PANEL IZQ |-----------------------------------------------------
		panelLh = new JPanel();
		panelLh.setBounds(0, 0, 489, 560);
		panelLh.setBackground(backgroundPanellh);
		panelLh.setLayout(null);
		
		contentPane.add(panelLh);
		
		lblTituloLH_1 = new JLabel("LISTA DE HUÉSPEDES");
		lblTituloLH_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloLH_1.setForeground(new Color(3, 60, 86));
		lblTituloLH_1.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTituloLH_1.setBounds(10, 56, 469, 42);
		panelLh.add(lblTituloLH_1);
		
		scrollPaneLH = new JScrollPane();
		scrollPaneLH.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneLH.setBounds(30, 106, 429, 180);
		panelLh.add(scrollPaneLH);
		
		tbHuespedes = new JTable();					
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		String t[]= {"No","Id","Nombre","Apellido"};
		tbHuespedesModelo = new DefaultTableModel(null, t) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
			tbHuespedes.setModel(tbHuespedesModelo);
		
		for (int i = 0; i < t.length; i++) {
			tbHuespedes.getColumnModel().getColumn(i).setResizable(false);
		}
		tbHuespedes.getColumnModel().getColumn(0).setPreferredWidth(20);
		tbHuespedes.getColumnModel().getColumn(1).setPreferredWidth(40);
		tbHuespedes.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbHuespedes.getColumnModel().getColumn(3).setPreferredWidth(150);
		tbHuespedes.setRowHeight(30);
		scrollPaneLH.setViewportView(tbHuespedes);

		CargarValoresTbHuepedes ();
		
		lblTituloLH_2 = new JLabel("HUÉSPEDES ASIGNADOS");
		lblTituloLH_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloLH_2.setForeground(new Color(3, 60, 86));
		lblTituloLH_2.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTituloLH_2.setBounds(10, 296, 369, 42);
		panelLh.add(lblTituloLH_2);
		
		lblTituloLH_3 = new JLabel("");
		lblTituloLH_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloLH_3.setForeground(new Color(3, 60, 86));
		lblTituloLH_3.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTituloLH_3.setBounds(379, 296, 100, 42);
		panelLh.add(lblTituloLH_3);
		
		scrollPaneLH2 = new JScrollPane();
		scrollPaneLH2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneLH2.setBounds(30, 346, 429, 180);
		panelLh.add(scrollPaneLH2);
		
		tbHuespedesSeleccionados = new JTable();					
		tbHuespedesSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedesSeleccionados.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		
		tbHuespedesSeleccionadosModelo = new DefaultTableModel(null, t) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
			tbHuespedesSeleccionados.setModel(tbHuespedesSeleccionadosModelo);
		
		for (int i = 0; i < t.length; i++) {
			tbHuespedesSeleccionados.getColumnModel().getColumn(i).setResizable(false);
		}
		tbHuespedesSeleccionados.getColumnModel().getColumn(0).setPreferredWidth(20);
		tbHuespedesSeleccionados.getColumnModel().getColumn(1).setPreferredWidth(40);
		tbHuespedesSeleccionados.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbHuespedesSeleccionados.getColumnModel().getColumn(3).setPreferredWidth(150);
		tbHuespedesSeleccionados.setRowHeight(30);
		
		scrollPaneLH2.setViewportView(tbHuespedesSeleccionados);
		
		//--| PANEL MENU NUEVO HUESPED |--------------------------------------
		ConfPanelNuevoHuesp();
		//--| PANEL FILTRO Y AGREGAR HUESPED----------------------------------
		ConfPanelFiltro();
		//--| ATRAS |---------------------------------------------------------
		btnAtras = new JPanel();
		btnAtras.setLayout(null);
		btnAtras.setBackground(panelLh.getBackground());
		btnAtras.setBounds(0, 0, 127, 36);
		
		header.add(btnAtras);
		
		labelAtras = new JLabel("Regresar");
		labelAtras.setBounds(10, 0, 117, 36);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.BOLD, 19));
		labelAtras.setForeground(txtHeaderbtn);
		
		btnAtras.add(labelAtras);
		
		//--| EXIT |-----------------------------------------------------------
		btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(contentPane.getBackground());
		btnexit.setBounds(857, 0, 53, 36);
		
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(txtHeaderbtn);
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnexit.add(labelExit);

	}

	private void CargarValoresTbHuepedes(String nombre, String apellido) {
		if(Huespedes==null || Huespedes.size()<=0) {
			return;
		}
		
		if (nombre.isBlank()||nombre.isEmpty()) {
			nombre=null;
		}
		if (apellido.isBlank()||apellido.isEmpty()) {
			apellido=null;
		}
		
		if (nombre==null&&apellido==null) {
			CargarValoresTbHuepedes();
			return;}
		
		if (nombre!=null) {
			nombre=nombre.toLowerCase();
		}
		if (apellido!=null) {
			apellido=apellido.toLowerCase();
		}
		
		tbHuespedesModelo.setRowCount(0);
		int x=1;
		boolean agregar=true;
		for (Huesped H:Huespedes) {
			agregar=true;
			String HN=H.getDatoPersonal().getNombre().toLowerCase();
			String HA=H.getDatoPersonal().getApellido().toLowerCase();
			
			if (nombre!=null && !HN.contains(nombre)) {
				agregar=false;
			}
			if (apellido!=null && !HA.contains(apellido)) {
				agregar=false;
			}
			
			if (agregar) {
				Object[] fila = new Object[4];
				fila [0]=x;
				fila [1]=H.getId().toString();
				fila [2]=H.getDatoPersonal().getNombre();
				fila [3]=H.getDatoPersonal().getApellido();
				tbHuespedesModelo.addRow(fila);
				x++;
			}
			
		}
		
	}

	private void CargarValoresTbHuepedes() {
		if(Huespedes==null || Huespedes.size()<=0) {
			return;
		}
		tbHuespedesModelo.setRowCount(0);
		int x=1;
		for (Huesped H:Huespedes) {
			Object[] fila = new Object[4];
			fila [0]=x;
			fila [1]=H.getId().toString();
			fila [2]=H.getDatoPersonal().getNombre();
			fila [3]=H.getDatoPersonal().getApellido();
			tbHuespedesModelo.addRow(fila);
			x++;
		}
		
	}
	
	private void CargarValoresTbHuepedesSelecionados() {
		if(HuespedesSeleccionados==null) {
			return;
		}
		
		tbHuespedesSeleccionadosModelo.setRowCount(0);
		int x=1;
		for (Huesped H:HuespedesSeleccionados) {
			Object[] fila = new Object[4];
			fila [0]=x;
			fila [1]=H.getId().toString();
			fila [2]=H.getDatoPersonal().getNombre();
			fila [3]=H.getDatoPersonal().getApellido();
			tbHuespedesSeleccionadosModelo.addRow(fila);
			x++;
		}
		HuepedSeleccionadosEst();
		
	}
	
	private void ConfPanelFiltro() {
		panelFiltro = new JPanel();
		panelFiltro.setLocation(panelNuevoHuesped.getLocation());
		panelFiltro.setSize(panelNuevoHuesped.getSize());
		panelFiltro.setBackground(contentPane.getBackground());
		panelFiltro.setLayout(null);
		contentPane.add(panelFiltro);
		
		lblFiltro_T = new JLabel("FILTRO");
		lblFiltro_T.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltro_T.setForeground(txtHeaderbtn);
		lblFiltro_T.setFont(new Font("Roboto", Font.BOLD, 20));
		lblFiltro_T.setLocation(lblNuevoHuesp_T.getLocation());
		lblFiltro_T.setSize(lblNuevoHuesp_T.getSize());
		panelFiltro.add(lblFiltro_T);
		int d = 65; // Distancia entre elementos
		
		// NOMBRE -----------------------------------------------------
			lblFiltroNombre = new JLabel("NOMBRE");
			txtFiltroNombre = new JTextField();
			separator_6 = new JSeparator();
			NuevoHuespedDefaultTxtField (
					panelFiltro,
					lblFiltroNombre,
					txtFiltroNombre,
					separator_6,
					0,51,
					401);
		// APELLIDO ---------------------------------------------------
			lblFiltroApellido = new JLabel("APELLIDO");
			txtFiltroApellido = new JTextField();
			separator_7 = new JSeparator();
			NuevoHuespedDefaultTxtField (
					panelFiltro,
					lblFiltroApellido,
					txtFiltroApellido,
					separator_7,
					0,(lblFiltroNombre.getLocation().y+d),
					401);
		// BOTON GUARDAR NUEVO HUESPED --------------------------------
			btnFiltro = new JPanel();
			lblFiltro = new JLabel("APLICAR");
			ConfiguarBotonDefault (
					panelFiltro,
					btnFiltro,
					lblFiltro,
					140,(lblFiltroApellido.getLocation().y+d+10), 121);
			
		// BOTON GUARDAR NUEVO HUESPED --------------------------------
			separator_8 = new JSeparator();
			separator_8.setLocation(5, (btnFiltro.getLocation().y+55));
			separator_8.setSize(391,2);
			separator_8.setForeground(Color.GRAY);
			separator_8.setBackground(Color.GRAY);
			panelFiltro.add(separator_8);
			
			btnAddHuesped = new JPanel();
			lblAddHuesped = new JLabel("AGREGAR HUESPED NUEVO");
			ConfiguarBotonDefault (
					panelFiltro,
					btnAddHuesped,
					lblAddHuesped,
					50,(btnFiltro.getLocation().y+d+1), 301);
			
			separator_9 = new JSeparator();
			separator_9.setLocation(5, (btnFiltro.getLocation().y+120));
			separator_9.setSize(391,2);
			separator_9.setForeground(Color.GRAY);
			separator_9.setBackground(Color.GRAY);
			panelFiltro.add(separator_9);
		// BOTON AGREGAR HUESPED A LA RECERVA -------------------------
			lblFiltro_T2 = new JLabel("ASIGNACIÓN DE HUÉSPED A LA RESERVA");
			lblFiltro_T2.setHorizontalAlignment(SwingConstants.CENTER);
			lblFiltro_T2.setForeground(txtHeaderbtn);
			lblFiltro_T2.setFont(new Font("Roboto", Font.BOLD, 18));
			lblFiltro_T2.setLocation(lblNuevoHuesp_T.getLocation().x,separator_9.getLocation().y+5);
			lblFiltro_T2.setSize(lblNuevoHuesp_T.getSize());
			panelFiltro.add(lblFiltro_T2);
			
			btnAsignarHuesped = new JPanel();
			lblAsignarHuesped = new JLabel("AGREGAR");
			
			ConfiguarBotonDefault (
					panelFiltro,
					btnAsignarHuesped,
					lblAsignarHuesped,
					25,(lblFiltro_T2.getLocation().y+d-20), 150);
			
			btnQuitarHuesped = new JPanel();
			lblQuitarHuesped = new JLabel("QUITAR");
			ConfiguarBotonDefault (
					panelFiltro,
					btnQuitarHuesped,
					lblQuitarHuesped,
					(btnAsignarHuesped.getLocation().x+btnAsignarHuesped.getSize().width+50),
					(lblFiltro_T2.getLocation().y+d-20), 150);
			separator_10 = new JSeparator();
			separator_10.setLocation(5, (btnQuitarHuesped.getLocation().y+60));
			separator_10.setSize(391,2);
			separator_10.setForeground(Color.GRAY);
			separator_10.setBackground(Color.GRAY);
			panelFiltro.add(separator_10);
			// BOTON GENERAR LA RESERVA -----------------------------------			
			btnGenerarReserva = new JPanel();
			lblGenerarReserva = new JLabel("GENERAR RESERVA");
			ConfiguarBotonDefault (
					panelFiltro,
					btnGenerarReserva,
					lblGenerarReserva,
					btnAddHuesped.getLocation().x,
					(separator_10.getLocation().y+15), btnAddHuesped.getSize().width);
			
	}
	
	private void ConfPanelNuevoHuesp() {
		panelNuevoHuesped = new JPanel();
		panelNuevoHuesped.setBounds(499, 46, 401, 504);
		panelNuevoHuesped.setBackground(contentPane.getBackground());
		panelNuevoHuesped.setLayout(null);
		panelNuevoHuesped.setVisible(false);
		contentPane.add(panelNuevoHuesped);
		
		
		lblNuevoHuesp_T = new JLabel("NUEVO HUESPED");
		lblNuevoHuesp_T.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoHuesp_T.setForeground(txtHeaderbtn);
		lblNuevoHuesp_T.setFont(new Font("Roboto", Font.BOLD, 20));
		lblNuevoHuesp_T.setBounds(5, 10, 391, 42);
		panelNuevoHuesped.add(lblNuevoHuesp_T);
		
		int d = 65; // Distancia entre elementos
		// NOMBRE -----------------------------------------------------
		lblNombre = new JLabel("NOMBRE");
		txtNombre = new JTextField();
		separator_1 = new JSeparator();
		NuevoHuespedDefaultTxtField (
				panelNuevoHuesped,
				lblNombre,
				txtNombre,
				separator_1,
				0,67,
				401);

		// APELLIDO ---------------------------------------------------
		lblApellido = new JLabel("APELLIDO");
		txtApellido = new JTextField();
		separator_2 = new JSeparator();
		NuevoHuespedDefaultTxtField(
				panelNuevoHuesped,
				lblApellido,
				txtApellido,
				separator_2,
				0,(lblNombre.getLocation().y+d),
				401);
		
		// FECHA DE NACIMIENTO ----------------------------------------
		lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		txtFechaN = new JDateChooser();
		separator_3 = new JSeparator();
		NuevoHuespedDefaultDateField(
				panelNuevoHuesped,
				lblFechaN,
				txtFechaN,
				separator_3,
				0, (lblApellido.getLocation().y+d),
				401);
		
		// NACIONALIDAD -----------------------------------------------
		lblNacionalidad = new JLabel("NACIONALIDAD");
		txtNacionalidad = new JComboBox<String>();
		separator_4 = new JSeparator();

		NuevoHuespedDefaultComboBox(
				panelNuevoHuesped,
				lblNacionalidad,
				txtNacionalidad,
				separator_4,
				0,(lblFechaN.getLocation().y+d),
				401);
				
		for (Nacionalidad N:Nacionalidades) {
			txtNacionalidad.addItem(N.getGentilicio());
		}
		
		// TELEFONO ---------------------------------------------------
		lblTelefono = new JLabel("TELÉFONO");
		txtTelefono = new JTextField();
		separator_5 = new JSeparator();
		NuevoHuespedDefaultTxtField(
				panelNuevoHuesped,
				lblTelefono,
				txtTelefono,
				separator_5,
				0, (lblNacionalidad.getLocation().y+d),
				401);
		// BOTON GUARDAR NUEVO HUESPED --------------------------------
		btnNuevoHuesped = new JPanel();
		lblNuevoHuesped = new JLabel("GUARDAR");
		ConfiguarBotonDefault (
				panelNuevoHuesped,
				btnNuevoHuesped,
				lblNuevoHuesped,
				50,435, 120);
		// BOTON CACELAR NUEVO HUESPED --------------------------------
		btnCancelarNuevoHuesped = new JPanel();
		lblCancelarNuevoHuesped = new JLabel("CANCELAR");
		ConfiguarBotonDefault (
				panelNuevoHuesped,
				btnCancelarNuevoHuesped,
				lblCancelarNuevoHuesped,
				230,435, 120);
	}
	
	private void ConfiguarBotonDefault (JPanel panelcontenido, JPanel btnPanel, JLabel lblLabel, int x, int y, int Width) {
		btnPanel.setLocation(x, y);
		btnPanel.setSize(Width, 42);;
		btnPanel.setLayout(null);
		btnPanel.setBackground(SystemColor.textHighlight);
		panelcontenido.add(btnPanel);
		
		lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel.setForeground(SystemColor.controlLtHighlight);
		lblLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblLabel.setBounds(0, 0, Width, 42);
		btnPanel.add(lblLabel);
		
		btnPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPanel.setBackground(new Color(0, 156, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPanel.setBackground(SystemColor.textHighlight);
			}
		});
	}
	
	private void NuevoHuespedDefaultTxtField(JPanel Panel,JLabel Label,JTextField txtField, JSeparator Separator, int x, int y, int Width) {
		int dx =50;
		int w=(Width-(dx*2));
		int h= 25;
		CofigurarLabelSepataror(Panel, Label, Separator, x, y, Width, dx, w, h);
		
		txtField.setLocation(x+dx,y+21);
		txtField.setSize(w, h);
		txtField.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtField.setBackground(Color.WHITE);
		txtField.setColumns(10);
		txtField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Panel.add(txtField);
		
	}
	
	private void NuevoHuespedDefaultComboBox(JPanel Panel,JLabel Label,JComboBox<String> ComboBox, JSeparator Separator, int x, int y, int Width) {
		int dx =50;
		int w=(Width-(dx*2));
		int h= 35;
		CofigurarLabelSepataror (Panel, Label, Separator, x, y, Width, dx, w,h);
		
		ComboBox.setLocation(x+dx,y+21);
		ComboBox.setSize(w, h);
		ComboBox.setBackground(SystemColor.text);
		ComboBox.setEditable(false);
		ComboBox.setFont(new Font("Roboto", Font.PLAIN, 18));
		Panel.add(ComboBox);

	}
	
	private void NuevoHuespedDefaultDateField(JPanel Panel,JLabel Label,JDateChooser DateChooser, JSeparator Separator, int x, int y, int Width) {
		int dx =50;
		int w=(Width-(dx*2));
		int h= 35;
		CofigurarLabelSepataror (Panel, Label, Separator, x, y, Width, dx, w, h);
		
		DateChooser.setLocation(x+dx,y+21);
		DateChooser.setSize(w, h);
		DateChooser.setFont(new Font("Roboto", Font.PLAIN, 18));
		DateChooser.getCalendarButton().setIcon(DirectorioImg.ico("icon-reservas.png"));
		DateChooser.getCalendarButton().setBackground(SystemColor.textHighlight);
		DateChooser.setDateFormatString("yyyy-MM-dd");
		
		Panel.add(DateChooser);

	}
	private void CofigurarLabelSepataror(JPanel Panel, JLabel Label, JSeparator Separator, int x, int y, int Width, int dx, int w, int h) {
		Label.setLocation(x, y);
		Label.setSize(Width, 18);
		Label.setForeground(SystemColor.textInactiveText);
		Label.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		Panel.add(Label);
		
		Separator.setLocation(x+dx, y+h+25);
		Separator.setSize(w,2);
		Separator.setForeground(new Color(12, 138, 199));
		Separator.setBackground(new Color(12, 138, 199));
		Panel.add(Separator);
	}
	
	
	private void ConfigurarAcciones() {
	//--| BOTONES ATRAS |-------------------------------------------------
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
				btnAtras.setBackground(backgroundBtnMouseEntered);
				labelAtras.setForeground(Color.white);
				
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(panelLh.getBackground());
				 labelAtras.setForeground(txtHeaderbtn);
			     
			}
		});
	//--| BOTONES EXIT |--------------------------------------------------
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
				 btnexit.setBackground(contentPane.getBackground());
			     labelExit.setForeground(txtHeaderbtn);
			}
		});
		//--| BOTON NUEVO HUESPED |------------------------------------------
		btnAddHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelFiltro.setVisible(false);
				panelNuevoHuesped.setVisible(true);
				txtNombre.grabFocus();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
				
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		//--| BOTON CANCELAR NUEVO HUESPED |-----------------------------
		btnCancelarNuevoHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelNuevoHuesped.setVisible(false);
				panelFiltro.setVisible(true);
				LimpiarPanelNuevoHuesped();
			}
		});
		
		//--| BOTON GUARDAR NUEVO HUESPED |------------------------------
		btnNuevoHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtNombre.getText()==null|| 
						txtNombre.getText().isBlank()|| 
						txtNombre.getText().isEmpty()) {
					txtNombre.setText("");
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un nombre");
					txtNombre.grabFocus();
					return;
				}
				
				if (txtApellido.getText()==null|| 
						txtApellido.getText().isBlank()|| 
						txtApellido.getText().isEmpty()) {
					txtApellido.setText("");
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un apellido");
					txtApellido.grabFocus();
					return;
				}
				
				if (txtFechaN.getDate()==null) {
					txtFechaN.setDate(null);
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar una fecha de nacimiento");
					txtFechaN.getDateEditor().getUiComponent().requestFocusInWindow();
					return;
				}
				
				if (txtNacionalidad.getSelectedItem().toString()==null|| 
						txtNacionalidad.getSelectedItem().toString().isBlank()|| 
						txtNacionalidad.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de seleccionar una nacionalidad");
					txtNacionalidad.grabFocus();
					return;
				}
				
				if (txtNombre.getText()==null|| 
						txtTelefono.getText().isBlank()|| 
						txtTelefono.getText().isEmpty()) {
					txtTelefono.setText("");
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un numero de telefono");
					txtTelefono.grabFocus();
					return;
				}
				
				
				if (!MyValidation.Name(txtNombre.getText())) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un nombre valido");
					txtNombre.grabFocus();
					return;
				}
				if (MyValidation.finalSpace(txtNombre.getText())) {
					if (txtNombre.getText()!=null &&txtNombre.getText().length()>0) {
						String t= txtNombre.getText();
						t=t.substring(0, t.length() - 1);
						txtNombre.setText(t);
						if (txtNombre.getForeground()==Color.RED){
							txtNombre.setForeground(Color.BLACK);
						}
					}
				}
				
				if (!MyValidation.Name(txtApellido.getText())) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un apellido valido");
					txtApellido.grabFocus();
					return;
				}
				
				if (MyValidation.finalSpace(txtApellido.getText())) {
					if (txtApellido.getText()!=null &&txtApellido.getText().length()>0) {
						String t= txtApellido.getText();
						t=t.substring(0, t.length() - 1);
						txtApellido.setText(t);
						if (txtApellido.getForeground()==Color.RED){
							txtApellido.setForeground(Color.BLACK);
						}
					}

				}
				
				if (!MyValidation.Telephonumber(txtTelefono.getText())) {
					
					JOptionPane.showMessageDialog(EstaVentana, 
							"Debe de ingresar un numero de telefono valido");
					txtTelefono.grabFocus();
					return;
				}
				if (Nacionalidades.size()==0 ||Nacionalidades==null ) {
					return;
				}
				if (txtNacionalidad.getItemCount()>Nacionalidades.size()) {
					return;
				}
				if (txtNacionalidad.getSelectedIndex()>=Nacionalidades.size()) {
					return;
				}
				
				try {
					Nacionalidad nacionalidad = Nacionalidades.get(txtNacionalidad.getSelectedIndex());
	
					DatoPersonal datopersonal = new DatoPersonal(
							txtNombre.getText(), 
							txtApellido.getText(),
							MyDateConver.toLocalDate(txtFechaN.getDate()),
							nacionalidad, 
							txtTelefono.getText());
					Huesped huesped = new Huesped(datopersonal);
					
						
					DatoPersonalDAO DatoPersonalDAO= new DatoPersonalDAO (em);
					HuespedDAO  HuespedDAO=new HuespedDAO(em);
				
				
					em.getTransaction().begin();
					DatoPersonalDAO.guardar(datopersonal);
					HuespedDAO.guardar(huesped);
					em.getTransaction().commit();
					Huespedes = HuespedDAO.ConsultarTodos();
					em.clear();
					
					CargarValoresTbHuepedes();
					AgregarHuepedAreserva(huesped);
					
					panelNuevoHuesped.setVisible(false);
					panelFiltro.setVisible(true);
					LimpiarPanelNuevoHuesped();
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Huesped fue guardado exitaosamente¡ \n"+
							"El codigo del nuevo hueped es: "+
							huesped.getId());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Que pena! el nuevo huesped no fue guardo. \n"+
							"Occurrio un error en la conexion con la Base de datos"
									,"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}
		});
		//--| BOTON ASIGNAR HUESPED A RESERVA |--------------------------
		btnAsignarHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rs =tbHuespedes.getSelectedRow();
				if (rs<0) {
					JOptionPane.showMessageDialog(EstaVentana, 
						"Debe seleccionar un huésped de la lista de huéspedes");
					return;
				}

				if(rs>=Huespedes.size()) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Hueped seleccionado no se encuentra en la base de datos");
					return;
				}
				
				if (Huespedes==null) {return;}
				if (Huespedes.size()==0) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Error: No se encontro huepedes en la Base de datos");
					return;}
				long id = Long.parseLong((String) tbHuespedesModelo.getValueAt(rs, 1));
				Huesped h = ObtenerHuespeddeID(id);
				if (h!=null) {
					AgregarHuepedAreserva(h);
					LimpiarFiltro();
				}
				
			}

			
		});
		
		//--| BOTON QUITAR HUESPED ASIGNADO |----------------------------
		btnQuitarHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rs =tbHuespedesSeleccionados.getSelectedRow();
				if (rs<0) {
					JOptionPane.showMessageDialog(EstaVentana, 
						"Debe seleccionar un huésped de los asignados");
					return;
				}
				
				if (HuespedesSeleccionados==null) {return;}
				if (HuespedesSeleccionados.size()==0) {return;}
				if(rs>=HuespedesSeleccionados.size()) {return;}
				
				Huesped h =HuespedesSeleccionados.get(rs);
				QuitarHuepedAreserva(h);
				
			}
			
		});
		//--| -----------------------------------------------------------
		btnFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String a=txtFiltroApellido.getText();
				String n=txtFiltroNombre.getText();
				CargarValoresTbHuepedes(n, a);
			}
			
		});
		
		//--| BOTON DE GENERAR RESERVA |---------------------------------
		btnGenerarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(HuespedesSeleccionados==null) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Error: No se encontro huepedes asignados");
					return;}
				
				if(tbHuespedesSeleccionadosModelo.getRowCount()!=HuespedesSeleccionados.size()){
					JOptionPane.showMessageDialog(EstaVentana, 
							"Error: Tamño de tabla no coincide con arraylist (Huespedes Asignados)");
					return;}
				if(Reserva==null) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Error: Reserva nula");
					return;}
				if(Reserva.getHabitacion().getCapacidad()==null) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Error: capacidad de habitacion nula");
					return;	}
				if(Reserva.getHabitacion().getCapacidad()<=0) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"No se puede generar la reserva por Capacidad de habitacion no definida");
					return;}
				
				if(HuespedesSeleccionados.size()<=0) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"Bebe de seleccionar al menos 1 huesped");
					return;	}
				if(HuespedesSeleccionados.size()>Reserva.getHabitacion().getCapacidad()) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"No se puede generar la reserva, capacidad de habitación excedida");
					return;	}
				
				
				Reserva.setHuespedes(HuespedesSeleccionados);
				
				
				ReservaDAO ReservaDAO= new ReservaDAO (em);
				
				try {
					em.getTransaction().begin();
					ReservaDAO.guardar(Reserva);
					em.getTransaction().commit();
					em.clear();
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Reserva fue generada exitaosamente¡ \n"+
							"El numero de reserva es: "+
									Reserva.getId());
					
					MenuUsuario usuario = new MenuUsuario(Usuario);
					usuario.setVisible(true);
					CerrarVentana();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Que pena! su reserva no puedo ser generada \n"+
							"Occurrio un error en la conexion con la Base de datos"+
									Reserva.getId(),"ERROR",JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		//--| -----------------------------------------------------------
		//--| -----------------------------------------------------------
		//--| INGRESO DE DATOS TELEFONO |--------------------------------
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				MyValidation.KeyTypedTelephoNumberValidation(e,txtTelefono);}
			@Override
			public void keyReleased(KeyEvent e) {
				String t= txtTelefono.getText();
				boolean Valido=MyValidation.Telephonumber(t);
				if (!Valido) {
					//e.consume();
					txtTelefono.setForeground(Color.RED);
				} else {
					txtTelefono.setForeground(Color.BLACK);
				}
			}
			
		});
		//--| INGRESO DE DATOS NOMBRE |----------------------------------
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				MyValidation.KeyTypedNameValidation(e,txtNombre);}
			@Override
			public void keyReleased(KeyEvent e) {
				MyValidation.KeyReleaseNameValidation(txtNombre);}
		});
		//--| INGRESO DE DATOS APELLIDO |--------------------------------
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				MyValidation.KeyTypedNameValidation(e,txtApellido);}
			@Override
			public void keyReleased(KeyEvent e) {
				MyValidation.KeyReleaseNameValidation(txtApellido);}
		});

		
	}

	private void LimpiarFiltro() {
		txtFiltroApellido.setText("");
		txtFiltroNombre.setText("");
		CargarValoresTbHuepedes();
		
	}
	private void LimpiarPanelNuevoHuesped() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtFechaN.setDate(null);
		
		txtNombre.setForeground(Color.BLACK);
		txtApellido.setForeground(Color.BLACK);
		txtTelefono.setForeground(Color.BLACK);
	}
	
	private Huesped ObtenerHuespeddeID(long id) {
		
		for (Huesped h: Huespedes) {
			if(h.getId()==id){
				return h;
			}
		}
		return null;
	}
	
	private void QuitarHuepedAreserva(Huesped huesped) {
		if (HuespedesSeleccionados==null) {return;}
		if (HuespedesSeleccionados.size()==0) {return;}
		
		for (Huesped h:HuespedesSeleccionados) {
			if (huesped.getId()==h.getId()) {
				HuespedesSeleccionados.remove(h);
				CargarValoresTbHuepedesSelecionados();
				return;
			}
		}
	
	}
	
	private void AgregarHuepedAreserva(Huesped huesped) {
		if(Reserva.getHabitacion().getCapacidad()==null) {
			JOptionPane.showMessageDialog(EstaVentana, "Error capacidad maxima de habitacion no definida");
			return;
		}
		if(HuespedesSeleccionados.size()>=Reserva.getHabitacion().getCapacidad()) {
			JOptionPane.showMessageDialog(EstaVentana, "No se asigno el huespedes, capacidad maxima alcanzada");
			return;
		}
		
		for (Huesped h:HuespedesSeleccionados) {
			if (huesped.getId()==h.getId()) {
				JOptionPane.showMessageDialog(EstaVentana, 
						"Este huesped ya ha sido agregado");
				return;
			}
		}
		
		HuespedesSeleccionados.add(huesped);
		CargarValoresTbHuepedesSelecionados();
	}
	
	private void CerrarVentana() {
		try {
			em.close();
		} catch (Exception e) {
		}
		
		dispose();
	}
	
	private void HuepedSeleccionadosEst() {
		String t="";
		
		if(Reserva.getHabitacion().getCapacidad()==null){}
		else if(HuespedesSeleccionados==null){
			lblTituloLH_3.setText(t);
			return;}
		
		Integer Hmax=Reserva.getHabitacion().getCapacidad();
		Integer HSelect=HuespedesSeleccionados.size();
		
		if (Hmax<=0) {}
		else if (HSelect<0) {}
		else {t=HSelect+"/"+Hmax;}
		
		lblTituloLH_3.setText(t);
	
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
