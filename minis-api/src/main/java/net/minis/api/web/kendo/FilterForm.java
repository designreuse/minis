package net.minis.api.web.kendo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FilterForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer take;

    private Integer skip;

    private Integer page;

    private Integer pageSize;

    private Filter filter;

    private List<Sorted> sort;

    // Kendo UI Filter 所產生的資料結構大約如下:
    // {
    //      "take":5,
    //      "skip":0,
    //      "page":1,
    //      "pageSize":5,
    //      "filter": {
    //          "logic":"and",
    //          "filters":[
    //              {
    //                  "logic":"or",
    //                  "filters":[
    //                      {
    //                          "field":"id",
    //                          "operator":"eq",
    //                          "value":"ADD_USER"
    //                      },
    //                      {
    //                          "field":"id",
    //                          "operator":"eq",
    //                          "value":"UPDATE_USER"
    //                      }
    //                  ]
    //              },
    //              {
    //                  "field":"name",
    //                  "operator":"eq",
    //                  "value":"修改使用者按鈕"
    //              }
    //          ]
    //      }
    // }

}
