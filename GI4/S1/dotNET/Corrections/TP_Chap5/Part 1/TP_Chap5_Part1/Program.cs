using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap5_Part1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Écrire un programme dans lequel on demande à l’utilisateur de saisir un entier en gérant l’exception dans le cas où il ne saisit pas un entier correctement
            #region P1

            try
            {
                Console.WriteLine("Entrer un entier");
                int x = int.Parse(Console.ReadLine());
                Console.WriteLine(x);
            }
            catch (FormatException)
            {
                Console.WriteLine("Ce n'est pas un entier");
            }

            #endregion

            //Écrire un programme dans lequel on fait la division de 2 entiers saisis par l’utilisateur en gérant l’exception dans le cas d’une division par zéro
            #region P2

            try
            {
                Console.WriteLine("Entrer 2 entiers");
                int x = int.Parse(Console.ReadLine());
                int y = int.Parse(Console.ReadLine());

                Console.WriteLine("La division est : " + (x / y));
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("Division par zéro");
            }

            #endregion

            //Compléter le programme suivant pour que les erreurs susceptibles de se produire soient gérées par des exceptions
            #region P3

            List<int> liste = new List<int>() { 17, 12, 15, 38, 29, 157, 89, -22, 0, 5 };

            try
            {
                Console.WriteLine("Entrer l'indice de l'entier à diviser :");
                int indice = int.Parse(Console.ReadLine());

                Console.WriteLine("Entrer le diviseur :");
                int diviseur = int.Parse(Console.ReadLine());

                Console.WriteLine("Le résultat de la division est " + liste[indice] / diviseur);
            }
            catch (FormatException)
            {
                Console.WriteLine("Ce n'est pas un entier");
            }
            catch(ArgumentOutOfRangeException)
            {
                Console.WriteLine("Indice hors la liste");
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("Division par zéro");
            }

            #endregion

            Console.Read();

        }
    }
}
