import React from "react";
import "../pagestyle/ContactPage.css";

function ContactPage() {
  return (
    <div className="contact-container">
      <div className="contact-left">
        <h2>Get in Touch</h2>
        <p>We’d love to hear from you! Please fill out the form below.</p>
        <form className="contact-form">
          <input type="text" placeholder="Your Name" required />
          <input type="email" placeholder="Your Email" required />
          <input type="tel" placeholder="Mobile Number" required />
          <input type="text" placeholder="Subject" required />
          <textarea placeholder="Your Message" rows="5" required></textarea>
          <button type="submit">Send Message</button>
        </form>
      </div>
      <div className="contact-right">
        <h3>Contact Information</h3>
        <p><strong>Email:</strong> info@etour.com</p>
        <p><strong>Phone:</strong> +91 98765 43210</p>
        <p><strong>Location:</strong> Mumbai, India</p>
        <div className="map-container">
          <iframe
            title="Google Map"
            src="https://maps.google.com/maps?q=Mumbai&t=&z=13&ie=UTF8&iwloc=&output=embed"
            frameBorder="0"
            allowFullScreen
          ></iframe>
        </div>
      </div>
    </div>
  );
}

export default ContactPage;
