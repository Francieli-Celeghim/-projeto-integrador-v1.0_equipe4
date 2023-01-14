import React, { useContext } from 'react';
import { AuthProvider, AuthContext } from '../context/auth';
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
  useParams,
} from 'react-router-dom';
import Header from '../component/Header/Header';
import Panel from '../pages/Panel';
import Login from '../pages/Login';
import Footer from '../component/Footer/Footer';
import Homepage from '../pages/Homepage';
import Register from '../pages/Register';
import Error from '../component/Error';
import Products from '../pages/Products';
import Details from '../pages/Details';
import ProductsCategory from '../pages/ProductsCategory';
import ReservaSucedida from '../pages/ReservaBemSucedida';
import ConfirmationNewProduct from '../pages/ConfirmationNewProduct';
import CriarReserva from '../pages/CriarReserva';
import NewProduct from '../pages/NewProduct';

const Paginas = () => {
  const Private = ({ children }) => {
    const { authenticated, loading } = useContext(AuthContext);

    if (loading) {
      return <div className='loading'>Carregando...</div>;
    }

    if (!authenticated) {
      return <Navigate to='/login' />;
    }

    return children;
  };
  return (
    <BrowserRouter>
      <AuthProvider>
        <Header />
        <Routes>
          <Route path='/' element={<Homepage />} />
          <Route path='/Produtos' element={<ProductsCategory />} />
          <Route path='/Registro' element={<Register />} />
          <Route path='/login' element={<Login />} />
          <Route
            path='/userpanel'
            element={
              <Private>
                <Panel />
              </Private>
            }
          />
          <Route path='/Sucesso' element={<ReservaSucedida />} />
          <Route path='/novo-produto' element={<ConfirmationNewProduct />} />

          <Route
            path='/Reserva/:idProduto'
            element={
              
                <CriarReserva />
              
            }
          />

          <Route
            path='/Produtos/:cidade/:startDate/:endDate'
            element={<ProductsCategory />}
          />
          <Route
            path='/Produtos/:startDate/:endDate'
            element={<ProductsCategory />}
          />
          <Route path='/Produtos/:cidade/' element={<ProductsCategory />} />
          <Route path='/detalhes/:id' element={<Details />} />
          <Route path='/categorias/:id' element={<ProductsCategory />} />

          <Route
            path='/administracao/*'
            element={
              <Private>
              <NewProduct />
              </Private>
              }
          />

          <Route path='*' element={<Error />} />
        </Routes>
        <Footer />
      </AuthProvider>
    </BrowserRouter>
  );
};

export default Paginas;
