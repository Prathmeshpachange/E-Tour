import React from "react";
import { Link } from "react-router-dom";
import { FaStar } from "react-icons/fa";
//import "./PackageCard.css";

function PackageCard({ pkg }) {
  return (
    <div className="package-card">
            <div>
              <img
              className="package-image"
              src={`/images/Packages/${pkg.packageImagePath}`}
              alt={pkg.packageName}
              onError={(e) => {
                e.target.src = "/images/default.png";
              }}
            />
            </div>
            
            <div className="package-content">
              <h3 className="package-title">{pkg.packageName}</h3>
              <p className="package-description">
                {pkg.packageInfo || "No description available"}
              </p>
              <p className="package-duration">
                Duration: {pkg.durationDays} days
              </p>
              <div className="package-footer">
                <span className="package-rating">
                  <FaStar className="star-icon" />
                  4.2
                </span>
                <Link to={`/packages/${pkg.packageId}`} className="explore-btn">
                  Explore
                </Link>
              </div>
            </div>
          </div>
  );
}

export default PackageCard;
