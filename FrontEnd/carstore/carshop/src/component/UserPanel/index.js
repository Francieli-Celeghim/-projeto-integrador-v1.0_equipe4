import React, { useContext } from "react";
//userpanel
import { AuthContext } from "../../context/auth";
import ButtonSair from "../Buttons/ButtonSair";


//userpanel
const UserPanel = () => {
    const { authenticated} = useContext (AuthContext);
    
   
    return (
        
   // eslint-disable-next-line no-unreachable
        <>
            <div className="Panel">
            <h1>Minha conta</h1>
            <p>{String(authenticated)}</p>
            <ButtonSair />
            </div>
            
   
         
    </>
       
    );  
};

export default UserPanel;