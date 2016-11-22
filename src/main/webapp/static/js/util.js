var DATATABLEHEIGHT = 270;
var RES_UNAUTHORIZED = 403;
var RES_SUCCESS = 1;

function getContextPath()
{
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
function getCurrentMonth()
{
    var date=new Date;
    var month=date.getMonth()+1;
    return month;
}

/**
 * 设置未来(全局)的AJAX请求默认选项
 * 主要设置了AJAX请求遇到Session过期的情况
 */
$.ajaxSetup({
    type: 'POST',
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus != null)
        {
            switch (sessionStatus)
            {
                case "forceLogout":
                    var top = getTopWinow();
                    var yes = confirm('您已被管理员踢出系统!');
                    if (yes) {
                        top.location.href = '/testshiro3/html/login.html?loginStatus=KickOut';
                    }
                    break;
                case "relogin":
                    var top = getTopWinow();
                    var yes = confirm('因重复登录,被迫下线!');
                    if (yes) {
                        top.location.href = '/testshiro3/html/login.html?loginStatus=Relogin';
                    }
                    break;
                case "":
                    break;
                default :
                    break;

            }
        }

    }
});

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}