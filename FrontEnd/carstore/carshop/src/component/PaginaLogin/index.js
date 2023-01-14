import "./styles.css";
import React, { useState, useContext } from "react";

import { AuthContext } from "../../../src/context/auth";



const PaginaLogin = () => {
    const { authenticated, login } = useContext(AuthContext);

    const [email, setEmail] = useState("");
    const[password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();

        
        login(email, password);// integração com o meu contexto / api
    };


    return (
        <div id="login">
            <h1 className="title">Faça seu login</h1>
           
            <form className="form" onSubmit={handleSubmit}>
                <div className="field">
                    <label htmlFor="email">E-mail</label>
                    <input
                        type="email"
                        name="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className="field">
                    <label htmlFor="password">Senha</label>
                    <input
                        type="password"
                        name="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="actions">
                    <button type="submit">Entrar</button>
                </div>

                <div className=" text-center">
                    <span className="txt1">Não possui conta?</span>
                    <a className="txt2" href="/Registro">Criar conta</a>
                </div>
            </form>
           
        </div>
    );     
};




export default PaginaLogin;