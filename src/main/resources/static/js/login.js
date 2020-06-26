$(document).ready(function () {
    $('button').click(function () {
        var username = $(" input[ name='username' ] ").val();
        var password = $(" input[ name='password' ] ").val();
        param = {};
        param.username = username;
        param.password = password;
        $.ajax({
            type: 'post',
            url: 'http://localhost:8579/user/login',
            data: param,
            success: function (data) {
                if(data.code ==="200"){
                    window.location.href = "index.html";
                }else{
                    alert("登陆失败")
                }
            }
        });
    })
    // $.ajax({
    //     type: 'get',
    //     url: 'http://localhost:6137/getUserInfo',
    //     success: function (data) {
    //         if(data.code === "200"){
    //             user = data.data;
    //             $('#userInfo').text(user.nickname);
    //             $('#userInfo').children("img").val(user.avatar);
    //         }
    //     }
    // })
})