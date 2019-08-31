function submitComment(parentId, comment, type) {
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentId":parentId,
            "type":type,
            "comment":comment
        }),
        success: function (response) {
            if(response.code == 2000){
                window.location.reload();
            }else{
                if(response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=b9a52c873efad241e9f9&scope=user&state=1");
                        localStorage.setItem("closeable", "true");
                    }
                }else{
                    alert(response.message);
                }
            }
        },
        dataType: "json",
        contentType: "application/json;charset=utf-8"
    });
}

function replyComment() {
    var parentId = $("#comment_parent_id").val();
    var comment = $("#comment_content").val();
    submitComment(parentId, comment, 1);
}

function replySubComment(e) {
    var parentId = e.getAttribute("data-id");
    var comment = $("#sub_comment_content_"+parentId).val();
    submitComment(parentId, comment, 2);
}

function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var sub_comments = $("#sub_comment_id_"+id);

    var hasClass = sub_comments.hasClass("in");
    if(hasClass){
        sub_comments.removeClass("in");
    }else{
        sub_comments.addClass("in");
        if(sub_comments.children().length <= 1){
            $.getJSON( "/comment/"+id, function( data ) {
                $.each(data.data, function( index, comment) {
                    var media = $("<div/>", {
                        "class":"col-xs-12 col-sm-12 col-md-12 col-lg-12 media"
                    }).append(
                        $("<div/>", {
                            "class":"media-left"}).append(
                            $("<img/>", {
                                "class":"media-object img-rounded icon-size",
                                "src":comment.user.avatarUrl
                            })
                        )
                    );
                    var mediaBody = $("<div/>", {
                        "class":"media-body"
                    });
                    mediaBody.append(
                        $("<h5/>", {
                            "class":"media-heading",
                            "html":comment.user.name
                        })
                    );
                    mediaBody.append(
                        $("<span/>", {
                            "html":comment.comment
                        })
                    );
                    mediaBody.append(
                        $("<span/>", {
                            "class":"pull-right menu",
                            "html":moment(comment.gmtCreate).format("YYYY-MM-DD")
                        })
                    );
                    mediaBody.append(
                        $("<hr/>", {
                            "class":"comment-hr"
                        })
                    );
                    media.append(mediaBody);
                    sub_comments.prepend(media);
                });
            });
        }
    }
}

function displayTagsNav(isDisplay) {
    if(isDisplay){
        $('#tags').show();
    }else{
        $('#tags').hide();
    }
}

function addTag(e) {
    var tagName = e.firstElementChild.innerText;
    var tag = $('#tag');
    if(tag.val().indexOf(tagName) < 0){
        if(tag.val().length == 0){
            tag.val(tagName);
        }else{
            tag.val(tag.val()+','+tagName);
        }
    }
}