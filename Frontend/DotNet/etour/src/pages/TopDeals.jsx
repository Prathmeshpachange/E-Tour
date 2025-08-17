import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../pagestyle/TopDeals.css';

const deals = [
  {
    id: 1,
    title: "Goa Beach Bonanza",
    description: "Enjoy 4 nights in Goa with beachside resorts and water sports.",
    price: "₹8,999",
    image: "/images/goa.jpg"
  },
  {
    id: 2,
    title: "Himalayan Retreat",
    description: "3 nights in Manali with trekking and hot springs.",
    price: "₹7,499",
    image: "/images/manali.jpg"
  },
  {
    id: 3,
    title: "Kerala Backwater Bliss",
    description: "Houseboat stay in Alleppey for 2 nights with meals.",
    price: "₹9,299",
    image: "/images/kerala.jpg"
  },
  {
    id: 4,
    title: "Golden Triangle Tour",
    description: "Delhi, Agra, Jaipur – 5 days with guides & hotel.",
    price: "₹10,999",
    image: "/images/golden-triangle.jpg"
  }
];

const TopDeals = () => {
  const navigate = useNavigate();

  const handleBooking = (id) => {
    navigate(`/booking/${id}`);
  };

  return (
    <div className="top-deals-wrapper">
      <h2 className="top-deals-title">🔥 Special Tour Offers</h2>
      <p className="top-deals-subtitle">Grab these exclusive deals before they're gone!</p>
      <div className="deals-grid">
        {deals.map(deal => (
          <div className="deal-card" key={deal.id}>
            <img src={deal.image} alt={deal.title} className="deal-image" />
            <div className="deal-content">
              <h3>{deal.title}</h3>
              <p>{deal.description}</p>
              <div className="deal-price">{deal.price}</div>
              <button className="book-now-btn" onClick={() => handleBooking(deal.id)}>Book Now</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TopDeals;
