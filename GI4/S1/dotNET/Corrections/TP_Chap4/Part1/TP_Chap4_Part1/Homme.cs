using System;

namespace TP_Chap4_Part1
{
    public class Homme
    {
        public string Nom { get; set; }
        public string Prenom { get; set; }
        public DateTime DateNaissance { get; set; }
        public int Taille { get; set; }
        public int Poids { get; set; }

        public Homme()
        {
            Nom = "Nom Inconnu";
            Prenom = "Prenom Inconnu";
            DateNaissance = DateTime.MinValue;
            Taille = 190;
            Poids = 60;
        }

        public Homme(string Nom, string Prenom): this()
        {
            this.Nom = Nom;
            this.Prenom = Prenom;
        }

        public int CalculerAge()
        {
            return DateTime.Now.Year - DateNaissance.Year;
        }

        public bool EstUnJoueurBasket()
        {
            return this.Taille >= 180;
        }

        public override string ToString()
        {
            return Nom + " " + Prenom;
        }
    }
}
