package com.target.myretail.process;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.myretail.client.RestClient;
import com.target.myretail.dao.NameDo;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductNameProcessorTest {

	@Mock
	RestClient restClient;

	@InjectMocks
	ProductNameProcessor productNameProcessor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProductName() throws Exception {
		NameDo nameDo = new NameDo();
		nameDo.setId(1.0);
		nameDo.setName("TestName");
		when(restClient.getProductName(1)).thenReturn(nameDo);
		String name = productNameProcessor.getProductName(1);
		Assert.assertEquals("TestName", name);
	}

	@Test
	public void testGetProductNameDefault() throws Exception {
		NameDo nameDo = new NameDo();
		nameDo.setId(2.0);
		nameDo.setName("DefaultName");
		when(restClient.getProductName(2)).thenReturn(nameDo);
		String name = productNameProcessor.getProductName(2);
		Assert.assertEquals("DefaultName", name);
	}

}
