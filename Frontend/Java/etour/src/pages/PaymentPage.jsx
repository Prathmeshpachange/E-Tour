import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import jsPDF from "jspdf";
import autoTable from "jspdf-autotable"; // ✅ Correctly imported
import "../pagestyle/PaymentPage.css";

function PaymentPage() {
  const { state } = useLocation();
  const navigate = useNavigate();
  const bookingId = state?.bookingId;

  const [cardNumber, setCardNumber] = useState("");
  const [expiry, setExpiry] = useState("");
  const [cvv, setCvv] = useState("");
  const [cardName, setCardName] = useState("");

  const handlePayment = () => {
    if (!bookingId) {
      Swal.fire("Error", "No booking found. Please try again.", "error");
      return;
    }

    fetch(`http://localhost:8081/api/bookings/${bookingId}/payment?status=Paid`, {
      method: "PUT"
    })
      .then(res => {
        if (res.ok) {
          Swal.fire({
            icon: "success",
            title: "Congratulations!",
            text: "Your payment was successful!",
            confirmButtonText: "Download Confirmation"
          }).then(() => {
            downloadPdf();
            navigate("/");
          });
        } else {
          Swal.fire("Error", "Payment update failed!", "error");
        }
      })
      .catch(err => {
        console.error("Payment error:", err);
        Swal.fire("Error", "Something went wrong!", "error");
      });
  };

  const downloadPdf = () => {
    const doc = new jsPDF();

    doc.setFontSize(18);
    doc.text("E-Tour Booking Confirmation", 60, 20);

    doc.setFontSize(12);
    doc.text("✅ Booking Details:", 10, 35);
    doc.text(`Booking ID: ${bookingId}`, 10, 43);
    doc.text(`Payment Status: Paid`, 10, 51);
    doc.text(`Cardholder: ${cardName}`, 10, 59);
    doc.text(`Card Number: **** **** **** ${cardNumber.slice(-4)}`, 10, 67);
    doc.text(`Expiry: ${expiry}`, 10, 75);
    doc.text(`Date: ${new Date().toLocaleString()}`, 10, 83);

    doc.text("📌 Note: This is your official confirmation. Please save this PDF.", 10, 95);

    autoTable(doc, {
      startY: 105,
      head: [["Field", "Value"]],
      body: [
        ["Booking ID", bookingId],
        ["Cardholder", cardName],
        ["Card Number", "**** **** **** " + cardNumber.slice(-4)],
        ["Expiry", expiry],
        ["CVV", "***"],
        ["Payment Status", "Paid"],
        ["Downloaded On", new Date().toLocaleString()]
      ]
    });

    doc.save("E-Tour_Booking_Confirmation.pdf");
  };

  return (
    <div className="payment-container">
      <h2>Payment Details</h2>
      <div className="payment-form">
        <input
          type="text"
          placeholder="Card Number"
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />
        <input
          type="text"
          placeholder="Expiry Date (MM/YY)"
          value={expiry}
          onChange={(e) => setExpiry(e.target.value)}
        />
        <input
          type="password"
          placeholder="CVV"
          value={cvv}
          onChange={(e) => setCvv(e.target.value)}
        />
        <input
          type="text"
          placeholder="Cardholder Name"
          value={cardName}
          onChange={(e) => setCardName(e.target.value)}
        />
        <button onClick={handlePayment}>Pay Now</button>
      </div>
    </div>
  );
}

export default PaymentPage;













