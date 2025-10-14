import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmEnvios extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtRemitente, txtDireccion, txtPeso;
    private JComboBox<String> cmbTipoPaquete;
    private JTabbedPane tp;

    public FrmEnvios() {
        setSize(800, 500);
        setTitle("Gestión de Envíos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JToolBar tbEnvios = new JToolBar();
        tbEnvios.setFloatable(false);

        JButton btnAgregar = new JButton();
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/iconos/Agregar.png")));
        btnAgregar.setToolTipText("Agregar Envío");
        tbEnvios.add(btnAgregar);

        JButton btnEliminar = new JButton();
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/iconos/Eliminar.png")));
        btnEliminar.setToolTipText("Eliminar Envío");
        tbEnvios.add(btnEliminar);

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
            "Seleccionar...", "Terrestre", "Fluvial", "Aéreo"
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

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(300, 80, 100, 30);
        pnlEditarEnvio.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(420, 80, 100, 30);
        pnlEditarEnvio.add(btnCancelar);

        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
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
    }
}
