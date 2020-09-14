using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Personne
    {
        protected int id;
        protected string nom;
        protected string prenom;
        protected string mail;
        protected string telephone;
        protected double salaire;

        public Personne(int id , string nom , string prenom , string mail , string telephone , double salaire)
        {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.mail = mail;
            this.telephone = telephone;
            this.salaire = salaire;
        }

        public virtual double calculeSalaire()
        {
            return salaire;
        }
        public virtual void affiche()
        {
            Console.WriteLine("Nom : " + this.nom + " Prenom : " + this.prenom);
        }
     }
}
