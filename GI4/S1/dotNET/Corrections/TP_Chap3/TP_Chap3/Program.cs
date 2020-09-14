using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap3
{
    class Program
    {
        static void Main(string[] args)
        {
            #region Conditions et Boucles

            //// Création d’un nouveau projet « Console »
            //Console.WriteLine("Hello World!");

            //string s = Console.ReadLine();
            //Console.WriteLine(s);

            ////Manipulation des conditions
            //Console.WriteLine("*******************");

            //Console.WriteLine("Entrer un chiffre");
            //int x = int.Parse(Console.ReadLine());

            //if (x < 0)
            //{
            //    Console.WriteLine("Négatif");
            //}
            //else
            //{
            //    Console.WriteLine("Positif");
            //}

            //s = string.Empty;

            //s = x < 0 ? "Négatif" : "Positif";

            //Console.WriteLine(s);

            ////Réalisation des programmes de calcul avec les boucles
            //Console.WriteLine("*******************");

            //int somme = 0;

            //for (int i = 1; i <= 100; i++)
            //{
            //    somme += i;
            //}

            //Console.WriteLine(somme);

            //Console.WriteLine("*******************");

            //Console.WriteLine("Entrer le nombre de chiffres :");

            //int nb = int.Parse(Console.ReadLine());
            //List<int> list = new List<int>();
            //somme = 0;

            //for (int i = 0; i < nb; i++)
            //{
            //    list.Add(int.Parse(Console.ReadLine()));
            //}

            //foreach (int i in list)
            //{
            //    somme += i;
            //}

            //Console.WriteLine(somme);

            //Console.WriteLine("*******************");

            //Console.WriteLine("Entrer les chiffres :");

            //int multiplication = 1;
            //x = int.Parse(Console.ReadLine());

            //while (x != 0)
            //{
            //    multiplication *= x;
            //    x = int.Parse(Console.ReadLine());
            //}

            //Console.WriteLine(multiplication);

            //Console.WriteLine("*******************");

            //list = new List<int>() { 4, 5, 3, 9, 0, 207, 46 };
            //Console.WriteLine("Entrer un chiffre :");
            //x = int.Parse(Console.ReadLine());

            //foreach (int i in list)
            //{
            //    if (i == x)
            //    {
            //        Console.WriteLine("Existe");
            //        break;
            //    }
            //}

            //foreach (int i in list)
            //{
            //    if (i == 0)
            //    {
            //        continue;
            //    }

            //    x = x / i;
            //}

            //// Manipulation des « switch – case »
            //Console.WriteLine("*******************");

            //Console.WriteLine("Entrer un chiffre :");
            //x = int.Parse(Console.ReadLine());

            //switch (x)
            //{
            //    case 0:
            //        Console.WriteLine("Zero");
            //        break;
            //    case 1:
            //        Console.WriteLine("Un");
            //        break;
            //    case 2:
            //        Console.WriteLine("Deux");
            //        break;
            //    case 3:
            //        Console.WriteLine("Trois");
            //        break;
            //    case 4:
            //        Console.WriteLine("Quatre");
            //        break;
            //    case 5:
            //        Console.WriteLine("Cinq");
            //        break;
            //    case 6:
            //        Console.WriteLine("Six");
            //        break;
            //    case 7:
            //        Console.WriteLine("Sept");
            //        break;
            //    case 8:
            //        Console.WriteLine("Huit");
            //        break;
            //    case 9:
            //        Console.WriteLine("Neuf");
            //        break;
            //    case 10:
            //        Console.WriteLine("Dix");
            //        break;
            //    default:
            //        Console.WriteLine("Hors de portée");
            //        break;
            //}

            #endregion

            #region Cryptage

            ////Programme de cryptage
            //Console.WriteLine("*******************");

            //Console.WriteLine("Programme de cryptage (César)");
            //Console.WriteLine("Entrer la clé de cryptage : ");
            //int cle = int.Parse(Console.ReadLine());

            //Console.WriteLine("Pour crypter un texte, entrer 1, pour décrypter entrer 2 : ");
            //string mode = Console.ReadLine();

            //Console.WriteLine("Entrer le texte : ");
            //string texte = Console.ReadLine().ToLower();
            //string result = string.Empty;

            //if (mode == "2")
            //    cle = -cle;

            //foreach (char c in texte)
            //{
            //    int n;

            //    if (c >= 'a' && c <= 'z')
            //    {
            //        n = (char)((int)c + cle);

            //        if (n > 'z')
            //        {
            //            n = (char)(int)(n - 'z' + 'a' - 1);
            //        }
            //        else if (n < 'a')
            //        {
            //            n = (char)(int)(n + 'z' - 'a' + 1);
            //        }

            //        //n = 'a' + (int)((c - 'a' + cle) % 26);
            //    }
            //    else
            //    {
            //        n = (int)c;
            //    }

            //    result += (char)n;
            //}

            //Console.WriteLine(result);

            #endregion

            #region Chaine de caracteres

            // Programme de formatage
            Console.WriteLine("*******************");

            Console.WriteLine("Programme de formatage de texte");
            Console.WriteLine("Entrer le texte à formater : ");
            string word = /*Console.ReadLine();*/"bonjour tout le monde..aujourd'hui,il fait,froid à Lyon.espérons que demain sera plus    chaud";

            // Supprimer les espaces inutiles
            word = word.Trim();

            // Ajouter une majuscule au début
            string maj = word[0].ToString();
            word = word.Substring(1);
            word = word.Insert(0, maj.ToUpper());

            // ajouter . à la fin
            if (word.Last().ToString() != ".")
            {
                word = word + ".";
            }

            // Retour à la ligne après ..
            word = word.Replace("..", ".\n");

            // Ajouter l'espace
            word = word.Replace(".", ". ");
            word = word.Replace(";", "; ");
            word = word.Replace(",", ", ");
            word = word.Replace(":", ": ");
            word = word.Replace("!", "! ");
            word = word.Replace("?", "? ");
            word = word.Replace(". \n", ".\n");

            // Supprimer l'espace inutile
            while (word.Contains("  "))
            {
                word = word.Replace("  ", " ");
            }

            word = word.Trim();

            int lastIndex = 0;

            foreach(char c in word)
            {
                if(c == '.' || c == '!' || c == '?')
                {
                    if (word.IndexOf(c, lastIndex) + 1 < word.Length)
                    {
                        string word1 = word.Substring(0, word.IndexOf(c, lastIndex) + 2);
                        string word2 = word.Substring(word.IndexOf(c, lastIndex) + 3);

                        lastIndex = word.IndexOf(c, lastIndex) + 1;

                        word = word1 + word[lastIndex + 1].ToString().ToUpper() + word2;
                    }
                }
            }
            
            Console.WriteLine(word);

            #endregion

            Console.Read();
        }
    }
}
