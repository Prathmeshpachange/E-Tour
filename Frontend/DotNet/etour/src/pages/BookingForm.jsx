// // BookingForm.jsx (Enhanced and connected to backend using fetch)
// import React, { useState, useEffect } from 'react';
// import '../pagestyle/BookingForm.css';

// function BookingForm({ packageId, departureId, onClose }) {
//   const [formData, setFormData] = useState({
//     name: '',
//     dob: '',
//     gender: '',
//     email: '',
//     phone: '',
//     address: '',
//     city: '',
//     state: '',
//     pincode: '',
//     bookingType: '',
//     numberOfPeople: 1,
//   });
//   const [bookingType, setBookingType] = useState('');

//   // Fetch Package Info (if needed)
//   useEffect(() => {
//     if (packageId) {
//       fetch(`http://localhost:8081/api/packages/${packageId}`)
//         .then(res => res.json())
//         .then(data => console.log('Package Info:', data))
//         .catch(err => console.error('Error fetching package:', err));
//     }
//   }, [packageId]);

//   const handleFormChange = (e) => {
//     const { name, value } = e.target;
//     setFormData(prev => ({ ...prev, [name]: value }));
//   };

//   const handleBookingTypeChange = (e) => {
//     const selected = e.target.value;
//     setBookingType(selected);
//     setFormData(prev => ({ ...prev, bookingType: selected }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     // 1. Save customer info
//     const customerRes = await fetch('http://localhost:8081/api/customers', {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({
//         cust_name: formData.name,
//         cust_email: formData.email,
//         cust_phone: formData.phone,
//         cust_dob: formData.dob,
//         cust_gender: formData.gender,
//         cust_address: formData.address,
//         cust_city: formData.city,
//         cust_state: formData.state,
//         cust_pincode: formData.pincode
//       })
//     });

//     const customer = await customerRes.json();
//     const cust_id = customer.cust_id;

//     // 2. Create booking
//     const bookingRes = await fetch('http://localhost:8081/api/bookings', {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({
//         cust_id,
//         package_id: packageId,
//         departure_id: departureId,
//         no_of_pax: formData.numberOfPeople,
//         tour_amount: 0,
//         taxes: 0,
//         total_amount: 0,
//         payment_status: 'Pending'
//       })
//     });

//     const booking = await bookingRes.json();
//     const booking_id = booking.booking_id;

//     // 3. Add passenger(s)
//     await fetch('http://localhost:8081/api/passengers', {
//       method: 'POST',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify({
//         booking_id,
//         pax_name: formData.name,
//         pax_birthdate: formData.dob,
//         pax_type: formData.bookingType,
//         pax_amount: 0
//       })
//     });

//     alert("Booking submitted successfully!");
//     onClose();
//   };

//   return (
//     <div className="booking-form-overlay">
//       <div className="booking-form-modal">
//         <button className="close-btn" onClick={onClose}>✖</button>
//         <h3>📝 Booking Form</h3>
//         <form onSubmit={handleSubmit}>
//           <label>Name:
//             <input type="text" name="name" value={formData.name} onChange={handleFormChange} required />
//           </label>
//           <label>Email:
//             <input type="email" name="email" value={formData.email} onChange={handleFormChange} required />
//           </label>
//           <label>Phone:
//             <input type="tel" name="phone" value={formData.phone} onChange={handleFormChange} required />
//           </label>
//           <label>Date of Birth:
//             <input type="date" name="dob" value={formData.dob} onChange={handleFormChange} required />
//           </label>
//           <label>Gender:
//             <select name="gender" value={formData.gender} onChange={handleFormChange} required>
//               <option value="">Select Gender</option>
//               <option value="M">Male</option>
//               <option value="F">Female</option>
//               <option value="O">Other</option>
//             </select>
//           </label>
//           <label>Address:
//             <input type="text" name="address" value={formData.address} onChange={handleFormChange} required />
//           </label>
//           <label>City:
//             <input type="text" name="city" value={formData.city} onChange={handleFormChange} required />
//           </label>
//           <label>State:
//             <input type="text" name="state" value={formData.state} onChange={handleFormChange} required />
//           </label>
//           <label>Pincode:
//             <input type="text" name="pincode" value={formData.pincode} onChange={handleFormChange} required />
//           </label>
//           <label>Booking Type:
//             <select name="bookingType" value={formData.bookingType} onChange={handleBookingTypeChange} required>
//               <option value="">Select Booking Type</option>
//               <option value="Single">Single Person</option>
//               <option value="Twin">Twin Sharing</option>
//               <option value="ChildBed">Child with Bed</option>
//               <option value="Extra">Extra Person</option>
//             </select>
//           </label>

//           {bookingType && (
//             <>
//               <label>No. of People:
//                 <input type="number" name="numberOfPeople" min="1" value={formData.numberOfPeople} onChange={handleFormChange} required />
//               </label>
//               {bookingType === 'Twin' && <p className="note">You selected Twin Sharing. Ensure even number of participants.</p>}
//               {bookingType === 'ChildBed' && <p className="note">Child age should be under 12.</p>}
//             </>
//           )}

//           <button type="submit" className="submit-booking">Submit Booking</button>
//         </form>
//       </div>
//     </div>
//   );
// }

// export default BookingForm;
