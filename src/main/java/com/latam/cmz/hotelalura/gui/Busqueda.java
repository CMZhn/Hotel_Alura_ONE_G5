package com.latam.cmz.hotelalura.gui;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.latam.cmz.hotelalura.dao.HuespedDAO;
import com.latam.cmz.hotelalura.dao.NacionalidadDAO;
import com.latam.cmz.hotelalura.dao.ReservaDAO;
import com.latam.cmz.hotelalura.images.DirectorioImg;
import com.latam.cmz.hotelalura.modelo.Huesped;
import com.latam.cmz.hotelalura.modelo.Nacionalidad;
import com.latam.cmz.hotelalura.modelo.Reserva;
import com.latam.cmz.hotelalura.modelo.Usuario;
import com.latam.cmz.hotelalura.mytools.MyValidation;
import com.latam.cmz.hotelalura.tests.EntydadesTest;
import com.latam.cmz.hotelalura.utils.JPAUtils;
import com.latam.cmz.hotelalura.vo.RelatorioHuesped;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar0,txtBuscar1;
	private JTable tbHuespedesVO;
	private JTable tbReservas;
	private DefaultTableModel tbReservasmodelo;
	private DefaultTableModel tbHuespedesVOmodelo;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private Usuario Usuario;
	private JFrame EstaVentana = this;
	private EntityManager em;
	private List<RelatorioHuesped> HuespedesVO,
								   HuespedesVOtbList=new ArrayList<>();
	private List<Reserva> Reservas,
						  ReservastbList=new ArrayList<>();
	private List<Nacionalidad> Nacionalidades;
	
	private MyBoton btnbuscar,
					btnEditar,
					btnEliminar;
	
	private JTabbedPane panel;
	
	private boolean FiltroAplicado0,
					FiltroAplicado1;
	private JPanel PanelBusqueda;
	private JPHuespedUpdate PanelActualizarH;
	private JPReservaUpdate PanelActualizarR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda () {
		this(EntydadesTest.u());	
	}
	
	public Busqueda(Usuario usuario) {
		this.Usuario=usuario;
		setIconImage(DirectorioImg.img("lupa2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(910, 571);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		
		Configurar1000Header();

		FiltroAplicado0 = false;
		FiltroAplicado1 = false;
		
		em = JPAUtils.getEntityManager();
			HuespedDAO  HuespedDAO=new HuespedDAO(em);
			ReservaDAO ReservaDAO=new ReservaDAO(em);
			NacionalidadDAO  NacionalidadDAO=new NacionalidadDAO(em);
			
			HuespedesVO = HuespedDAO.RelatorioHuespedesVO();
			Reservas = ReservaDAO.ConsultarTodosCompleto();
			Nacionalidades = NacionalidadDAO.ConsultarTodos();
			
		em.close();
		
		
		
		PanelBusqueda = new JPanel();
		PanelBusqueda.setBounds(10, 40, 890, 521);
		PanelBusqueda.setLayout(null);
		PanelBusqueda.setBackground(Color.WHITE);
		PanelBusqueda.setVisible(true);
		contentPane.add(PanelBusqueda);
		
		PanelActualizarH=new JPHuespedUpdate(Nacionalidades);
		PanelActualizarH.setLocation(10,40);;
		PanelActualizarH.setVisible(false);
		contentPane.add(PanelActualizarH);
		
		PanelActualizarR=new JPReservaUpdate();
		PanelActualizarR.setLocation(10,40);;
		PanelActualizarR.setVisible(false);
		contentPane.add(PanelActualizarR);
		
		Configurar2000PanelBusqueda();
		Configurar3000PanelActualizarH();
		Configurar34000PanelActualizarR();
	}

	private void Configurar34000PanelActualizarR() {
		PanelActualizarR.addbtnCancelarMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelActualizarR.LimpiarPanel();
				PanelActualizarR.setVisible(false);
				PanelBusqueda.setVisible(true);
			}
				
			});
		PanelActualizarR.addbtnActualizarMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boolean Validacion =PanelActualizarR.ValidadarDatos(true);
				if (!Validacion) {return;}
				
				Reserva reserva=PanelActualizarR.getReserva();
				if (reserva==null) {return;}
				
				
				int n1 = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "La Reserva esta lista para ser actualizada \n"
			            +"¿Desea actualizar los datos?",
			            "ACTUALIZAR RESERVA",
			            JOptionPane.YES_NO_OPTION);
				if (n1!=0) {return;}
				
				try {
					try {
						if (!em.isOpen()) {em = JPAUtils.getEntityManager();}
					} catch (NullPointerException e2) {
						em = JPAUtils.getEntityManager();
					}
					
					
					ReservaDAO ReservaDAO=new ReservaDAO(em);
					em.getTransaction().begin();
						ReservaDAO.actualizar(reserva);
					em.getTransaction().commit();
						Reservas = ReservaDAO.ConsultarTodosCompleto();
					em.close();
					
					int rs0 =tbReservas.getSelectedRow();
					if(!txtBuscar0.getText().isEmpty()&&FiltroAplicado0) {
						CargarValorestbReservas(txtBuscar0.getText());
					} else {CargarValorestbReservas();}
					
					if(rs0>=0&&tbReservas.getRowCount()>0&&rs0<tbReservas.getRowCount()) {
						tbReservas.setRowSelectionInterval(rs0, rs0);
					}
					
					int n2 = JOptionPane.showConfirmDialog(
							EstaVentana,
				            "Reserva ha sido actualizada exitosamente \n"
				            +"¿Desea cerrar la ventana de edicion?",
				            "ACTUALIZACION EXITOSA",
				            JOptionPane.YES_NO_OPTION);
					if (n2==0) {
						PanelActualizarR.LimpiarPanel();
						PanelActualizarR.setVisible(false);
						PanelBusqueda.setVisible(true);
						return;}
					PanelActualizarR.CargarReserva(reserva);
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Que pena! Error al actualizar. \n"+
							"Los datos no pudieron ser actualizados"
							,"ERROR",JOptionPane.ERROR_MESSAGE);					
				}
				
			}
				
			});	
		
	}

	private void Configurar3000PanelActualizarH() {
		
		PanelActualizarH.addbtnCancelarMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelActualizarH.LimpiarPanel();
				PanelActualizarH.setVisible(false);
				PanelBusqueda.setVisible(true);
			}
				
			});
		PanelActualizarH.addbtnActualizarMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boolean Validacion =PanelActualizarH.ValidadarDatos(true);
				if (!Validacion) {return;}
				
				Huesped huesped=PanelActualizarH.getHuesped();
				if (huesped==null) {return;}
				
				
				int n1 = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "Huesped listo para ser actualizado \n"
			            +"¿Desea actualizar los datos?",
			            "ACTUALIZAR HUESPED",
			            JOptionPane.YES_NO_OPTION);
				if (n1!=0) {return;}
				try {
					if (!em.isOpen()) {em = JPAUtils.getEntityManager();}
					
					HuespedDAO HuespedDAO=new HuespedDAO(em);
					em.getTransaction().begin();
						HuespedDAO.actualizar(huesped);
					em.getTransaction().commit();
						HuespedesVO = HuespedDAO.RelatorioHuespedesVO();
					em.close();
					
					int rs1 =tbHuespedesVO.getSelectedRow();
					if(!txtBuscar1.getText().isEmpty()&&FiltroAplicado1) {
						CargarValorestbHuespedes(txtBuscar1.getText());
					} else {CargarValorestbHuespedes();}
					
					if(rs1>=0&&tbHuespedesVO.getRowCount()>0&&rs1<tbHuespedesVO.getRowCount()) {
						tbHuespedesVO.setRowSelectionInterval(rs1, rs1);
					}
					
					int n2 = JOptionPane.showConfirmDialog(
							EstaVentana,
				            "Huesped ha sido actualizado exitosamente \n"
				            +"¿Desea cerrar la ventana de edicion?",
				            "ACTUALIZACION EXITOSA",
				            JOptionPane.YES_NO_OPTION);
					if (n2==0) {
						PanelActualizarH.LimpiarPanel();
						PanelActualizarH.setVisible(false);
						PanelBusqueda.setVisible(true);
						return;}
					PanelActualizarH.CargarHuesped(huesped);
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(EstaVentana, 
							"¡Que pena! Error al actualizar. \n"+
							"Los datos no pudieron ser actualizados"
							,"ERROR",JOptionPane.ERROR_MESSAGE);					
				}
				
			}
				
			});	
	}

	private void Configurar2000PanelBusqueda() {
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(321, 22, 280, 42);
		PanelBusqueda.add(lblNewLabel_4);
		
		JLabel Logo_panelbusqueda = new JLabel("");
		Logo_panelbusqueda.setIcon(DirectorioImg.ico("Ha-100px.png"));
		Logo_panelbusqueda.setBounds(36, 11, 104, 107);
		PanelBusqueda.add(Logo_panelbusqueda);
		
		Configurar2010Filtro();
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
					ActualizarFiltroBuscar();
				}
				
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ActualizarFiltroBuscar();
			}
		});
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(10, 129, 865, 328);
		PanelBusqueda.add(panel);
		
		Configurar2020TbReservas();
		Configurar2030TbHuespedes();
		Configurar2040btnEditar();
		Configurar2040btnEliminar();

	}

	private void Configurar2040btnEliminar() {
		btnEliminar = new MyBoton(757, 468, 122);
		btnEliminar.setbtnText("ELIMINAR");
		PanelBusqueda.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rs0 =tbReservas.getSelectedRow();
				int rs1 =tbHuespedesVO.getSelectedRow();
				if(panel.getSelectedIndex()==0) {//Pestaña de reservas activa
					if (rs0<0) {
						JOptionPane.showMessageDialog(EstaVentana, 
							"Debe seleccionar una reserva de la lista de reservas");
						return;
					}
					if(rs0>=ReservastbList.size()) {
						JOptionPane.showMessageDialog(EstaVentana, 
								"Reserva seleccionada no se encuentra en la base de datos");
						return;
					}
					
					try {
						Long idrs=ReservastbList.get(rs0).getId();
						int n1 = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "Usted ha seleccionado la reserva numero:\n"
					            +idrs+"\n"
					            +"¿Desea eliminar la reserva seleccionada?",
					            "ELIMINAR RESERVA",
					            JOptionPane.YES_NO_OPTION);
						if (n1!=0) {return;}
						try {
							if (!em.isOpen()) {em = JPAUtils.getEntityManager();}
						} catch (NullPointerException e3) {
							em = JPAUtils.getEntityManager();
						}
						ReservaDAO ReservaDAO= new ReservaDAO(em);
						Reserva res =ReservaDAO.ConsultaPorId(idrs);
						em.getTransaction().begin();
							ReservaDAO.remover(res);
						em.getTransaction().commit();
							Reservas = ReservaDAO.ConsultarTodosCompleto();
						em.close();
						
						if(!txtBuscar0.getText().isEmpty()&&FiltroAplicado0) {
							CargarValorestbReservas(txtBuscar0.getText());
						} else {CargarValorestbReservas();}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
					
				}else if(panel.getSelectedIndex()==1) {//Pestaña de Huespedes activa
					if (rs1<0) {
						JOptionPane.showMessageDialog(EstaVentana, 
							"Debe seleccionar un huésped de la lista de huéspedes");
						return;
					}
					if(rs1>=HuespedesVOtbList.size()) {
						JOptionPane.showMessageDialog(EstaVentana, 
								"Hueped seleccionado no se encuentra en la base de datos");
						return;
					}

					try {
						String nombrehs=HuespedesVOtbList.get(rs1).getNombre();
						Long idhs=HuespedesVOtbList.get(rs1).getH_Id();
						int n1 = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "Usted ha seleccionado al huesped:\n"
					            +nombrehs+"\n"
					            +"Con numero de huesped: \n"
					            +idhs+"\n"
					            +"¿Desea eliminar el huesped seleccionado?",
					            "ELIMINAR HUESPED",
					            JOptionPane.YES_NO_OPTION);
						if (n1!=0) {return;}
						if (!em.isOpen()) {	em = JPAUtils.getEntityManager();}
						HuespedDAO  HuespedDAO=new HuespedDAO(em);
						Huesped H=HuespedDAO.ConsultaPorIdCompleto(idhs);
						H.setActivo(false);
						em.getTransaction().begin();
							HuespedDAO.actualizar(H);
						em.getTransaction().commit();
							HuespedesVO = HuespedDAO.RelatorioHuespedesVO();
						em.close();
						
						if(!txtBuscar1.getText().isEmpty()&&FiltroAplicado1) {
							CargarValorestbHuespedes(txtBuscar1.getText());
						} else {CargarValorestbHuespedes();}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			}
		});
		
	}

	private void Configurar2040btnEditar() {
		btnEditar = new MyBoton(625, 468, 122);
		btnEditar.setbtnText("EDITAR");
		PanelBusqueda.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rs0 =tbReservas.getSelectedRow();
				int rs1 =tbHuespedesVO.getSelectedRow();				
				
				if(panel.getSelectedIndex()==0) {//Pestaña de reservas activa
					if (rs0<0) {
						JOptionPane.showMessageDialog(EstaVentana, 
							"Debe seleccionar una reserva de la lista de reservas");
						return;
					}
					if(rs0>=ReservastbList.size()) {
						JOptionPane.showMessageDialog(EstaVentana, 
								"Reserva seleccionada no se encuentra en la base de datos");
						return;
					}
					try {
						
						Long idrs=ReservastbList.get(rs0).getId();
						int n1 = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "Usted ha seleccionado la reserva:\n"
					            +"Con numero: \n"
					            +idrs+"\n"
					            +"¿Desea editar la reserva seleccionada?",
					            "EDITAR",
					            JOptionPane.YES_NO_OPTION);
						if (n1!=0) {return;}
						
						PanelBusqueda.setVisible(false);
						
						if (!em.isOpen()) {	em = JPAUtils.getEntityManager();}
						ReservaDAO ReservaDAO = new ReservaDAO(em);
						Reserva R=ReservaDAO.ConsultaPorIdCompleto(idrs);
						em.close();
						PanelActualizarR.CargarReserva(R);

						PanelActualizarR.setVisible(true);
						
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(EstaVentana, 
								"Error al cargar menu de Edicion \n"
								+ "No se puede editar por un error",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} else if(panel.getSelectedIndex()==1) {//Pestaña de Huespedes activa
					
					if (rs1<0) {
						JOptionPane.showMessageDialog(EstaVentana, 
							"Debe seleccionar un huésped de la lista de huéspedes");
						return;
					}
					if(rs1>=HuespedesVOtbList.size()) {
						JOptionPane.showMessageDialog(EstaVentana, 
								"Hueped seleccionado no se encuentra en la base de datos");
						return;
					}

					try {
						String nombrehs=HuespedesVOtbList.get(rs1).getNombre();
						Long idhs=HuespedesVOtbList.get(rs1).getH_Id();
						int n1 = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "Usted ha seleccionado al huesped:\n"
					            +nombrehs+"\n"
					            +"Con numero de huesped: \n"
					            +idhs+"\n"
					            +"¿Desea editar el huesped seleccionado?",
					            "EDITAR",
					            JOptionPane.YES_NO_OPTION);
						if (n1!=0) {return;}
						PanelBusqueda.setVisible(false);
						
						if (!em.isOpen()) {	em = JPAUtils.getEntityManager();}
						HuespedDAO  HuespedDAO=new HuespedDAO(em);
						Huesped H=HuespedDAO.ConsultaPorIdCompleto(idhs);
						em.close();
						
						PanelActualizarH.CargarHuesped(H);
						PanelActualizarH.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(EstaVentana, 
								"Error al cargar menu de Edicion \n"
								+ "No se puede editar por un error",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				}
				
			}
		});
		
		
	}

	private void Configurar1000Header() {
		JPanel header = new JPanel();
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
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "Modificaciones sin Guradar."+"\n"
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
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 140, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("Regresar");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 140, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(
						EstaVentana,
			            "Cambios sin Guradar."+"\n"
			            +"Al cerrar la sesión perderá los cambios realizados"+"\n"
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
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
	}

	private void Configurar2030TbHuespedes() {
		tbHuespedesVO = new JTable();
		tbHuespedesVO.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedesVO.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		String[] tbHuespedesEncabezados={
				"N",
				"ID",
				"Nombre",
				"Apellido",
				"Fecha de Nacimiento",
				"Nacionalidad",
				"Telefono"	};
		tbHuespedesVOmodelo = new DefaultTableModel(null,tbHuespedesEncabezados) {
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		tbHuespedesVO.setModel(tbHuespedesVOmodelo);
		
		Integer [] tbHuespedesColumnWidth= {65,80,170,170,125,130,125};
		boolean [] tbHuespedesColumnResizable= {false,false,true,true,false,false,true};
		
		for (int x=0; x<tbHuespedesVOmodelo.getColumnCount(); x++) {
			tbHuespedesVO.getColumnModel().getColumn(x).setResizable(tbHuespedesColumnResizable[x]);
			tbHuespedesVO.getColumnModel().getColumn(x).setPreferredWidth(tbHuespedesColumnWidth[x]);
		}
		tbHuespedesVO.setRowHeight(22);
		CargarValorestbHuespedes();
	
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedesVO);
		panel.addTab("Huéspedes", DirectorioImg.ico("pessoas.png"), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);	
	}

	private void Configurar2020TbReservas() {
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		String[] tbReservaEncabezados={"N",
										"No. Reserva",
										"Fecha Check In",
										"Fecha Check Out",
										"Valor USD",
										"Forma de Pago",
										"No habitacion"	};
		tbReservasmodelo = new DefaultTableModel(null,tbReservaEncabezados) {
							
							boolean[] columnEditables = new boolean[] {
									false, false, false, false, false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
						
		tbReservas.setModel(tbReservasmodelo);
		Integer [] tbReservasColumnWidth= {65,100,125,125,170,180,100};
		
		for (int x=0; x<tbReservasmodelo.getColumnCount(); x++) {
			tbReservas.getColumnModel().getColumn(x).setResizable(false);
			tbReservas.getColumnModel().getColumn(x).setPreferredWidth(tbReservasColumnWidth[x]);
		}
		tbReservas.setRowHeight(22);
		CargarValorestbReservas();
		
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", DirectorioImg.ico("reservado.png"), scroll_table, null);
		scroll_table.setVisible(true);
		
	}

	private void Configurar2010Filtro() {
		txtBuscar0 = new JTextField();
		txtBuscar0.setBackground(new Color(255, 255, 255));
		txtBuscar0.setBounds(251, 87, 292, 31);
		txtBuscar0.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar0.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar0.setColumns(10);
		txtBuscar0.setVisible(true);
		PanelBusqueda.add(txtBuscar0);
		txtBuscar0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char kc=e.getKeyChar();
				int ki=kc;
				ActualizarTextoBtnBuscar();
				
				if(txtBuscar0.getText().length()<=0) {
					if(ki<=48||ki>57) {
						e.consume();
					}
				} else if(!MyValidation.EsNumeroEntero(String.valueOf(kc))) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtBuscar0.getText().isEmpty()&&FiltroAplicado0) {
					CargarValorestbReservas();
				}
			}
		});
		
		
		txtBuscar1 = new JTextField();
		txtBuscar1.setBackground(txtBuscar0.getBackground());
		txtBuscar1.setBounds(txtBuscar0.getBounds());
		txtBuscar1.setBorder(txtBuscar0.getBorder());
		txtBuscar1.setFont(txtBuscar0.getFont());
		txtBuscar1.setColumns(txtBuscar0.getColumns());
		txtBuscar1.setVisible(false);
		PanelBusqueda.add(txtBuscar1);
		txtBuscar1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ActualizarTextoBtnBuscar();
				MyValidation.KeyTypedNameValidation(e, txtBuscar1);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				MyValidation.KeyReleaseNameValidation(txtBuscar1);
				if(txtBuscar1.getText().isEmpty()&&FiltroAplicado1) {
					CargarValorestbHuespedes();
				}
			}
		});
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(251, 124, 292, 2);
		PanelBusqueda.add(separator_1_2);
		
		Configurar2011Boton();
		
	}

	private void Configurar2011Boton() {
		btnbuscar= new MyBoton(748,125,122);
		btnbuscar.setBounds(548, 85, 312, 40);
		btnbuscar.setbtnText("BUSCAR NÚMERO DE RESERVA");
		PanelBusqueda.add(btnbuscar);
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(panel.getSelectedIndex()==0) {
					if (txtBuscar0==null) {return;}
					if (txtBuscar0.getText()==null) {return;}
					if (txtBuscar0.getText().isBlank()) {return;}
					if (txtBuscar0.getText().isEmpty()) {return;}
										
					int rcvr= CargarValorestbReservas(txtBuscar0.getText());
					if (rcvr==-1) {
						
						JOptionPane.showMessageDialog(EstaVentana, 
								"¡Error: El filtró no se pudo aplicar! \n"+
								"Favor informar de este error a su equipo de TI");
						txtBuscar0.setText("");
						txtBuscar0.grabFocus();
						return;	
					} else if (rcvr==0) {
						int n = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "¡No se encontró ninguna coincidencia¡ \n"+
								"¿Desea borrar el filtro?",
					            "ADVERTENCIA",
					            JOptionPane.YES_NO_OPTION);
						if (n==0) {
							CargarValorestbReservas();
							txtBuscar0.setText("");
							txtBuscar0.grabFocus();
							return;
						}
					}
					FiltroAplicado0= true;
					
				}else if(panel.getSelectedIndex()==1) {
					if (txtBuscar1==null) {return;}
					if (txtBuscar1.getText()==null) {return;}
					if (txtBuscar1.getText().isBlank()) {return;}
					if (txtBuscar1.getText().isEmpty()) {return;}
					if (!MyValidation.Name(txtBuscar1.getText())){return;}
					
					boolean V=	MyValidation.DeletefinalSpace(txtBuscar1);
					if (!V) {
						JOptionPane.showMessageDialog(EstaVentana, 
								"¡Error: Eliminar espacio! \n"+
								"Favor informar de este error a su equipo de TI");
						return;
					}
					
					txtBuscar1.setForeground(Color.BLACK);
					int rcvh= CargarValorestbHuespedes(txtBuscar1.getText());
					if (rcvh==-1) {
						
						JOptionPane.showMessageDialog(EstaVentana, 
								"¡Error: El filtró no se pudo aplicar! \n"+
								"Favor informar de este error a su equipo de TI");
						txtBuscar1.setText("");
						txtBuscar1.grabFocus();
						return;
						
					} else if (rcvh==0) {
						int n = JOptionPane.showConfirmDialog(
								EstaVentana,
					            "¡No se encontró ninguna coincidencia¡ \n"+
								"¿Desea borrar el filtro?",
					            "ADVERTENCIA",
					            JOptionPane.YES_NO_OPTION);
						if (n==0) {
							CargarValorestbHuespedes();
							txtBuscar1.setText("");
							txtBuscar1.grabFocus();
							return;
						}

					}
					FiltroAplicado1= true;
					
				}
			}
		});
		
	}

	private int CargarValorestbHuespedes() {
		
		try {
			if (!ValidacionSinFiltro(1)){return -1;}
			tbHuespedesVOmodelo.setRowCount(0);
			HuespedesVOtbList.clear();
			int x =1;
			for (RelatorioHuesped rh : HuespedesVO) {
				Object[] fila = new Object[tbHuespedesVOmodelo.getColumnCount()];
				fila[0]=x;
				fila[1]=rh.getH_Id();
				fila[2]=rh.getNombre();
				fila[3]=rh.getApellido();
				fila[4]=rh.getFecha_de_nacimiento();
				fila[5]=rh.getGentilicio();
				fila[6]=rh.getTelefono();
				tbHuespedesVOmodelo.addRow(fila);
				HuespedesVOtbList.add(rh);
				x++;
			}
			FiltroAplicado1= false;
			return HuespedesVOtbList.size();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	private int CargarValorestbReservas() {
		try {
			if (!ValidacionSinFiltro(0)){return -1;}
			tbReservasmodelo.setRowCount(0);
			ReservastbList.clear();
			int x =1;
			for (Reserva r : Reservas) {
				Object[] fila = new Object[tbReservasmodelo.getColumnCount()];
				fila[0]=x;
				fila[1]=r.getId();
				fila[2]=r.getFecha_entrada();
				fila[3]=r.getFecha_salida();
				fila[4]=r.getValor_Total();
				fila[5]=r.getForma_de_pago();
				fila[6]=r.getHabitacion().getId();
				tbReservasmodelo.addRow(fila);
				ReservastbList.add(r);
				x++;
			}
			FiltroAplicado0= false;
			return ReservastbList.size();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			
		}
		
	}
	
	private int CargarValorestbHuespedes(String Filtro) {
		try {
			if (!ValidacionFiltro(1,Filtro)) {return -1;}
			Filtro=Filtro.toLowerCase();
			tbHuespedesVOmodelo.setRowCount(0);
			HuespedesVOtbList.clear();
			int x =1;
			for (RelatorioHuesped rh : HuespedesVO) {
				String rhA=rh.getApellido().toLowerCase();
				
				if (rhA.contains(Filtro)) {
					Object[] fila = new Object[tbHuespedesVOmodelo.getColumnCount()];
					fila[0]=x;
					fila[1]=rh.getH_Id();
					fila[2]=rh.getNombre();
					fila[3]=rh.getApellido();
					fila[4]=rh.getFecha_de_nacimiento();
					fila[5]=rh.getGentilicio();
					fila[6]=rh.getTelefono();
					tbHuespedesVOmodelo.addRow(fila);
					HuespedesVOtbList.add(rh);
					x++;
				}
			}
			return HuespedesVOtbList.size();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			
		}
	}
	
	private int CargarValorestbReservas(String Filtro) {
		try {
			if (!ValidacionFiltro(0,Filtro)) {return -1;}
			Long FiltroLong=Long.valueOf(Filtro);
			tbReservasmodelo.setRowCount(0);
			ReservastbList.clear();
			int x =1;
			for (Reserva r : Reservas) {
				Long rL=r.getId();
				
				if (FiltroLong==rL) {
					Object[] fila = new Object[tbReservasmodelo.getColumnCount()];
					fila[0]=x;
					fila[1]=r.getId();
					fila[2]=r.getFecha_entrada();
					fila[3]=r.getFecha_salida();
					fila[4]=r.getValor_Total();
					fila[5]=r.getForma_de_pago();
					fila[6]=r.getHabitacion().getId();
					tbReservasmodelo.addRow(fila);
					ReservastbList.add(r);
					x++;
				}
			}
			return ReservastbList.size();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	private boolean ValidacionFiltro(int Tipo_Filtro, String Filtro) {
		try {
			switch(Tipo_Filtro) {
			  case 0:
				  	if (!ValidacionSinFiltro(0)){return false;}
					if (Filtro.isBlank()||Filtro.isEmpty()) {return false;}
					return true;
			  case 1:
				  	if (!ValidacionSinFiltro(1)){return false;}
					if (Filtro==null) {return false;}
					if (Filtro.isBlank()||Filtro.isEmpty()) {return false;}
					return true;
			  default:
				  	return false;	  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private boolean ValidacionSinFiltro(int Tipo_Filtro) {
		try {
			switch(Tipo_Filtro) {
			  case 0:
				  	if (Reservas==null){return false;}
					if (tbReservasmodelo==null){return false;}
					if (ReservastbList==null){return false;}
					return true;
			  case 1:
				  	if (HuespedesVO==null) {return false;}
					if (tbHuespedesVOmodelo==null){return false;}
					if (HuespedesVOtbList==null) {return false;}
					return true;
			  default:
				  	return false;	  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	private void CerrarVentana() {
		try {
			em.close();
		} catch (Exception e) {
		}
		
		dispose();
	}
	private void ActualizarTextoBtnBuscar() {
		if (panel.getSelectedIndex()==0) {
			btnbuscar.setbtnText("BUSCAR NÚMERO DE RESERVA");}
		
		if (panel.getSelectedIndex()==1) {
			btnbuscar.setbtnText("BUSCAR APELLIDO");}
	}
	private void ActualizarMostrartxtBuscar() {
		if (panel.getSelectedIndex()==0) {
			txtBuscar1.setVisible(false);
			txtBuscar0.setVisible(true);}
		
		if (panel.getSelectedIndex()==1) {
			txtBuscar0.setVisible(false);
			txtBuscar1.setVisible(true);}
	}
	private void ActualizarFiltroBuscar() {
		ActualizarTextoBtnBuscar();
		ActualizarMostrartxtBuscar();
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
