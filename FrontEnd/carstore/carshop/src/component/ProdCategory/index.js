/* eslint-disable jsx-a11y/alt-text */
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Modal from '../Modal';
import './styles.css';

export default function ProdCategory({ prmProduct }) {
  const [isModalVisible, setIsModalVisible] = useState(false);

  return (
    <div id='page-container' className='ProdCategoryBody'>
      <ul className='ProdCategoryLu'>
        <li className='ProdCategoryLi'>
          <div className='LinkProdCategory'>
            <h3 className='H3ProdQualification'>{prmProduct.titulo}</h3>
            <img
              className='ImgProdCategory'
              src={
                prmProduct.imagens[0] == undefined
                  ? ''
                  : prmProduct.imagens[0].url
              }
            />
            <h3 className='H3ProdCategory'>{prmProduct.nome}</h3>
            <p className='ParagProdCategory'>{prmProduct.descricao}</p>
          </div>
          <button
            className='ButtonProductItem'
            onClick={() => setIsModalVisible(true)}>
            Mais Detalhes
          </button>
          {isModalVisible ? (
            <Modal
              detalhes={prmProduct}
              onClose={() => setIsModalVisible(false)}></Modal>
          ) : null}
        </li>
      </ul>
    </div>
  );
}
