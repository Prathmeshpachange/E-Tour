// src/pages/LoginSignupPage.jsx
import React, { useState } from 'react';
import '../pagestyle/LoginSignupPage.css';

function LoginSignupPage() {
  const [isLogin, setIsLogin] = useState(true);

  // Use backend DTO field names
  const [signupData, setSignupData] = useState({
    name: '',
    email: '',
    phone: '',
    dob: '',
    gender: 'M',
    address: '',
    city: '',
    state: '',
    pincode: '',
    password: ''
  });

  const [loginData, setLoginData] = useState({
    email: '',
    password: ''
  });

  const toggleForm = () => setIsLogin(!isLogin);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSignupData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSignupSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8081/api/auth/signup', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(signupData),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to register user');
      }

      const result = await response.json();
      alert('Signup successful!');
      console.log('Signup successful:', result);

      setSignupData({
        name: '',
        email: '',
        phone: '',
        dob: '',
        gender: 'M',
        address: '',
        city: '',
        state: '',
        pincode: '',
        password: ''
      });

      setIsLogin(true); // switch to login form
    } catch (error) {
      alert('Signup failed: ' + error.message);
      console.error('Signup error:', error);
    }
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8081/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          email: loginData.email,
          password: loginData.password
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Login failed');
      }

      const result = await response.json();
      // Store JWT token in localStorage
      localStorage.setItem('token', result.token);
      alert('Login successful!');
      // Redirect or update UI as needed
    } catch (error) {
      alert('Login failed: ' + error.message);
      console.error('Login error:', error);
    }
  };

  return (
    <div className="auth-wrapper">
      <div className="auth-container">
        <div className="form-box">
          {isLogin ? (
            <form className="form" onSubmit={handleLoginSubmit}>
              <h2>Login</h2>
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={loginData.email}
                onChange={handleLoginChange}
                required
              />
              <input
                type="password"
                name="password"
                placeholder="Password"
                value={loginData.password}
                onChange={handleLoginChange}
                required
              />
              <button type="submit">Login</button>
              <p onClick={toggleForm}>Don't have an account? <span>Signup</span></p>
            </form>
          ) : (
            <form className="form" onSubmit={handleSignupSubmit}>
              <h2>Signup</h2>
              <div className="form-grid">
                <input
                  type="text"
                  name="name"
                  placeholder="Full Name"
                  value={signupData.name}
                  onChange={handleChange}
                  required
                />
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  value={signupData.email}
                  onChange={handleChange}
                  required
                />
                <input
                  type="text"
                  name="phone"
                  placeholder="Phone"
                  value={signupData.phone}
                  onChange={handleChange}
                  required
                />
                <input
                  type="date"
                  name="dob"
                  value={signupData.dob}
                  onChange={handleChange}
                />
                <select
                  name="gender"
                  value={signupData.gender}
                  onChange={handleChange}
                  required
                >
                  <option value="">Select Gender</option>
                  <option value="M">Male</option>
                  <option value="F">Female</option>
                  <option value="O">Other</option>
                </select>
                <input
                  type="text"
                  name="city"
                  placeholder="City"
                  value={signupData.city}
                  onChange={handleChange}
                />
                <input
                  type="text"
                  name="state"
                  placeholder="State"
                  value={signupData.state}
                  onChange={handleChange}
                />
                <input
                  type="text"
                  name="pincode"
                  placeholder="Pincode"
                  value={signupData.pincode}
                  onChange={handleChange}
                />
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={signupData.password}
                  onChange={handleChange}
                  required
                />
              </div>
              <textarea
                name="address"
                placeholder="Address"
                value={signupData.address}
                onChange={handleChange}
              ></textarea>
              <button type="submit">Signup</button>
              <p onClick={toggleForm}>Already have an account? <span>Login</span></p>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}
export default LoginSignupPage;