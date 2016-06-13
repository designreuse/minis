package net.minis.api.web.kendo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.minis.api.convert.ModelConverter;
import net.minis.api.spring.data.MappedSearchCondition;
import net.minis.api.spring.data.SearchOperator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.google.common.collect.Lists;

@Slf4j
public class FilterFormConverter extends ModelConverter<FilterForm, MappedSearchCondition> {

    public MappedSearchCondition convert(FilterForm source, MappedSearchCondition target) {

        MappedSearchCondition searchCondition = new MappedSearchCondition();
        convertPageCondition(source, searchCondition);
        convertSortCondition(source, searchCondition);
        convertSearchCondition(source, searchCondition);

        log.debug("Search Condition = {}.", searchCondition);
        return searchCondition;
    }

    /**
     * Convert paging condition.
     * 
     * @param source
     * @param condition
     */
    private void convertPageCondition(FilterForm source, MappedSearchCondition condition) {

        if (source.getPage() != null) {
            condition.setPage(source.getPage() - 1);
        }

        if (source.getPageSize() != null) {
            condition.setPageSize(source.getPageSize());
        }
    }

    /**
     * convert sort condition.
     * 
     * @param source
     * @param condition
     */
    private void convertSortCondition(FilterForm source, MappedSearchCondition condition) {

        List<Sorted> sorted = source.getSort();

        if (CollectionUtils.isEmpty(sorted)) {
            return;
        }

        List<Order> orders = Lists.newArrayList();

        for (Sorted field : sorted) {
            SortedOperator operator = field.getOperator();
            if (operator == SortedOperator.asc) {
                orders.add(new Order(Direction.ASC, field.getField()));
            } else {
                orders.add(new Order(Direction.DESC, field.getField()));
            }
        }

        condition.setSort(new Sort(orders));
    }

    /**
     * convert search condition.
     * 
     * @param source
     * @param condition
     */
    private void convertSearchCondition(FilterForm source, MappedSearchCondition condition) {

        // check filter is not null;
        Filter filter = source.getFilter();
        if (filter == null) {
            return;
        }

        // check filters is not null or empty.
        List<FilterField> filters = filter.getFilters();
        if (CollectionUtils.isEmpty(filters)) {
            return;
        }

        // start convert.
        for (FilterField field : filters) {
            
            Boolean ignoreCase = field.getIgnoreCase();
            
            if (ignoreCase) {
                convertCaseInsensitiveSearchCondition(field, condition);
            } else {
                convertCaseSensitiveSearchCondition(field, condition);
            }
            
        }
    }

    private void convertCaseSensitiveSearchCondition(FilterField field, MappedSearchCondition condition) {

        String fieldName = field.getField();
        String fieldValue = field.getValue();
        FilterOperator operator = field.getOperator();

        if (StringUtils.isBlank(fieldName)) {
            return;
        }

        switch (operator) {
        case eq:
            condition.addSearch(SearchOperator.eq, fieldName, fieldValue);
            break;
        case contains:
            condition.addSearch(SearchOperator.like, fieldName, fieldValue);
            break;
        case startswith:
            condition.addSearch(SearchOperator.rlike, fieldName, fieldValue);
            break;
        default:
            break;
        }
    
    }

    
    private void convertCaseInsensitiveSearchCondition(FilterField field, MappedSearchCondition condition) {
        
        String fieldName = field.getField();
        String fieldValue = field.getValue();
        FilterOperator operator = field.getOperator();

        if (StringUtils.isBlank(fieldName)) {
            return;
        }

        switch (operator) {
        case eq:
            condition.addSearch(SearchOperator.ieq, fieldName, fieldValue);
            break;
        case contains:
            condition.addSearch(SearchOperator.ilike, fieldName, fieldValue);
            break;
        case startswith:
            condition.addSearch(SearchOperator.irlike, fieldName, fieldValue);
            break;
        default:
            break;
        }
    
    }

}
