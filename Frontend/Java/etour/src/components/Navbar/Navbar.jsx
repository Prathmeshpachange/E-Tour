import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [isLogin, setIsLogin] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");

  const toggleMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  const toggleAuth = () => {
    setIsLogin(!isLogin);
  };

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
    // Optional: Implement search logic here
  };

  return (
    <nav className="navbar">
      <div className="navbar-logo-text">
        <Link to="/" className="logo-link">
          <img src="/images/logo.jpg" alt="Logo" className="logo-img" />
          <span className="site-title">Safarnama</span>
        </Link>
      </div>

      {/* <div className="navbar-links-container">
        <Link to="/home" className="nav-link">Home</Link>
        <Link to="/about" className="nav-link">About</Link>
        <Link to="/tours" className="nav-link">Tours</Link>
        <Link to="/search" className="nav-link">Search</Link>
        <Link to="/top deals" className="nav-link">Top Deals</Link>
        <Link to="/download" className="nav-link">Download</Link>
        <Link to="/gallery" className="nav-link">Gallery</Link>
        <Link to="/contact" className="nav-link">Contact</Link>
      </div> */}

      <div className="navbar-links-container">
        <Link to="/home" className="nav-link">
          Home
        </Link>
        <Link to="/about" className="nav-link">
          About
        </Link>
        <Link to="/tours" className="nav-link">
          Tours
        </Link>
        <Link to="/search" className="nav-link">
          Search
        </Link>
        <Link to="/top-deals" className="nav-link">
          Top Deals
        </Link>{" "}
        {/* 👈 Updated */}
        <Link to="/download" className="nav-link">
          Download
        </Link>
        <Link to="/gallery" className="nav-link">
          Gallery
        </Link>
        <Link to="/contact" className="nav-link">
          Contact
        </Link>
      </div>

      <div className="navbar-right">
        <input
          type="text"
          className="search-bar"
          placeholder="Search category..."
          value={searchTerm}
          onChange={handleSearch}
        />
        <Link
          to={`/${isLogin ? "login" : "signup"}`}
          className="auth-button"
          onClick={toggleAuth}
        >
          {isLogin ? "Login" : "Signup"}
        </Link>
        <div className="hamburger" onClick={toggleMenu}>
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>

      {/* Mobile Menu */}
      {isMobileMenuOpen && (
        <div className="mobile-menu">
          <Link to="/home" className="nav-link">
            Home
          </Link>
          <Link to="/about" className="nav-link">
            About
          </Link>
          <Link to="/tours" className="nav-link">
            Tours
          </Link>
          <Link to="/search" className="nav-link">
            Search
          </Link>
          <Link to="/bid" className="nav-link">
            Bid
          </Link>
          <Link to="/download" className="nav-link">
            Download
          </Link>
          <Link to="/gallery" className="nav-link">
            Gallery
          </Link>
          <Link to="/contact" className="nav-link">
            Contact
          </Link>
        </div>
      )}
    </nav>
  );
}

export default Navbar;
