import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author TRONC Clément
 * @author DI PIAZZA Hugo
 * La fenetre Modifier qui permet l'édition des albums présent dans la base.
 * La fenetre est le formulaire permettant l'edition
 */
public class Modifier extends JDialog implements ActionListener {
    /**
     * La fenetre principal du formulaire
     */
    private JFrame fenetre_ajouter;
    /**
     * L'instance de Montrage depuis son apparition
     */
    private Montrage fenetre_principal;
    /**
     * Le bouton Valider.
     */
    private JButton Valider;
    /**
     * L'Album qui va se faire modifie
     */
    private Album album;
    /**
     * L'Album modifié
     */
    Album ajout;
    /**
     * The Txt titre.
     */
    private JTextField txtTitre;
    /**
     * The Txt artiste.
     */
    private JTextField txtArtiste;
    /**
     * The Txt annee.
     */
    private JTextField txtAnnee;
    /**
     * The Txt duree.
     */
    private JTextField txtDuree;
    /**
     * The Txt image.
     */
    private JTextField txtImage;

    /**
     * Instantiates a new Modifier.
     *
     * @param principal the principal
     * @param _album    the album
     */
    public Modifier(Montrage principal, Album _album){
        album = _album;
        fenetre_ajouter = new JFrame("Ajouter un album");
        ajout = null;
        fenetre_principal = principal;

        txtTitre = new JTextField(10);
        txtArtiste = new JTextField(10);
        txtAnnee = new JTextField(10);
        txtDuree = new JTextField(10);
        txtImage = new JTextField(10);
        txtTitre.setText(album.getNom());
        txtArtiste.setText(album.getArtiste());
        txtAnnee.setText(album.getDate());
        txtDuree.setText(album.getDuree());
        txtImage.setText(album.getImage());

        JLabel lblTitre = new JLabel("Titre", JLabel.RIGHT);
        lblTitre.setDisplayedMnemonic('N');
        lblTitre.setLabelFor(txtTitre);

        JLabel lblArtiste = new JLabel("Artiste:", JLabel.RIGHT);
        lblArtiste.setDisplayedMnemonic('s');
        lblArtiste.setDisplayedMnemonicIndex(5);
        lblArtiste.setLabelFor(txtArtiste);

        JLabel lblAnnee = new JLabel("Année", JLabel.RIGHT);
        lblAnnee.setDisplayedMnemonic('V');
        lblAnnee.setLabelFor(txtAnnee);

        JLabel lblDuree = new JLabel("Durée", JLabel.RIGHT);
        lblDuree.setDisplayedMnemonic('P');
        lblDuree.setLabelFor(txtDuree);

        JLabel lblImage = new JLabel("Image (url)", JLabel.RIGHT);
        lblImage.setDisplayedMnemonic('E');
        lblImage.setLabelFor(txtImage);

        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setActionCommand("Valider");
        Valider.addActionListener(this);

        JPanel p = new JPanel( );
        p.setLayout(new GridLayout(7, 4, 7, 8));
        p.add(lblTitre);
        p.add(txtTitre);
        p.add(lblArtiste);
        p.add(txtArtiste);
        p.add(lblAnnee);
        p.add(txtAnnee);
        p.add(lblDuree);
        p.add(txtDuree);
        p.add(lblImage);
        p.add(txtImage);
        p.add(Valider);

        fenetre_ajouter.setContentPane(p);
        fenetre_ajouter.setLocationRelativeTo(null);
        fenetre_ajouter.pack( );
        fenetre_ajouter.setVisible(true);
    }

    /**
     * Réalise l'envoie de l'album modifié vers la classe Montrage lors de la pression du bouton valider
     * @param evenement
     */
    @Override
    public void actionPerformed(ActionEvent evenement){
        if(evenement.getActionCommand().equals("Valider")){
            String titre = txtTitre.getText();
            String artiste = txtArtiste.getText();
            String annee = txtAnnee.getText();
            String duree = txtDuree.getText();
            String image = txtImage.getText();
            if(!titre.isEmpty() || !artiste.isEmpty() || !annee.isEmpty() || !duree.isEmpty()) {
                if(!testImage(image) && !image.isEmpty())
                    JOptionPane.showMessageDialog(null,"URL non valide",
                            "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                else {
                    if (image.isEmpty())
                        image = "https://images-eu.ssl-images-amazon.com/images/I/61SGCYpjz-L._AC_UL600_SR597,600_.jpg";
                    ajout = new Album(titre, artiste, annee, album.getGenre(), album.getNb_pistes(), duree, image);
                    fenetre_principal.Modifier(ajout);
                    fenetre_ajouter.dispose();
                }
            }

        }
    }

    /**
     * Fonction qui test si l'url d'une image est valide ou pas.
     * Retourne un boolean en fonction si l'url est bien une image ou pas
     *
     * @param url the url
     * @return the boolean
     */
    public Boolean testImage(String url){
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            if(image != null){
                return true;
            } else{
                return false;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.err.println("URL error with image");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("IO error with image");
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
