package net.minis.api.spring.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.ToString;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

@ToString
@SuppressWarnings("rawtypes")
public class MappedSearchCondition extends BaseSearchCondition {

    private static final long serialVersionUID = 1L;

    private List<SearchProperty> search;

    public MappedSearchCondition() {
        search = Lists.newArrayList();
    }

    public void addSearch(SearchOperator operator, String propertyName, Object propertyValue) {
        search.add(new SearchProperty(operator, propertyName, propertyValue));
    }

    @Override
    public Specification<?> getSpecification() {

        return new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

                Predicate conjunction = cb.conjunction();

                for (SearchProperty operator : search) {
                    Predicate predicate = operator.getPredicate(root, cb);
                    conjunction = cb.and(predicate);
                }

                return conjunction;
            }

        };
    }

}
