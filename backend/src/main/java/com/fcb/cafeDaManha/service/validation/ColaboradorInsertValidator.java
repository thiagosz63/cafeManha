package com.fcb.cafeDaManha.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fcb.cafeDaManha.controller.exception.FieldMessage;
import com.fcb.cafeDaManha.dto.ColaboradorNewDTO;
import com.fcb.cafeDaManha.service.validation.utils.BR;

public class ColaboradorInsertValidator implements ConstraintValidator<ColaboradorInsert, ColaboradorNewDTO> {

	@Override
	public boolean isValid(ColaboradorNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (!BR.isValidCPF(objDTO.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
