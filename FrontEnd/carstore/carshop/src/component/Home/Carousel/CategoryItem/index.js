import React from "react";
import './styles.css';
import { Link } from "react-router-dom";




export default function CategoryItem({ prmCategory }) {
    return (
    <section className="categoryItemSection">   
        <ul className="categoryItemUl">
            <li className="categoryItemLi">
                <Link className="categoryItemLink" to={`/categorias/${prmCategory.id}`}>
                    <img className="categoryItemImg" src={prmCategory.url_imagem} />
                    <h3 className="categoryItemH3">{prmCategory.qualificacao}</h3>
                    {<p className="categoryItemP" >{prmCategory.descricao}</p> }
                </Link>
            </li>
        </ul>
    </section>         
    );
  }
  
   