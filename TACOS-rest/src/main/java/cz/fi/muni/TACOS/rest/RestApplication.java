package cz.fi.muni.TACOS.rest;

import cz.fi.muni.TACOS.rest.controllers.OrderController;
import cz.fi.muni.TACOS.rest.controllers.UserController;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@ApplicationScoped
@ApplicationPath(ApiUris.REST_ROOT)
public class RestApplication extends Application {

	@Inject
	private SampleData sampleData;

	private Set<Object> singletons = new HashSet<>();
	private HashSet<Class<?>> classes = new HashSet<>();

	public RestApplication()
	{
		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
		singletons.add(corsFilter);

		classes.add(OrderController.class);
		classes.add(UserController.class);
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public HashSet<Class<?>> getClasses(){
		return classes;
	}

	@PostConstruct
	public void init() {
		try {
			sampleData.loadOrderSampleData();
			sampleData.loadUserSampleData();
			sampleData.loadAttributeCategorySampleData();
			sampleData.loadAttributeSampleData();
			sampleData.loadCreatedProductSampleData();
			sampleData.loadProductCategorySampleData();
			sampleData.loadProductSampleData();
			sampleData.loadTemplateSampleData();
		} catch (Exception e) {

		}
	}
}
