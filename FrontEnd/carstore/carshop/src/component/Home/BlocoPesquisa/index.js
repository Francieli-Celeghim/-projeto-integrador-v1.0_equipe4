import { useEffect, useState, useRef } from 'react';
import './styles.css';
import format from 'date-fns/format';
import api from '../../../services/api';

import { DateRange } from 'react-date-range';

import { addDays, setAddDays } from 'date-fns';

import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { useNavigate } from 'react-router';

const BlocoPesquisa = () => {
  const [cidade, setCidade] = useState('');
  const [cidades, setCidades] = useState([]);
  const [inputValue, setInputValue] = useState('');
  const [stringTexto, setStringTexto] = useState('');
  const [openSugestions, setOpenSugestions] = useState(false);

  const onChange = event => {
    setOpenSugestions(true); // -> não faz nada
    handleShowSugestions(true);
    setInputValue(event.target.value);
  };

  async function callCidadesApi() {
    try {
      const response = await api.get('/api/v1/cities');
      setCidades(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    callCidadesApi();
  }, []);
  useEffect(() => {
    document.getElementById('pesquisa-retirar-local').value = stringTexto;
  });

  //Parte do calendário ###############################################################################################################
  const minDate = new Date(
    new Date().getFullYear(),
    new Date().getMonth(),
    new Date().getDate()
  );
  const maxDate = new Date(
    new Date().getFullYear(),
    new Date().getMonth() + 2,
    new Date().getDate()
  );

  const [range, setRange] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 1),
      key: 'selection',
    },
  ]);
  const [open, setOpen] = useState(false);

  const navigate = useNavigate();

  const handleSearch = () => {
    /* navigate("/Produtos",{state: {range,cidade}}); */
    if (cidade !== '') {
      navigate(
        `/Produtos/${cidade}/${format(
          range[0].startDate,
          'yyyy-MM-dd'
        )}/${format(range[0].endDate, 'yyyy-MM-dd')}`
      );
    } else {
      navigate(
        `/Produtos/${format(range[0].startDate, 'yyyy-MM-dd')}/${format(
          range[0].endDate,
          'yyyy-MM-dd'
        )}`
      );
    }
  };

  const handleSelectCity = (value, text) => {
    setInputValue(stringTexto);
    setStringTexto(text.toString());
    setCidade(value);
  };

  const handleShowSugestions = () => {
    const robson = document.getElementsByClassName('dropdown')[0];

    if (openSugestions) {
      robson.style.display = 'none';
      setOpenSugestions(!openSugestions);
    } else if (!openSugestions) {
      robson.style.display = 'block';
      setOpenSugestions(!openSugestions);
    }
  };

  return (
    <>
      <div>
        <div className='pesquisa-primeira-fileira'>
          <div className='pesquisa-itens-duplos'>
            <div className='search-container'>
              <div className='search-inner'>
                <input
                  placeholder='Digite o local de retirada'
                  type='text'
                  id='pesquisa-retirar-local'
                  name='pesquisa-retirar-local'
                  className='pesquisa-inputs'
                  value={inputValue}
                  onChange={onChange}
                  onClick={handleShowSugestions}
                />
              </div>

              <div className='dropdown'>
                {cidades
                  .filter(item => {
                    const searchTerm = inputValue.toString().toLowerCase();
                    const fullName = item.nome.toString().toLowerCase();

                    return fullName.includes(searchTerm);
                  })
                  .map(item => (
                    <div key={item.id} className='dropdown-row'>
                      <option
                        onClick={item =>
                          handleSelectCity(item.target.value, item.target.text)
                        }
                        value={item.id}>
                        {item.nome}, {item.estado}
                      </option>
                    </div>
                  ))}
              </div>
            </div>
          </div>

          <div className='pesquisa-itens-duplos'>
            <input
              value={`${format(range[0].startDate, 'dd/MM/yyyy')} to ${format(
                range[0].endDate,
                'dd/MM/yyyy'
              )}`}
              readOnly
              className='inputBox'
              onClick={() => setOpen(open => !open)}
            />
            <div className='calendarFrontPage'>
              {open && (
                <DateRange
                  onChange={item => setRange([item.selection])}
                  minDate={minDate}
                  maxDate={maxDate}
                  editableDateInputs={true}
                  moveRangeOnFirstSelection={false}
                  ranges={range}
                  className='date'
                />
              )}
            </div>
          </div>

          <input
            type='button'
            onClick={handleSearch}
            id='pesquisa-botao-buscar'
            name='pesquisa-botao-buscar'
            value='Buscar'
          />
        </div>
      </div>
    </>
  );
};

export default BlocoPesquisa;
