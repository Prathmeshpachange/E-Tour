import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
// import './SubcategoryList.css';

function SubcategoryList() {
  const { categoryId } = useParams();
  const [subcategories, setSubcategories] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8081/api/subcategories/category/${categoryId}`)
      .then((res) => res.json())
      .then((data) => setSubcategories(data))
      .catch((err) => console.error('Error fetching subcategories:', err));
  }, [categoryId]);

  return (
    <div className="subcategory-container">
      <h2 className="subcategory-heading">Subcategories</h2>
      <div className="subcategory-grid">
        {subcategories.map((sub) => (
          <Link
            to={`/subcategories/${sub.subcategoryId}/packages`}
            key={sub.subcategoryId}
            className="subcategory-card"
          >
            <img
              src={`/images/${sub.subcategoryImagePath || 'default.png'}`}
              alt={sub.subcategoryName}
              onError={(e) => {
                e.target.onerror = null;
                e.target.src = '/images/Kashmir.jpeg';
              }}
            />
            <p>{sub.subcategoryName}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}

export default SubcategoryList;
