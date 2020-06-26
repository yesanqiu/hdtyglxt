$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/user/getUserMsg",
        success: function (data) {
            if(data.code == "200"){
                $('.nickname').text(data.data.nickname);
                if(data.data.role == 0){
                    $('.admin').hide();
                }else{
                    $('.admin').show();
                }
            }
        }
    })

    $(document).on('click','.nickname',function () {
        window.location.href = "personal_msg.html"
    })
    $(document).on('click','.admin',function () {
        window.location.href = "user_mode.html"
    })
    $(document).on('click','.warn',function () {
        $.ajax({
            type: 'delete',
            url: 'http://localhost:8579/user/logout',
            success: function (data) {
                window.location.href = "login.html"
            }
        })
    })
})