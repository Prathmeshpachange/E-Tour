import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import '../pagestyle/PackageDetailPage.css';

function PackageDetailPage() {
  const { packageId } = useParams();
  const [packageData, setPackageData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://localhost:5264/api/package/${packageId}`)
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch package");
        return res.json();
      })
      .then(data => setPackageData(data))
      .catch(err => console.error(err));
  }, [packageId]);

  if (!packageData) {
    return <div>Loading...</div>;
  }

  const {
    packageName,
    packageInfo,
    durationDays,
    startDate,
    endDate,
    packageImagePath,
    itineraries,
    costs,
    departures
  } = packageData;

  const handleBook = () => {
    navigate(`/booking/${packageId}`, { state: { packageData } });
  };

  return (
    <div className="package-detail">
      <h2>{packageName}</h2>
      <div className='package-detail-image'>
         <img
        src={`/images/Packages/${packageImagePath}`}
        alt={packageName}
        className="package-image"
        onError={(e) => (e.target.src = '/images/icons/default.png')}
      />
      </div>
     
      <p>{packageInfo}</p>
      <p><strong>Duration:</strong> {durationDays} Days</p>
      <p><strong>Start:</strong> {startDate} | <strong>End:</strong> {endDate}</p>

      {/* <div className="section">
        <h3>🧭 Itinerary</h3>
        <ul>
          {itineraries.map((it) => (
            <li key={it.itineraryId}>
              <strong>Day {it.dayNo}:</strong> {it.dayDetail}
            </li>
          ))}
        </ul>
      </div> */}

      <div className="section itinerary-section">
  <h3>🧭 Itinerary</h3>
  <div className="itinerary-grid">
    {itineraries.map((it) => (
      <div className="itinerary-day-card" key={it.itineraryId}>
        <div className="itinerary-day-header">Day {it.dayNo}</div>
        <div className="itinerary-day-body">{it.dayDetail}</div>
      </div>
    ))}
  </div>
</div>



      <div className="section">
        <h3>💰 Cost Details</h3>
        {costs.map((cost, index) => (
          <div key={cost.costId || index}>
            <p>Single Person: ₹{cost.singlePersonCost}</p>
            <p>Extra Person: ₹{cost.extraPersonCost}</p>
            <p>Child With Bed: ₹{cost.childWithBed}</p>
            <p>Child Without Bed: ₹{cost.childWithoutBed}</p>
            <p><small>Valid From: {cost.validFrom} to {cost.validTo}</small></p>
            <hr />
          </div>
        ))}
      </div>

      <div className="section">
        <h3>🚍 Departure Dates</h3>
        <ul>
          {departures.map(dep => (
            <li key={dep.departureId}>
              {dep.departureDate} → {dep.endDate} ({dep.noOfDays} days)
            </li>
          ))}
        </ul>
      </div>

      <button className="book-btn" onClick={handleBook}>
        Book Package
      </button>
    </div>
  );
}

export default PackageDetailPage;
