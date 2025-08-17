import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../pagestyle/Showcase.css';

const slides = [
  {
    title: 'International Tours',
    description: 'Explore the world with exclusive international packages!',
    image: '/images/international.jpg',
  },
  {
    title: 'Domestic Tours',
    description: 'Discover hidden gems across your own country.',
    image: '/images/domestic.jpg',
  },
  {
    title: 'Festival Tours',
    description: 'Celebrate vibrant festivals across regions.',
    image: '/images/festival.jpg',
  },
  {
    title: 'Theme Based Tours',
    description: 'Adventure, culture, wildlife, and more—choose your theme!',
    image: '/images/theme.jpg',
  },
  {
    title: 'Seasonal Tours',
    description: 'Best destinations for every season.',
    image: '/images/seasonal.jpg',
  },
];

const ShowcasePage = () => {
  const navigate = useNavigate();
  const [currentSlide, setCurrentSlide] = useState(0);

  const handleSkip = () => navigate('/home');

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentSlide((prev) => (prev + 1) % slides.length);
    }, 4000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="showcase-container">
      <div className="carousel-wrapper">
        {slides.map((slide, index) => (
          <div
            className={`carousel-slide ${index === currentSlide ? 'active' : ''}`}
            key={index}
            style={{ backgroundImage: `url(${slide.image})` }}
          >
            <div className="carousel-overlay">
              <h2 className="carousel-title">{slide.title}</h2>
              <p className="carousel-description">{slide.description}</p>
              <button className="explore-btn" onClick={handleSkip}>
                Explore More
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ShowcasePage;
