package br.com.restwith.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.restwith.converter.mocks.MockPerson;
import br.com.restwith.data.model.Person;
import br.com.restwith.data.vo.PersonVO;

public class DozerConverterTest {
	
	MockPerson inObject;
	
	@Before
	public void setUp() {
		inObject = new MockPerson();
	}
	
	@Test
	public void parseEntityVoTest() {
		PersonVO out = Converter.parse(inObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0l), out.getKey());
		Assert.assertEquals("Fisrt name 0", out.getFirstName());
	}
	
	@Test 
	public void persoEntityListToVoListTest() {
		List<PersonVO> outList = Converter.parseList(inObject.mockListEntity(), PersonVO.class);
		PersonVO out = outList.get(0);
		Assert.assertEquals(Long.valueOf(0l), out.getKey());
		Assert.assertEquals("Fisrt name 0", out.getFirstName());
		
		PersonVO out7 = outList.get(7);
		Assert.assertEquals(Long.valueOf(7l), out7.getKey());
		Assert.assertEquals("Fisrt name 7", out7.getFirstName());
	}
	
	
	@Test
	public void parseVoTest() {
		Person out = Converter.parse(inObject.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0l), out.getId());
		Assert.assertEquals("Fisrt name 0", out.getFirstName());
	}
	
	@Test 
	public void persoVoListToEntityListTest() {
		List<Person> outList = Converter.parseList(inObject.mockListVO(), Person.class);
		Person out = outList.get(0);
		Assert.assertEquals(Long.valueOf(0l), out.getId());
		Assert.assertEquals("Fisrt name 0", out.getFirstName());
		
		Person out7 = outList.get(7);
		Assert.assertEquals(Long.valueOf(7l), out7.getId());
		Assert.assertEquals("Fisrt name 7", out7.getFirstName());
	}

}
