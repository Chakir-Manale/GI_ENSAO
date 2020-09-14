using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap4_Part3
{
    class Program
    {
        static void Main(string[] args)
        {
            Mercedes m = new Mercedes()
            {
                Nom = "Mercedes",
                Couleur = "Noir",
                Modele = "S Class",
                Constructeur = "Allemagne"
            };

            Renault r = new Renault()
            {
                Nom = "Renault",
                Couleur = "Blanche",
                Modele = "Talisman",
                Constructeur = "France"
            };

            m.AfficherCaracteristiques();
            Console.WriteLine(m.CalculerVitesse());

            r.AfficherCaracteristiques();
            Console.WriteLine(r.CalculerVitesse());

            Console.Read();
        }
    }
}
