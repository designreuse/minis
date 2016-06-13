
var Minis = Minis || {};
Minis.Collections = Minis.Collections || {};

Minis.Collections.complement = function(arr1, arr2) {
    var diff = $.grep(arr1, function(e) {
        for(var i in arr2) {
            if(e.id === arr2[i].id) {
                return false;
            }
        }
        return true;
    });
}

$(document).ready(function() {
    Minis.registerPageResize();
    setTimeout(function() {
        $(document).trigger('resize');
    }, 20);
});


var MinisOptions = {
	baseUrl : "/minis-web"
} 

Minis.registerPageResize = function() {
    $(window).resize(Minis.pageResize);
    $(document).resize(Minis.pageResize);
};

Minis.pageResize = function() {
   
	var paddingSize = 12;
	
    $("#content").height(0);

    var windowHeight = $(window).outerHeight();
    var contentHeight = $("#content")[0].scrollHeight;
    var otherHeight = $("#header").outerHeight() + $("#footer").outerHeight();

    if (windowHeight >= (contentHeight + otherHeight)) {
        $("#content").height(windowHeight - otherHeight - paddingSize);
    } else {
        $("#content").height(contentHeight - paddingSize);
    }
};
