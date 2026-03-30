import axios from "axios";
import { useEffect, useState } from "react";
import AddStudent from "./AddStudent";
import UpdateStudent from "./UpdateStudent";

function StudentList() {

  const [students, setStudents] = useState([]);
  const [selected, setSelected] = useState(null);

  // Load all students
  const loadStudents = () => {
    axios.get("http://localhost:8080/students")
      .then(res => {
        setStudents(res.data);
      });
  };

  // Run when page loads
  useEffect(() => {
    loadStudents();
  }, []);

  // DELETE function
  const deleteStudent = (id) => {
    axios.delete(`http://localhost:8080/students/${id}`)
      .then(() => {
        loadStudents(); // refresh after delete
      });
  };

  return (
    <div>
      <h1>Student Management System</h1>

      {/* ADD STUDENT */}
      <AddStudent refresh={loadStudents} />

      {/* UPDATE STUDENT */}
      <UpdateStudent 
        selected={selected}
        refresh={loadStudents}
        clearSelection={() => setSelected(null)}
      />

      <h2>Student List</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.course}</td>

              <td>
                {/* UPDATE BUTTON */}
                <button onClick={() => setSelected(s)}>
                  Update
                </button>

                {/* DELETE BUTTON */}
                <button onClick={() => deleteStudent(s.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>
    </div>
  );
}

export default StudentList;