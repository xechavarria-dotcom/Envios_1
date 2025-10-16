import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logica.GestionEnvios;
import logica.LogisticaEnvios;
import logica.ValidadorEnvio;
import modelos.*;

public class FrmEnvios extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtRemitente, txtDistancia, txtPeso;
    private JComboBox<String> cmbTipoEnvio;
    private JTabbedPane tp;
    private LogisticaEnvios logistica;
    private DefaultTableModel modelo;
    private JButton btnAgregar, btnEliminar, btnGuardar, btnCancelar;

    public FrmEnvios() {
        setSize(800, 500);
        setTitle("Gestión de Envíos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        logistica = new LogisticaEnvios();

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

        // Campos y etiquetas
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
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEnvio();
            }
        });

       
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEnvio();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void guardarEnvio() {
    String codigo = txtCodigo.getText().trim();
    String cliente = txtRemitente.getText().trim();

    if (!ValidadorEnvio.tipoValido(cmbTipoEnvio.getSelectedIndex())) {
        JOptionPane.showMessageDialog(this, "Seleccione un tipo de envío válido.", "Error",
                JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (!ValidadorEnvio.camposCompletos(codigo, cliente, txtPeso.getText(), txtDistancia.getText())) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error",
                JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (!ValidadorEnvio.valoresNumericos(txtCodigo.getText(),txtPeso.getText(), txtDistancia.getText())) {
        JOptionPane.showMessageDialog(this, "Número, peso y distancia deben ser numéricos.", "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }
        double peso = Double.parseDouble(txtPeso.getText());
        double distancia = Double.parseDouble(txtDistancia.getText());
        TipoEnvio tipo = TipoEnvio.valueOf(cmbTipoEnvio.getSelectedItem().toString().toUpperCase());
        Envio envio = GestionEnvios.crearEnvio(tipo, codigo, cliente, peso, distancia);
        logistica.agregarEnvio(envio);

        Object[] fila = { tipo, codigo, cliente, peso, distancia, envio.calcularTarifa() };
        modelo.addRow(fila);

        limpiarCampos();
}
    private void eliminarEnvio() {
        int filaSeleccionada = tblEnvios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este envío?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                String codigo = modelo.getValueAt(filaSeleccionada, 1).toString();
                logistica.eliminarEnvio(codigo);
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