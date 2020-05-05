package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.model.Flat;
import association.service.FlatService;
import association.web.dto.FlatDTO;

@Component
public class FlatDTOToFlat implements Converter<FlatDTO, Flat> {

	@Autowired
	private FlatService flatService;

	@Override
	public Flat convert(FlatDTO dto) {

		Flat flat = null;

		if (dto.getId() != null) {
			flat = flatService.findOne(dto.getId());
		} else {
			flat = new Flat();
		}

		flat.setAddress(dto.getAddress());
		flat.setPresident(dto.getPresident());
		flat.setNoOfApartments(dto.getNoOfApartments());
		flat.setNoOfTenants(dto.getNoOfTenants());

		return flat;
	}

	public List<Flat> convert(List<FlatDTO> dtoList) {
		List<Flat> ret = new ArrayList<>();

		for (FlatDTO dto : dtoList) {
			ret.add(convert(dto));
		}

		return ret;
	}

}
