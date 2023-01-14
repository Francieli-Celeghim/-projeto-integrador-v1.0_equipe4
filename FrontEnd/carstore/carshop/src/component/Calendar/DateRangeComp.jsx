import { useEffect, useRef, useState } from 'react'
import { DateRange } from 'react-date-range'
import { Link } from 'react-router-dom'
import format from 'date-fns/format'
import { addDays } from 'date-fns'
import "./styles.css"
import 'react-date-range/dist/styles.css'
import 'react-date-range/dist/theme/default.css'

const DateRangeComp = ({callApiProductsDateRange,cidadeEscolhida}) => {
  //vo mudar o calendário
  // date state
  const [range, setRange] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 1),
      key: 'selection'
    }
  ])

  // open close
  const [open, setOpen] = useState(false)

  // get the target element to toggle 
  const refOne = useRef(null)

  useEffect(() => {
    // event listeners
    document.addEventListener("keydown", hideOnEscape, true)
    document.addEventListener("click", hideOnClickOutside, true)
  }, [])

  // hide dropdown on ESC press
  const hideOnEscape = (e) => {
    if( e.key === "Escape" ) {
      setOpen(false)
    }
  }

  // Hide on outside click
  const hideOnClickOutside = (e) => {
    if( refOne.current && !refOne.current.contains(e.target) ) {
      setOpen(false)
    }
  }


  const minDate = new Date( new Date().getFullYear(), new Date().getMonth(),new Date().getDate());
  const maxDate = new Date( new Date().getFullYear(), new Date().getMonth()+2,new Date().getDate());
  
  return (
    <div className="calendarWrap">
      <div className="flex-container-calendar">
        <input
          
           value={`${format(range[0].startDate, "dd/MM/yyyy")} a ${format(range[0].endDate, "dd/MM/yyyy")}`}          
          readOnly
          className="inputBox"
          onClick={ () => setOpen(open => !open) }
        />

        <div ref={refOne}>
          {open && 
            <DateRange
              
              minDate={minDate}
              maxDate={maxDate}              
              onChange={item => setRange([item.selection])}
              editableDateInputs={true}
              moveRangeOnFirstSelection={false}
              ranges={range}
              months={1}
              direction="horizontal"
              className="calendarElement"
              
            />
          }
        </div>
        <input type="button" id="pesquisa-botao-buscar" className="pesquisa-botao-buscar" onClick={() => callApiProductsDateRange(range,cidadeEscolhida)} value="Teste" />
        {/* < Link to="/Produtos" id="pesquisa-botao-buscar" className="pesquisa-botao-buscar" >buscar</Link> */}

      </div>
    </div>
  )
}

export default DateRangeComp
