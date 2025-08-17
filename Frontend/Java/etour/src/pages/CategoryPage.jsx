import React, { useEffect, useState } from 'react';
import CategoryList from '../components/Category/CategoryList';
import '../pagestyle/CategoryPage.css';

const CategoryPage = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8081/api/categories')
      .then((res) => res.json())
      .then((data) => setCategories(data))
      .catch((err) => console.error('Error fetching categories:', err));
  }, []);

  return (
    <div className="category-page">
      <CategoryList categories={categories} />
    </div>
  );
};

export default CategoryPage;
