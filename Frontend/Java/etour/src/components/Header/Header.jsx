import React from "react";
import "./Header.css";

const images = [
  "/images/header/slide1.jpg",
  "/images/header/slide2.jpg",
  "/images/header/slide3.jpg",
];

export default function Header() {
  return (
    <div className="header">
      <div className="carousel">
        {images.map((src, index) => (
          <div className="carousel-image" key={index}>
            <img src={src} alt={`carousel ${index}`} />
          </div>
        ))}
      </div>
    </div>
  );
}
