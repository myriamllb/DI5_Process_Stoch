import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by lelib on 17/10/2017.
 */
public class VerifEgibilite {
    private JCheckBox client;
    private JCheckBox PAPS;
    private JCheckBox dureeService;
    private JButton soumettreButton;
    private JPanel panel;
    private static boolean ok=false;
    public VerifEgibilite() {
        soumettreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok=false;
                boolean resClient=client.isSelected();
                boolean resPAPS=PAPS.isSelected();
                boolean resDureeService=dureeService.isSelected();
                if(resClient&&resPAPS&&resDureeService){
                    ok=true;
                }
                else{
                    showMessageDialog(null,"Les trois paramètres doivent être respectés !");
                    client.setSelected(false);
                    PAPS.setSelected(false);
                    dureeService.setSelected(false);
                }
            }
        });
    }
    public boolean isOK(){
        return ok;
    }
    public JPanel getPanel(){
        return panel;
    }
}
