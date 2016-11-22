/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.1
Version: 1.5.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.5/admin/
*/
var handleSlimScroll = function() {
    "use strict";
    $("[data-scrollbar=true]").each(function() {
        generateSlimScroll($(this))
    })
};
var generateSlimScroll = function(e) {
    var t = $(e).attr("data-height");
    t = !t ? $(e).height() : t;
    var n = {
        height: t,
        alwaysVisible: true
    };
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        n.wheelStep = 3;
        n.touchScrollStep = 100
    }
    $(e).slimScroll(n)
};
var handleSidebarMenu = function() {
    "use strict";
    $(".sidebar .nav > .has-sub > a").click(function() {
        var e = $(this).next(".sub-menu");
        var t = ".sidebar .nav > li.has-sub > .sub-menu";
        if ($(".page-sidebar-minified").length === 0) {
            $(t).not(e).slideUp(250,
            function() {
                $(this).closest("li").removeClass("expand")
            });
            $(e).slideToggle(250,
            function() {
                var e = $(this).closest("li");
                if ($(e).hasClass("expand")) {
                    $(e).removeClass("expand")
                } else {
                    $(e).addClass("expand")
                }
            })
        }
    });
    $(".sidebar .nav > .has-sub .sub-menu li.has-sub > a").click(function() {
        if ($(".page-sidebar-minified").length === 0) {
            var e = $(this).next(".sub-menu");
            $(e).slideToggle(250)
        }
    })
};

var handleSidebarMinify = function() {
    $("[data-click=sidebar-minify]").click(function(e) {
        e.preventDefault();
        var t = "page-sidebar-minified";
        var n = "#page-container";
        if ($(n).hasClass(t)) {
            $(n).removeClass(t);
            if ($(n).hasClass("page-sidebar-fixed")) {
                generateSlimScroll($('#sidebar [data-scrollbar="true"]'))
            }
        } else {
            $(n).addClass(t);
            if ($(n).hasClass("page-sidebar-fixed")) {
                $('#sidebar [data-scrollbar="true"]').slimScroll({
                    destroy: true
                });
                $('#sidebar [data-scrollbar="true"]').removeAttr("style")
            }
            $("#sidebar [data-scrollbar=true]").trigger("mouseover")
        }
        $(window).trigger("resize")
    })
};
var handlePageContentView = function() {
    "use strict";
    $.when($("#page-loader").addClass("hide")).done(function() {
        $("#page-container").addClass("in")
    })
};
var handlePanelAction = function() {
    "use strict";
    $("[data-click=panel-remove]").hover(function() {
        $(this).tooltip({
            title: "Remove",
            placement: "bottom",
            trigger: "hover",
            container: "body"
        });
        $(this).tooltip("show")
    });
    $("[data-click=panel-remove]").click(function(e) {
        e.preventDefault();
        $(this).tooltip("destroy");
        $(this).closest(".panel").remove()
    });
    $("[data-click=panel-collapse]").hover(function() {
        $(this).tooltip({
            title: "Collapse / Expand",
            placement: "bottom",
            trigger: "hover",
            container: "body"
        });
        $(this).tooltip("show")
    });
    $("[data-click=panel-collapse]").click(function(e) {
        e.preventDefault();
        $(this).closest(".panel").find(".panel-body").slideToggle()
    });
    $("[data-click=panel-reload]").hover(function() {
        $(this).tooltip({
            title: "Reload",
            placement: "bottom",
            trigger: "hover",
            container: "body"
        });
        $(this).tooltip("show")
    });
    $("[data-click=panel-reload]").click(function(e) {
        e.preventDefault();
        var t = $(this).closest(".panel");
        if (!$(t).hasClass("panel-loading")) {
            var n = $(t).find(".panel-body");
            var r = '<div class="panel-loader"><span class="spinner-small"></span></div>';
            $(t).addClass("panel-loading");
            $(n).prepend(r);
            setTimeout(function() {
                $(t).removeClass("panel-loading");
                $(t).find(".panel-loader").remove()
            },
            2e3)
        }
    });
    $("[data-click=panel-expand]").hover(function() {
        $(this).tooltip({
            title: "Expand / Compress",
            placement: "bottom",
            trigger: "hover",
            container: "body"
        });
        $(this).tooltip("show")
    });
    $("[data-click=panel-expand]").click(function(e) {
        e.preventDefault();
        var t = $(this).closest(".panel");
        var n = $(t).find(".panel-body");
        var r = 40;
        if ($(n).length !== 0) {
            var i = $(t).offset().top;
            var s = $(n).offset().top;
            r = s - i
        }
        if ($("body").hasClass("panel-expand") && $(t).hasClass("panel-expand")) {
            $("body, .panel").removeClass("panel-expand");
            $(".panel").removeAttr("style");
            $(n).removeAttr("style")
        } else {
            $("body").addClass("panel-expand");
            $(this).closest(".panel").addClass("panel-expand");
            if ($(n).length !== 0 && r != 40) {
                var o = 40;
                $(t).find(" > *").each(function() {
                    var e = $(this).attr("class");
                    if (e != "panel-heading" && e != "panel-body") {
                        o += $(this).height() + 30
                    }
                });
                if (o != 40) {
                    $(n).css("top", o + "px")
                }
            }
        }
        $(window).trigger("resize")
    })
};
var handelTooltipPopoverActivation = function() {
    "use strict";
    $("[data-toggle=tooltip]").tooltip();
    $("[data-toggle=popover]").popover()
};
var handleScrollToTopButton = function() {
    "use strict";
    $(document).scroll(function() {
        var e = $(document).scrollTop();
        if (e >= 200) {
            $("[data-click=scroll-top]").addClass("in")
        } else {
            $("[data-click=scroll-top]").removeClass("in")
        }
    });
    $("[data-click=scroll-top]").click(function(e) {
        e.preventDefault();
        $("html, body").animate({
            scrollTop: $("body").offset().top
        },
        500)
    })
};
var handleAfterPageLoadAddClass = function() {
    if ($("[data-pageload-addclass]").length !== 0) {
        $(window).load(function() {
            $("[data-pageload-addclass]").each(function() {
                var e = $(this).attr("data-pageload-addclass");
                $(this).addClass(e)
            })
        })
    }
};
var handleSavePanelPosition = function(e) {
    "use strict";
    if ($(".ui-sortable").length !== 0) {
        var t = [];
        var n = 0;
        $.when($(".ui-sortable").each(function() {
            var e = $(this).find("[data-sortable-id]");
            if (e.length !== 0) {
                var r = [];
                $(e).each(function() {
                    var e = $(this).attr("data-sortable-id");
                    r.push({
                        id: e
                    })
                });
                t.push(r)
            } else {
                t.push([])
            }
            n++
        })).done(function() {
            var n = window.location.href;
            n = n.split("?");
            n = n[0];
            localStorage.setItem(n, JSON.stringify(t));
            $(e).find('[data-id="title-spinner"]').delay(500).fadeOut(500,
            function() {
                $(this).remove()
            })
        })
    }
};
var handleInitRespondInfo = function(index){
    "use strict";
    var contextPath = getContextPath();
    // 加载用户个人信息
    $.ajax({
        url : contextPath + "/sysController/selectActiveInfo",
        dataType : "json" ,
        async : false ,
        data: {
            index: index
        },
        success : function(data)
        {
            $('.activeUserName').html(data.activeUserName);
            var menulistHtml = "<li class='nav-header'>导航栏</li>" + data.menuStr
                +"<li><a href='javascript;' class='sidebar-minify-btn' data-click='sidebar-minify'><i class='fa fa-angle-double-left'></i></a></li>";
            $('#menuList').html(menulistHtml);
        }
    });
};
var handleSetInfo = function(){
    "use strict";
    var contextPath = getContextPath();
    // 加载用户个人信息
    $.ajax({
        url : contextPath + "/sysController/selectActiveInfo",
        dataType : "json" ,
        async : false ,
        success : function(data)
        {
            $('#set_UserName').val(data.activeUserCode);
            $('#set_GivenName').val(data.activeUserName);
        }
    });
};
var App = function() {
    "use strict";
    return {
        init: function(index) {
            handleInitRespondInfo(index);
            handleSlimScroll();
            handleSidebarMenu();
            handleSidebarMinify();
            handlePageContentView();
        }
    }
} ();
var setUserInfo = function(){
    "use strict";
    handleSetInfo();
    $('#set_UserInfoModal').modal('show');
};
var changeUserPwd = function(){
    "use strict";
    $('#set_CurUserPasswrod').html();
    $('#set_SetUserPasswrod').html();
    $('#set_SetConfirmUserPasswrod').html();
    $('#set_PwdModal').modal('show');
    $('#set_CurUserPasswrod').focus();
};
var confirmInputSetUserInfo = function(){
    "use strict";
    var userName = $('#set_UserName').val();
    var givenName = $('#set_GivenName').val();
    if($.trim(userName) == '')
    {
        alert('用户名称不能为空！');
        $('#set_UserName').focus();
        return ;
    }
    if($.trim(givenName) == '')
    {
        alert('用户中文名不能为空！');
        $('#set_GivenName').focus();
        return ;
    }
    $.ajax({
        url : getContextPath() + "/userController/UpdateCurrentUserInfo",
        dataType : "json" ,
        async : false ,
        data: {
            userName : userName ,
            givenName : givenName
        },
        success : function(data)
        {
            switch (data.result)
            {
                case RES_SUCCESS:
                    alert('用户信息修改成功！');
                    $('#set_UserInfoModal').modal('hide');
                    break;
                case RES_UNAUTHORIZED:
                    alert('无权限！');
                    break;
                case -1:
                    alert('SQL语句错误！');
                    break;
                case -2:
                    alert('用户名称已存在！');
                    break;
                default :
                    alert('未知错误！');
                    break;
            }
        }
    });
};

