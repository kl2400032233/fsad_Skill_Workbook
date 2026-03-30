import React, { useState, useEffect } from "react";
import axios from "axios";

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [filter, setFilter] = useState("");

  const fetchData = () => {
    axios.get("https://dummyjson.com/posts")
      .then(res => setPosts(res.data.posts));
  };

  useEffect(() => {
    fetchData();
  }, []);

  const filtered = filter
    ? posts.filter(p => p.userId === Number(filter))
    : posts;

  return (
    <div>
      <h2>Fake Posts</h2>

      <button onClick={fetchData}>Refresh</button>

      <select onChange={e => setFilter(e.target.value)}>
        <option value="">All</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
      </select>

      {filtered.map(p => (
        <div key={p.id}>
          <h4>{p.title}</h4>
          <p>{p.body}</p>
        </div>
      ))}
    </div>
  );
}

export default FakePostList;