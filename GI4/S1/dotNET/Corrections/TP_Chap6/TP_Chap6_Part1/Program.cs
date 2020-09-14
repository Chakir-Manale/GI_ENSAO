using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap6_Part1
{
    class Program
    {
        static void Main(string[] args)
        {            
            string search = string.Empty;
            StudentBL bl = new StudentBL();

            while (search != "x")
            {
                Console.WriteLine("Vous cherchez quel étudiant ? (ou x pour sortir)");
                search = Console.ReadLine();

                if (int.TryParse(search, out int id))
                {
                    // Un seul étudiant avec son ID
                    StudentDTO dto = bl.GetStudentByID(id);

                    if (dto != null)
                    {
                        Console.WriteLine(dto);
                    }
                    else
                    {
                        Console.WriteLine("Aucun étudiant correspond à cet ID : " + id);
                    }
                }
                else
                {
                    //Une liste d'étudiants
                    List<StudentDTO> list = bl.SearchStudents(search);

                    if (list.Count > 0)
                    {
                        list.ForEach(s => Console.WriteLine(s));
                    }
                    else
                    {
                        Console.WriteLine("Aucun étudiant correspond à : " + search);
                    }
                }
            }
        }
    }
}
