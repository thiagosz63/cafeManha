package com.fcb.cafeDaManha.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fcb.cafeDaManha.controller.exception.FieldMessage;
import com.fcb.cafeDaManha.dto.ColaboradorDTO;
import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.service.validation.utils.BR;

public class ColaboradorInsertValidator implements ConstraintValidator<ColaboradorInsert, ColaboradorDTO> {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Override
	public boolean isValid(ColaboradorDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (!BR.isValidCPF(objDTO.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}
		
		Colaborador col = colaboradorRepository.buscarPorCpf(objDTO.getCpf());
		if (col != null) {
			list.add(new FieldMessage("cpf", "CPF já cadastrado"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
