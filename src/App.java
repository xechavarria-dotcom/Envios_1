import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmEnvios ventana = new FrmEnvios();
            ventana.setVisible(true);
        });
    }
}
