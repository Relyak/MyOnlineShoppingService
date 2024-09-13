package com.myonlineshopping.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingresos {
    private Long idCuenta;
    private Long idPropietario;
    private Integer dinero;
}