var confirmInputSetUserPwd = function(){
    "use strict";
    var curPwd = $('#set_CurUserPasswrod').val();
    var pwdSet = $('#set_SetUserPasswrod').val();
    var pwdConfirm = $('#set_SetConfirmUserPasswrod').val();
    if($.trim(curPwd) == '')
    {
        alert('当前密码不能为空！');
        $('#set_CurUserPasswrod').focus();
        return ;
    }
    if($.trim(pwdSet) == '')
    {
        alert('设置密码不能为空！');
        $('#set_SetUserPasswrod').focus();
        return ;
    }
    if($.trim(pwdConfirm) == '')
    {
        alert('确认密码不能为空！');
        $('#set_SetConfirmUserPasswrod').focus();
        return ;
    }
    if($.trim(pwdSet) != $.trim(pwdConfirm))
    {
        alert('两次密码不相同！');
        return;
    }
    $.ajax({
        type: "POST",
        url : getContextPath() + "/userController/UpdateCurrentUserPassword",
        data: {
            curPwd : curPwd ,
            setPwd : pwdSet
        },
        success : function(data)
        {
            switch (data.result)
            {
                case RES_SUCCESS:
                    alert('密码修改成功！');
                    $('#set_PwdModal').modal('hide');
                    break;
                case RES_UNAUTHORIZED:
                    alert('无权限！');
                    break;
                case -1:
                    alert('SQL语句错误！');
                    break;
                case -2:
                    alert('密码错误！');
                    break;
                default :
                    alert('未知错误！');
                    break;
            }
        }
    });
};