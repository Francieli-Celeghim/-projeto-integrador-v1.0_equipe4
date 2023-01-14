import './style.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleCheck } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

export default function ConfirmationNewProduct () {

    return (
        <div id='confNewProduct'>
        <div id='confNewProduct-card'>
            <FontAwesomeIcon className="checkIcon" icon={faCircleCheck} />
            <h3>Muito obrigado!</h3>
            <h5>Seu produto foi criado com sucesso.</h5>
            <Link to={'/'}><button className='confNewProductBtn'>Voltar</button></Link>
        </div>
        </div>
    )
}

