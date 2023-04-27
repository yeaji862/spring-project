  (function () { // 해당 유저의 포스트 좋아요 표시
   $.ajax({
                                      url: "/user/LikePost",
                                      method: "POST",
                                      dataType: "json",
                                      success:function(data){
                                       for(var i=0 in data){
                                         $(".Like"+data[i]).css("display" , "");
                                         $(".unLike"+data[i]).css("display" , "none");
                                           }
                                       },
                                       error:function(){}
                                      });

                     })();

function insertLike(num){ // 좋아요 등록
                            $.ajax({
                                url: "/user/insertLike",
                                method: "POST",
                                data: {"post_num" : num },
                                dataType: "json",
                                success:function(data){
                                $(".Like"+num).css("display" , "");
                                $(".unLike"+num).css("display" , "none");
                                postCount(num);
                                  },
                                   error:function(){}
                                    });
}
function deleteLike(num){ // 좋아요 해제
                            $.ajax({
                                url: "/user/deleteLike",
                                method: "POST",
                                data: {"post_num" : num },
                                dataType: "json",
                                success:function(data){
                                $(".Like"+num).css("display" , "none");
                                $(".unLike"+num).css("display" , "");
                                postCount(num);
                                  },
                                   error:function(){}
                                    });
}
function insertFollow(name){ // 팔로우 등록
                            $.ajax({
                                url: "/user/insertFollow",
                                method: "POST",
                                data: {"from_user_name" : name },
                                dataType: "json",
                                success:function(){
                                $(".follow").css("display" , "none");
                                $(".following").css("display" , "");
                                history.go(0);
                                followCount(name)
                                  },
                                   error:function(){}
                                    });
}

function deleteFollow(name){ // 팔로우 해제
                            $.ajax({
                                url: "/user/deleteFollow",
                                method: "POST",
                                data: {"from_user_name" : name },
                                dataType: "json",
                                success:function(){
                                $(".follow").css("display" , "");
                                $(".following").css("display" , "none");
                                history.go(0);
                                followCount(name);
                                  },
                                   error:function(){}
                                    });
}

function followCount(name){ // 팔로우 갯수 가져오기
                            $.ajax({
                                url: "/user/followCount",
                                method: "POST",
                                data: {"from_user_name" : name },
                                dataType: "json",
                                success:function(data){
                                $(".followCount").text(data + ' 팔로워')
                                  },
                                   error:function(){}
                                    });
}

  function followShow(){ // 해당 유저의 팔로우 표시
   const from_user_name = $(".user_name").text();
     $.ajax({
                                        url: "/user/selectFollow",
                                        method: "POST",
                                        data: {"from_user_name" : from_user_name} ,
                                        dataType: "json",
                                        success:function(data){
                                            if(data == null){
                                            $(".following").css("display" , "none");
                                            }else $(".follow").css("display" , "none");
                                         },
                                         error:function(){}
                                        });
    }


