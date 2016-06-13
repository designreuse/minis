
function KendoGridOptions() { };

KendoGridOptions.FILTERABLE_CONTAINS = {
    cell : {
        showOperators: false,
        operator : "contains",
        ignoreCase: true,
        delay: 600
    }
};

KendoGridOptions.FILTERABLE_EQUALS = {
    cell : {
        showOperators: false,
        operator : "eq",
        ignoreCase: true,
        delay: 600
    }
};