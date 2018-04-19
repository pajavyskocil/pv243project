package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.dto.OrderDTO;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.service.Impl.BeanMappingServiceImpl;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeanMappingServiceTest {

    private Mapper mapper = mock(Mapper.class);
    private Order order;
    private OrderDTO orderDTO;
    private OrderDTO resultDTO;

    @InjectMocks
    private BeanMappingService beanMappingService;

    @Before
    public void setUpService() {
        beanMappingService = new BeanMappingServiceImpl(mapper);
        order = EntityCreator.createTestOrder();
        User user = new User();
        user.setId(55L);
        user.addSubmittedOrder(order);
        orderDTO = EntityCreator.createTestOrderDTO();
        orderDTO.setSubmitterId(null);
        resultDTO = EntityCreator.createTestOrderDTO();
        resultDTO.setSubmitterId(55L);
    }

    @Test
    public void testMapOrder() {
        when(mapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO mappedDTO = beanMappingService.mapTo(order, OrderDTO.class);

        assertThat(mappedDTO).isEqualToComparingFieldByField(resultDTO);
    }

    @Test
    public void testMapCollectionOfOrders() {
        when(mapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> mappedDTOs = beanMappingService.mapTo(Collections.singletonList(order), OrderDTO.class);

        assertThat((mappedDTOs).get(0)).isEqualToComparingFieldByField(resultDTO);
    }
}
