import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FenetreGraphique extends JPanel {
    JFrame fenetre;
    private JPanel Central;
    private JTextField BarRecherche;
    private JButton Valider;
    private JComboBox comboBox1;
    private JPanel PanelMenu;
    private JPanel Regroupe;
    private JPanel PannelRecherche;
    private JPanel PannelMontrage;


    FenetreGraphique(){
        fenetre = new JFrame("Bibliothèque");
        fenetre.setSize(1080, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Central = new JPanel();
        Central.setLayout(new BorderLayout(0, 0));
        Regroupe = new JPanel();
        Regroupe.setLayout(new BorderLayout(0, 0));
        Central.add(Regroupe, BorderLayout.CENTER);

        PannelRecherche = new JPanel();
        PannelRecherche.setBackground(Color.GRAY);
        PannelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Regroupe.add(PannelRecherche, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setText("Recherche");
        PannelRecherche.add(label1);
        BarRecherche = new JTextField();
        BarRecherche.setEditable(true);
        BarRecherche.setText("");
        BarRecherche.putClientProperty("caretWidth", new Integer(300));
        PannelRecherche.add(BarRecherche);

        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setForeground(Color.BLACK);
        Valider.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        Valider.setBorder(compound);
        PannelRecherche.add(Valider);

        PannelMontrage = new JPanel();
        PannelMontrage.setBackground(Color.darkGray);
        PannelMontrage.setLayout(new BorderLayout(0, 0));
        Regroupe.add(PannelMontrage, BorderLayout.CENTER);

        PanelMenu = new JPanel();
        PanelMenu.setBackground(Color.gray);
        PanelMenu.setLayout(new GridBagLayout());
        Central.add(PanelMenu, BorderLayout.WEST);
        comboBox1 = new JComboBox();
        comboBox1.setForeground(Color.BLACK);
        comboBox1.setBackground(Color.WHITE);
        comboBox1.setBorder(compound);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(comboBox1, gbc);

        fenetre.add(Central);
        fenetre.setVisible(true);
    }
}
