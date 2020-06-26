$(document).ready(function () {
    $('button').click(function () {
        var username = $(" input[ name='username' ] ").val();
        var nickname = $(" input[ name='nickname' ] ").val();
        var studentId = $(" input[ name='studentId' ] ").val();
        var password = $(" input[ name='password' ] ").val();
        param = {};
        param.username = username;
        param.nickname = nickname;
        param.studentId = studentId;
        param.password = password;
        if(nickname == null || username == null || studentId == null || password ==null){
            alert("信息不完整")
        }else {
            $.ajax({
                type: 'post',
                url: 'http://localhost:8579/user/register',
                data: param,
                success: function (data) {
                    if(data.code ==="200"){
                        alert("注册成功")
                        window.location.href = "login.html";
                    }
                }
            });
        }


    })
})