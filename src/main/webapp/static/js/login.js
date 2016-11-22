var handlePageContentView = function() {
    "use strict";
    $.when($("#page-loader").addClass("hide")).done(function() {
        $("#page-container").addClass("in")
    })
};
var handleLoginPageChangeBackground=function(){
    $('[data-click="change-bg"]').live("click",
        function(){
            var e='[data-id="login-cover-image"]';
            var t=$(this).find("img").attr("src");
            var n='<img src="'+t+'" data-id="login-cover-image" />';
            $(".login-cover-image").prepend(n);
            $(e).not('[src="'+t+'"]').fadeOut("slow",function(){$(this).remove()});
            $('[data-click="change-bg"]').closest("li").removeClass("active");
            $(this).closest("li").addClass("active")
        }
    )
};

var getUrlParam = function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
};
var handleLoginParams = function() {
    "use strict";
    var loginStatus = getUrlParam('loginStatus');
    if(loginStatus != null)
    {
        switch (loginStatus)
        {
            case "UnknownUserAccount":
                $('#resLogin').html('用户名未知！');
                $("#resLogin").show();
                break;
            case "PasswordError":
                $('#resLogin').html('密码错误！');
                $("#resLogin").show();
                console.log(loginStatus);
                break;
            case "LockedAccount":
                $('#resLogin').html('用户锁定！');
                $("#resLogin").show();
                break;
            case "KickOut":
                $('#resLogin').html('您已被剔除！');
                $("#resLogin").show();
                break;
            case "Relogin":
                $('#resLogin').html('重复登录！');
                $("#resLogin").show();
                break;
            case "SessionExpire":
                $('#resLogin').html('会话过期！');
                $("#resLogin").show();
                break;
            default :
                $('#resLogin').html('服务器错误！');
                $("#resLogin").show();
                break;
        }
    }
    else
    {
        $('#resLogin').html('');
        $("#resLogin").hide();
    }
};
var LoginV2=function(){
    "use strict";
    return{
        init:function(){
        handlePageContentView();
        handleLoginPageChangeBackground();
        handleLoginParams();
    }
    }
}();