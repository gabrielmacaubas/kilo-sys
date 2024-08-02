package appswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Refeicao;
import regras_negocio.Fachada;

public class TelaConsulta {

    JFrame frame;
    private JTextField txtData;
    private JTextField txtPeso;
    private JTextField txtBebidas;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaConsulta window = new TelaConsulta();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaConsulta() {
        initialize();
        Fachada.inicializar();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(10, 10, 80, 25);
        frame.getContentPane().add(lblData);

        txtData = new JTextField();
        txtData.setBounds(100, 10, 150, 25);
        frame.getContentPane().add(txtData);
        txtData.setColumns(10);

        JButton btnConsultarPorData = new JButton("Consultar por Data");
        btnConsultarPorData.setBounds(260, 10, 180, 25);
        frame.getContentPane().add(btnConsultarPorData);
        btnConsultarPorData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPorData();
            }
        });

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(10, 50, 80, 25);
        frame.getContentPane().add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(100, 50, 150, 25);
        frame.getContentPane().add(txtPeso);
        txtPeso.setColumns(10);

        JButton btnConsultarPorPeso = new JButton("Peso Maior que");
        btnConsultarPorPeso.setBounds(260, 50, 180, 25);
        frame.getContentPane().add(btnConsultarPorPeso);
        btnConsultarPorPeso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPorPeso();
            }
        });

        JLabel lblBebidas = new JLabel("Qtd Bebidas:");
        lblBebidas.setBounds(10, 90, 80, 25);
        frame.getContentPane().add(lblBebidas);

        txtBebidas = new JTextField();
        txtBebidas.setBounds(100, 90, 150, 25);
        frame.getContentPane().add(txtBebidas);
        txtBebidas.setColumns(10);

        JButton btnConsultarPorBebidas = new JButton("Bebidas Maior que");
        btnConsultarPorBebidas.setBounds(260, 90, 180, 25);
        frame.getContentPane().add(btnConsultarPorBebidas);
        btnConsultarPorBebidas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPorBebidas();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 130, 560, 220);
        frame.getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID","Data","Valor Pago" }
        );
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    private void consultarPorData() {
        try {
            String data = txtData.getText();
            List<Refeicao> refeicoes = Fachada.listarRefeicoesPorData(data);
            atualizarTabela(refeicoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consultarPorPeso() {
        try {
            double peso = Double.parseDouble(txtPeso.getText());
            List<Refeicao> refeicoes = Fachada.listarRefeicoesComPesagemMaiorQue(peso);
            atualizarTabela(refeicoes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void consultarPorBebidas() {
        try {
            int qtdBebidas = Integer.parseInt(txtBebidas.getText());
            List<Refeicao> refeicoes = Fachada.listarRefeicoesComMaisDeNBebidas(qtdBebidas);
            atualizarTabela(refeicoes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void atualizarTabela(List<Refeicao> refeicoes) {
        tableModel.setRowCount(0); // Limpar a tabela
        for (Refeicao r : refeicoes) {
            tableModel.addRow(new Object[] { r.getId(), r.getData(), r.getValorPago()});
        }
    }
}
