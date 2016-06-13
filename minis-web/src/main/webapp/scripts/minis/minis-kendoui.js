
(function($, kendo) {

    kendo.ui.plugin(kendo.ui.Grid.extend({

        options : {
            name : "MinisGrid",
            sortable : true,
            scrollable : true,
            filterable : true,
        },

        init : function(element, options) {

            var that = this;

            if (options.editable === "inline") {
                
                // if inline edit disable button text.
                options.edit = function() {
                    $(".k-grid-update")
                        .css("min-width", "16px")
                        .html("<span class='k-icon k-update'></span>")
                        .removeClass("k-button-icontext");
                    $(".k-grid-cancel")
                        .css("min-width", "16px")
                        .html("<span class='k-icon k-cancel'></span>")
                        .removeClass("k-button-icontext");
                }
            }

            // call papa.
            kendo.ui.Grid.fn.init.call(that, element, options);
        }

    }));

})(window.kendo.jQuery, window.kendo);
