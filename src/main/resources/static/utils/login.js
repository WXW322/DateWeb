function login() {
    //loading层
    var index = layer.load(2, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    var name = $("#username").val();
    var password = $("#password").val();
    var verifyCode = $("#verifyCode").val();
    var role = $("#role").val();
    console.log("开始提交");
    $.ajax({
        url: ctx + "api/user/loginSubmit",
        method: "post",
        timeout: 2000, //通过timeout属性，设置超时时间
        data: {username: name, password: password, verifyCode: verifyCode, role: role},
        success: function (resp) {
            layer.close(index);
            if (resp.status == 200) {
                var role = $("#role").val();
                if (role == 0 || role == 1) {
                    window.location.href = ctx + "index2";
                } else {
                    window.location.href = ctx + "index";
                }
            } else {
                changeCode();
                layer.msg(resp.msg);
            }
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.close(index);
                layer.msg('请求超时');
            }
        }
    })
}

//提交表单登陆
$("#submit").click(function () {
    login();
})
//点击回车 登陆
$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        login();
    }
});
$("#logout").click(function (e) {
    window.location.href = ctx + "/login"
})

function changeCode() {
    //点击刷新验证码
    var img = document.getElementsByTagName("img")[0];
    img.src = ctx + "/imageCode?" + new Date().getTime();
}
