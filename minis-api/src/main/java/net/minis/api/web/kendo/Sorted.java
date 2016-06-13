package net.minis.api.web.kendo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.google.gson.annotations.SerializedName;

@Data
@ToString
public class Sorted implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;

    @SerializedName("dir")
    private SortedOperator operator;

}
