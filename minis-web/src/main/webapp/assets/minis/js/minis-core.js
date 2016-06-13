
///////////////////////////////////////////////////////////////////////////////
// Add string internal function.

String.prototype.abbr = String.prototype.abbr || function(n) {
	return (this.length > n) ? this.substr(0, n - 1) + ' ...' : this;
};


function Minis() { };

Minis.getBaseUrl = function(url) {

	var baseUrl = $('meta[name="ContentPath"]').attr("content");

	if (baseUrl !== undefined && url !== undefined) {
        if (url.indexOf("~/") == 0) {
            return baseUrl + url.substring(1);
        }
    }

    return url;
};

Minis.CallService = function(type, url, data) {
	var baseUrl = Minis.getBaseUrl(url);
	return $.ajax({
		url : baseUrl,
		type : type,
		contentType : "application/json",
		data : JSON.stringify(data)
	});
};

Minis.RedirectPage = function(url) {
	var baseUrl = Minis.getBaseUrl(url);
	window.location.replace(baseUrl);
};

/*******************************************************************
 * The Validation.
 *******************************************************************/

function Validation() { };

Validation.bindErrorMessages = function(xhr) {

    Validation.clearErrorMessage();

    var statusCode = xhr.status;
    if(statusCode === 400 || statusCode === 412){
        var messages = $.parseJSON(xhr.responseText);
        $.each(messages.fieldErrors, function(index, value){
            var errorMessages = value.errorMessage.join();
            $('#error_' + value.field).text(errorMessages);
        });
    }
};

Validation.clearErrorMessage = function() {
    $('span[id^="error_"]').text('');
};

/*******************************************************************
 * The Integrate Noty to Message Box.
 *******************************************************************/

function MessageBox() { };
	
MessageBox.init = function() {
		
	var overrideTopCenter = {
		css : {
			display : 'none',
			width   : '500px',
            'margin-left' : '-110px',
		}
	};

	$.extend($.noty.layouts.topCenter, overrideTopCenter);
};

MessageBox.success = function(message) {
	MessageBox.init();
	noty({
		layout      : "topCenter",
		type        : "success",
		text        : message,
		dismissQueue: true,
		timeout     : 3000
	});
};

MessageBox.info = function(message) {
	MessageBox.init();
	noty({
		layout      : "topCenter",
		type        : "information",
		text        : message,
		dismissQueue: true,
		timeout     : 3000
	});
};

MessageBox.warn = function(message) {
	MessageBox.init();
	noty({
		layout      : "topCenter",
		type        : "warning",
		text        : message,
		dismissQueue: true,
		timeout     : 3000
	});
};

MessageBox.error = function(message) {
	MessageBox.init();
	noty({
		layout      : "topCenter",
		type        : "error",
		text        : message,
		dismissQueue: true,
		timeout     : 3000
	});
};

/*******************************************************************
 * The Block UI 
 ******************************************************************/

$.blockUI.defaults = {
        message: "<div class='loading-page' />", 
        css: { 
            margin          : 0, 
            padding         : '10px', 
            width           : 'auto', 
            top             : '35%', 
            left            : '45%', 
            cursor          : 'wait', 
            textAlign       : 'center',
            color           : '#000',
            border          : '0px solid #aaa',
            backgroundColor : '#66ceff',
            'border-radius' : '10px',
            '-moz-border-radius'   : '10px', 
            '-webkit-border-radius': '10px',
            cursor          : 'wait'
        }, 
        themedCSS: { 
            width:  'auto', 
            top:    '35%', 
            left:   '45%' 
        }, 
        overlayCSS:  { 
            opacity:         0.2, 
            cursor:          'wait',
            backgroundColor: '#000', 
        }, 
        // styles applied when using $.growlUI
        growlCSS: {
            width      : '350px',
            top        : '10px',
            left       : '',
            right      : '10px',
            border     : 'none',
            padding    : '5px',
            opacity    : 0.2,
            cursor     : 'default',
            color      : '#fff',
            backgroundColor: '#000',
            'border-radius':        '10px',
            '-webkit-border-radius':'10px',
            '-moz-border-radius':   '10px',
        },

        title: null,
        theme: false,
        draggable: true,
        cursorReset: 'default',

        iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank',
        forceIframe: false,

        baseZ: 1000,
        centerX: true,
        centerY: true,

        allowBodyStretch: true,
        bindEvents: true,
        constrainTabKey: true,
        fadeIn : 50,
        fadeOut: 400,
        timeout: 0,
        showOverlay: true,
        focusInput: true,
        focusableElements: ':input:enabled:visible',
        onBlock: null,
        onUnblock: null,
        onOverlayClick: null,
        quirksmodeOffsetHack: 4,
        blockMsgClass: 'blockMsg',
        ignoreIfBlocked: false,
};

