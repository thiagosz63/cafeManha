package com.fcb.cafeDaManha.service.utils;

public class Utilitarios {
	// CPF
	private static final int[] validacao = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	private static int calculo(final String str, final int[] weight) {
		int sum = 0;
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}

	public static boolean isValidCPF(final String cod) {
		if ((cod == null) || (cod.length() != 11) || cod.matches(cod.charAt(0) + "{11}"))
			return false;

		final Integer digit1 = calculo(cod.substring(0, 9), validacao);
		final Integer digit2 = calculo(cod.substring(0, 9) + digit1, validacao);
		return cod.equals(cod.substring(0, 9) + digit1.toString() + digit2.toString());
	}
	
}
