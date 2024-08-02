package appswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Bebida;
import regras_negocio.Fachada;

public class TelaBebida {

    JFrame frame;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtVolume;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaBebida window = new TelaBebida();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaBebida() {
        initialize();
        Fachada.inicializar();
        listarBebidas();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Bebida");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 80, 25);
        frame.getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 10, 200, 25);
        frame.getContentPane().add(txtId);
        txtId.setColumns(10);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 45, 80, 25);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 45, 200, 25);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(10, 80, 80, 25);
        frame.getContentPane().add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(100, 80, 200, 25);
        frame.getContentPane().add(txtPreco);
        txtPreco.setColumns(10);

        JLabel lblVolume = new JLabel("Volume:");
        lblVolume.setBounds(10, 115, 80, 25);
        frame.getContentPane().add(lblVolume);

        txtVolume = new JTextField();
        txtVolume.setBounds(100, 115, 200, 25);
        frame.getContentPane().add(txtVolume);
        txtVolume.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(10, 150, 120, 25);
        frame.getContentPane().add(btnCadastrar);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarBebida();
            }
        });

       
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(270, 150, 120, 25);
        frame.getContentPane().add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirBebida();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 185, 560, 160);
        frame.getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nome", "Preço", "Volume" }
        );
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    private void cadastrarBebida() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            double volume = Double.parseDouble(txtVolume.getText());
            Fachada.cadastrarBebida(id, nome, preco, volume);
            listarBebidas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    private void excluirBebida() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Fachada.excluirBebida(id);
            listarBebidas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarBebidas() {
        try {
            tableModel.setRowCount(0);
            List<Bebida> bebidas = Fachada.listarBebidas();
            for (Bebida b : bebidas) {
                tableModel.addRow(new Object[] { b.getId(), b.getNome(), b.getPreco(), b.getVolume() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
