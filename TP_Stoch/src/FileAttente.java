import javax.swing.*;

/**
 * Created by lelib on 17/10/2017.
 */
public class FileAttente {

    public static void main(String[] args) {
        JFrame verif= new JFrame ("Verification");
        verif.setContentPane(new VerifEgibilite().getPanel());
        verif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verif.setSize(900,600);
        // on rend le frame visible
        verif.setVisible(true);
        while(new VerifEgibilite().isOK()==false){
        }
        verif.dispose();
        JFrame saisie= new JFrame("Saisie Informations");
        saisie.setContentPane(new SaisieInfo().getPanel());
        saisie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saisie.setSize(1000,600);
        // on rend le frame visible
        saisie.setVisible(true);
    }
}
