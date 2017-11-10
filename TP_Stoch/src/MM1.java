/**
 * Created by lelib on 23/10/2017.
 */
public class MM1 {

    private double lambda;
    private double mu;
    private double Ro;


    public MM1(double lambda, double mu)
    {
        this.lambda = lambda;
        this.mu = mu;
    }


    // Getters and Setters

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    /**
     * Calcul du Ro pour vérifier l'apparition ou non d'un bouchon
     * dans le systeme
     *
     * @return : La valeur de Ro
     */
    public double calculRo()
    {
        this.Ro = lambda / mu;

        return Ro;
    }


    /**
     * Calcul du nombre moyen de clients dans le syteme avec file de type M|M|1
     *
     * @return L : le nombre moyen
     */
    public double nombreMoyenClientSysteme()
    {
        double L = lambda / (mu - lambda);

        return L;
    }

    /**
     * Calcul du nombre moyen de clients dans la file d'attente d'une file de type M|M|1
     *
     * @return Lo : le nombre moyen
     */
    public double nombreMoyenClientFile()
    {
        double Lo = (lambda * lambda) / (mu * (mu - lambda));

        return Lo;
    }

    /**
     * Calcul de la durée moyenne d'attente dans le système avec file de type M|M|1
     *
     * @return W : la durée moyenne
     */
    public double dureeMoyenneAttenteSysteme()
    {
        double W = 1 / (mu - lambda);

        return W;
    }

    /**
     * Calcul de la durée moyenne dans la file d'attente d'une file de type M|M|1
     *
     * @return Wq : la durée moyenne
     */
    public double dureeMoyenneAttenteFile()
    {
        double Wq = lambda / (mu * (mu - lambda));

        return Wq;
    }

    /**
     * Calcul d'une probabilite d'etre dans un état j
     *
     * @param etat : Etat j donné
     *
     * @return : Qj : La propabilité
     */
    public double calculProbabilite(int etat)
    {
        double Qj = (Math.pow(Ro, etat)) * (1 - Ro);

        return Qj;
    }

    /**
     * Durée de séjour dans le systeme P(to > t) avec file M|M|1
     *
     * @param t : le temps
     *
     * @return P : La probabilite
     */
    public double calculProbabiliteAvecTemps(double t)
    {
        double P = Math.exp(-mu * (1 - Ro) * t);

        return P;
    }
}
