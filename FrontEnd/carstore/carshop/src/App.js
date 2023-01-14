import Paginas from './routes';
import { Admin, Resource, CustomRoutes } from "react-admin";
import jsonServerProvider from "ra-data-json-server";
import {ProductList, ProductCreate, ProductEdit } from "../src/component/ProductsCrud";




const dataProvider = jsonServerProvider("http://localhost:3000");


const App = () => {
  return (
    
    <>
      
      <Paginas />
       
      
       
    </>        
                
    
      
    
  );
}

export default App;
