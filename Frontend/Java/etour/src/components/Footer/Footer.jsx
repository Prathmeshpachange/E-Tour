import React from 'react';
import {
  FaFacebookF,
  FaInstagram,
  FaTwitter,
  FaWhatsapp,
  FaLinkedinIn,
  FaPhoneAlt,
  FaEnvelope,
  FaMapMarkerAlt,
} from 'react-icons/fa';
import './Footer.css';

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">

        <div className="footer-top">
          {/* Address Section */}
          <div className="footer-section address-section">
            <h4>Contact Us</h4>
            <p><FaMapMarkerAlt /> 123 Tour Lane, Safar Nagar, Mumbai, India</p>
            <p><FaPhoneAlt /> +91-9876543210</p>
            <p><FaEnvelope /> support@safarnama.com</p>
          </div>

          {/* Quick Links */}
          <div className="footer-section">
            <h4>Quick Links</h4>
            <ul>
              <li><a href="/about">About Us</a></li>
              <li><a href="/contact">Contact</a></li>
              <li><a href="/help">Help</a></li>
              <li><a href="/faq">FAQs</a></li>
              <li><a href="/careers">Careers</a></li>
            </ul>
          </div>

          {/* Legal Section */}
          <div className="footer-section">
            <h4>Legal</h4>
            <ul>
              <li><a href="/terms">Terms</a></li>
              <li><a href="/privacy">Privacy</a></li>
              <li><a href="/refund">Refund</a></li>
            </ul>
          </div>

          {/* Social Section */}
          <div className="footer-section">
            <h4>Follow Us</h4>
            <div className="social-icons">
              <a href="https://facebook.com" target="_blank" rel="noreferrer"><FaFacebookF /></a>
              <a href="https://instagram.com" target="_blank" rel="noreferrer"><FaInstagram /></a>
              <a href="https://twitter.com" target="_blank" rel="noreferrer"><FaTwitter /></a>
              <a href="https://wa.me/919876543210" target="_blank" rel="noreferrer"><FaWhatsapp /></a>
              <a href="https://linkedin.com" target="_blank" rel="noreferrer"><FaLinkedinIn /></a>
            </div>
          </div>
        </div>

        <div className="footer-bottom">
          <p>© {new Date().getFullYear()} Safarnama. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
