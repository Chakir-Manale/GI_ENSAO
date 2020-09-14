using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using System.IO;
using System.Xml;

namespace TP2.Part3
{
    class Program
    {
        static void Main(string[] args)
        {
            // Chargement du document XML
            XElement xelement = XElement.Load("..\\..\\Employees.xml");

            Console.WriteLine("\n2---------------------\n");

            // Lecture de tous le document XML
            IEnumerable<XElement> employees = xelement.Elements();

            employees.ToList().ForEach(e => Console.WriteLine(e));
            
            Console.WriteLine("\n3---------------------\n");

            // Lecture de tous les éléments "Name" et "EmpId"
            employees.ToList().ForEach(e => Console.WriteLine(e.Element("Name").Value + " a l'identifiant " + e.Element("EmpId").Value));
            
            Console.WriteLine("\n4---------------------\n");

            // Lecture de tous les employés dont le sexe est "Female"
            var females = from emp in employees where emp.Element("Sex").Value == "Female" select emp;

            females = employees.Where(e => e.Element("Sex").Value == "Female");

            females.ToList().ForEach(e => Console.WriteLine(e));
            
            Console.WriteLine("\n5---------------------\n");

            //Afficher tous les employés qui habite à la ville « Alta »
            var alta = employees.Where(e => e.Element("Address").Element("City").Value == "Alta");

            alta = from emp in employees where emp.Element("Address").Element("City").Value == "Alta" select emp;

            alta.ToList().ForEach(e => Console.WriteLine(e.Element("Name").Value));

            Console.WriteLine("\n6---------------------\n");

            // Lecture de tous les numéro de téléphone de type "Home"
            var phoneNumbers = from tel in employees.Elements("Phone") where tel.Attribute("Type").Value == "Home" select tel.Value;

            phoneNumbers = employees.Elements("Phone").Where(p => p.Attribute("Type").Value == "Home").Select(e => e.Value);

            phoneNumbers.ToList().ForEach(p => Console.WriteLine(p));
            
            Console.WriteLine("\n7---------------------\n");

            // Lecture de tous les zip codes
            var zipCodes = employees.Descendants("Zip");

            zipCodes.ToList().ForEach(z => Console.WriteLine(z));
            
            Console.WriteLine("\n8---------------------\n");

            // Lecture et tri ascendant de tous les zip codes
            zipCodes = from zip in zipCodes orderby zip.Value select zip;

            //IEnumerable<string> codes = from code in xelement.Elements("Employee")
            //                            let zip = (string)code.Element("Address").Element("Zip")
            //                            orderby zip
            //                            select zip;

            zipCodes.ToList().ForEach(z => Console.WriteLine(z.Value));
            
            Console.WriteLine("\n9---------------------\n");

            // Lecture de l'élément Employee à la 2eme position
            var empPos2 = employees.ElementAt(1);
            Console.WriteLine(empPos2);

            Console.WriteLine("\n10---------------------\n");

            // Lecture des 2 premiers éléments
            var emps = employees.Skip(1).Take(2);

            emps.ToList().ForEach(e => Console.WriteLine(e));
            
            //Console.WriteLine("\n10---------------------\n");

            // Ajouter un employé au fichier XML et persister les modifications 
            //xelement.Add(
            //    new XElement("Employee",
            //        new XElement("EmpId", 5),
            //        new XElement("Name", "George"),
            //        new XElement("Sex", "Male"),
            //        new XElement("Phone", "423-555-4224", new XAttribute("Type", "Home")),
            //        new XElement("Phone", "424-555-0545", new XAttribute("Type", "Work")),
            //        new XElement("Address",
            //            new XElement("Street", "Fred Park, East Bay"),
            //            new XElement("City", "Acampo"),
            //            new XElement("State", "CA"),
            //            new XElement("Zip", "95220"),
            //            new XElement("Country", "USA"))));

            //xelement.Save("..\\..\\Employees.xml");
            //Console.WriteLine("Modification enregistré avec succès");

            //Console.WriteLine("\n10---------------------\n");

            // Créer un nouveau fichier XML
            //XNamespace empNM = "urn:lst-emp:emp";

            //XDocument xDoc = new XDocument(
            //    new XDeclaration("1.0", "UTF-16", null),
            //    new XElement(empNM + "Employees",
            //        new XElement("Employee",
            //            new XComment("Only 3 elements for demo purposes"),
            //            new XElement("EmpId", "5"),
            //            new XElement("Name", "Kimmy"),
            //            new XElement("Sex", "Female")
            //            )));

            //xDoc.Save("E:\\ListeEmployes.xml");
            //Console.WriteLine("Fichier créé avec succès");

            //foreach (var ele in employees)
            //{
            //    Console.WriteLine("new Employee() { EmpId = " + ele.Element("EmpId").Value + ", Name = \"" + ele.Element("Name").Value + "\", Sex = \"" + ele.Element("Sex").Value + "\", Phone = \"" + ele.Elements("Phone").First().Value + "\", Address = new Address() { Street = \"" + ele.Descendants("Street").First().Value + "\", City = \"" + ele.Descendants("City").First().Value + "\", State = \"" + ele.Descendants("State").First().Value + "\", Zip = \"" + ele.Descendants("Zip").First().Value + "\", Country = \"" + ele.Descendants("Country").First().Value + "\" }},");
            //}

            Console.Read();
        }
    }
}
