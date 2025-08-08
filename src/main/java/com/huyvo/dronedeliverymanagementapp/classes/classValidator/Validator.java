package com.huyvo.dronedeliverymanagementapp.classes.classValidator;

public interface Validator<T> {
    void validate(T t) throws ValidationException;
}

