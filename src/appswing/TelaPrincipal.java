package appswing;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.EventQueue;

import regras_negocio.Fachada;


public class TelaPrincipal {
    private JFrame frame;
    private JMenu mnBebida;
    private JMenu mnRefeicao;
    private JMenu mnPesagem;
    private JMenu mnConsulta;
    private JLabel label;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal window = new TelaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPrincipal() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Restaurante-a-Kilo");
        frame.setBounds(100, 100, 450, 363);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("");
        label.setFont(new Font("Tahoma", Font.PLAIN, 26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("Restaurante a Kilo");
        label.setBounds(0, 0, 467, 302);
        ImageIcon imagem = new ImageIcon(getClass().getResource(""));
        imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(imagem);
        frame.getContentPane().add(label);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnBebida = new JMenu("Bebida");
        mnBebida.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        });
        menuBar.add(mnBebida);

        mnRefeicao = new JMenu("Refeicao");
        mnRefeicao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            TelaRefeicao window = new TelaRefeicao();
                            window.frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        menuBar.add(mnRefeicao);

        mnPesagem = new JMenu("Pesagem");
        mnPesagem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        });
        menuBar.add(mnPesagem);

        mnConsulta = new JMenu("Consulta");
        mnConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        });
        menuBar.add(mnConsulta);
    }
}
