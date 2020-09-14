using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap5_Part5
{
    class Program
    {
        static void Main(string[] args)
        {
            SimulateurTirArc simulateur = new SimulateurTirArc(1000);
            Joueur joueur = new Joueur(simulateur);

            joueur.DemarrerAnalyse();
            joueur.AfficherRapport();

            Console.Read();
        }
    }
}
