import React, { useState, useEffect, createContext } from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import api from '../../services/api';
import Swal from 'sweetalert2';
import { Link, useNavigate } from 'react-router-dom';

import './styles.css';

const PaginaRegistro = ({ onSubmit }) => {
  const [user, setUser] = useState(null);

  var funcao = { id: 1 };

  const sleep = ms => new Promise(r => setTimeout(r, ms));
  const handleSubmit = async (values, { setSubmitting }) => {
    setTimeout(() => {
      api
        .post('/api/v1/users', {
          nome: values.nome,
          sobrenome: values.sobrenome,
          email: values.email,
          senha: values.senha,
          funcao: funcao,
        })
        .then(responseUser => {
          
          api
            .post('/authenticate', {
              email: values.email,
              senha: values.senha,
            })
            .then(responseJwt => {
              
              const userToken = responseJwt.data;

              const loggedUser = {
                id: responseUser.data.id,
                nome: responseUser.data.nome,
                sobrenome: responseUser.data.sobrenome,
                email: responseUser.data.email,
                funcao: funcao,
                token: userToken,
              };
              

              localStorage.setItem('signed', JSON.stringify(loggedUser));
              setUser(loggedUser);

              Swal.fire({
                icon: 'success',
                title: 'Sucesso!',
                text: 'Cadastro realizado com sucesso!',
                html: '',
                confirmButtonColor: 'var(--primary-color)',
                imageWidth: 100,
                width: 350,
              });
            });
        })
        .catch(error => {
          console.error(error);
          Swal.fire({
            title:
              'Infelizmente, você não pôde se registrar. Por favor, tente novamente mais tarde.',
            icon: 'error',
            text: error,
          });
        });
      setSubmitting(false);
    }, 400);
    await sleep(500);
  };

  return (
    <div id='page-container'>
      <div className='container'>
        <a className='links' id='paracadastro'></a>
        <a className='links' id='paralogin'></a>

        <div className='content'>
          <div id='cadastro'>
            <Formik
              initialValues={{ nome: '', sobrenome: '', email: '', senha: '' }}
              validationSchema={Yup.object({
                nome: Yup.string()
                  .max(15, 'Deve conter no máximo 15 letras')
                  .required('Obrigatório'),
                sobrenome: Yup.string()
                  .max(20, 'Deve conter no máximo 20 letras')
                  .required('Obrigatório'),
                email: Yup.string()
                  .email('Email inválido')
                  .required('Obrigatório'),
                senha: Yup.string()
                  .min(7, 'A senha deve ter no mínimo 7 caracteres')
                  .required('Obrigatório'),
              })}
              onSubmit={handleSubmit}>
              <Form className='acessForm'>
                <h2 className='acessForm-h2'>Crie sua conta</h2>

                <p className='formField'>
                  <div className='fieldHalf'>
                    <label className='fieldHalf-label' for='nome'>
                      Nome
                    </label>
                    <Field
                      className='field'
                      name='nome'
                      type='text'
                      id='nome'
                    />
                    <div className='errorMessage'>
                      <ErrorMessage name='nome'>
                        {msg => (msg ? msg : '')}
                      </ErrorMessage>
                    </div>
                  </div>

                  <div className='fieldHalf'>
                    <label className='fieldHalf-label' for='sobrenome'>
                      Sobrenome
                    </label>
                    <Field
                      className='field'
                      name='sobrenome'
                      type='text'
                      id='sobrenome'
                    />
                    <div className='errorMessage'>
                      <ErrorMessage name='sobrenome'>
                        {msg => (msg ? msg : '')}
                      </ErrorMessage>
                    </div>
                  </div>
                </p>

                <div className='clear'></div>

                <p className='formField'>
                  <label className='fieldHalf-label' for='email'>
                    E-mail
                  </label>
                  <Field
                    className='field'
                    name='email'
                    type='text'
                    id='email'
                  />
                  <div className='errorMessage'>
                    <ErrorMessage name='email'>
                      {msg => (msg ? msg : '')}
                    </ErrorMessage>
                  </div>
                </p>

                <p className='formField'>
                  <label className='fieldHalf-label' htmlFor='senha'>
                    Senha
                  </label>
                  <Field
                    className='field'
                    name='senha'
                    type='password'
                    id='senha'
                  />
                  <div className='errorMessage'>
                    <ErrorMessage name='senha'>
                      {msg => (msg ? msg : '')}
                    </ErrorMessage>
                  </div>
                </p>

                <button className='buttonForm' type='submit'>
                  Registrar
                </button>

                <p className='link'>
                  Já tem conta?
                  <a href='/login'> Ir para Login </a>
                </p>
              </Form>
            </Formik>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PaginaRegistro;
