package br.com.restwith.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.restwith.data.model.Person;
import br.com.restwith.data.vo.PersonVO;

public class MockPerson {
	
	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockListEntity() {
		List<Person> listEntity = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			listEntity.add(mockEntity(i));
		}
		return listEntity;
	}
	
	public List<PersonVO> mockListVO() {
		List<PersonVO> listVo = new ArrayList<PersonVO>();
		for (int i = 0; i < 10; i++) {
			listVo.add(mockVO(i));
		}
		return listVo;
	}
	
	private Person mockEntity(Integer number) {
		Person person = new Person();
		person.setAddress("Address " + number);
		person.setFirstName("Fisrt name " + number);
		person.setGender(((number % 2)==0)?"Male":"Female");
		person.setId(number.longValue());
		person.setLastName("Lest name "+number);
		return person;
	}
	
	private PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setAddress("Address " + number);
		person.setFirstName("Fisrt name " + number);
		person.setGender(((number % 2)==0)?"Male":"Female");
		person.setKey(number.longValue());
		person.setLastName("Lest name "+number);
		return person;
	}

}
