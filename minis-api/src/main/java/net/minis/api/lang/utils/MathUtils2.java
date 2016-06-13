package net.minis.api.lang.utils;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.math.BigDecimal;
import java.util.Collection;

public class MathUtils2 {

    private MathUtils2() {
    }

    final public static BigDecimal summary(Collection<BigDecimal> values, BigDecimal defaultValue) {

        if (isEmpty(values)) {
            return defaultValue;
        }

        BigDecimal results = ZERO;

        for (BigDecimal value : values) {
            results = results.add(value);
        }

        return results;

    }

}
