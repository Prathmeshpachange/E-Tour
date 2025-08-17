// import React from 'react';
// import { Link } from 'react-router-dom';
// import './Category.css';

// function CategoryCard({ category }) {
//   const { categoryId, categoryName, categoryImagePath } = category;

//   return (
//     <Link to={`/categories/${categoryId}`} className="category-card">
//       <img
//         src={`/images/${categoryImagePath}`}
//         alt={categoryName}
//         className="category-image"
//         onError={(e) => {
//           e.target.src = '/images/icons/default.png';
//         }}
//       />
//       <p className="category-title">{categoryName}</p>
//     </Link>
//   );
// }

// export default CategoryCard;






import React from 'react';
import './Category.css';

function CategoryCard({ category }) {
  const { categoryName, categoryImagePath } = category;

  return (
    <div className="category-card">
      <img
        src={`/images/${categoryImagePath}`}
        alt={categoryName}
        className="card-image"
        onError={(e) => (e.target.src = '/images/icons/default.png')}
      />
      <div className="card-overlay">
        <span className="card-title">{categoryName}</span>
      </div>
    </div>
  );
}

export default CategoryCard;
