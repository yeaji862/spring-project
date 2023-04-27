
function passLoc(data){ // 상세 프로필로 이동
                  document.location.href = "/user/profilePost?pass="+data;
              }

function view(data){ // 검색 결과 둘 중 하나의 컨텐츠만 보이게
                         var none = '';
                         if(data == 'content') none = 'profile';
                         if(data == 'profile') none = 'content';
                         $("."+data).css("display" , "");
                         $("."+none).css("display" , "none");
                         $('.'+data+'-click').addClass('span-search-check');
                         $('.'+none+'-click').removeClass('span-search-check');
                     }

function keyup(){ // 검색창 엔터 누를 때 페이지 이동
                 document.location.href = "/user/search?search="+$("input[name = search]").val();
                }

   function postCount(num){ // 특정 게시물의 좋아요 댓글 갯수 가져오기
             $.ajax({
                            url: "/user/post_Like_Comment_count",
                            method: "POST",
                            data: {"post_num" : num },
                            dataType: "json",
                            success:function(data){
                            $(".likeC"+num).text(' '+data.post_like);
                            $(".CommC"+num).text(' '+data.post_comment);
                              },
                               error:function(){}
                                });
   }

   function deleteC(comment_num , post_num){ // 댓글 삭제
         $.ajax({
                                    url: "/user/deleteComment",
                                    method: "POST",
                                    data: {"post_num" : post_num , "comment_num" :  comment_num},
                                    dataType: "json",
                                    success:function(data){
                                        userPost('userPost' , post_num);
                                      },
                                       error:function(){
                                       alert('다시 시도해주세요');
                                       userPost('userPost' , post_num);
                                       }
                                        });
   }

      function deleteR(post_num , comment_num , reply_num){ // 대댓글 삭제
            $.ajax({
                                       url: "/user/deleteReply",
                                       method: "POST",
                                       data: {"post_num" : post_num , "comment_num" :  comment_num , "reply_num" : reply_num},
                                       dataType: "json",
                                       success:function(data){
                                            userPost('userPost' , post_num);
                                         },
                                          error:function(){
                                          alert('다시 시도해주세요');
                                          userPost('userPost' , post_num);
                                          }
                                           });
      }

      function deletePost(num){
        let result = confirm('게시물을 삭제하시겠습니까?');
        if(result){ document.location.href = "/user/deletePost?pass="+num
        }else return false;
      }

       function setThumbnail_post(event) {
              var reader = new FileReader();

              reader.onload = function(event) {
                $('.contentImg').attr('src' , event.target.result);
                $('.contentImg').attr('class' , 'insert-img insert-postImg');
              };

              reader.readAsDataURL(event.target.files[0]);
            }

           function contentSubmit(data){
            if(data == 'contentInsert'){
                if($('input[name = post_file]').val()=='' || $('input[name = post_content]').val()==''){
                            alert('내용을 추가해주세요');
                  }else{
                               $('.'+data).submit();
                               }
            }else{
            $('.'+data).submit();
            }
          }

