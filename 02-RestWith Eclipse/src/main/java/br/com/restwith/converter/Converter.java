package br.com.restwith.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class Converter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O,D> D parse(O origin, Class<D> destion){
		return mapper.map(origin,destion);
	}
	
	public static <O,D> List<D> parseList(List<O> origin, Class<D> destion){
		List<D> destinationObj = new ArrayList<D>();

		for (Object obj : origin) {
			destinationObj.add(mapper.map(obj,destion));
		}
		return destinationObj;
	}
	
}
