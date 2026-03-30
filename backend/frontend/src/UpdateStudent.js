import axios from "axios";
import { useState, useEffect } from "react";

function UpdateStudent({ selected, refresh, clearSelection }) {

  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  // Prefill data
  useEffect(() => {
    if (selected) {
      setStudent(selected);
    }
  }, [selected]);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const update = async (e) => {
    e.preventDefault();

    await axios.put(
      `http://localhost:8080/students/${student.id}`,
      student
    );

    setStudent({ name: "", email: "", course: "" });
    clearSelection();
    refresh();
  };

  if (!selected) return null; // show only when update clicked

  return (
    <div>
      <h2>Update Student</h2>

      <form onSubmit={update}>
        <input
          name="name"
          value={student.name}
          onChange={handleChange}
        /><br/><br/>

        <input
          name="email"
          value={student.email}
          onChange={handleChange}
        /><br/><br/>

        <input
          name="course"
          value={student.course}
          onChange={handleChange}
        /><br/><br/>

        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default UpdateStudent;