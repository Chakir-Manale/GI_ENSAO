using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap4_Part3
{
    public class Mercedes : Voiture
    {
        public override void AfficherCaracteristiques()
        {
            Console.WriteLine("###########################");
            Console.WriteLine(Nom + " :");
            Console.WriteLine(" # Couleur : " + Couleur);
            Console.WriteLine(" # Modele : " + Modele);
            Console.WriteLine(" # Constructeur : " + Constructeur);
            Console.WriteLine("###########################");
        }

        public override int CalculerVitesse()
        {
            return 300;
        }
    }
}
