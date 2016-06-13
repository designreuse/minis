package net.minis.api.spring.data;

import lombok.Data;
import lombok.ToString;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@ToString
public abstract class BaseSearchCondition implements SearchCondition {

    private static final long serialVersionUID = 1L;

    private Integer page;

    private Integer pageSize;

    private Sort sort;

    public Pageable getPageable() {

        if (page == null || pageSize == null) {
            return null;
        }

        if (sort == null) {
            return new PageRequest(page - 1, pageSize);
        }

        return new PageRequest(page - 1, pageSize, sort);
    }

}