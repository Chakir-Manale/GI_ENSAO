using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap5_Part5
{
    public class Joueur
    {
        private SimulateurTirArc simulateurTirArc;
        private int nombreDeFoisOuLeTirAChange;
        private int nombreDeFoisOuLeTirEstExcellent;

        public Joueur(SimulateurTirArc simulateur)
        {
            simulateurTirArc = simulateur;
            nombreDeFoisOuLeTirAChange = 0;
            nombreDeFoisOuLeTirEstExcellent = 0;
        }

        public void DemarrerAnalyse()
        {
            nombreDeFoisOuLeTirAChange = 0;
            nombreDeFoisOuLeTirEstExcellent = 0;
            simulateurTirArc.QuandLaCibleChange += new SimulateurTirArc.TirExcellentDelegate(simulateurTirArc_QuandLaCibleChange);
            simulateurTirArc.Demarrer();
            simulateurTirArc.QuandLaCibleChange -= new SimulateurTirArc.TirExcellentDelegate(simulateurTirArc_QuandLaCibleChange);
        }

        private void simulateurTirArc_QuandLaCibleChange(Cible cible)
        {
            if (cible == Cible.Excellent)
            {
                nombreDeFoisOuLeTirEstExcellent++;
            }

            nombreDeFoisOuLeTirAChange++;
        }

        public void AfficherRapport()
        {
            Console.WriteLine("Nombre de fois où le tir a changé : " + nombreDeFoisOuLeTirAChange);
            Console.WriteLine("Nombre de fois où le tir est Excellent : " + nombreDeFoisOuLeTirEstExcellent);
            Console.WriteLine("Pourcentage de tir Excellent : " + nombreDeFoisOuLeTirEstExcellent * 100 / nombreDeFoisOuLeTirAChange + " %");
        }
    }
}
