import React, { useState, useEffect, createContext } from 'react';
import Swal from 'sweetalert2';

import { useNavigate } from 'react-router-dom';
import api from '../services/api';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const recoveredUser = localStorage.getItem('user');

        if (recoveredUser){
            setUser(JSON.parse(recoveredUser));
        }

        setLoading(false);
       
     }, []);

    const login = (email, senha) => {
        //quando eu recebir inf de email e password iria no servidor e a api criaría  uma seccion

        api.post('/authenticate', {
            email: email,
            senha: senha
        })
        .then((responseJwt) => {
            const userToken = responseJwt.data;

            api.defaults.headers.common["Authorization"] = `Bearer ${userToken.jwt}`;

            api.get(`/api/v1/users/email/${email}`)
            .then((responseUser) => {
              const loggedUser = {
                id: responseUser.data.id,
                nome: responseUser.data.nome,
                sobrenome: responseUser.data.sobrenome,
                email: responseUser.data.email,
                funcao: responseUser.data.funcao.nome,
                token: userToken
              };

              localStorage.setItem('signed', JSON.stringify(loggedUser));
                setUser(loggedUser);
                if (email === "katamarieth@gmail.com"){
                    navigate("/administracao/*");
                } else { navigate("/Produtos") };
            
              
            })
        })
        .catch((error) => {
            console.error('error', error);
      
            Swal.fire({
              icon: 'error',
              title: 'Ops!',
              text: 'Por favor, tente novamente, suas credenciais são inválidas',
              confirmButtonColor: 'var(--primary-color)',
              imageWidth: 100,
              width: 350,
            })
        });
    };

    const logout = () => {
        localStorage.removeItem("signed");
        setUser(null);
        navigate("/");
    };
    return (
        <AuthContext.Provider
            // user =/= null
    // authenticated == true
    //ou
    //user == null
    //autenticated = false
    // !! (Boolean)
            value={{
                authenticated: !!user, user,loading, login,
                logout
            }}
        >
            {children}    
        </AuthContext.Provider>
    );
};
