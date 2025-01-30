package com.coderhouse.dtos;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaDto {
	private Long clieId;
	private List<Long> productoIds;
	private List<Integer> cantidades;
	private String metodoPago;
}
