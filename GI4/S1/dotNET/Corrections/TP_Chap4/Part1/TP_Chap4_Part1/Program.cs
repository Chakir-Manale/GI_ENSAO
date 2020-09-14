using System;

namespace TP_Chap4_Part1
{
    class Program
    {
        static void Main(string[] args)
        {
            Homme h = new Homme("Nom", "Prenom");

            Console.WriteLine(h);
            Console.WriteLine(h.CalculerAge());
            Console.WriteLine(h.EstUnJoueurBasket());
            Console.Read();
        }
    }
}
