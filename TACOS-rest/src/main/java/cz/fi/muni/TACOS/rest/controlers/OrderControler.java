package cz.fi.muni.TACOS.rest.controlers;

import cz.fi.muni.TACOS.dto.OrderCreateDTO;
import cz.fi.muni.TACOS.dto.OrderDTO;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.facade.OrderFacade;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.rest.ApiUris;
import cz.fi.muni.TACOS.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Path(ApiUris.URI_ORDERS)
public class OrderControler {

	private static final Logger log = LoggerFactory.getLogger(OrderControler.class);

	@Inject
	private OrderFacade orderFacade;

	@Inject
	private CreatedProductFacade createdProductFacade;

	/**
	 * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Name", "surname":"Surname", "email":"email@mail.com", "role":"SUBMITTER"}' http://localhost:8080/TACOS-rest/api/users/create
	 *
	 * @param orderCreateDTO
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
		log.debug("Rest create Order ({})", orderCreateDTO);

		Long id = orderFacade.create(orderCreateDTO);
		return orderFacade.findById(id);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void deleteOrder(@PathParam("id") Long id) {
		log.debug("Rest delete Order with id ({})", id);

		try {
			orderFacade.delete(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Order not found.");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderDTO> getAll() {
		log.debug("Rest get all orders");

		return orderFacade.getAll();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/filter/{state}")
	public List<OrderDTO> getAllForState(@PathParam("state") OrderState state) {
		log.debug("Rest get all orders for state ({})", state);

		if (state == null){
			throw new IllegalArgumentException("Invalid parameters given.");
		}

		return orderFacade.getAllForState(state);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{orderId}/addCreatedProduct/{createdProductId}")
	public void addCreatedProduct(@PathParam("orderId") Long id, @QueryParam("createdProductId") Long createdProductId) {
		log.debug("Rest add createdProduct with id ({}) to Order with id ({})", id, createdProductId);

		if (orderFacade.findById(id) == null) {
			throw new ResourceNotFoundException("Order with id "+ id +" not found!");
		}
		if (createdProductFacade.findById(createdProductId) == null) {
			throw new ResourceNotFoundException("CreatedProduct with id "+ createdProductId +" not found!");
		}

		orderFacade.addProduct(id, createdProductId);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{orderId}/removeCreatedProduct/{createdProductId}")
	public void removeCreatedProduct(@PathParam("orderId") Long id, @QueryParam("createdProductId") Long createdProductId) {
		log.debug("Rest remove CreatedProduct with id ({}) to Order with id ({})", id, createdProductId);

		if (orderFacade.findById(id) == null) {
			throw new ResourceNotFoundException("Order with id "+ id +" not found!");
		}
		if (createdProductFacade.findById(createdProductId) == null) {
			throw new ResourceNotFoundException("CreatedProduct with id "+ createdProductId +" not found!");
		}

		orderFacade.removeProduct(id, createdProductId);
	}


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/submitOrder/{id}")
	public void submitOrder(@QueryParam("id") Long id) {
		log.debug("Rest submit Order with id ({})", id);

		try {
			orderFacade.submitOrder(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Order not found");
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancelOrder/{id}")
	public void cancelOrder(@QueryParam("id") Long id) {
		log.debug("Rest cancel Order with id ({})", id);

		try {
			orderFacade.cancelOrder(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Order not found");
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/finishOrder/{id}")
	public void finishOrder(@QueryParam("id") Long id) {
		log.debug("Rest finish Order with id ({})", id);

		try {
			orderFacade.finishOrder(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Order not found");
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/processOrder/{id}")
	public void processOrder(@QueryParam("id") Long id) {
		log.debug("Rest process Order with id ({})", id);

		try {
			orderFacade.processOrder(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Order not found");
		}
	}

}
