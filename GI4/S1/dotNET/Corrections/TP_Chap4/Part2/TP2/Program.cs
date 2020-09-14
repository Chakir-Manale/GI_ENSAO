using System;
using System.Collections.Generic;
using System.Linq;

namespace TP2
{
    class Program
    {
        static Auteur A1, A2, A3;
        static MaisonPublication MP1, MP2, MP3, MP4;
        static List<Livre> liste;

        static void Main(string[] args)
        {
            InitialiserAuteurs();
            InitialiserMaisonsPublication();
            InitialiserListeLivres();

            Console.WriteLine("Programme de gestion de la bibliothèque");

            Console.WriteLine("Entrer un identifiant d'un livre :");
            int idL = int.Parse(Console.ReadLine());
            Livre livre1 = InformationsLivre(idL);

            livre1 = liste.SingleOrDefault(l => l.IdL == idL);

            Console.WriteLine(livre1);

            Console.WriteLine("Entrer un titre d'un livre :");
            Livre livre2 = InformationsLivre(Console.ReadLine());

            livre2 = liste.FirstOrDefault(l => l.TitreL == Console.ReadLine());

            Console.WriteLine(livre2);

            Console.WriteLine("Entrer un identifiant d'un auteur :");
            int idA = int.Parse(Console.ReadLine());
            List<Livre> livres = LivresAuteur(idA);

            livres = liste.Where(l => l.AuteurL.IdA == idA).ToList();

            foreach(Livre l in livres)
                Console.WriteLine(l);

            Console.WriteLine("Entrer une année de publication :");
            int annee = int.Parse(Console.ReadLine());
            int count = AnneePublication(new DateTime(annee, 1, 1));

            count = liste.Where(l => l.AnneeL.Year == annee).Select(l => l.MaisonL.IdM).Distinct().Count();

            Console.WriteLine(count);

            liste.Any(l => l.NbPagesL > 50);

            Console.ReadLine();
        }

        private static int AnneePublication(DateTime Annee)
        {
            List<int> listeM = new List<int>();

            foreach (Livre l in liste)
            {
                if (l.AnneeL.Year == Annee.Year)
                {
                    if(!listeM.Contains(l.MaisonL.IdM))
                    {
                        listeM.Add(l.MaisonL.IdM);
                    }
                }
            }

            return listeM.Count;
        }

        private static List<Livre> LivresAuteur(int IdA)
        {
            List<Livre> returnedList = new List<Livre>();

            foreach(Livre l in liste)
            {
                if(l.AuteurL.IdA == IdA)
                {
                    returnedList.Add(l);
                }
            }

            return returnedList;
        }

        private static Livre InformationsLivre(int IdL)
        {
            Livre returnedValue = null;

            foreach(Livre l in liste)
            {
                if(l.IdL == IdL)
                {
                    returnedValue = l;
                    break;
                }
            }

            return returnedValue;
        }

        private static Livre InformationsLivre(string TitreL)
        {
            Livre returnedValue = null;

            foreach (Livre l in liste)
            {
                if (l.TitreL == TitreL)
                {
                    returnedValue = l;
                    break;
                }
            }

            return returnedValue;
        }

        private static void InitialiserMaisonsPublication()
        {
            MP1 = new MaisonPublication() { IdM = 1, NomM = "Maison1", VilleM = "Lyon" };
            MP2 = new MaisonPublication() { IdM = 2, NomM = "Maison2", VilleM = "Paris" };
            MP3 = new MaisonPublication() { IdM = 3, NomM = "Maison3", VilleM = "Lille" };
            MP4 = new MaisonPublication() { IdM = 4, NomM = "Maison4", VilleM = "Toulouse" };
        }

        private static void InitialiserAuteurs()
        {
            A1 = new Auteur() { IdA = 1, NomA = "NomA1", PrenomA = "PrenomA1" };
            A2 = new Auteur() { IdA = 2, NomA = "NomA2", PrenomA = "PrenomA2" };
            A3 = new Auteur() { IdA = 3, NomA = "NomA3", PrenomA = "PrenomA3" };
        }

        private static void InitialiserListeLivres()
        {
            liste = new List<Livre>()
            {
                new Livre()
                {
                    IdL = 1,
                    TitreL = "Livre 1",
                    AnneeL = new DateTime(2017, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A1,
                    MaisonL = MP1
                },
                new Livre()
                {
                    IdL = 2,
                    TitreL = "Livre 2",
                    AnneeL = new DateTime(2017, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A1,
                    MaisonL = MP2
                },
                new Livre()
                {
                    IdL = 3,
                    TitreL = "Livre 3",
                    AnneeL = new DateTime(2010, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A3,
                    MaisonL = MP4
                },
                new Livre()
                {
                    IdL = 4,
                    TitreL = "Livre 4",
                    AnneeL = new DateTime(2017, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A1,
                    MaisonL = MP2
                },
                new Livre()
                {
                    IdL = 5,
                    TitreL = "Livre 5",
                    AnneeL = new DateTime(2013, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A2,
                    MaisonL = MP3
                },
                new Livre()
                {
                    IdL = 6,
                    TitreL = "Livre 6",
                    AnneeL = new DateTime(2010, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A1,
                    MaisonL = MP3
                },
                new Livre()
                {
                    IdL = 7,
                    TitreL = "Livre 7",
                    AnneeL = new DateTime(2016, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A2,
                    MaisonL = MP3
                },
                new Livre()
                {
                    IdL = 8,
                    TitreL = "Livre 8",
                    AnneeL = new DateTime(2017, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A3,
                    MaisonL = MP1
                },
                new Livre()
                {
                    IdL = 9,
                    TitreL = "Livre 9",
                    AnneeL = new DateTime(2010, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A1,
                    MaisonL = MP4
                },
                new Livre()
                {
                    IdL = 10,
                    TitreL = "Livre 10",
                    AnneeL = new DateTime(2017, 1, 1),
                    NbPagesL = 50,
                    PrixL = 22.00f,
                    AuteurL = A2,
                    MaisonL = MP3
                },
            };
        }
    }
}
