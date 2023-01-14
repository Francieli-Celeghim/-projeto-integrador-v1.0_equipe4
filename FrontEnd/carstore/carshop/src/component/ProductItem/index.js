/* eslint-disable jsx-a11y/alt-text */

import { Link } from "react-router-dom";
import './styles.css'
    
export default function ProductItem({ prmProduct }) {
 
    return (
         
        <ul>
            <li className="ProductItemLi" >
                <Link className="LinkProductItem"   to={`/detalhes/${prmProduct.id}`}>
                    <img className="ImgProductItem"  src={prmProduct.url_imagem} />
                    <h3 className="H3ProductItem">{prmProduct.titulo}</h3>  
                </Link>
                
                {/*<button className="ButtonProductItem">
                   Reserve Agora
                </button>*/}
            </li>
        </ul>
        
    );
}