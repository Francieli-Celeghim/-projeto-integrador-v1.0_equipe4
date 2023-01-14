import React, { Link, useEffect, useState} from "react";
import api from "../../services/api";
import './styles.css';
import { useNavigate } from 'react-router';
import Details from "../../pages/Details";
import format from 'date-fns/format';
import parseJSON from 'date-fns/parseJSON'
import { DateRange } from 'react-date-range'



const  Modal = ({id = 'modal' , detalhes, onClose}) => {
    const handleOutsideClick = (e) => {
        if (e.target.id === id) onClose();
    }; 
    const [characteristic, setCharacteristic] = useState([]);
    /* const [productDetails, setProductDetails] = useState([]); */
    const [listaParaMostrar, setListaParaMostrar] = useState([]);
    const [listaDeDatasRealmenteDesabilitadas,setListaDeDatasRealmenteDesabilitadas] = useState([]);
    

    useEffect(() => {
        callProductDetailsApi();
        window.addEventListener('resize', handleResize);
        /* window.removeEventListener('resize', handleResize); */
        setListaDeDatasRealmenteDesabilitadas(soltarEmDias())


      }, []);

      //pegar width
      function getWindowDimensions() {
        const { innerWidth: width, innerHeight: height } = window;
        return {
          width,
          height
        };
      }
      const [windowDimensions, setWindowDimensions] = useState(getWindowDimensions());
      function handleResize() {
        setWindowDimensions(getWindowDimensions());
      }

      



    async function callProductDetailsApi(id) {
      try {
        const response = await api.get(`api/v1/products`);
        const productDetails = response.data;
        setListaParaMostrar(filterDetails(productDetails));
      }
      catch (error) {     
      }
    } 
   
    const navigate  = useNavigate()
    
    
    const handleBook = () => {
      navigate(`/Reserva/${detalhes.id}/`);
    }



      
      const filterDetails = (productDetails) =>{
        try{
        
        const listaDeCaracs = []
        const listaDeCaracsFiltrada = []
        /* detalhes.caracteristicas.map(n => listaDeCaracs.push(productDetails.filter( e => e.id == n.id ))) */
        detalhes.caracteristicas.map(n => listaDeCaracs.push(n.id))
        listaDeCaracsFiltrada.push(productDetails.filter( e => listaDeCaracs.includes(e.id)))

        

        return listaDeCaracsFiltrada
        
      }catch(error){
        console.error(error)}}

        try {
          
        } catch (error) {
          
        }


      const soltarCaracs = (caracsInput) =>{
        
        if (!(caracsInput[0] === undefined)){
            return (
                <>
                
                <div>
                    {caracsInput[0].map(e => <  >
                    <div className="flex-container">
                    <img className="modal-atributo-imagem" key={e.characteristic} src={e.icon_url} width="30px" height={"30px"}></img> 
                    <p className="modal-atributo-texto" >{e.characteristic}</p>
                    </div>
                    </>) }
    
    
                </div>
                
                
                </>
            )
        }

        
    }
    const soltarCaracsV2 = () =>{
        
        return <>
                
                <div>
                    {detalhes.caracteristicas.map(e => <  >
                    <div className="flex-container">
                    <img className="modal-atributo-imagem" key={e.characteristic} src={e.icone} width="30px" height={"30px"}></img> 
                    <p className="modal-atributo-texto" >{e.nome}</p>
                    </div>
                    </>) }
    
    
                </div>
                
                
                </>
            
        }


//Parte do calendario
    const disabledDatesList=[]
    //TODO: aumentar aumentar 1 dia
    
    const datasLista = []
    const diffDays = (date, otherDate) => Math.ceil(Math.abs(date - otherDate) / (1000 * 60 * 60 * 24)+1);

    
    const trabalharOsPares =(inicio,fim) =>{
        const listaDeDias = []
        inicio = new Date(inicio)
        inicio.setDate(inicio.getDate() + 1);
        fim = new Date(fim)
        fim.setDate(fim.getDate() + 1);
        const diferenca = diffDays(inicio,fim)

        var i = 0;
        for (; i < diferenca; i++) {
            listaDeDias.push(new Date(inicio.getFullYear(),inicio.getMonth(),inicio.getDate() + i));
            
            // more statements
}

return listaDeDias





        /* const qtdDias = diffDays(inicio,fim) */
        
        

    }

    const listaTotalDeDias = []
    const soltarEmDias = () => {
      detalhes.reservas.map(e => 
        (datasLista.push([e.inicioReserva,e.fimReserva]))
      )
      
      return datasLista.map(e =>  trabalharOsPares(e[0],e[1])).flat(Infinity)
                                        
                                        
    }

    /* detalhes.datas_ocupadas.map(e =>  disabledDatesList.push(new Date(new Date(e.data).getFullYear(),new Date(e.data).getMonth(),new Date(e.data).getDate()+1) )) */
    
    const minDate: Date = new Date( new Date().getFullYear(), new Date().getMonth(),new Date().getDate());
    const maxDate: Date = new Date( new Date().getFullYear(), new Date().getMonth()+2,new Date().getDate());

    //PEGAR ISSO DA API v
   /*  const data1 : Date = new Date(2022,6,27);
    const data2 : Date = new Date(2022,6,28);
    const disabledDatesList = [data1,data2]; */
    // /\
      

    return (
            <>
                <div id={id} className="ProdutoModal" onClick={handleOutsideClick}>
                    <div className="container2">
                        <button className="close" onClick={onClose}/>
                        { /* Organização de pc ou pc pequeno : organização de cell */  }

                    <div className="flex-container">

                                <div className="flex-child-resto">        
                                            <p className="modal-descricao" >{detalhes.descricao}</p>                           
                                            <div className="listaAtributosBox">
                                            {soltarCaracsV2()}
                                            </div>
                                                                     
                                </div>  

                              
                                    <div className="modal-bloco-valorDiaria-botao-imagem">
                                        <img className="modal-imagem" src={detalhes.imagens[0].url}  alt="" />
                                        <p>Valor da diária: R${detalhes.valorDiaria},00</p>
                                        <button onClick={handleBook} className="ButtonModal" >
                                        Reserve Agora
                                        </button>
                                    
                                 </div>
                            
                                                          
                                   
                              
                            </div>
                        </div>
                    </div>
            </>
    );
}

export default Modal;