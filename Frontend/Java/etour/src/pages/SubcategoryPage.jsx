// import React, { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
// import { Link } from "react-router-dom";
// import "../pagestyle/SubcategoryPage.css"; // Import the CSS file

// const SubcategoryPage = () => {
//   const { id } = useParams();
//   const [subcategories, setSubcategories] = useState([]);
//   const [categoryName, setCategoryName] = useState("");

//   useEffect(() => {
//     fetch(`http://localhost:8081/api/subcategories/category/${id}`)
//       .then((res) => res.json())
//       .then((data) => setSubcategories(data))
//       .catch((err) => console.error(err));
//     fetch(`http://localhost:8081/api/categories/${id}`)
//       .then((res) => res.json())
//       .then((data) => setCategoryName(data.categoryName))
//       .catch((err) => console.error(err));
//   }, [id]);
//   useEffect(() => {
//     console.log(subcategories);
//   });
//   return (
//     <div className="subcategory-page">
//       <h2>Subcategories of {categoryName}</h2>
//       <div className="subcategory-list">
//         {subcategories.map((sub) => (
//           <div className="subcategory-card" key={sub.subcategoryId}>
//             <div className="card-image-container">
//               <img
//                 src={`/images/subcategories/${sub.subcategoryImagePath}`}
//                 className="card-image"
//                 onError={(e) =>
//                   (e.target.src = "/images/subcategories/default.png")
//                 }
//               />
//             </div>

//             <div className="card-content">
//               <h3 className="card-title">{sub.subcategoryName}</h3>
//               {/* <p className="card-description">
//                 {sub.description || "No description available"}
//               </p> */}

//               <div className="card-footer">
//                 {/* <div className="rating">
//                   <span className="rating-star">★</span>
//                   <span>4.2</span>
//                 </div> */}
//                 <Link to={`/subcategories/${sub.subcategoryId}/packages`}>
//                   <button className="add-to-cart-btn">Explore</button>
//                 </Link>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default SubcategoryPage;






// import React, { useEffect, useState } from "react";
// import { useParams, Link } from "react-router-dom";
// import "../pagestyle/SubcategoryPage.css";

// const SubcategoryPage = () => {
//   const { id } = useParams();
//   const [subcategories, setSubcategories] = useState([]);
//   const [categoryName, setCategoryName] = useState("");

//   useEffect(() => {
//     fetch(`http://localhost:8081/api/subcategories/category/${id}`)
//       .then((res) => res.json())
//       .then((data) => setSubcategories(data))
//       .catch((err) => console.error(err));

//     fetch(`http://localhost:8081/api/categories/${id}`)
//       .then((res) => res.json())
//       .then((data) => setCategoryName(data.categoryName))
//       .catch((err) => console.error(err));
//   }, [id]);

//   return (
//     <div className="subcategory-section">
//       <h2 className="subcategory-heading">Subcategories of {categoryName}</h2>
//       <div className="subcategory-grid">
//         {subcategories.map((sub) => (
//           <div
//             key={sub.subcategoryId}
//             className="subcategory-card"
//             style={{
//               backgroundImage: `url(/images/subcategories/${sub.subcategoryImagePath || 'default.png'})`,
//             }}
//           >
//             <div className="overlay">
//               <h3>{sub.subcategoryName}</h3>
//               <Link to={`/subcategories/${sub.subcategoryId}/packages`}>
//                 <button className="explore-button">Explore</button>
//               </Link>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default SubcategoryPage;








import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "../pagestyle/SubcategoryPage.css";

const SubcategoryPage = () => {
  const { id } = useParams();
  const [subcategories, setSubcategories] = useState([]);
  const [categoryName, setCategoryName] = useState("");

  useEffect(() => {
    fetch(`http://localhost:8081/api/subcategories/category/${id}`)
      .then((res) => res.json())
      .then((data) => setSubcategories(data))
      .catch((err) => console.error(err));

    fetch(`http://localhost:8081/api/categories/${id}`)
      .then((res) => res.json())
      .then((data) => setCategoryName(data.categoryName))
      .catch((err) => console.error(err));
  }, [id]);

  return (
    <div className="subcategory-section">
      <h2 className="subcategory-heading">{categoryName}</h2>
      <div className="subcategory-grid">
        {subcategories.map((sub) => (
          <div
            key={sub.subcategoryId}
            className="subcategory-card"
            style={{
              backgroundImage: `url(/images/subcategories/${sub.subcategoryImagePath || 'default.png'})`,
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
