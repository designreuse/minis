package net.minis.api.web.kendo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AutoCompleteView {

    private AutoCompleteResult d;

    private long total;

    private long totalPages;

    public AutoCompleteView(List<?> results) {
        this.d = new AutoCompleteResult(results);
    }

    public AutoCompleteView(List<?> results, long total, long totalPages) {
        this.d = new AutoCompleteResult(results);
        this.total = total;
        this.totalPages = totalPages;
    }

    public void setResults(List<?> results) {
        this.d = new AutoCompleteResult(results);
    }

}
