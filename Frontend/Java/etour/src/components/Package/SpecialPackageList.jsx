// components/Package/SpecialPackageList.js
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PackageCard from './PackageCard';

const SpecialPackageList = () => {
  const { categoryId } = useParams();
  const [packages, setPackages] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8081/api/categories/${categoryId}/packages`)
      .then((res) => res.json())
      .then((data) => setPackages(data))
      .catch((err) => console.error('Error fetching packages:', err));
  }, [categoryId]);

  return (
    <div className="special-packages">
      <h2>Packages for this Category</h2>
      <div className="package-list">
        {packages.map((pkg) => (
          <PackageCard key={pkg.packageId} tourPackage={pkg} />
        ))}
      </div>
    </div>
  );
};

export default SpecialPackageList;
