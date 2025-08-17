import React from 'react';
import '../pagestyle/AboutPage.css';

function AboutPage() {
  return (
    <div className="about-page">
      <header className="about-header">
        <h1>About Safarnama</h1>
        <p>Your Gateway to Memorable Journeys</p>
      </header>

      <section className="about-section">
        <div className="about-text">
          <h2>Our Vision</h2>
          <p>
            Safarnama (TourIndia) is your one-stop travel platform that offers domestic and international tours,
            specialized couple tours, event-based packages, and adventure travel across India and beyond.
            From booking to exploration – we provide a seamless experience enhanced with multimedia showcases,
            real-time search, and detailed itineraries.
          </p>
        </div>
        <div className="about-image">
          <img src="/images/vision.jpg" alt="Vision" />
        </div>
      </section>

      <section className="about-section reverse">
        <div className="about-image">
          <img src="/images/showcase.jpg" alt="Showcase Page" />
        </div>
        <div className="about-text">
          <h2>Immersive Showcase Experience</h2>
          <p>
            Our journey starts with a visually rich Showcase Page that features advertisements and campaigns in audio-visual,
            image, or PDF formats. Users can skip and proceed to the home page, giving them the power to explore at their pace.
          </p>
        </div>
      </section>

      <section className="about-section">
        <div className="about-text">
          <h2>Easy Navigation & Smart Search</h2>
          <p>
            With intuitive navigation and a dynamic top menu, users can explore different tours based on cost, duration,
            and preferred travel periods. Smart search capabilities ensure users find the perfect tour match efficiently.
          </p>
        </div>
        <div className="about-image">
          <img src="/images/search.jpg" alt="Search Functionality" />
        </div>
      </section>

      <section className="about-section reverse">
        <div className="about-image">
          <img src="/images/tour-page.jpg" alt="Tour Details" />
        </div>
        <div className="about-text">
          <h2>Tour Details & Booking</h2>
          <p>
            Detailed tour pages provide itinerary, cost, stay, maps, weather, visa requirements, and media galleries.
            Users can easily book tours, add fellow travelers, view real-time pricing, and securely make payments through the integrated gateway.
          </p>
        </div>
      </section>

      <section className="about-conclusion">
        <h2>Why Choose Safarnama?</h2>
        <ul>
          <li>📺 Multimedia-rich tour presentation</li>
          <li>🔎 Advanced tour search & filters</li>
          <li>🧭 Seamless navigation across sectors & sub-sectors</li>
          <li>💳 Secure tour booking with payment & PDF receipts</li>
          <li>🌍 Domestic, International, Couple & Event Tours</li>
        </ul>
      </section>
    </div>
  );
}

export default AboutPage;
