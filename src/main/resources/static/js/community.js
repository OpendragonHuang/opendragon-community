function comment_submit() {
    var parenId = $("#comment_parent_id").val();
    var comment = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentId":parenId,
            "type":1,
            "comment":comment
        }),
        success: function (response) {
            if(response.code == 2000){
                $("#comment_div").hide();
            }else{
                alert(response.message);
            }
        },
        dataType: "json",
        contentType: "application/json;charset=utf-8"
    });
}