$.blockUI.widget.defaults = {
        message: "<div class='loading-widget' />", 
        css: { 
            margin          : 0, 
            padding         : '10px', 
            width           : 'auto', 
            top             : '35%', 
            left            : '35%', 
            cursor          : 'wait', 
            textAlign       : 'center',
            border          : '0px solid #aaa',
            backgroundColor : '#FFF',
            'border-radius' : '10px',
            '-moz-border-radius'   : '10px', 
            '-webkit-border-radius': '10px'
        }, 
        themedCSS: { 
            width:  'auto', 
            top:    '35%', 
            left:   '45%' 
        }, 
        overlayCSS:  { 
            opacity:         0.3, 
            cursor:          'wait',
            backgroundColor: '#000', 
        }, 
        // styles applied when using $.growlUI
        growlCSS: {
            width      : '350px',
            top        : '10px',
            left       : '',
            right      : '10px',
            border     : 'none',
            padding    : '5px',
            opacity    : 0.3,
            cursor     : 'default',
            color      : '#fff',
            backgroundColor: '#000',
            'border-radius':        '10px',
            '-webkit-border-radius':'10px',
            '-moz-border-radius':   '10px',
        },

        title: null,
        theme: false,
        draggable: true,
        cursorReset: 'default',

        iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank',
        forceIframe: false,

        baseZ: 1000,
        centerX: true,
        centerY: true,

        allowBodyStretch: true,
        bindEvents: true,
        constrainTabKey: true,
        fadeIn:  200,
        fadeOut:  400,
        timeout: 0,
        showOverlay: true,
        focusInput: true,
        focusableElements: ':input:enabled:visible',
        onBlock: null,
        onUnblock: null,
        onOverlayClick: null,
        quirksmodeOffsetHack: 4,
        blockMsgClass: 'blockMsg',
        ignoreIfBlocked: false,
};

/*******************************************************************
 * The LayoutManager.
 *******************************************************************/

function LayoutManager(options) {

	var _options = options;

	this.autofitByContent = function() {
		$(window).resize(resizeByContent);
		$(document).resize(resizeByContent);
		$(document).trigger('resize');
	}

	this.autofitByWindow = function() {
		$(window).resize(resizeByWindow);
		$(document).resize(resizeByWindow);
		$(document).trigger('resize');
	}

	function resizeByContent() {

		var $splitter = $(options.splitter);

		$splitter.height(0);

		var extraHeight = 20;
		var windowHeight = $(window).outerHeight();
		var autofitHeight = $(options.autofit)[0].scrollHeight + 2; // 2 is depth element border size.

		var fixedHeight = extraHeight;
		$.each(options.fixed, function(index, value) {
			fixedHeight += $(value).outerHeight();
		});

		if (windowHeight >= (autofitHeight + fixedHeight)) {
			$splitter.height(windowHeight - fixedHeight);
		} else {
			$splitter.height(autofitHeight);
		}
	}

	function resizeByWindow() {

		var windowHeight = $(window).outerHeight();

		var fixedHeight = options.padding;
		$.each(options.fixed, function(index, value) {
			fixedHeight += $(value).outerHeight();
		});

		$(options.splitter).height(windowHeight - fixedHeight);
	}

}


LayoutManager.isHeightGreater600px = function () {
	return window.matchMedia("(min-height: 600px)").matches;
}

LayoutManager.isHeightGreater768px = function () {
	return window.matchMedia("(min-height: 768px)").matches;
}


