

import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import HomePage from './pages/HomePage';
import SubcategoryPage from './pages/SubcategoryPage';
import PackageList from './components/Package/PackageList';
import PackageDetailPage from './pages/PackageDetailPage';
import Showcase from './pages/Showcase';
import AboutPage from './pages/AboutPage';
import LoginSignupPage from './pages/LoginSignupPage';
import SearchPage from './pages/SearchPage';
import BookingPage from './pages/BookingPage';
import BookingSummaryPage from './pages/BookingSummaryPage';
import PaymentPage from './pages/PaymentPage';
import PackageListByCategory from './pages/PackageListByCategory';
import SpecialPackageList from './components/Package/SpecialPackageList';
import ContactPage from "./pages/ContactPage"; // adjust path as needed
import TopDeals from './pages/TopDeals'; 




function App() {
  return (
    <>
      <Header />
      <Navbar />

      <Routes>
        <Route path="/" element={<Showcase />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/categories/:id" element={<SubcategoryPage />} />
        <Route path="/subcategory/:id" element={<SubcategoryPage />} /> {/* Added this line */}
        <Route path="/subcategories/:subcategoryId/packages" element={<PackageList />} />
        <Route path="/packages/:packageId" element={<PackageDetailPage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/login" element={<LoginSignupPage />} />
        <Route path="/signup" element={<LoginSignupPage />} />
        <Route path="/search" element={<SearchPage />} />
         <Route path="/booking/:packageId" element={<BookingPage />} />
         <Route path="/booking-summary" element={<BookingSummaryPage />} />
         <Route path="/payment" element={<PaymentPage />} />
        <Route path="/packages/category/:categoryId" element={<PackageListByCategory />} />
        <Route path="/subcategories/:categoryId" element={<SubcategoryPage />} />
        <Route path="/category/:categoryId/packages" element={<SpecialPackageList />} />
        <Route path="/packages/:id" element={<PackageDetailPage />} />
        <Route path="/contact" element={<ContactPage />} />
        <Route path="/top-deals" element={<TopDeals />} />
      </Routes>

      <Footer />
    </>
  );
}

export default App;

















// import React from 'react';
// import { Routes, Route } from 'react-router-dom';
// import Showcase from './pages/Showcase';
// import Navbar from './components/Navbar/Navbar';
// import Header from './components/Header/Header';
// import Footer from './components/Footer/Footer';
// import LoginSignupPage from './pages/LoginSignupPage';
// import ProtectedAppLayout from './components/ProtectedAppLayout';

// import HomePage from './pages/HomePage';
// import SubcategoryPage from './pages/SubcategoryPage';
// import PackageList from './components/Package/PackageList';
// import PackageDetailPage from './pages/PackageDetailPage';
// import AboutPage from './pages/AboutPage';
// import SearchPage from './pages/SearchPage';
// import BookingPage from './pages/BookingPage';
// import BookingSummaryPage from './pages/BookingSummaryPage';
// import PaymentPage from './pages/PaymentPage';
// import PackageListByCategory from './pages/PackageListByCategory';
// import SpecialPackageList from './components/Package/SpecialPackageList';
// import ContactPage from './pages/ContactPage';

// function App() {
//   return (
//     <>
//     <Header />
//       <Navbar />
//     <Routes>
//       {/* Public Route - only showcase and login/signup accessible without login */}
//       <Route path="/" element={<Showcase />} />
//       <Route path="/login" element={<LoginSignupPage />} />
//       <Route path="/signup" element={<LoginSignupPage />} />
//       <Route path="/home" element={<HomePage />} />
//       {/* Protected Routes - only accessible if logged in */}
//       <Route element={<ProtectedAppLayout />}>*/
        
//         <Route path="/about" element={<AboutPage />} />
//         <Route path="/search" element={<SearchPage />} />
//         <Route path="/contact" element={<ContactPage />} />
        
//         <Route path="/categories/:id" element={<SubcategoryPage />} />
//         <Route path="/subcategory/:id" element={<SubcategoryPage />} />
//         <Route path="/subcategories/:categoryId" element={<SubcategoryPage />} />
//         <Route path="/subcategories/:subcategoryId/packages" element={<PackageList />} />
//         <Route path="/packages/:packageId" element={<PackageDetailPage />} />
//         <Route path="/packages/:id" element={<PackageDetailPage />} />
//         <Route path="/packages/category/:categoryId" element={<PackageListByCategory />} />
//         <Route path="/category/:categoryId/packages" element={<SpecialPackageList />} />
        
//         <Route path="/booking/:packageId" element={<BookingPage />} />
//         <Route path="/booking-summary" element={<BookingSummaryPage />} />
//         <Route path="/payment" element={<PaymentPage />} />
//       </Route>
//     </Routes>

//     <Footer/>

//     </>
//   );
// }

// export default App;
