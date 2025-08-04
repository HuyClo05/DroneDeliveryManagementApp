package DroneDeliveryApp.src.validation;

public interface Validator<T> {
    void validate(T t) throws ValidationException;
}

