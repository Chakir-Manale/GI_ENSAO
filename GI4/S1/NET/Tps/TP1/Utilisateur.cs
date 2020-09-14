using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Utilisateur : Personne
    {
        private string login;
        private string password;
        private string service;
        public Profile profile { get; set; }

        public Utilisateur(string login , string password , string service , 
            int id , string nom , string prenom , string mail ,
            string telephone , double salaire , Profile profile) : base (id , nom , prenom , mail , telephone , salaire)
        {
            this.login = login;
            this.password = password;
            this.service = service;
            this.profile = profile;
        }

        public override double calculeSalaire()
        {
            if(profile.code.Equals("MN")) return this.salaire+0.1 * this.salaire;
            if (profile.code.Equals("DG")) return this.salaire + 0.4 * this.salaire;
            return base.calculeSalaire();
        }

        public override void affiche()
        {
            base.affiche();
            Console.WriteLine(" " + profile.libelle + " " + this.calculeSalaire());
        }
    }     
}
