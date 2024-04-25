package com.fiap.food.enums;

import com.fiap.food.core.exception.NotFoundException;

public enum OrderStatus {
    RECEIVED("RECEIVED"),
    IN_PREPARATION("IN_PREPARATION"),
    READY("READY"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    private String statusOrder;

    OrderStatus(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public static OrderStatus fromString(String text) throws NotFoundException {
        for(OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.statusOrder.equals(text.toUpperCase())) {
                return orderStatus;
            }
        }
        throw new NotFoundException("Status de order não encontrado: " + text);
    }

}
