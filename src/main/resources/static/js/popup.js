function openModal(){
      $(".modal").addClass("show");
      $("body").css("overflow" , "hidden");
      }

function closeModal(){
      $(".modal").removeClass("show");
      $("body").css("overflow" , "auto");
      $(".outLine").css("display" , "none");
      }

function followList(data){
       $("."+data).css("display" , "");
       openModal();
}

function userEdit(data){
 $("."+data).css("display" , "");
 openModal();
}

function insertPost(data){
 $("."+data).css("display" , "");
  openModal();
}

function updatePost(data , num){
$("."+data).css("display" , "");
             $.ajax({
                              url: "/user/selectPost",
                              data: { "post_num" : num },
                              method: "POST",
                              dataType: "json",
                              success:function(data){
                              $('#id_post_img').attr('src' , '/post_img/'+data.post_img_file);
                              $('.id_post_content').val(data.post_content);
                              $('.id_post_num').val(data.post_num);
                               },
                               error:function(){
                               alert('다시 시도해주세요');
                               }
                              });
 openModal();
}
