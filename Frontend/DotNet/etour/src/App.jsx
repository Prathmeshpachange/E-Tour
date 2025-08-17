import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Routes, Route } from 'react-router-dom';

import Header from './components/Header/Header';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';

import Showcase from './pages/Showcase';
import HomePage from './pages/HomePage';
import CategoryPage from './pages/CategoryPage';
import SubcategoryPage from './pages/SubcategoryPage';
import PackageList from './components/Package/PackageList';
import PackageDetailPage from './pages/PackageDetailPage';
import AboutPage from './pages/AboutPage';
import LoginSignupPage from './pages/LoginSignupPage';
import SearchPage from './pages/SearchPage';
import BookingPage from './pages/BookingPage';
import BookingSummaryPage from './pages/BookingSummaryPage';
import PaymentPage from './pages/PaymentPage';
import PackageListByCategory from './pages/PackageListByCategory';
import SpecialPackageList from './components/Package/SpecialPackageList';
import ContactPage from './pages/ContactPage';
import TopDeals from './pages/TopDeals';

import ProtectedAppLayout from './components/ProtectedAppLayout';

function App() {
  return (
    <>
      {/* Always visible */}
      <Header />
      <Navbar />

      <Routes>
        {/* Public Routes */}
        <Route path="/" element={<Showcase />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/categories" element={<CategoryPage />} />
        <Route path="/login" element={<LoginSignupPage />} />
        <Route path="/signup" element={<LoginSignupPage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/contact" element={<ContactPage />} />
        <Route path="/top-deals" element={<TopDeals />} />

        {/* Protected Routes */}
        <Route element={<ProtectedAppLayout />}>
          <Route path="/categories/:id" element={<SubcategoryPage />} />
          <Route path="/subcategory/:id" element={<SubcategoryPage />} />
          <Route path="/subcategories/:categoryId" element={<SubcategoryPage />} />
          <Route path="/subcategories/:subcategoryId/packages" element={<PackageList />} />
          <Route path="/packages/:packageId" element={<PackageDetailPage />} />
          <Route path="/packages/:id" element={<PackageDetailPage />} />
          <Route path="/packages/category/:categoryId" element={<PackageListByCategory />} />
          <Route path="/category/:categoryId/packages" element={<SpecialPackageList />} />
          <Route path="/booking/:packageId" element={<BookingPage />} />
          <Route path="/booking-summary" element={<BookingSummaryPage />} />
          <Route path="/payment" element={<PaymentPage />} />
        </Route>
      </Routes>

      {/* Always visible */}
      <Footer />
    </>
  );
}

export default App;
