package com.fcb.cafeDaManha.entities.enums;


public enum ItemStatus {
	Disponivel(1, "Disponivel"),
	NaoDisponivel(2, "Não Disponivel"); 

	private int cod;
	private String description;

	private ItemStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static ItemStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (ItemStatus x : ItemStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id invalido " + cod);
	}

}
