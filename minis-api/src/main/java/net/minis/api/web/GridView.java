package net.minis.api.web;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GridView implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long page;

    public Long pageSize;

    public Long total;

    public Long totalPages;

    public List<?> contents;

    public void setPage(long page) {
        this.page = page;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

}
