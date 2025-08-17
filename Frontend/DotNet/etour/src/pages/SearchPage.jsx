import React, { useState } from 'react';
import '../pagestyle/SearchPage.css';
import { Link } from 'react-router-dom';

const dummyTours = [
  {
    id: 1,
    tourCode: 'T001',
    startDate: '2025-08-10',
    endDate: '2025-08-20',
    cost: 25000,
    duration: 10
  },
  {
    id: 2,
    tourCode: 'T002',
    startDate: '2025-09-01',
    endDate: '2025-09-06',
    cost: 18000,
    duration: 5
  },
  {
    id: 3,
    tourCode: 'T003',
    startDate: '2025-08-15',
    endDate: '2025-08-25',
    cost: 32000,
    duration: 10
  }
];

function SearchPage() {
  const [period, setPeriod] = useState({ from: '', to: '' });
  const [costRange, setCostRange] = useState({ min: '', max: '' });
  const [durationRange, setDurationRange] = useState({ min: '', max: '' });
  const [results, setResults] = useState([]);

  const handleSearchByPeriod = () => {
    const filtered = dummyTours.filter(tour =>
      tour.startDate >= period.from &&
      tour.endDate <= period.to
    );
    setResults(filtered);
  };

  const handleSearchByCost = () => {
    const filtered = dummyTours.filter(tour =>
      tour.cost >= costRange.min &&
      tour.cost <= costRange.max
    );
    setResults(filtered);
  };

  const handleSearchByDuration = () => {
    const filtered = dummyTours.filter(tour =>
      tour.duration >= durationRange.min &&
      tour.duration <= durationRange.max
    );
    setResults(filtered);
  };

  return (
    <div className="search-container">
      <h2>Search Tours</h2>

      {/* Search by Period */}
      <div className="search-section">
        <h3>Search by Period</h3>
        <input
          type="date"
          value={period.from}
          onChange={(e) => setPeriod({ ...period, from: e.target.value })}
          placeholder="From Date"
        />
        <input
          type="date"
          value={period.to}
          onChange={(e) => setPeriod({ ...period, to: e.target.value })}
          placeholder="To Date"
        />
        <button onClick={handleSearchByPeriod}>Search</button>
      </div>

      {/* Search by Cost */}
      <div className="search-section">
        <h3>Search by Cost</h3>
        <input
          type="number"
          value={costRange.min}
          onChange={(e) => setCostRange({ ...costRange, min: e.target.value })}
          placeholder="Min Cost"
        />
        <input
          type="number"
          value={costRange.max}
          onChange={(e) => setCostRange({ ...costRange, max: e.target.value })}
          placeholder="Max Cost"
        />
        <button onClick={handleSearchByCost}>Search</button>
      </div>

      {/* Search by Duration */}
      <div className="search-section">
        <h3>Search by Duration</h3>
        <input
          type="number"
          value={durationRange.min}
          onChange={(e) => setDurationRange({ ...durationRange, min: e.target.value })}
          placeholder="Min Days"
        />
        <input
          type="number"
          value={durationRange.max}
          onChange={(e) => setDurationRange({ ...durationRange, max: e.target.value })}
          placeholder="Max Days"
        />
        <button onClick={handleSearchByDuration}>Search</button>
      </div>

      {/* Search Results */}
      <div className="search-results">
        <h3>Search Results</h3>
        {results.length === 0 ? (
          <p>No tours found.</p>
        ) : (
          results.map((tour) => (
            <div key={tour.id} className="result-card">
              <p><strong>Tour Code:</strong> {tour.tourCode}</p>
              <p><strong>Start Date:</strong> {tour.startDate}</p>
              <p><strong>End Date:</strong> {tour.endDate}</p>
              {tour.duration && <p><strong>Duration:</strong> {tour.duration} days</p>}
              <p><strong>Tour Cost:</strong> ₹{tour.cost}</p>
              <Link to={`/tours/${tour.id}`} className="details-link">Get Details</Link>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default SearchPage;
