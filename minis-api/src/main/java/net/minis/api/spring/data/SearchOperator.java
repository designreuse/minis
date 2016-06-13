package net.minis.api.spring.data;

import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;

public enum SearchOperator {

    eq {
        @SuppressWarnings({ "rawtypes" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            return cb.equal(expression, propertyValue);
        }
    },
    ieq {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String value = propertyValue != null ? propertyValue.toString() : null;
            return cb.equal(cb.upper(expression), Objects.toString(propertyValue, value));
        }
    },
    like {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String pattern = "%" + Objects.toString(propertyValue, "") + "%";
            return cb.like(expression, pattern);
        }
    },
    ilike {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String pattern = "%" + Objects.toString(propertyValue, "") + "%";
            return cb.like(cb.upper(expression), StringUtils.upperCase(pattern));
        }
    },
    llike {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String pattern = "%" + Objects.toString(propertyValue, "");
            return cb.like(expression, pattern);
        }
    },
    rlike {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String pattern = Objects.toString(propertyValue, "") + "%";
            return cb.like(expression, pattern);
        }
    }, 
    irlike {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue) {
            String pattern = Objects.toString(propertyValue, "") + "%";
            return cb.like(cb.upper(expression), StringUtils.upperCase(pattern));
        }
    };

    @SuppressWarnings({ "rawtypes" })
    abstract public Predicate getPredicate(Expression expression, CriteriaBuilder cb, String propertyName, Object propertyValue);

}
