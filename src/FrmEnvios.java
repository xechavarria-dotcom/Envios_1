import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import logica.FabricaEnvios;
import logica.Logistica;
import modelos.*;

public class FrmEnvios extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtRemitente, txtDireccion, txtPeso;
    private JComboBox<String> cmbTipoPaquete;
    private JTabbedPane tp;
    private Logistica logistica;
    private DefaultTableModel modelo;
    private JButton btnAgregar, btnEliminar, btnGuardar, btnCancelar;

    public FrmEnvios() {
        setSize(800, 500);
        setTitle("Gestión de Envíos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializa la lógica principal
        logistica = new Logistica();

        // Barra de herramientas
        JToolBar tbEnvios = new JToolBar();
        tbEnvios.setFloatable(false);

        btnAgregar = new JButton();
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/iconos/Agregar.png")));
        btnAgregar.setToolTipText("Agregar Envío");
        tbEnvios.add(btnAgregar);

        btnEliminar = new JButton();
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/iconos/Eliminar.png")));
        btnEliminar.setToolTipText("Eliminar Envío");
        tbEnvios.add(btnEliminar);

        // Panel de datos
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(800, 120));
        pnlEditarEnvio.setLayout(null);

        JLabel lblCodigo = new JLabel("Número");
        lblCodigo.setBounds(10, 10, 80, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(90, 10, 180, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(300, 10, 80, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoPaquete = new JComboBox<>(new String[] {
                "Seleccionar...", "Terrestre", "Maritimo", "Aéreo"
        });
        cmbTipoPaquete.setBounds(400, 10, 120, 25);
        pnlEditarEnvio.add(cmbTipoPaquete);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 45, 80, 25);
        pnlEditarEnvio.add(lblCliente);

        txtRemitente = new JTextField();
        txtRemitente.setBounds(90, 45, 180, 25);
        pnlEditarEnvio.add(txtRemitente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(300, 45, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(400, 45, 120, 25);
        pnlEditarEnvio.add(txtDireccion);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 80, 80, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(90, 80, 180, 25);
        pnlEditarEnvio.add(txtPeso);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(300, 80, 100, 30);
        pnlEditarEnvio.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(420, 80, 100, 30);
        pnlEditarEnvio.add(btnCancelar);

        // Tabla
        String[] columnas = { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" };
        modelo = new DefaultTableModel(columnas, 0);
        tblEnvios = new JTable(modelo);
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        JScrollPane spEnvios = new JScrollPane(pnlEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tp = new JTabbedPane();
        tp.addTab("Envíos", spEnvios);

        getContentPane().add(tbEnvios, BorderLayout.NORTH);
        getContentPane().add(tp, BorderLayout.CENTER);

        agregarEventos();
    }

    private void agregarEventos() {
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarEnvio();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarEnvio();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void guardarEnvio() {
        String tipo = (String) cmbTipoPaquete.getSelectedItem();
        String codigo = txtCodigo.getText();
        String cliente = txtRemitente.getText();

        double peso;
        double distancia;

        try {
            peso = Double.parseDouble(txtPeso.getText());
            distancia = Double.parseDouble(txtDireccion.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Peso y distancia deben ser numéricos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tipo.equals("Seleccionar...")) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de envío.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Envio envio;
        try {
            envio = FabricaEnvios.crearEnvio(tipo, codigo, cliente, peso, distancia);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (envio != null) {
            logistica.agregarEnvio(envio);
            Object[] fila = {
                    tipo,
                    envio.getCodigo(),
                    envio.getCliente(),
                    envio.getPeso(),
                    envio.getDistancia(),
                    envio.calcularTarifa()
            };
            modelo.addRow(fila);
            limpiarCampos();
        }
    }

    private void eliminarEnvio() {
        int filaSeleccionada = tblEnvios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = modelo.getValueAt(filaSeleccionada, 1).toString();
            logistica.eliminarEnvio(codigo);
            modelo.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un envío para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtRemitente.setText("");
        txtDireccion.setText("");
        txtPeso.setText("");
        cmbTipoPaquete.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new FrmEnvios().setVisible(true);
    }
}
