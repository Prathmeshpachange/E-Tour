import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import PackageCard from "./PackageCard";
import "./PackageCard.css";

function PackageList() {
  const { subcategoryId } = useParams();
  const [packages, setPackages] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:5264/api/package/subcategory/${subcategoryId}`)
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch packages");
        return res.json();
      })
      .then((data) => setPackages(data))
      .catch((err) => console.error(err));
  }, [subcategoryId]);

  return (
    <div>
      <div className="avail-package">
        <h2>Available Packages</h2>
      </div>
      <div className="package-list">
        {packages.map((pkg) => (
          <PackageCard key={pkg.packageId} pkg={pkg} />
        ))}
      </div>
    </div>
  );
}

export default PackageList;
