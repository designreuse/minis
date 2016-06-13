package net.minis.api.web.kendo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AutoCompleteResult {

    private List<?> results;

    public AutoCompleteResult(List<?> results) {
        this.results = results;
    }

}
