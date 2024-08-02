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
import modelo.Pesagem;
import regras_negocio.Fachada;

public class TelaPesagem {

    JFrame frame;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtPeso;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPesagem window = new TelaPesagem();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaPesagem() {
        initialize();
        Fachada.inicializar();
        listarPesagens();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
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

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(10, 115, 80, 25);
        frame.getContentPane().add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(100, 115, 200, 25);
        frame.getContentPane().add(txtPeso);
        txtPeso.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(10, 150, 120, 25);
        frame.getContentPane().add(btnCadastrar);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarPesagem();
            }
        });


        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(270, 150, 120, 25);
        frame.getContentPane().add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirPesagem();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 190, 560, 150);
        frame.getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nome", "Preço", "Peso" }
        );
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    private void cadastrarPesagem() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            Fachada.cadastrarPesagem(id, nome, preco, peso);
            listarPesagens();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void excluirPesagem() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Fachada.excluirPesagem(id);
            listarPesagens();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarPesagens() {
        try {
            tableModel.setRowCount(0);
            List<Pesagem> pesagens = Fachada.listarPesagens();
            for (Pesagem p : pesagens) {
                tableModel.addRow(new Object[] { p.getId(), p.getNome(), p.getPreco(), p.getPeso() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}