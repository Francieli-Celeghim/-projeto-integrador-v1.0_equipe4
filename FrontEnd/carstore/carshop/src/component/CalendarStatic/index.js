import { useEffect, useRef, useState } from 'react'
import { DateRange } from 'react-date-range'

import format from 'date-fns/format'
import { addDays } from 'date-fns'



import './styles.css';

const CalendarStatic = () => {

  // date state
  const [range, setRange] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 1),
      key: 'selection'
    }
  ])



  

 


  

  


//Pegar valores da Api! TODO
  const data1 : Date = new Date(2022,5,27);
  const data2 : Date = new Date(2022,5,28);
  const disabledDatesList = [data1,data2];

  const minDate: Date = new Date( new Date().getFullYear(), new Date().getMonth(),new Date().getDate());
  const maxDate: Date = new Date( new Date().getFullYear(), new Date().getMonth()+2,new Date().getDate());
  
  
  return (
    <div className="calendarWrap">
      
      
          <DateRange
            
            minDate={minDate}
            maxDate={maxDate}
            
            disabledDates={disabledDatesList}
            editableDateInputs={false}
            moveRangeOnFirstSelection={false}
            
            months={2}
            direction="horizontal"
            className="calendarElement"
            
          />
      
      
      

    </div>
  )
}

export default CalendarStatic
