import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PackageCard from '../components/Package/PackageCard';


const PackageListByCategory = () => {
  const { categoryId } = useParams();
  const [packages, setPackages] = useState([]);

useEffect(() => {
  fetch(`http://localhost:8081/api/categories/${categoryId}/packages`)
    .then((res) => res.json())
    .then((data) => setPackages(data))
    .catch((err) => console.error('Error fetching packages:', err));
}, [categoryId]);
console.log("Packages:", packages); 
console.log("categoryId:", categoryId);
  return (
    <div>
      <h2>Packages for Special Category</h2>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {packages.map((pkg) => (
          <PackageCard key={pkg.packageId} pkg={pkg} />
        ))}
      </div>
    </div>
  );
};

export default PackageListByCategory;
