import React, { useContext } from 'react';
import { AuthContext } from '../../../context/auth';

const ButtonSair = () => {
  const { logout } = useContext(AuthContext);

  const handlelogout = () => {
    logout();
  };
  return (
    <>
      <button onClick={handlelogout} class='btn btn-warning btn-lg'>
        Logout
      </button>
    </>
  );
};

export default ButtonSair;
