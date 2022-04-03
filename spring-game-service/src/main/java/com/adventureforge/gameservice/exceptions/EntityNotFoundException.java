package com.adventureforge.gameservice.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz, Object... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(
                clazz.getSimpleName(),
                toMap(searchParamsMap)
        ));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return entity.toUpperCase() +
                " was not found for parameters " +
                searchParams;
    }

    private static Map<String, String> toMap(Object... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }

        return IntStream
                .range(0, (entries.length / 2))
                .map(i -> i * 2)
                .collect(
                        HashMap::new,
                        (m, i) -> m.put(entries[i].toString(), entries[i + 1].toString()),
                        Map::putAll
                );
    }

}