package DroneDeliveryApp.src.domain;

import java.security.SecureRandom;
import DroneDeliveryApp.src.model.enums.IdType;

public class IdentificationNumber {
    // Character pool for generating the ID
    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom(); // Use SecureRandom for better randomness

    private final String id;

    public IdentificationNumber(IdType type) {
        this.id = type.getPrefix() + generateRandomId();
    }

    private String generateRandomId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            sb.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentificationNumber that = (IdentificationNumber) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "domain.IdentificationNumber{" +
                "id='" + id + '\'' +
                '}';
    }
}