function userPost(data , num){
         const comment_reply = $('.comment_reply2').html();
         const comment = $('.comment').html();
         const reply = $('.reply').html();
         $('.insert').removeAttr('onclick');
         $('.comment-text').attr('placeholder' , '댓글 달기' );
         $('.comment-text').val("");
         $("."+data).css("display" , "");
         $('.comment_reply2').html('');
         $('.comment_reply').html('');
         $('.comment').html('');
         $('.reply').html('');
         $.ajax({
            url: "/user/sessionName",
            method: "POST",
            dataType: "text",
            success:function(user_name){

            $.ajax({
               url: "/user/userComment",
               method: "POST",
               data: { "post_num" : num},
               dataType: "json",
               success:function(data){
                   let i = 0;
                 for (let arrC of data) {
                   $('.comment_reply').append(comment);
                   $('#comment_user_name').text(arrC.user_name);
                   $('#comment_user_name').addClass('pointer');
                   $('#comment_content').text(arrC.comment_content);
                   $('#comment_user_name').attr('onclick' , 'passLoc("'+arrC.user_name+'")');
                   $('.imgC').attr('class' , 'img_comment '+arrC.comment_num);
                   $('.'+arrC.comment_num).attr('src' , '/profile_img/'+arrC.user_img_file);
                   $('#comment_user_name').attr('onclick' , 'passLoc("'+arrC.user_name+'")');
                   $('#comment_user_name').attr('id' , 'c_u'+arrC.comment_num);
                   $('#comment_content').attr('id' , 'c_c'+arrC.comment_num);
                   $('.ch').attr('class' , 'pointer comm re reply'+arrC.comment_num);
                   $('.reply'+arrC.comment_num).attr('onclick' , 'reply_btn("'+arrC.user_name+'" , "'+arrC.comment_num+'" , "'+num+'")');
                 if(user_name == arrC.user_name){
                   $('#comment_btn').append('<span id="replyEx" class="pointer re comm c_de'+arrC.comment_num+'" >삭제 </span>')
                   $('#comment_btn').attr('id' , 'c_btn'+arrC.comment_num);
                   $('.c_de'+arrC.comment_num).attr('onclick' , 'deleteC("'+arrC.comment_num+'","'+num+'")');
                 }
                 if(arrC.replyList != null){
                   for (let arrR of arrC.replyList) {
                     $('.comment_reply').append(reply);
                     $('#reply_user_id').text(arrR.user_name);
                     $('#reply_user_id').addClass('pointer');
                     $('#reply_content').text(arrR.reply_content);
                     $('.imgR').attr('class' , 'img_comment '+arrR.reply_num);
                     $('.'+arrR.reply_num).attr('src' , '/profile_img/'+arrR.user_img_file);
                     $('#reply_user_id').attr('onclick' , 'passLoc("'+arrR.user_name+'")');
                     $('#reply_user_id').attr('id' , 'r_u'+arrR.comment_num);
                     $('#reply_content').attr('id' , 'r_c'+arrR.comment_num);
                   if(user_name == arrR.user_name){
                     $('#reply_btn').append('<span id="replyEx" class="pointer re comm r_de'+arrR.comment_num+'" >삭제 </span>')
                     $('#reply_btn').attr('id' , 'r_btn'+arrR.comment_num);
                     $('.r_de'+arrR.comment_num).attr('onclick' , 'deleteR("'+num+'","'+arrR.comment_num+'","'+arrR.reply_num+'")');
                 }
                 }
                 }
                     i++;
                 }
                  $('.insert').attr('onclick' , 'insertComment("'+num+'")')
                  $('.comment_reply2').html(comment_reply);
               },
               error:function(){}
               });

            },
            error:function(){}
            });
             $.ajax({
                              url: "/user/selectPost",
                              data: { "post_num" : num },
                              method: "POST",
                              dataType: "json",
                              success:function(data){
                              $('#post-img').attr('src' , '/post_img/'+data.post_img_file);
                              $('#user_post_profileImg').attr('src' , '/profile_img/'+data.user_img_file);
                              $('#post-name').text(data.user_name);
                              $('#post-name').attr('onclick' , 'passLoc("'+data.user_name+'")');
                              $('#post-content').text(data.post_content);
                               },
                               error:function(){
                               alert('다시 시도해주세요');
                               }
                              });
            openModal();
}

function reply_btn(name , comment_num , post_num){
    $('.insert').removeAttr('onclick');
    $('.insert').attr('onclick' , 'insertReply("'+comment_num+'" , "'+post_num+'")');
    $('.comment-text').attr('placeholder' , '@'+name );
}

function insertReply(comment_num , post_num){
     $.ajax({
                      url: "/user/insertReply",
                      data: { "comment_num" : comment_num , "post_num" : post_num , "content" :  $('.comment-text').val() },
                      method: "POST",
                      dataType: "text",
                      success:function(data){
                        userPost('userPost' , post_num);
                       },
                       error:function(){}
                      });
}

function insertComment(post_num){
     $.ajax({
                          url: "/user/insertComment",
                          data: { "post_num" : post_num , "content" :  $('.comment-text').val() },
                          method: "POST",
                          dataType: "text",
                          success:function(data){
                            userPost('userPost' , post_num);
                           },
                           error:function(){}
                          });
}