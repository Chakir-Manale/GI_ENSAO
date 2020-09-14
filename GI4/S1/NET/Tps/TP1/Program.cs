using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Program
    {
        static void Main(string[] args)
        {
            Profile[] profile = new Profile[5];
            profile[0] = new Profile(0,"CP", "Chef de projet");
            profile[1] = new Profile(1,"DP", "Directeur de projet");
            profile[2] = new Profile(2,"MN", "Manager");
            profile[3] = new Profile(3,"DRH", "Directeur de ressources humaine");
            profile[4] = new Profile(4,"DG", "Directeur general");

            Utilisateur[] utilisateur = new Utilisateur[3];
            utilisateur[0] = new Utilisateur("rafa", "grandslam15", "Manager", 0, "rafael", "nadal", "rafa.nadal@gmail.com", "+31", 20000, profile[2]);
            utilisateur[1] = new Utilisateur("roger", "grandslam18", "Manager", 1, "roger", "federer", "roger.federer@gmail.com", "+31", 20000, profile[2]);
            utilisateur[2] = new Utilisateur("hardwell", "tomorowland", "Directeur de projet", 2, "hardwell", "hardwell", "hardwell.dj@gmail.com", "+31", 10000, profile[1]);

            foreach(Utilisateur user in utilisateur)
            {
                if (user.profile.code.Equals("MN")) user.affiche();
            }
        }
    }
}
