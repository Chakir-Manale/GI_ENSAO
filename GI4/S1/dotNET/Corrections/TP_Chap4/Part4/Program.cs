using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2
{
    class Program
    {
        static void Main(string[] args)
        {
            ClubInterne clubSportif = new ClubInterne(10000) { Nom = "Club sportif" };

            clubSportif.FaireActivite(new Activite() { Type = TypeActivite.Tournoi, Revenus = 800, Charges = 2000 });
            clubSportif.FaireActivite(new Activite() { Type = TypeActivite.Soiree, Revenus = 0, Charges = 3000 });
            clubSportif.FaireActivite(new Activite() { Type = TypeActivite.Sortie, Revenus = 0, Charges = 2000 });

            clubSportif.AfficherResume();
            clubSportif.ValeurSolde();

            ClubExterne associationAnciensLaureats = new ClubExterne(10000, 2.5M) { Nom = "Association des anciens Lauréats" };

            associationAnciensLaureats.FaireActivite(new Activite() { Type = TypeActivite.Seminaire, Revenus = 200, Charges = 2000 });
            associationAnciensLaureats.FaireActivite(new Activite() { Type = TypeActivite.Soiree, Revenus = 0, Charges = 3000 });
            associationAnciensLaureats.FaireActivite(new Activite() { Type = TypeActivite.Forum, Revenus = 5000, Charges = 10000 });

            associationAnciensLaureats.AfficherResume();
            associationAnciensLaureats.ValeurSolde();

            Console.Read();
        }
    }
}
