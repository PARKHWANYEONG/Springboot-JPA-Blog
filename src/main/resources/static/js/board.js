let index = {
    init: function(){
        $("#btn-save").on("click",()=>{
           this.save();
        });
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-comment-save").on("click",()=>{
            this.commentSave();
        });
    },
    save: function(){
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
            dataType: "json" //응답받을 데이터 타입 (응답받는 데이터가 json타입일 경우 자바스크립트 오브젝트로 변경해줌)
        }).done(function(res){
            alert("작성이 완료되었습니다.");
            location.href= "/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    deleteById: function(){
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function(res){
            alert("삭제가 완료되었습니다.");
            location.href= "/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    update: function(){
        let id = $("#id").val();
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };
        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res){
            alert("수정이 완료되었습니다.");
            location.href= "/board/"+id;
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },
    commentSave: function(){
            let data = {
                boardId : $("#id").text(),
                userId : $("#userId").val(),
                content : $("#comment-content").val()
            };

            $.ajax({
                type: "POST",
                url: `/api/board/${data.boardId}/comment`,
                data: JSON.stringify(data), //http body 데이터
                contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
                dataType: "json" //응답받을 데이터 타입 (응답받는 데이터가 json타입일 경우 자바스크립트 오브젝트로 변경해줌)
            }).done(function(res){
                alert("댓글 작성 완료.");
                location.href= `/board/${data.boardId}`;
            }).fail(function(err){
                alert(JSON.stringify(err));
            });
        },
        commentDelete: function(boardId,commentId){
                    $.ajax({
                        type: "DELETE",
                        url: `/api/board/${boardId}/comment/${commentId}`,
                        dataType: "json"
                    }).done(function(res){
                        alert("댓글 삭제 완료.");
                        location.href= `/board/${boardId}`;
                    }).fail(function(err){
                        alert(JSON.stringify(err));
                    });
                }
}
index.init();