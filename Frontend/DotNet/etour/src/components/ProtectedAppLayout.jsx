import React from 'react';
import { Navigate, Outlet, useLocation } from 'react-router-dom';

const ProtectedAppLayout = () => {
  const isAuthenticated = !!sessionStorage.getItem('token'); // check token presence
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }

  return <Outlet />; // Just renders protected content, layout is handled in App.jsx
};

export default ProtectedAppLayout;
