package net.minis.api.spring.data;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface SearchCondition extends Serializable {

    Pageable getPageable();

    @SuppressWarnings("rawtypes")
    Specification getSpecification();

}