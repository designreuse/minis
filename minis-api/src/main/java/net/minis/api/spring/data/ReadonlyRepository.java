package net.minis.api.spring.data;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface ReadonlyRepository<T, ID extends Serializable> 
        extends Repository<T, ID>, JpaSpecificationExecutor<T> {

}
