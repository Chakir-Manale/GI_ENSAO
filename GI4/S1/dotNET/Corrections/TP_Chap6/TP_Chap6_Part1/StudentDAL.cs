using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap6_Part1
{
    public class StudentDAL
    {
        public string ConnexionString
        {
            get
            {
                return ConfigurationManager.ConnectionStrings["CnxString"].ConnectionString;
            }
        }

        public SqlConnection Connection
        {
            get
            {
                if (connection == null)
                {
                    connection = new SqlConnection(ConnexionString);
                }

                return connection;
            }
        }

        private SqlConnection connection;

        public List<StudentDTO> ShowStudents()
        {
            List<StudentDTO> list = new List<StudentDTO>();

            using (SqlCommand cmd = new SqlCommand())
            {
                try
                {
                    Connection.Open();
                    cmd.Connection = Connection;
                    cmd.CommandType = System.Data.CommandType.Text;
                    cmd.CommandText = "Select * From Student";

                    using (SqlDataReader dr = cmd.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            StudentDTO dto = new StudentDTO()
                            {
                                ID = dr.GetInt32(dr.GetOrdinal("ID")),
                                LastName = dr["LastName"].ToString(),
                                FirstName = dr["FirstName"].ToString(),
                                CNE = dr["CNE"].ToString(),
                            };

                            list.Add(dto);
                        }
                    }
                }
                catch (Exception)
                {
                    Console.WriteLine("Erreur");
                }
                finally
                {
                    Connection.Close();
                }
            }

            return list;
        }
    }
}
