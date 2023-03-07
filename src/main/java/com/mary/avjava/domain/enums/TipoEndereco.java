package com.mary.avjava.domain.enums;

public enum TipoEndereco {

	PRINCIAL(1,"Endereço principal"),
	SECUNDARIO(2, "Encedereço Secundário");
	
	private int cod;
	private String descricao;
	
	private TipoEndereco (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public static TipoEndereco toEnum(Integer cod) {
		if(cod==null)
			
		{return null;}
		for (TipoEndereco x : TipoEndereco.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id Invalido: "+ cod);
	}

}
