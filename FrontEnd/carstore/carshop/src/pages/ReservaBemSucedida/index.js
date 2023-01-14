import './styles.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleCheck } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';


export default function ReservaSucedida() {

    return (
        <div id='confirmeReserva'>
        <div id='confirmeReserva-card'>
            <FontAwesomeIcon className="checkIcon" icon={faCircleCheck} />
            <h3>Muito obrigado!</h3>
            <h5>Sua reserva foi feita com sucesso.</h5>
            <Link to={'/'}><button className='confirmeReservaBtn'>Voltar</button></Link>
        </div>
        </div>      





// {/* <div className="container-reserva-sucedida">
// <h2>Reserva bem sucedida!</h2>
// <img src="https://w7.pngwing.com/pngs/944/793/png-transparent-computer-icons-check-mark-presentation-symbol-check-list-miscellaneous-angle-text.png" alt="Reserva bem sucedida"  />
// <Link to='/'> <p> voltar para home</p></Link> */}








// //</div>
)
}