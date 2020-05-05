package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Flat;
import association.web.dto.FlatDTO;

@Component
public class FlatToFlatDTO implements Converter<Flat, FlatDTO> {

	@Override
	public FlatDTO convert(Flat source) {
		FlatDTO dto = new FlatDTO();

		dto.setId(source.getId());
		dto.setAddress(source.getAddress());

		return dto;
	}

	public List<FlatDTO> convert(List<Flat> sourceList) {
		List<FlatDTO> dtoList = new ArrayList<>();

		for (Flat source : sourceList) {
			dtoList.add(convert(source));
		}

		return dtoList;
	}

}
