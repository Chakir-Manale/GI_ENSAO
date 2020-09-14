using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap6_Part1
{
    public class StudentBL
    {
        public List<StudentDTO> SearchStudents(string search)
        {
            StudentDAL dal = new StudentDAL();

            return dal.ShowStudents().Where(s => s.LastName.Contains(search) ||
                                                 s.FirstName.Contains(search)).ToList();
        }

        public StudentDTO GetStudentByID(int ID)
        {
            StudentDAL dal = new StudentDAL();

            return dal.ShowStudents().SingleOrDefault(s => s.ID == ID);
        }
    }
}
