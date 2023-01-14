/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from 'react';
import api from '../../services/api';
import './styles.css';
import format from 'date-fns/format';

export default function Products({ handleFilter }) {
  const [categoria, setCategoria] = useState([]);

  const [cidade, setCidade] = useState([]);

  const [cidadeEscolhida, setCidadeEscolhida] = useState();

  useEffect(() => {
    callCategoriesApi();
    callCidadesApi();
  }, []);

  async function callCategoriesApi() {
    try {
      const response = await api.get('/api/v1/categories');
      setCategoria(response.data);
    } catch (error) {}
  }

  async function callCidadesApi() {
    try {
      const response = await api.get('/api/v1/cities');
      setCidade(response.data);
    } catch (error) {}
  }

  async function callProductByCity(id) {
    try {
      const URL = `/api/v1/products/city/${id}`;
      const response = await api.get(URL);
      handleFilter(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  async function callApiProductsCategory(id) {
    try {
      //const URL = "categories"
      const URL = `/api/v1/products/category/${id}`;
      const response = await api.get(URL);
      handleFilter(response.data);
    } catch (error) {}
  }

  async function callApiProductsDateRange(range, cidadeEscolhida) {
    try {
      const DataDeInicio = format(
        range[0].startDate,
        'yyyy-MM-dd'
      ); /* range[0].startDate; */
      const DataDeTermino = format(
        range[0].endDate,
        'yyyy-MM-dd'
      ); /* range[0].endDate; */
      const cidade = cidadeEscolhida;

      const URL = `products?cityId=${cidade}&data_inicio=${DataDeInicio}&data_final=${DataDeTermino}`;
      const response = await api.get(URL);
      handleFilter(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <div className='item-list-products-body'>
        <div className='formDiv'>
          {/* <h1 className="formFiltersH1">Mostrando frota para:</h1> */}
          <form className='formFilters'>
            {/* <p className="paragrafoForm">Grupos de carros</p> */}
            {/* <label className="label">Categoria:&nbsp;&nbsp;</label> */}
            <select
              className='select'
              name='categoria'
              onChange={item => callApiProductsCategory(item.target.value)}>
              <option value=''>Selecionar por categorias</option>
              {categoria.map(item => (
                <option key={item.id} value={item.id}>
                  {item.titulo}
                </option>
              ))}
            </select>
            {/* <p className="paragrafoForm">Localização do veículo</p> */}
            {/* <label className="label">Cidade:</label> */}
            <select
              className='select-blocoPesquisa'
              name='cidade'
              onChange={item => callProductByCity(item.target.value)}>
              <option value=''>Escolha a cidade</option>
              {cidade.map(item => (
                <option key={item.id} value={item.id}>
                  {item.nome}
                </option>
              ))}
            </select>
            {/*
                <p>Escolha a data</p>

                <select className="select" name="cidade"  onChange={item => setCidadeEscolhida(item.target.value)}>
                <option  value="">Escolha a cidade</option>
                {cidade.map((item) => (
                <option  key={item.id}value={item.id}>{item.nome}</option>
                ))}
                </select>

                <DateRangeComp callApiProductsDateRange={callApiProductsDateRange} cidadeEscolhida={cidadeEscolhida} /> */}
          </form>
        </div>
        {/* <ul className="item-list-products-ul" >
            <li className="item-list-products-li" >
            {products.map((item) => (

              <><ProdCategory key={item.id} prmProduct={item} /></>
            ))}
          </li>
        </ul>       */}
      </div>
    </>
  );
}
