package com.target.myretail.process;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.myretail.dao.ProductDo;
import com.target.myretail.dao.ProductDo.Price;
import com.target.myretail.doc.CurrentPrice;
import com.target.myretail.doc.Product;
import com.target.myretail.dao.ProductRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductPriceProcessorTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductPriceProcessor productPriceProcessor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProduct() throws Exception {

		// Prepare mock data
		ProductDo nameDo = new ProductDo();
		nameDo.setId(1.0);
		Price price = nameDo.new Price();
		price.setCurrency("USD");
		price.setValue(100.00);
		nameDo.setCurrentPrice(price);
		List<ProductDo> productDos = new ArrayList<>();
		productDos.add(nameDo);
		when(productRepository.findById(1)).thenReturn(productDos);

		// Invoke test
		ProductDo result = productPriceProcessor.getProduct(1);

		// Assert the results
		assertEquals(1.0, result.getId().doubleValue(), 1.0);
		assertEquals(100.00, result.getCurrentPrice().getValue(), 100.0);
		assertEquals("USD", result.getCurrentPrice().getCurrency());
	}

	@Test
	public void testUpdateProduct() throws Exception {

		// Prepare mock data
		ProductDo nameDo = new ProductDo();
		nameDo.setId(2.0);
		Price price = nameDo.new Price();
		price.setCurrency("USD");
		price.setValue(200.00);
		nameDo.setCurrentPrice(price);
		List<ProductDo> productDos = new ArrayList<>();
		productDos.add(nameDo);

		Product product = new Product();
		product.setId(2);
		CurrentPrice reqPrice = new CurrentPrice();
		reqPrice.setCurrencyCode("USD");
		reqPrice.setValue(210.00);
		product.setCurrentPrice(reqPrice);

		ProductDo resultDo = new ProductDo();
		resultDo.setId(2.0);
		Price resultPrice = resultDo.new Price();
		resultPrice.setCurrency("USD");
		resultPrice.setValue(210.00);
		resultDo.setCurrentPrice(resultPrice);

		when(productRepository.findById(2)).thenReturn(productDos);
		when(productRepository.save(Mockito.any(ProductDo.class))).thenReturn(resultDo);

		// Invoke test
		ProductDo result = productPriceProcessor.updateProduct(product);

		// Assert the results
		assertEquals(2.0, result.getId().doubleValue(), 2.0);
		assertEquals(210.00, result.getCurrentPrice().getValue(), 210.0);
		assertEquals("USD", result.getCurrentPrice().getCurrency());
	}

}
