package com.settipalli.todo.util;

import com.settipalli.todo.model.Todo;

public class PayloadValidator {

    public static boolean validateCreatePaylod(Todo payload) {
        return payload.getId() > 0;
    }
}
