package appswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Refeicao;
import modelo.Consumo;
import regras_negocio.Fachada;


public class TelaRefeicao {

    JFrame frame;
    private JTextField txtId;
    private JTextField txtData;
    private JTextField txtIdConsumo;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaRefeicao window = new TelaRefeicao();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaRefeicao() {
        initialize();
        Fachada.inicializar();
        listarRefeicoes();
    }

    private void initialize() {
        setFrame(new JFrame());
        getFrame().setBounds(100, 100, 600, 400);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 80, 25);
        getFrame().getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 10, 200, 25);
        getFrame().getContentPane().add(txtId);
        txtId.setColumns(10);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(10, 45, 80, 25);
        getFrame().getContentPane().add(lblData);

        txtData = new JTextField();
        txtData.setBounds(100, 45, 200, 25);
        getFrame().getContentPane().add(txtData);
        txtData.setColumns(10);

        JLabel lblIdConsumo = new JLabel("ID Consumo:");
        lblIdConsumo.setBounds(10, 80, 80, 25);
        getFrame().getContentPane().add(lblIdConsumo);

        txtIdConsumo = new JTextField();
        txtIdConsumo.setBounds(100, 80, 200, 25);
        getFrame().getContentPane().add(txtIdConsumo);
        txtIdConsumo.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(10, 115, 120, 25);
        getFrame().getContentPane().add(btnCadastrar);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarRefeicao();
            }
        });


        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(270, 115, 120, 25);
        getFrame().getContentPane().add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirRefeicao();
            }
        });

        JButton btnAdicionarConsumo = new JButton("Adicionar Consumo");
        btnAdicionarConsumo.setBounds(400, 80, 170, 25);
        getFrame().getContentPane().add(btnAdicionarConsumo);
        btnAdicionarConsumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarConsumoNaRefeicao();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 150, 560, 200);
        getFrame().getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Data", "Valor Pago" }
        );
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    private void cadastrarRefeicao() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String data = txtData.getText();
            Fachada.cadastrarRefeicao(id, data);
            listarRefeicoes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    private void excluirRefeicao() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Fachada.excluirRefeicao(id);
            listarRefeicoes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarRefeicoes() {
        try {
            tableModel.setRowCount(0);
            List<Refeicao> refeicoes = Fachada.listarRefeicoes();
            for (Refeicao r : refeicoes) {
                tableModel.addRow(new Object[] { r.getId(), r.getData(), r.getValorPago() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarConsumoNaRefeicao() {
        try {
            int idRefeicao = Integer.parseInt(txtId.getText());
            int idConsumo = Integer.parseInt(txtIdConsumo.getText());
            Fachada.adicionarConsumoNaRefeicao(idRefeicao, idConsumo);
            listarRefeicoes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
