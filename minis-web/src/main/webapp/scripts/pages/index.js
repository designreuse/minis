/**
 * Created by admin on 2013/12/3.
 */

$(document).ready(function () {
    
    $("#project-grid-view").kendoGrid({
        dataSource: {
            type: "jsonp",
            transport: {
                read: "./project/list.json"
            },
            pageSize: 10
        },
        sortable: true,
        groupable: false,
        pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 5
        },
        columns: [{
            field: "id",
            title: "No.",
            width: 30
        }, {
            field: "code",
            title: "專案代號",
            width: 80
        }, {
            field: "name",
            title: "專案名稱",
            width: 250
        }, {
            field: "startDate",
            title: "開始日期",
            template: '#= kendo.toString(kendo.parseDate(startDate), "yyyy/MM/dd") #',
            width: 100
        }, {
            field: "closeDate",
            title: "結束日期",
            template: '#= kendo.toString(kendo.parseDate(closeDate), "yyyy/MM/dd") #',
            width: 100
        }, {
            field: "kickoffDate",
            title: "開工日期",
            template: '#= kendo.toString(kendo.parseDate(kickoffDate), "yyyy/MM/dd") #',
            width: 100,
        }]
    });
});
