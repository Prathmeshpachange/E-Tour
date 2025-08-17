import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../pagestyle/BookingSummaryPage.css";

function BookingSummaryPage() {
  const { state } = useLocation();
  const navigate = useNavigate();
  const { packageData, passengers } = state || {};

  if (!packageData || !passengers) return <p>No booking data.</p>;

  const basePrice = passengers.reduce((sum, p) => sum + Number(p.price), 0);
  const tax = basePrice * 0.05;
  const total = basePrice + tax;

  const getPaxTypeNumber = (type) => {
    switch (type) {
      case "TWIN_SHARING": return 0;
      case "SINGLE_PERSON": return 1;
      case "EXTRA_PERSON": return 2;
      case "CHILD_WITH_BED": return 3;
      case "CHILD_WITHOUT_BED": return 4;
      default: return 0;
    }
  };

  const confirmBooking = () => {
    const bookingData = {
      customerId: 1,
      packageId: packageData.packageId,
      departureId: 1,
      noOfPax: passengers.length,
      tourAmount: basePrice,
      taxes: tax,
      totalAmount: total,
      paymentStatus: "Pending",
      bookingDate: new Date().toISOString(),
      passengers: passengers.map(p => ({
        paxName: `${p.firstName} ${p.lastName}`,
        paxBirthdate: p.dob,
        paxType: getPaxTypeNumber(p.paxType),
        paxTypeId: getPaxTypeNumber(p.paxType),
        paxTypeName: p.paxType,
        paxAmount: p.price
      }))
    };

    fetch("http://localhost:5264/api/booking", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(bookingData)
    })
      .then(res => res.json())
      .then((data) => {
        if (data?.bookingId) {
          navigate("/payment", { state: { bookingId: data.bookingId } });
        } else {
          alert("Booking creation failed: No bookingId returned");
        }
      })
      .catch(err => console.error("Error creating booking:", err));
  };

  return (
    <div className="summary-container">
      <h2>Booking Summary</h2>
      <h3>{packageData.packageName}</h3>
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>DOB</th>
            <th>Mobile</th>
            <th>Email</th>
            <th>Passenger Type</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {passengers.map((p, i) => (
            <tr key={i}>
              <td>{i + 1}</td>
              <td>{p.firstName} {p.lastName}</td>
              <td>{p.dob}</td>
              <td>{p.mobile}</td>
              <td>{p.email}</td>
              <td>{p.paxType}</td>
              <td>₹{p.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="price-summary">
        <p>Base Price: ₹{basePrice}</p>
        <p>Tax (5%): ₹{tax}</p>
        <p><strong>Total Price: ₹{total}</strong></p>
      </div>
      <button onClick={confirmBooking}>Confirm Booking</button>
    </div>
  );
}

export default BookingSummaryPage;
