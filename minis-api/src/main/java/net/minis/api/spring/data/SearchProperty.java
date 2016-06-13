package net.minis.api.spring.data;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.Data;
import lombok.ToString;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

@Data
@ToString
public class SearchProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    private SearchOperator operator;

    private String propertyName;

    private Object propertyValue;

    public SearchProperty(SearchOperator operator, String propertyName, Object propertyValue) {
        Validate.notNull(operator, "the filter operator could not be null.");
        Validate.notBlank(propertyName, "the filter property name could not be null or blank.");

        this.operator = operator;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public Predicate getPredicate(Root<?> root, CriteriaBuilder cb) {

        Expression<?> expression;

        if (StringUtils.contains(propertyName, ".")) {
            expression = getJoinExpression(root, propertyName);
        } else {
            expression = root.get(propertyName);
        }

        Object convertedValue = ConvertUtils.convert(propertyValue, expression.getJavaType());
        return operator.getPredicate(expression, cb, propertyName, convertedValue);
    }

    private Expression<?> getJoinExpression(Root<?> root, String propertyName) {
        String[] propertyNames = StringUtils.split(propertyName, ".");
        return root.join(propertyNames[0]).get(propertyNames[1]);
    }

}
