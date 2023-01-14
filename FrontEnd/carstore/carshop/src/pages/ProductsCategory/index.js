/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable no-unused-vars */
/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from 'react';
import ProdCategory from '../../component/ProdCategory';
import Products from '../Products';
import api from '../../services/api';
import { useLocation, useParams } from 'react-router-dom';
import './styles.css';
import format from 'date-fns/format';

export default function ProductsCategory() {
  const [products, setProducts] = useState([]);
  /* const location = useLocation(); */
  const params = useParams();

  const [cidade, setCidade] = useState(params.cidade);
  const [startDate, setstartDate] = useState(params.startDate);
  const [endDate, setendDate] = useState(params.endDate);

  useEffect(() => {
    if (!!cidade !== false && !(startDate === undefined)) {
      callApiProductsDateCity(cidade, startDate, endDate);
    } else {
      if (!(cidade === undefined)) {
        callApiProductsCity(cidade);
      } else {
        if (!(startDate === undefined)) {
          callApiProductsDate(startDate, endDate);
        } else {
          callApiProducts();
        }
      }
    }
  }, []);

  async function callApiProductsCategory(categoryId) {
    try {
      const URL = `/category/${categoryId}`;
      const response = await api.get(URL);
      setProducts(response.data);
    } catch (error) {}
  }

  async function callApiProductsCity(cityId) {
    try {
      const URL = `products/city/${cityId}`;
      const response = await api.get(URL);
      setProducts(response.data);
    } catch (error) {}
  }

  async function callApiProducts() {
    try {
      //const URL = "categories"

      const URL = `/api/v1/products`;
      const response = await api.get(URL);
      setProducts(response.data);
    } catch (error) {}
  }

  async function callApiProductsDateCity(cidade, startDate, endDate) {
    try {
      const DataDeInicio = startDate;
      const DataDeTermino = endDate;
      const URL = `api/v1/products?cityId=${cidade}&dataInicio=${DataDeInicio}&dataTermino=${DataDeTermino}`; /* &data_inicial=${DataDeInicio}&data_final=${DataDeTermino}`; */
      
      const response = await api.get(URL);
      setProducts(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  async function callApiProductsDate(startDate, endDate) {
    try {
      const DataDeInicio = startDate;
      const DataDeTermino = endDate;
      const URL = `api/v1/products?dataInicio=${DataDeInicio}&dataTermino=${DataDeTermino}`;
      const response = await api.get(URL);
      setProducts(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <div className='ProCategory-body'>
        <Products handleFilter={setProducts} />
        <h1 className='ProCategory-h1'></h1>
        <ul className='ProCategory-ul'>
          <li className='ProCategory-li'>
            {products.map(item => (
              <ProdCategory key={item.id} prmProduct={item} />
            ))}
          </li>
        </ul>
      </div>
    </>
  );
}
