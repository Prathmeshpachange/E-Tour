import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../pagestyle/BookingPage.css";

function BookingPage() {
  const { packageId } = useParams();
  const navigate = useNavigate();

  const [packageData, setPackageData] = useState(null);
  const [costData, setCostData] = useState(null);
  const [passengers, setPassengers] = useState([
    { firstName: "", lastName: "", mobile: "", email: "", dob: "", paxType: "", price: 0 }
  ]);
  const [errors, setErrors] = useState([{}]); // parallel array to store passenger form errors

  useEffect(() => {
    fetch(`http://localhost:5264/api/package/${packageId}`)
      .then(res => res.json())
      .then(data => setPackageData(data))
      .catch(err => console.error(err));

    fetch(`http://localhost:5264/api/cost/package/${packageId}`)
      .then(res => res.json())
      .then(data => {
        setCostData({
          singlePersonCost: data.singlePersonCost ?? 0,
          extraPersonCost: data.extraPersonCost ?? 0,
          childWithBed: data.childWithBed ?? 0,
          childWithoutBed: data.childWithoutBed ?? 0
        });
      })
      .catch(err => console.error(err));
  }, [packageId]);

  // Validation Function
  const validatePassenger = (pax) => {
    const newErrors = {};

    if (!pax.firstName.trim() || pax.firstName.length < 2 || !/^[A-Za-z]+$/.test(pax.firstName)) {
      newErrors.firstName = "First name must be at least 2 letters.";
    }

    if (!pax.lastName.trim() || pax.lastName.length < 2 || !/^[A-Za-z]+$/.test(pax.lastName)) {
      newErrors.lastName = "Last name must be at least 2 letters.";
    }

    if (!/^\d{10}$/.test(pax.mobile)) {
      newErrors.mobile = "Mobile number must be exactly 10 digits.";
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(pax.email)) {
      newErrors.email = "Enter a valid email address.";
    }

    if (!pax.dob) {
      newErrors.dob = "Date of Birth is required.";
    } else {
      const age = (new Date().getFullYear()) - (new Date(pax.dob).getFullYear());
      if (age < 2) {
        newErrors.dob = "Passenger must be at least 2 years old.";
      }
    }

    if (!pax.paxType) {
      newErrors.paxType = "Select a passenger type.";
    }

    return newErrors;
  };

  const handleChange = (index, e) => {
    const { name, value } = e.target;
    const updatedPassengers = [...passengers];
    updatedPassengers[index][name] = value;

    // Update price if paxType changes
    if (name === "paxType" && costData) {
      let price = 0;
      switch (value) {
        case "SINGLE_PERSON":
          price = costData.singlePersonCost;
          break;
        case "EXTRA_PERSON":
          price = costData.extraPersonCost;
          break;
        case "CHILD_WITH_BED":
          price = costData.childWithBed;
          break;
        case "CHILD_WITHOUT_BED":
          price = costData.childWithoutBed;
          break;
        case "TWIN_SHARING":
          price = (costData.singlePersonCost || 0) * 2;
          break;
        default:
          price = 0;
      }
      updatedPassengers[index].price = price;
    }

    setPassengers(updatedPassengers);

    // live validation for that passenger
    const updatedErrors = [...errors];
    updatedErrors[index] = validatePassenger(updatedPassengers[index]);
    setErrors(updatedErrors);
  };

  const addPassenger = () => {
    setPassengers([...passengers, { firstName: "", lastName: "", mobile: "", email: "", dob: "", paxType: "", price: 0 }]);
    setErrors([...errors, {}]);
  };

  const removePassenger = (index) => {
    setPassengers(passengers.filter((_, i) => i !== index));
    setErrors(errors.filter((_, i) => i !== index));
  };

  const proceedToSummary = () => {
    const validationResults = passengers.map(validatePassenger);

    // Check for any field errors
    const hasErrors = validationResults.some(err => Object.keys(err).length > 0);
    setErrors(validationResults);

    if (hasErrors) {
      alert("Please correct the errors before proceeding.");
      return;
    }

    navigate("/booking-summary", { state: { packageData, passengers } });
  };

  if (!packageData || !costData) return <p>Loading...</p>;

  return (
    <div className="booking-container">
      <div className="trip-info">
        <p className="error-text">
          Days: {packageData.durationDays} | Dates: {packageData.startDate} - {packageData.endDate}
        </p>
      </div>

      <h2 className="form-heading">Passenger Details</h2>
      {passengers.map((pax, index) => (
        <div className="passenger-card" key={index}>
          <div className="passenger-fields">
            <input
              name="firstName"
              placeholder="First Name"
              value={pax.firstName}
              onChange={(e) => handleChange(index, e)}
            />
            {errors[index]?.firstName && <p className="error-text">{errors[index].firstName}</p>}

            <input
              name="lastName"
              placeholder="Last Name"
              value={pax.lastName}
              onChange={(e) => handleChange(index, e)}
            />
            {errors[index]?.lastName && <p className="error-text">{errors[index].lastName}</p>}

            <input
              name="mobile"
              placeholder="Mobile Number"
              value={pax.mobile}
              onChange={(e) => handleChange(index, e)}
            />
            {errors[index]?.mobile && <p className="error-text">{errors[index].mobile}</p>}

            <input
              name="email"
              placeholder="Email"
              value={pax.email}
              onChange={(e) => handleChange(index, e)}
            />
            {errors[index]?.email && <p className="error-text">{errors[index].email}</p>}

            <input
              type="date"
              name="dob"
              value={pax.dob}
              onChange={(e) => handleChange(index, e)}
            />
            {errors[index]?.dob && <p className="error-text">{errors[index].dob}</p>}

            <select
              name="paxType"
              value={pax.paxType}
              onChange={(e) => handleChange(index, e)}
            >
              <option value="">Choose</option>
              <option value="TWIN_SHARING">Twin Sharing ({(costData.singlePersonCost || 0) * 2} ₹ for 2)</option>
              <option value="SINGLE_PERSON">Single Person ({costData.singlePersonCost} ₹)</option>
              <option value="EXTRA_PERSON">Extra Person ({costData.extraPersonCost} ₹)</option>
              <option value="CHILD_WITH_BED">Child With Bed ({costData.childWithBed} ₹)</option>
              <option value="CHILD_WITHOUT_BED">Child Without Bed ({costData.childWithoutBed} ₹)</option>
            </select>
            {errors[index]?.paxType && <p className="error-text">{errors[index].paxType}</p>}

            <input type="number" value={pax.price} readOnly />
            {passengers.length > 1 && (
              <button type="button" className="remove-btn" onClick={() => removePassenger(index)}>✕</button>
            )}
          </div>
        </div>
      ))}

      <div className="form-buttons">
        <button type="button" onClick={addPassenger}>+ Add Passenger</button>
        <button type="button" onClick={proceedToSummary}>Proceed to Summary</button>
      </div>
    </div>
  );
}

export default BookingPage;