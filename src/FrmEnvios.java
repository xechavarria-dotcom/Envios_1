import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logica.*;
import modelos.*;

public class FrmEnvios extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtRemitente, txtDistancia, txtPeso;
    private JComboBox<String> cmbTipoEnvio;
    private JTabbedPane tp;
    private LogisticaEnvios logistica;
    private ControladorEnvios controlador;
    private DefaultTableModel modelo;
    private JButton btnAgregar, btnEliminar, btnGuardar, btnCancelar;

    public FrmEnvios() {
        setSize(900, 500);
        setTitle("Gestión de Envíos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        logistica = new LogisticaEnvios();
        controlador = new ControladorEnvios(logistica);

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

        // Panel principal
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

        cmbTipoEnvio = new JComboBox<>(new String[] { "Seleccionar...", "Terrestre", "Maritimo", "Aéreo" });
        cmbTipoEnvio.setBounds(400, 10, 120, 25);
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 45, 80, 25);
        pnlEditarEnvio.add(lblCliente);

        txtRemitente = new JTextField();
        txtRemitente.setBounds(90, 45, 180, 25);
        pnlEditarEnvio.add(txtRemitente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(300, 45, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(400, 45, 120, 25);
        pnlEditarEnvio.add(txtDistancia);

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

    // ====================== MÉTODOS AUXILIARES ======================

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

    private String[] obtenerDatosFormulario() {
        String[] datos = new String[5];
        datos[0] = txtCodigo.getText().trim();
        datos[1] = txtRemitente.getText().trim();
        datos[2] = txtPeso.getText().trim();
        datos[3] = txtDistancia.getText().trim();
        datos[4] = cmbTipoEnvio.getSelectedItem().toString();
        return datos;
    }

    private void guardarEnvio() {
        String[] datos = obtenerDatosFormulario();
        String codigo = datos[0];
        String cliente = datos[1];
        String pesoStr = datos[2];
        String distanciaStr = datos[3];
        String tipoStr = datos[4];

        TipoEnvio tipo = null;
        try {
            tipo = TipoEnvio.valueOf(tipoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            tipo = null;
        }

        String resultado = controlador.agregarEnvio(codigo, cliente, pesoStr, distanciaStr, tipo);

        if (!resultado.equals("OK")) {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Envio envio = logistica.listarEnvios().get(logistica.listarEnvios().size() - 1);
        agregarFilaTabla(envio, tipo);
        limpiarCampos();
    }

    private void agregarFilaTabla(Envio envio, TipoEnvio tipo) {
        modelo.addRow(new Object[] {
            tipo,
            envio.getCodigo(),
            envio.getCliente(),
            envio.getPeso(),
            envio.getDistancia(),
            envio.calcularTarifa()
        });
    }

    private void eliminarEnvio() {
        int filaSeleccionada = tblEnvios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este envío?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                String codigo = modelo.getValueAt(filaSeleccionada, 1).toString();
                controlador.eliminarEnvio(codigo);
                modelo.removeRow(filaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un envío para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtRemitente.setText("");
        txtDistancia.setText("");
        txtPeso.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }
}


