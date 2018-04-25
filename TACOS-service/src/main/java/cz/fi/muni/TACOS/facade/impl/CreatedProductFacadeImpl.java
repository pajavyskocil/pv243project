package cz.fi.muni.TACOS.facade.impl;

import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductDTO;
import cz.fi.muni.TACOS.exceptions.InvalidRelationEntityIdException;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.enums.OrderState;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.CreatedProductService;
import cz.fi.muni.TACOS.service.OrderService;
import cz.fi.muni.TACOS.service.ProductService;
import cz.fi.muni.TACOS.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Implementation of CreatedProductFacade interface
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Transactional(rollbackOn = { Exception.class })
@ApplicationScoped
public class CreatedProductFacadeImpl implements CreatedProductFacade {

	private final CreatedProductService createdProductService;

	private final AttributeService attributeService;

	private final BeanMappingService beanMappingService;

	private final UserService userService;

	private final OrderService orderService;

	private final ProductService productService;

	@Inject
	public CreatedProductFacadeImpl(CreatedProductService createdProductService, AttributeService attributeService,
									BeanMappingService beanMappingService, UserService userService, OrderService orderService,
									ProductService productService) {
		this.createdProductService = createdProductService;
		this.attributeService = attributeService;
		this.beanMappingService = beanMappingService;
		this.userService = userService;
		this.orderService = orderService;
		this.productService = productService;
	}

	@Override
	public Long create(CreatedProductCreateDTO entity, Long userId) throws InvalidRelationEntityIdException {
		CreatedProduct createdProduct = beanMappingService.mapTo(entity, CreatedProduct.class);
		BigDecimal price = calculatePrice(entity);
		createdProduct.setPrice(price);
		createdProductService.create(createdProduct);
		Product product = productService.findById(entity.getProductId());
		if (product == null) {
			throw new InvalidRelationEntityIdException("Product for given id does not exist. id" + entity.getProductId());
		}
		product.addCreatedProduct(createdProduct);


		User user = userService.findById(userId);
		if (user == null) {
			throw new InvalidRelationEntityIdException("User for given id does not exist. id: " + userId);
		}
		Set<Order> userOrders = user.getOrders();

		Order openedOrder = null;

		for (Order order : userOrders) {
			if (order.getState().equals(OrderState.BASKET)) {
				openedOrder = order;
				break;
			}
		}
		if (openedOrder == null) {
			openedOrder = new Order();
			openedOrder.setState(OrderState.BASKET);
			openedOrder.setPrice(BigDecimal.ZERO);
			user.addSubmittedOrder(openedOrder);
			orderService.create(openedOrder);
		}

		for (Long attrId : entity.getAttributeIds()) {
			Attribute attribute = attributeService.findById(attrId);
			if (attribute == null) {
				throw new InvalidRelationEntityIdException("Attribute for given id does not exist. id: " + attrId);
			}

			createdProductService.addAttribute(createdProduct, attribute);
			price = price.add(attribute.getPrice());
		}

		orderService.addProduct(openedOrder, createdProduct);
		return createdProduct.getId();
	}

	/**
	 * Calculates price of created product
	 *
	 * @param product specification
	 * @return price
	 * @throws InvalidRelationEntityIdException when attributes for given ids dont exist
	 */
	private BigDecimal calculatePrice(CreatedProductCreateDTO product) throws InvalidRelationEntityIdException {
		BigDecimal price = BigDecimal.ZERO;
		for (Long attrId : product.getAttributeIds()) {
			Attribute attribute = attributeService.findById(attrId);
			if (attribute == null) {
				throw new InvalidRelationEntityIdException("Attribute for given id does not exist. id: " + attrId);
			}

			price = price.add(attribute.getPrice());
		}
		return price;
	}

	@Override
	public void delete(Long id) {
		createdProductService.delete(createdProductService.findById(id));
	}

	@Override
	public CreatedProductDTO findById(Long id) {
		return beanMappingService.mapTo(createdProductService.findById(id), CreatedProductDTO.class);
	}

	@Override
	public List<CreatedProductDTO> getAll() {
		return beanMappingService.mapTo(createdProductService.getAll(), CreatedProductDTO.class);
	}

	@Override
	public void addAttribute(Long createdProductId, Long attributeId) {
		createdProductService.addAttribute(createdProductService.findById(createdProductId), attributeService.findById(attributeId));
	}

	@Override
	public void removeAttribute(Long createdProductId, Long attributeId) {
		createdProductService.removeAttribute(createdProductService.findById(createdProductId), attributeService.findById(attributeId));
	}
}
