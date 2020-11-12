import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FenetreGraphique extends JFrame implements ActionListener {
    JFrame fenetre;
    private JPanel Central;
    private JTextField BarRecherche;
    private JButton Valider;
    private JButton MesAlbums;
    private JButton MesArtistes;
    private JButton MesPlaylists;
    private JButton Retour;
    private JComboBox comboBox1;
    private JPanel PanelMenu;
    private JPanel Regroupe;
    private JPanel PannelRecherche;
    private JPanel PannelMontrage;
    private Montrage montrage;

    FenetreGraphique(){
        //Initialisation
        fenetre = new JFrame("Bibliothèque");
        fenetre.setSize(1200, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Central = new JPanel();
        Central.setLayout(new BorderLayout(0, 0));

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        Regroupe = new JPanel();
        Regroupe.setLayout(new BorderLayout(0, 0));
        Central.add(Regroupe, BorderLayout.CENTER);

        montrage = new Montrage();


        //La barre de recherche
        PannelRecherche = new JPanel();
        PannelRecherche.setBackground(Color.darkGray);
        PannelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Regroupe.add(PannelRecherche, BorderLayout.NORTH);
        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setActionCommand("Valider");
        Valider.addActionListener(this);
        Valider = TransformButton(Valider);
        final JLabel label1 = new JLabel();
        label1.setText("Recherche");
        label1.setForeground(Color.lightGray);
        PannelRecherche.add(label1);
        BarRecherche = new JTextField(30);
        PannelRecherche.add(BarRecherche);
        PannelRecherche.add(Valider);

        JPanel PannelMontrage = new JPanel();
        PannelMontrage.setLayout(new BorderLayout());
        montrage.ChangementPanel(1);
        PannelMontrage.add(montrage.getPrincipal());

        Regroupe.add(PannelMontrage, BorderLayout.CENTER);


        //Le menu à droite
        PanelMenu = new JPanel();

        PanelMenu.setLayout(new GridBagLayout());
        PanelMenu.setBackground(Color.darkGray);

        Central.add(PanelMenu, BorderLayout.WEST);
        MesAlbums = new JButton();
        MesAlbums.setText("Mes Albums");
        TransformButton(MesAlbums);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(MesAlbums, gbc);

        MesArtistes = new JButton();
        MesArtistes.setText("Mes Artistes");
        TransformButton(MesArtistes);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(MesArtistes, gbc);

        MesPlaylists = new JButton();
        MesPlaylists.setText("Mes Playlists");
        TransformButton(MesPlaylists);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(MesPlaylists, gbc);

        Retour = new JButton();
        Retour.setText("Retour");
        Retour.setActionCommand("Retour");
        Retour.addActionListener(this);
        TransformButton(Retour);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Retour, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label4, gbc);
        Border bord = new EmptyBorder(0,4,220,4);
        PanelMenu.setBorder(new CompoundBorder(bord,bord));

        fenetre.add(Central);
        fenetre.setVisible(true);
    }

    JButton TransformButton(JButton bouton){
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(Color.gray);
        bouton.setBorder(compound);
        return bouton;
    }

        @Override
    public void actionPerformed(ActionEvent evenement){
        if(evenement.getActionCommand().equals("Retour"))
            montrage.RetourArrière();
        if(evenement.getActionCommand().equals("Valider")){
            String recherche = BarRecherche.getText();
            if(!recherche.isEmpty())
                montrage.Recherche(recherche);
        }
    }
}
