/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable no-unused-vars */
/* eslint-disable react-hooks/exhaustive-deps */
import {useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../services/api";
import './styles.css';

export default function Details() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        callApiDetails();
    }, []);    
  
    const parametro = useParams();
   
    async function callApiDetails() {
        
        try {
          const response = await api.get(`/products/${parametro.id}`);
            setProducts(response.data);
        
        }
        catch (error) {      
        }
      } 
    return (
        <>
            
            <img src={products.url_imagem} />
            <h1>{products.qualificacao}</h1>
          
            
        </>
    );
}
