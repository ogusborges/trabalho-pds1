import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import FormAnswer from './pages/formAnswer';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

const routes = createBrowserRouter([
    {
      path: "/",
      element: <App />,
      children: {
        path: "/",
        element: <FormAnswer />
      }
    }
]);
  

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
      <RouterProvider router = {routes} />
    </React.StrictMode>
);