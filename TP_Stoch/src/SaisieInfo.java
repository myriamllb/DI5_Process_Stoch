import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by lelib on 17/10/2017.
 */

/**
 * A partir du jeudi 16
 */
public class SaisieInfo {
    private JPanel panel;
    private JCheckBox clientIndetermine;
    private JSpinner clientSysteme;
    private JSpinner serveurSysteme;
    private JSpinner servicesSysteme;
    private JCheckBox TempsIndetermine;
    private JSpinner tempsMax;
    private JSpinner nbClientPresent;
    private JCheckBox clientPresentIndetermine;
    private JSpinner nbClient;
    private JCheckBox arriveeClientIndetermine;
    private JButton Calcul;
    private JCheckBox serviceSystemeIndetermine;

    public SaisieInfo() {

        Calcul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double nbClientMax=0;
                double serviceParUniteTemps=0;
                double clientParUniteTemps=0;
                double duree=0;
                int clientPresent=0;
                int nbServeurSysteme=0;

                if(clientIndetermine.isSelected()==false){
                    nbClientMax= Double.parseDouble(clientSysteme.getValue().toString());
                }
                if(serviceSystemeIndetermine.isSelected()==false){
                    serviceParUniteTemps=Double.parseDouble(servicesSysteme.getValue().toString());
                }
                if(arriveeClientIndetermine.isSelected()==false){
                    clientParUniteTemps=Double.parseDouble(nbClient.getValue().toString());
                }
                if(TempsIndetermine.isSelected()==false){
                    duree=Double.parseDouble(tempsMax.getValue().toString());
                }
                if(clientPresentIndetermine.isSelected()==false){
                    clientPresent=Integer.parseInt(nbClientPresent.getValue().toString());
                }
                nbServeurSysteme= Integer.parseInt(serveurSysteme.getValue().toString());
                //MM1
                if(nbServeurSysteme==1&& nbClientMax==0){
                    double L=0;
                    double Lq=0;
                    double W=0;
                    double Wq=0;
                    double Pt=0;
                    double Px=0;
                    MM1 mm1= new MM1(clientParUniteTemps,serviceParUniteTemps);
                    double Ro=mm1.calculRo();
                    if(Ro<1){
                        L=mm1.nombreMoyenClientSysteme();
                        Lq=mm1.nombreMoyenClientFile();
                        W= mm1.dureeMoyenneAttenteSysteme();
                        Wq=mm1.dureeMoyenneAttenteFile();
                        Pt=mm1.calculProbabiliteAvecTemps(duree);
                        Px=mm1.calculProbabilite(clientPresent);
                    }
                    else{
                        showMessageDialog(null,"Rho est supérieur ou égale à 1");
                    }

                    JFrame affichage= new JFrame("Affichage des résultats");
                    affichage.setContentPane(new AffichageResultat(Ro,L,Lq,W,Wq,Pt,duree,Px,clientPresent).getPanel());
                    affichage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    affichage.setSize(750,600);
                    // on rend le frame visible
                    affichage.setVisible(true);

                }
                //MM1K
                else if(nbServeurSysteme==1&& nbClientMax!=0){
                    double L=0;
                    double Lq=0;
                    double W=0;
                    double Wq=0;
                    double Pt=0;
                    double Px=0;
                    MM1K mm1k= new MM1K(clientParUniteTemps,serviceParUniteTemps,nbClientMax);
                    double Ro=mm1k.calculRo();
                    if(Ro<1){
                        L=mm1k.nombreMoyenClientSysteme();
                        Lq=mm1k.nombreMoyenClientFile();
                        W= mm1k.dureeMoyenneAttenteSysteme();
                        Wq=mm1k.dureeMoyenneAttenteFile();
                        Pt=mm1k.calculProbabiliteAvecTemps(duree);
                        Px=mm1k.calculProbabilite(clientPresent);
                    }
                    else{
                        showMessageDialog(null,"Rho est supérieur ou égale à 1");
                    }

                    JFrame affichage= new JFrame("Affichage des résultats");
                    affichage.setContentPane(new AffichageResultat(Ro,L,Lq,W,Wq,Pt,duree,Px,clientPresent).getPanel());
                    affichage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    affichage.setSize(750,600);
                    // on rend le frame visible
                    affichage.setVisible(true);
                }
                //MMS
                else if(nbServeurSysteme !=1){

                    double L=0;
                    double Lq=0;
                    double W=0;
                    double Wq=0;
                    double Pt=0;
                    double Px=0;
                    MMS mms= new MMS(clientParUniteTemps,serviceParUniteTemps,nbServeurSysteme);
                    double Ro=mms.calculRo();
                    if(Ro<1){
                        L=mms.nombreMoyenClientSysteme();
                        Lq=mms.nombreMoyenClientFile();
                        W= mms.dureeMoyenneAttenteSysteme();
                        Wq=mms.dureeMoyenneAttenteFile();
                        Pt=mms.calculProbabiliteAvecTemps(duree);
                        Px=mms.calculProbabilite(clientPresent);
                    }
                    else{
                        showMessageDialog(null,"Rho est supérieur ou égale à 1");
                    }

                    JFrame affichage= new JFrame("Affichage des résultats");
                    affichage.setContentPane(new AffichageResultat(Ro,L,Lq,W,Wq,Pt,duree,Px,clientPresent).getPanel());
                    affichage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    affichage.setSize(750,600);
                    // on rend le frame visible
                    affichage.setVisible(true);
                }
                else{
                    showMessageDialog(null,"Les paramètres fournis ne correspondent à aucune des situations définies");
                }
            }
        });
    }

    private void createUIComponents() {
        SpinnerNumberModel modelSpinner;

        modelSpinner = new SpinnerNumberModel(1, 1, 10000, 1);
        servicesSysteme= new JSpinner(modelSpinner);

        modelSpinner = new SpinnerNumberModel(1, 1, 10000, 1);
        clientSysteme=new JSpinner(modelSpinner);

        modelSpinner = new SpinnerNumberModel(1, 1, 10000, 1);
        serveurSysteme=new JSpinner(modelSpinner);

        modelSpinner = new SpinnerNumberModel(1, 1, 10000, 1);
        tempsMax=new JSpinner(modelSpinner);

        modelSpinner = new SpinnerNumberModel(0, 0, 10000, 1);
        nbClientPresent=new JSpinner(modelSpinner);

        modelSpinner = new SpinnerNumberModel(0, 0, 10000, 1);
        nbClient=new JSpinner(modelSpinner);

    }

    public JPanel getPanel(){
        return panel;
    }
}
