package com.latam.cmz.hotelalura.gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.latam.cmz.hotelalura.modelo.Reserva;

public class JPtbReservas extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JLabel Label;
	
	private JTable tbHuespedes;
	private DefaultTableModel tbHuespedesModelo;

	/**
	 * Create the panel.
	 */
	public JPtbReservas() {
		ConfigaurarElementos();

	}

	private void ConfigaurarElementos() {
		this.setSize(425, 217);
		setLayout(null);
		
		Label = new JLabel("HOLA");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		Label.setLocation(0, 5);
		Label.setSize(425, 18);
		Label.setForeground(SystemColor.textInactiveText);
		Label.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		this.add(Label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 28, 425, 189);
		add(scrollPane);
		
		
		tbHuespedes = new JTable();					
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 17));
		
		String t[]= {"No","Id","Check In","Check Out"};
		tbHuespedesModelo = new DefaultTableModel(null, t) {
			private static final long serialVersionUID = 1L;
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
		tbHuespedes.setRowHeight(25);
		scrollPane.setViewportView(tbHuespedes);
		
	}
	
	public void CargarValoresTbHuepedes(List<Reserva> Reserva) {
		if(Reserva==null || Reserva.size()<=0) {
			return;
		}
		tbHuespedesModelo.setRowCount(0);
		int x=1;
		for (Reserva R:Reserva) {
			Object[] fila = new Object[4];
			fila [0]=x;
			fila [1]=R.getId().toString();
			fila [2]=R.getFecha_entrada().toString();
			fila [3]=R.getFecha_salida().toString();
			tbHuespedesModelo.addRow(fila);
			x++;
		}
		
	}
	
	public void setLabelText(String label) {
		Label.setText(label);
	}

	public void ValuesClear() {
		tbHuespedesModelo.setRowCount(0);	
	}
	public int getTBSelectedRow() {
		return tbHuespedes.getSelectedRow();
	}
	
	public Object getTBValueAt(int row, int column) {
		return tbHuespedesModelo.getValueAt(row, column);
	}
	
	

}
