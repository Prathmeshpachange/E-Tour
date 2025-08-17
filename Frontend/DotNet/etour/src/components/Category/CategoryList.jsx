// import React from 'react';
// import { useNavigate } from 'react-router-dom'; // You were missing this import
// import CategoryCard from './CategoryCard';
// import './Category.css';

// const CategoryList = ({ categories }) => {
//   const navigate = useNavigate();

//   const handleCategoryClick = (category) => {
//     if (category.flag) {
//       // Navigate directly to packages page
//       navigate(`/packages/category/${category.categoryId}`);
//     } else {
//       // Navigate to subcategory page
//       navigate(`/subcategory/${category.categoryId}`);
//     }
//   };

//   return (
//     <div className="category-list">
//       <h2>Explore Categories</h2>
//       <div className="category-grid">
//         {categories.map((cat) => (
//           <div key={cat.categoryId} onClick={() => handleCategoryClick(cat)}>
//             <CategoryCard category={cat} />
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default CategoryList;






import React from 'react';
import { useNavigate } from 'react-router-dom';
import CategoryCard from './CategoryCard';
import './Category.css';

const CategoryList = ({ categories }) => {
  const navigate = useNavigate();

  const handleCategoryClick = (category) => {
    if (category.flag) {
      navigate(`/packages/category/${category.categoryId}`);
    } else {
      navigate(`/subcategory/${category.categoryId}`);
    }
  };

  return (
    <div className="category-list-wrapper">
      <div className="marquee">
        <p>🧳 Explore the world with us | 🌍 International, Domestic, Festivals & More | ✈️ Book Your Dream Tour Today!</p>
      </div>

      <h2 className="category-heading">Explore Categories</h2>

      <div className="category-grid">
        {categories.map((cat) => (
          <div key={cat.categoryId} onClick={() => handleCategoryClick(cat)}>
            <CategoryCard category={cat} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoryList;
