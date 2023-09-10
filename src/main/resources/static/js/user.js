let index = {
    init: function(){
        $("#btn-save").on("click",()=>{
           this.save();
        });
        $("#btn-update").on("click",()=>{
           this.update();
        });
    },
    save: function(){
        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val()
        };

        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
            dataType: "json" //응답받을 데이터 타입 (응답받는 데이터가 json타입일 경우 자바스크립트 오브젝트로 변경해줌)
        }).done(function(res){
            alert("회원가입이 완료되었습니다.");
            console.log(res);
            location.href= "/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    update: function(){
            let data = {
                id : $("#id").val(),
                usernname : $("#username").val(),
                password : $("#password").val(),
                email : $("#email").val()
            };
            $.ajax({
                type: "PUT",
                url: "/user",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(res){
                alert("회원수정이 완료되었습니다.");
                location.href= "/";
            }).fail(function(err){
                alert(JSON.stringify(err));
            });
        }
}
index.init();