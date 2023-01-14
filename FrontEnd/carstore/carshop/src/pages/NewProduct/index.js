// eslint-disable-next-line
import { Link } from 'react-admin';
import { Admin, Resource, Layout } from 'react-admin';
import { Route } from 'react-router';
import {
  ProductCreate,
  ProductEdit,
  ProductList,
} from '../../component/ProductsCrud';
import DataProvider from './DataProvider';
import style from './style.css';

const dataProvider = DataProvider('http://3.15.133.120:8080');

const CustomLayout = props => <Layout {...props} appBar={() => <div></div>} />;

const NewProduct = () => {
  return (
    <>
      <Admin
        basename='/administracao'
        layout={CustomLayout}
        dataProvider={dataProvider}>
        <Resource
          name='api/v1/products'
          edit={ProductEdit}
          list={ProductList}
          create={ProductCreate}
        />
      </Admin>
    </>
  );
};

export default NewProduct;
