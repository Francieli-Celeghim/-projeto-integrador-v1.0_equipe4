/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable no-unused-vars */
/* eslint-disable react-hooks/exhaustive-deps */
import {useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import api from "../../services/api";
import './styles.css';
import { DateRange } from 'react-date-range';
import { addDays, set } from 'date-fns'
import format from 'date-fns/format';
import Swal from 'sweetalert2';

export default function CriarReserva() {

    const [produto, setProduto] = useState([]);
    const [cidades, setCidades] = useState([]);
    const [selectedCity, setSelectedCity] = useState("");
    const [userr,setUserr] = useState()
    const [loaded, setLoaded] = useState(false)
    const [listaDeDatasRealmenteDesabilitadas,setListaDeDatasRealmenteDesabilitadas] = useState([1,2,3]);

    const parametro = useParams();
    const navigate = useNavigate();


    
    useEffect(() => {
        callApiDetails(parametro.idProduto);
        /* callCidadesApi() */
        filterCity(1)
        setUserr(estaLogado())
        window.addEventListener('resize', handleResize);
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

  

   
    
    const enviarReserva = () => {
      try{
        const response = api.post("/api/v1/bookings",
        {
          inicioReserva : `${format(range[0].startDate, "yyyy-MM-dd")}`,
          fimReserva : `${format(range[0].endDate, "yyyy-MM-dd")}`,
          
          produto:{
                      "id":`${produto.id}`
                    },
          cidade:{
                    "id":`${produto.cidade.id}`
                  },
    usuario:{
        "id":`${userr.id}`
    }
        }).then(function deuBom(response) {
          // handle success
          navigate("/Sucesso")
        })
        .catch(function deuRuim(error) {
          
          // handle error
          Swal.fire({
            icon: 'error',
            title: 'Ops!',
            text: 'Houve um erro ao enviar sua reserva, tente novamente mais tarde.',
            confirmButtonColor: 'var(--primary-color)',
            imageWidth: 100,
            width: 350,
          })
          console.error(error);
        })






        
      } catch{

      }
          

    }
  

    async function callApiDetails(x) {
        
        try {
          const response = await api.get(`/api/v1/products/${x}`).then(setLoaded(true));
          setProduto(response.data); 
        }
        catch (error) {      
        }
      } 

    /*   async function callCidadesApi() {
        try {
          const response = await api.get("/cities");
          setCidades(response.data);
        }
        catch (error) { 
    
        }
      } */
    
      function filterCity(botaOId){
        try{
        
        const roberto = cidades.filter( e => e.id === botaOId )
        
        return (roberto[0].nome)
      }catch{}}


      //PARTE DO CALENDARIO


      const datasLista = []
    

    
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
    const soltarEmDias = () => {try {

      produto.reservas.map(e => 
        (datasLista.push([e.inicioReserva,e.fimReserva]))
                             )


        console.log(datasLista[0])
        
        return datasLista.map(e =>  trabalharOsPares(e[0],e[1])).flat(Infinity)
      
    } catch (error) {
      console.error(error)
      return [1,1,1,1,1]
    }
                                        
                                        
                                        

                               }















        const diffDays = (date, otherDate) => Math.ceil(Math.abs(date - otherDate) / (1000 * 60 * 60 * 24)+1);

        const minDate = new Date( new Date().getFullYear(), new Date().getMonth(),new Date().getDate());
        const maxDate = new Date( new Date().getFullYear(), new Date().getMonth()+2,new Date().getDate());
        
        const [range, setRange] = useState([
        {
          startDate:  new Date(),
          endDate: addDays(new Date(), 1),
          key: 'selection'
        }
      ])
      //filtrando as datas
        const data1 : Date = new Date(2022,7,20);
        const data2 : Date = new Date(2022,7,25);
        const disabledDatesList=[]
        const diff = (data2-data1)/864e5;
        const d1 = data1
        
        const dates = Array.from(
          {length: diff+1},
          (_,i) => {
            const date = new Date(data1) 
            date.setDate(d1.getDate()+i) 
            disabledDatesList.push(date)
            const weekday = date
            return `${weekday}`
          }
        )

        function estaLogado() {
          try {
            let user = JSON.parse(localStorage.getItem("signed"));
            return user
      
          } catch (error) {
            
            return false
          }
        }

        const dadosPessoa = () => {
          try {
            
            
              return(
                <>
                
                
              <p>Nome: <br/> <input type="text" disabled="true" value={(userr.nome+" "+userr.sobrenome)} /> </p>
              <p>email:<br/> <input type="text" disabled="true" value={userr.email}/></p>
              <p>Data de Início:<br/> <input type="text" disabled="true" value={format(range[0].startDate, "dd/MM/yyyy")}/></p>
              <p>Data de Devolução:<br/> <input type="text" disabled="true" value={format(range[0].endDate, "dd/MM/yyyy")}/></p>
              <p>Diárias:<br/> <input type="text" disabled="true" value={diffDays(range[0].startDate,range[0].endDate)}/></p>
              <p>Valor Total:</p>
              <p>R$ {produto.valorDiaria*diffDays(range[0].startDate,range[0].endDate)},00</p>
                
                
                </>
              )
          } catch (error) {
            
          }

        }

        const handleCategoriaCarro = () => {
          try {
            
           
              return  produto.categorias.map(e => e.titulo+"  ")
          } catch (error) {
            
          }

        }

        const handleNomeCarro = () => {
          try {
              return  produto.nome
          } catch (error) {}
        }
        const handleCidadeCarro = () => {
          try {
            
              return  produto.cidade.nome+","+produto.cidade.estado
          } catch (error) {}
        }

        const handleImagemCarro = () => {
          try {
              return  produto.imagens[0].url
          } catch (error) {}
        }
        function handleSetarDatas (){
          if (loaded === true){
            return<>
            <DateRange 
                    
                    onChange={item => setRange([item.selection])}
                    minDate={minDate}
                    maxDate={maxDate} 
                    editableDateInputs={true} 
                    moveRangeOnFirstSelection={false}
                    ranges={range}
                    months={2}
                    disabledDates={soltarEmDias()}
                    direction="horizontal"
                className="date" 
                style={{marginLeft:10,width: 250,height: 100, padding:10}}
                    />
                  </>
          }
          
        }
        
        

        
        



    return (
        <>
            <h2 className="bloco-titulo-reserva-h2">Confirme sua reserva:</h2>
            <div className="flex-container-reserva">
              <div className="bloco-titulo-reserva">
              </div>
              

            <div className="bloco-detalhes-reserva">
                <br />
                <h4 className="reserva-titulo-carro" >{handleNomeCarro()}</h4>
                <div className="flex-container-reserva1">
                  <img className="imagem-reserva" onClick={handleSetarDatas} src={handleImagemCarro()} width="50%" />
                  <div className="detalhes-reserva-1">
                    <h4>Categoria(s): {handleCategoriaCarro()}</h4>
                    
                    <h4>Localização do produto: {handleCidadeCarro()}</h4>
                    
                  </div>
                </div>
                <div className="flex-container-reserva2">
                  <div className="reserva-dados-pessoa">
                    {dadosPessoa()}
                  </div>
                  
              {handleSetarDatas()}    
              
              </div>

                  
                  
                  
                            {/* <Link to="/Sucesso"> */} <button className="button-reserva" onClick={enviarReserva}> Reservar</button> {/* </Link> */}
                </div>
            </div>
            <Link to="/Produtos"> <p className="voltarprodutos" >voltar aos produtos</p> </Link>
          
            
        </>
    );
}