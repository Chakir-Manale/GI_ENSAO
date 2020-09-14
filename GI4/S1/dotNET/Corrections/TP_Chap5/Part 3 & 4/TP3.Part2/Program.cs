using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2.Part4
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Employee> listEmployees = new List<Employee>()
            {                
                new Employee() { EmpId = 1, Name = "Sam", Sex = "Male", Phone = "423-555-0124", Address = new Address() { Street = "7A Cox Street", City = "Acampo", State = "CA", Zip = "95220", Country = "USA" }},
                new Employee() { EmpId = 2, Name = "Lucy", Sex = "Female", Phone = "143-555-0763", Address = new Address() { Street = "Jess Bay", City = "Alta", State = "CA", Zip = "95701", Country = "USA" }},
                new Employee() { EmpId = 3, Name = "Kate", Sex = "Female", Phone = "166-555-0231", Address = new Address() { Street = "23 Boxen Street", City = "Milford", State = "CA", Zip = "96121", Country = "USA" }},
                new Employee() { EmpId = 4, Name = "Chris", Sex = "Male", Phone = "564-555-0122", Address = new Address() { Street = "124 Kutbay", City = "Montara", State = "CA", Zip = "94037", Country = "USA" }},
            };
            
            Console.WriteLine("\n1---------------------\n");

            // La liste de tous les employés dont le sexe est Male et habite à USA

            var employees = listEmployees.Where(e => e.Sex == "Male" && e.Address != null && e.Address.Country == "USA");

            foreach (var emp in employees)
            {
                Console.WriteLine(emp);
            }
            
            Console.WriteLine("\n2---------------------\n");

            // La liste de tous les employés dont le numéro de tél commence par 1 ordonnés en descendant par le numéro de téléphone et après en ascendant par la ville

            employees = listEmployees.Where(e => e.Phone.StartsWith("1")).OrderByDescending(e => e.Phone).ThenBy(e => e.Address.City);

            foreach (var emp in employees)
            {
                Console.WriteLine(emp);
            }

            Console.WriteLine("\n3---------------------\n");

            // l'employé à la position 1 de la liste de tous les employés dont le numéro de tél commence par 1 ordonnés en ascendant

            var employee = listEmployees.Where(e => e.Phone.StartsWith("1")).OrderBy(e => e.Phone).ElementAt(0);

            Console.WriteLine(employee);

            Console.WriteLine("\n4---------------------\n");

            // les noms de tous les employés

            var names = listEmployees.Select(e => e.Name);

            foreach (var s in names)
            {
                Console.WriteLine(s);
            }

            Console.WriteLine("\n5---------------------\n");

            // l'employé à la 3ème position

            employees = listEmployees.Skip(2).Take(1);

            foreach (var emp in employees)
            {
                Console.WriteLine(emp);
            }

            Console.WriteLine("\n6---------------------\n");

            // l'employé dont l'identifiant est 2

            employee = listEmployees.SingleOrDefault(e => e.EmpId == 2);

            Console.WriteLine(employee);

            Console.WriteLine("\n7---------------------\n");

            // Si au moins un employé habite à la ville d'Alta

            bool vrai = listEmployees.Any(e => e.Address.City == "Alta");

            Console.WriteLine(vrai);

            Console.WriteLine("\n8---------------------\n");

            // la longueur du plus grand nom de voie

            int length = listEmployees.Max(e => e.Address.Street.Length);

            Console.WriteLine(length);

            Console.WriteLine("\n9---------------------\n");

            // Multiplication par 10 des identifiants de tous les employés

            listEmployees.ForEach(e => e.EmpId *= 10);

            foreach (var emp in listEmployees)
            {
                Console.WriteLine(emp);
            }

            Console.WriteLine("\n10---------------------\n");

            // La somme des identifiants de tous les employés dont le sexe est "Male"

            int somme = listEmployees.Where(e => e.Sex == "Male").Sum(e => e.EmpId);

            Console.WriteLine(somme);

            Console.Read();
        }
    }
}
