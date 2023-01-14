import aplicativo from "../../../assets/aplicativo.jpg";
import google from "../../../assets/google.png";
import AppStore from "../../../assets/AppStore.png";
import './styles.css';

const SectionApp = () => {
    return(
       
        <>
            <div className="sectionApp-content">
               <>
                <img  className="sectionApp-content-img"src={aplicativo} alt='' />
                </>
                    <div className="sectionApp-content-h2-p-icons">
                    <h2 className="sectionApp-content-h2">Baixe o aplicativo do <br /> Ctd Cars</h2>
                    <p className="sectionApp-content-p"> Drive Happy com o novo aplicativo do Ctd Cars.
                        Faça sua reserva rapidamente, visualize ou modifique facilmente suas próximas
                        reservas e obtenha instruções para chegar à sua agência mais proxima. Mantenha-se
                        conectado à conta Ctd Cars para facilitar o acesso a suas reservas
                        e economize 5% nas nossas tarifas.
                        <br />
                        é rapido e facil!!!
                    </p>
                    
            <div className="sectionApp-content-icons">
            <img className="sectionApp-content-google" src={google} alt='' />
            <img className="sectionApp-content-AppStore" src={AppStore} alt='' />
                    </div>
                    </div>
                </div>  

           
        </>  
    )




}

export default SectionApp;