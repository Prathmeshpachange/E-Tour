// src/pages/SubcategoryPage.jsx
import React, { useEffect, useState } from "react";
import { useParams, Link, useNavigate, useLocation } from "react-router-dom";
import "../pagestyle/SubcategoryPage.css";

const SubcategoryPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  const [subcategories, setSubcategories] = useState([]);
  const [categoryName, setCategoryName] = useState("");

  useEffect(() => {
    const token = sessionStorage.getItem("token") || null;
    const headers = token ? { "Authorization": `Bearer ${token}` } : {};

    // Protected call -> send Authorization header
    fetch(`http://localhost:5264/api/subcategory/category/${id}`, { headers })
      .then((res) => {
        if (res.status === 401) {
          // not authorized -> redirect to login and pass current path so user can return after login
          navigate("/login", { state: { from: location.pathname } });
          throw new Error("Not authorized");
        }
        if (!res.ok) throw new Error("Failed to fetch subcategories");
        return res.json();
      })
      .then((data) => setSubcategories(data))
      .catch((err) => {
        console.error("Error fetching subcategories:", err);
      });

    // Category name (public)
    fetch(`http://localhost:5264/api/category/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch category");
        return res.json();
      })
      .then((data) => setCategoryName(data.categoryName))
      .catch((err) => console.error("Error fetching category:", err));
  }, [id, navigate, location.pathname]);

  return (
    <div className="subcategory-section">
      <h2 className="subcategory-heading">{categoryName}</h2>
      <div className="subcategory-grid">
        {subcategories.map((sub) => (
          <div
            key={sub.subcategoryId}
            className="subcategory-card"
            style={{
              backgroundImage: `url(/images/subcategories/${sub.subcategoryImagePath || "default.png"})`,
            }}
          >
            <div className="overlay">
              <h3>{sub.subcategoryName}</h3>
              <Link to={`/subcategories/${sub.subcategoryId}/packages`}>
                <button className="explore-button">Explore</button>
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SubcategoryPage;
