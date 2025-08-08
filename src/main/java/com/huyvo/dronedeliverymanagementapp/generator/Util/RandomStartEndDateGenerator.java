package com.huyvo.dronedeliverymanagementapp.generator.Util;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomStartEndDateGenerator {
    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 12;
    private static final int MIN_DELIVERY_OFFSET_HOURS = 1;
    private static final int MAX_DELIVERY_OFFSET_HOURS = 5;
    private static final int MAX_MINUTE = 60;

    private final Random random;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public RandomStartEndDateGenerator(int year, int month, int day, Random random) {
        this.random = random;
        LocalDateTime[] times = generateStartEndDate(year, month, day);
        this.startTime = times[0];
        this.endTime = times[1];
    }

    private LocalDateTime @NotNull [] generateStartEndDate(int year, int month, int day) {
        int dayHourRange = MAX_HOUR - MIN_HOUR;
        int randomPickupHour = random.nextInt(dayHourRange);
        int randomPickupMinute = random.nextInt(MAX_MINUTE);

        LocalDateTime pickup = LocalDateTime.of(year, month, day, randomPickupHour, randomPickupMinute);

        int deliveryOffsetHours = MIN_DELIVERY_OFFSET_HOURS + random.nextInt(MAX_DELIVERY_OFFSET_HOURS);
        int deliveryOffsetMinutes = random.nextInt(MAX_MINUTE);

        LocalDateTime delivery = pickup.plusHours(deliveryOffsetHours).plusMinutes(deliveryOffsetMinutes);

        return new LocalDateTime[]{pickup, delivery};
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }
}

