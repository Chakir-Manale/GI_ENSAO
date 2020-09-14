using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap5_Part2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Programme de gestion de la présence");
            Console.WriteLine("Entrer la liste des élèves :");

            string presence = string.Empty;
            Dictionary<string, bool> dic = new Dictionary<string, bool>();

            while (presence != "x")
            {
                presence = Console.ReadLine();

                List<string> liste = presence.Split(',').ToList();

                if (liste.Count == 2 && (liste[1] == "t" || liste[1] == "f"))
                {
                    bool val = liste[1] == "t" ? true : false;

                    if (!dic.ContainsKey(liste[0]))
                    {
                        dic.Add(liste[0], val);
                    }
                    else
                    {
                        dic[liste[0]] = val;
                    }
                }
                else if (presence != "x")
                {
                    Console.WriteLine("\t\aEntrée non valide");
                }
            }

            int sommePresents = dic.Count(p => p.Value == true);
            List<string> absents = dic.Where(p => p.Value == false).Select(p => p.Key).ToList();

            Console.WriteLine("La somme des presents : " + sommePresents);

            if (absents.Count > 0)
            {
                Console.WriteLine("Les élèves absents sont : ");
                absents.ForEach(a => Console.WriteLine(a));
            }
            else
            {
                Console.WriteLine("Aucun élève absent !");
            }

            Console.Read();
        }
    }
}
