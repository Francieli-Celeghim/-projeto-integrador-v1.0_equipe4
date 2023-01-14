import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../../context/auth';
import ButtonSair from '../Buttons/ButtonSair';

import logo from '../../assets/logo.png';
import login from '../../assets/login.png';

import './Header.css';

const Header = () => {
  const { user } = useContext(AuthContext);

  function estaLogado() {
    try {
      let user = JSON.parse(localStorage.getItem('signed'));
      return user.nome;
    } catch (error) {
      return '';
    }
  }

  return (
    <div className='header'>
      <div className='logo'>
        <Link to='/'>
          <img src={logo} alt='' width={160} />
        </Link>
      </div>
      <div className='header-right'>
        <Link className='header-rightLink' to='/Produtos'>
          <span>Conheça nossa Frota</span>
        </Link>
        {localStorage.signed == null ? (
          <>
            <Link className='header-rightLink' to='/Registro'>
              <span>Criar conta</span>
            </Link>
            <a className='header-rightLink' href='/login'>
              Login
            </a>
          </>
        ) : (
          <>
            <img className='logInImg' src={login} alt='' />
            <div className='logIn'>Bem vindo, {estaLogado()}!</div>
            <ButtonSair />
          </>
        )}
      </div>
    </div>
  );
};

export default Header;
