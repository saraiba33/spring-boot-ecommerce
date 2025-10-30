package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };

        //disable http methods for Product: PUT, POST, DELETE
        disableHttpMethod(Product.class, config, theUnsupportedActions);
        disableHttpMethod(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethod(Country.class, config, theUnsupportedActions);
        disableHttpMethod(State.class, config, theUnsupportedActions);
    }

    private static void disableHttpMethod(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpmethods) -> httpmethods.disable(theUnsupportedActions))
                .withCollectionExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
