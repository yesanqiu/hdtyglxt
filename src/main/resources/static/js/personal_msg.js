$(document).ready(function () {
   loadMsg();
    $(document).on('click','.to_index',function () {
        window.location.href = "index.html"
    });
    $(document).on('click','.item',function () {
        if(!$(this).hasClass("active_item")){
            $(this).addClass("active_item");
            $(this).siblings().removeClass("active_item");
            if($(this).text() == "修改密码"){
                $('.psw').css('display','flex');
                $('.msg').hide();
            }else{
                $('.msg').show();
                $('.psw').hide();
            }
        }
    });
    $(document).on('click','.msg .btn',function () {
        var nickname =  $("input[name='nickname']").val();
        var studentId =  $("input[name='studentId']").val();
        var param = {};
        param.nickname = nickname;
        param.studentId = studentId;
        alert("1");
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8579/user/update',
            data: param,
            success: function (data) {
                if(data.code == "200"){
                    alert("success");
                    loadMsg();
                }
            },
            fail: function (da) {
                alert(da);
            }
        });
    });
    $(document).on('click','.psw .btn',function () {

        var password =  $("input[name='password']").val();
        var changePassword =  $("input[name='changePassword']").val();
        var repartPassword = $("input[name='repartPassword']").val();
        var param = {};
        param.password = password;
        param.changePassword = changePassword;
        if( changePassword ==repartPassword && changePassword != null && password != null){
            alert("23432")
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8579/user/changePassword',
                data: param,
                success: function (data) {
                    if(data.code == "200"){
                        alert("success");
                        loadMsg();
                    }
                },
                fail: function (data) {
                    alert(data);
                }
            });
        }else{
            alert("两次密码不一致");
        }
    })
});

function loadMsg() {
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/user/getUserMsg",
        success: function (data) {
            if(data.code == "200"){
                $('.nickname').text(data.data.nickname);
                $("input[name='nickname']").val(data.data.nickname);
                $("input[name='studentId']").val(data.data.studentId);
            }
        }
    });
}