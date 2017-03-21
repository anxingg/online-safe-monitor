(function($) {
    /*
     * 功能：將鍵盤按鍵 F5、Alt+Left(回上一頁)、Alt+Right(到下一頁) 按鍵 enable/disable
     * 6-JUNE-2009
     * by Walter Liao, http://www.dotblogs.com.tw/walter
     * Examples:
     * $.fn.disableF5Key();
     * $.fn.enableF5Key();
     */

    $.fn.extend({
        disableF5Key: function() {
            $(window.document).keydown(disableF5InnerFunc);
        },

        enableF5Key: function() {
            $(window.document).unbind("keydown", disableF5InnerFunc);
        }
    });

    function disableF5InnerFunc(event) {
        var e;
        if ($.browser.msie) { e = window.event; } else { e = event; }
        if (e.keyCode == 116) { e.keyCode = 0; return false; }
        if (e.altKey && (e.keyCode == 37 || e.keyCode == 39)) { return false; }
    };
})(jQuery);
