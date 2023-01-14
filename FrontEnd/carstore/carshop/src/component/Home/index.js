import React from "react";
import './styles.css';
import Carrousel from "./Carousel";
import BannerHome from "./BannerHome";
import BlocoPesquisa from "./BlocoPesquisa";
import SectionApp from "./SectionApp";

const home = (props) => {
    return (
     <>
      
     <div id='home'>
  
          <div id="HomeBase">
            <div>
              <BlocoPesquisa />
              <BannerHome />
              <SectionApp />
              <Carrousel />
            </div>
          </div>
        </div>
     
     </>
    );
  }
  
  export default home; 