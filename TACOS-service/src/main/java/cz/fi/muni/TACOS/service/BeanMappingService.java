package cz.fi.muni.TACOS.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface BeanMappingService {

	<T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

	<T> T mapTo(Object o, Class<T> mapToClass);

	Mapper getMapper();
}
