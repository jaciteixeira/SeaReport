package br.com.fiap.seareport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    DESPEJO_ILEGAL_RESIDUOS(1L, "Despejo Ilegal de Resíduos", "Relatos de despejo ilegal de resíduos no mar."),
    DERRAMAMENTO_OLEO(2L, "Derramamento de Óleo", "Relatos de derramamento de óleo no mar."),
    PESCA_ILEGAL(3L, "Pesca Ilegal", "Relatos de pesca ilegal."),
    PESCA_PREDATORIA(4L, "Pesca Predatória", "Relatos de pesca predatória."),
    OUTROS(5L, "Outros", "Qualquer outro tipo de incidente não coberto pelas categorias acima.");

    private Long id;
    private String name;
    private String description;

    public static Category fromId(int id) {
        for (Category category : Category.values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category id: " + id);
    }
}

