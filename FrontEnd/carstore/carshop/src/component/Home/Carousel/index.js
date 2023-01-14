import api from '../../../services/api';
import CategoryItem from './CategoryItem';
import { Carousel, Row, Col } from 'react-bootstrap';

import { useEffect, useState } from 'react';

import './styles.css';

const Carrousel = () => {
  var maxItemsPage = 3;
  var maxItems = 10;

  const [categories, setCategories] = useState([]);
  useEffect(() => {
    callApi();
  }, []);

  async function callApi() {
    try {
      const response = await api.get('/api/v1/categories');

      var dataBatchs = [];
      const data = response.data;

      for (let i = 0; i < maxItems; i++) {
        if (data[i] == undefined) {
          break;
        }

        if (i % maxItemsPage === 0) {
          dataBatchs.push([]);
        }

        dataBatchs[Math.floor(i / maxItemsPage)].push({
          id: data[i]['id'],
          qualificacao: data[i]['titulo'],
          descricao: data[i]['descricao'],
          url_imagem: data[i]['urlImagem'],
        });
      }

      setCategories(dataBatchs);
    } catch (error) {
      console.error('error', error);
    }
  }

  return (
    <section className='carrosselSection' id='carrossel'>
      <h2 className='carrosselSectionH2'>Grupos de carros</h2>

      <Carousel
        className='carrosselSectionDestaques'
        variant='dark'
        id='destaques'>
        {categories.map((dataBatch, index) => {
          return (
            <Carousel.Item key={index} className='carrosselSectionItem'>
              <Row className='carrosselSectionRow'>
                {dataBatch.map((item, index) => {
                  return (
                    <Col
                      key={item.id}
                      md={4}
                      sm={6}
                      className={`mx-auto ${
                        index === 1
                          ? 'd-none d-sm-block'
                          : index === 2
                          ? 'd-none d-md-block'
                          : ''
                      }`}>
                      <CategoryItem key={item.id} prmCategory={item} />
                    </Col>
                  );
                })}
              </Row>
            </Carousel.Item>
          );
        })}
      </Carousel>
    </section>
  );
};

export default Carrousel;
