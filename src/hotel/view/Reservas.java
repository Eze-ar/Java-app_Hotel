package hotel.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import hotel.controller.ReservaController;
import hotel.modelo.Reserva;


@SuppressWarnings("serial")
public class Reservas extends JFrame {

	private JPanel contentPane;
	private JTextField txtValor;
	private JDateChooser txtFechaE;
	private JDateChooser txtFechaS;
	private JComboBox<Format> txtFormaPago;
	private ReservaController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Reservas() {
		this.reservaController = new ReservaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245,245,245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtFechaE = new JDateChooser();
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);
		
		txtFechaS = new JDateChooser();
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor(getTxtFechaE(), getTxtFechaS());
			}
		});
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);
		
		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);
		txtValor.setText("0");
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);
		
		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer valor = Integer.valueOf(txtValor.getText());
				if(valor < 2500) {
					
					JOptionPane.showMessageDialog(panel, "Por favor seleccione una fecha valida");
					return;
				}else {
					guardar();
					RegistroHuesped huesped = new RegistroHuesped();
					huesped.setVisible(true);
					dispose();
				}
				
			}
		});
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(183, 436, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(new Color(65,105,225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
	}
	private void guardar() {
		if(txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
			
			
				String fechaE = ((JTextField)txtFechaE.getDateEditor().getUiComponent()).getText();
				String fechaS = ((JTextField)txtFechaS.getDateEditor().getUiComponent()).getText();
				Reserva reserva = new Reserva(java.sql.Date.valueOf(fechaE).toString(),java.sql.Date.valueOf(fechaS).toString(),txtValor.getText(),txtFormaPago.getSelectedItem().toString());
				this.reservaController.guardar(reserva);
				limpiar();
			
			}else {
				JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
			}
			
			
	}
	
	private void limpiar() {
		this.txtFormaPago.setSelectedIndex(0);
		this.txtValor.setText("");
		this.txtFechaE.setCalendar(null);
		this.txtFechaS.setCalendar(null);
	}
	private void calcularValor(JDateChooser fechaE,JDateChooser fechaS) {
		if (fechaE.getDate() != null && fechaS.getDate() != null) {
			Calendar inicio = fechaE.getCalendar();
			Calendar fin = fechaS.getCalendar();
			int dias = -1;
			int valorDiario = 2500; //valor día de hotel...en Argentina
			int valorFinal;
			
			while(inicio.before(fin)||inicio.equals(fin)) {
				dias++;
				inicio.add(Calendar.DATE, 1);
			}
			valorFinal = dias * valorDiario;
			txtValor.setText("" + valorFinal);
			
		}
	}
	
	public JDateChooser getTxtFechaE() {
		return txtFechaE;
	}
	
	public JDateChooser getTxtFechaS() {
		return txtFechaS;
	}
	
	public JTextField getTxtValor() {
		return txtValor;
	}
}
