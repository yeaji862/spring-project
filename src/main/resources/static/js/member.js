var   random_Num = 0;
const phoneRegex = /^01([016789])(\d{3,4})(\d{4})$/;
const idRegex = /^[a-zA-Z0-9-_]+$/;
const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
const nameRegex = /^[a-z0-9가-힣]{1,10}$/i;

function sand(data){ // 인증번호 보내기
    $('.sand-'+data).removeClass('check');
    if($('.'+data+'-phone').val() == ''){
     alert('핸드폰 번호를 입력해주세요');
     }else if(!(phoneRegex.test($('.'+data+'-phone').val()))){
     $('.error-phone-'+data).css('display' , '');
     }else{
       const randomNum = Math.floor(Math.random() * (999999 - 100000 + 1)) + 100000;
                       $.ajax({
                       url: "authenticationNumber.mem",
                       data: { "user_phone" : $(".join-phone").val() , "randomNumber" : randomNum },
                       method: "POST",
                       dataType: "text",
                       success:function(){
                       alert('인증번호를 보냈습니다');
                       $('.sand-'+data).addClass('check');
                       $('.error-'+data+'-phone').css('display' , 'none');
                       random_Num = randomNum;
                        },
                        error:function(){
                        alert('다시시도해주세요');
                        }
                       });
     }

           }

function idCheck(){
    $('.idCheck').removeClass('check');
    if(($('.join-user_id').val() == '') || !(idRegex.test($('.join-user_id').val()))){
        $('.error-join-id').css('display' , '');
     }else{

                       $.ajax({
                       url: "/user_id_check.mem",
                       data: { "user_id" : $(".join-user_id").val()},
                       method: "POST",
                       dataType: "text",
                       success:function(data){
                       if(data == 'false'){
                       $('.error-join-id').css('display' , 'none');
                       alert('사용가능한 아이디 입니다');
                       $('.idCheck').addClass('check');
                       }else if(data == 'true'){
                       alert('존재하는 아이디 입니다');
                       }
                        },
                        error:function(){
                        alert('다시시도해주세요');
                        }
                       });
     }

           }


           function findPass(data){
           $('.login-form').css('display' , 'none');
           $('.findPass').css('display' , '');
           }

           function signUp(){
           $('.login-form').css('display' , 'none');
           $('.join1').css('display' , '');
           }

           function signUp2(){
            if(($('.join-user_id').val() == '') || !(idRegex.test($('.join-user_id').val()))){
               $('.error-join-id').css('display' , '');
            }else if(!($('.idCheck').hasClass('check'))){
                alert('아이디 중복확인을 해주세요');
            }else if(($('.join-user_pass').val() == '') || !(passwordRegex.test($('.join-user_pass').val()))){
               $('.error-join-id').css('display' , 'none');
               $('.error-join-pass').css('display' , '');
            }else if($('.join-phone').val() == '' || !(phoneRegex.test($('.join-phone').val()))){
                $('.error-join-id').css('display' , 'none');
                $('.error-join-pass').css('display' , 'none');
                $('.error-join-phone').css('display' , '');
            }else if(!($('.sand-join').hasClass('check'))){
                  alert('인증번호를 확인해주세요');
                  }else if($('.join-num').val() == ''){
                  alert('인증번호를 입력해주세요');
                  }else if($('.join-num').val() != random_Num){
                  alert('인증번호가 일치하지 않습니다');
                  }else{
                  $('.join-1').css('display' , 'none');
                  $('.join2').css('display' , '');
                  $('.join-form-submit').css('display' , '');
                  }
           }

      function setThumbnail(event) {
        var reader = new FileReader();
        reader.onload = function(event) {
          $('.img-file').attr('src' , event.target.result);
        };

        reader.readAsDataURL(event.target.files[0]);
      }

     function main(){
     history.go(0);
     }

     function findPassCheck(name){
     if($('.findPass-userId').val() == ''){
     alert('아이디를 입력해주세요');
     }else if($('.findPass-phone').val() == ''){
     alert('핸드폰 번호를 입력해주세요');
     }else if(!(phoneRegex.test($('.findPass-phone').val()))){
     $('.error-phone-findPass').css('display' , '');
     }else if(!($('.sand-findPass').hasClass('check'))){
     alert('인증번호를 확인해주세요');
     }else if($('.num-findPass').val() == ''){
     alert('인증번호를 입력해주세요');
     }else if($('.num-findPass').val() != random_Num){
     alert('인증번호가 일치하지 않습니다');
     }else{
           $.ajax({
                                    url: "/findPassCheck.mem",
                                    data: { "user_id" : $('.findPass-userId').val() , "user_phone" : $('.findPass-phone').val() },
                                    method: "POST",
                                    dataType: "text",
                                    success:function(data){
                                    $('.error-phone-findPass').css('display' , 'none');
                                    if(data != 'true'){
                                    alert(data);
                                    }else{
                                    $('#'+name).submit();
                                    }
                                     },
                                     error:function(){
                                     alert('다시 시도해주세요');
                                     }
                                    });
     }


     }


     function emptyCheck(id,pass,name){
        if($('.'+id).val() == ''){
        alert('아이디를 입력해주세요');
        }else if($('.'+pass).val() == ''){
        alert('비밀번호를 입력해주세요');
        }else{
        $('.'+name).submit();
        }
     }

     function joinSubmit(data){
      if(($('.join-name').val() == '') || !(nameRegex.test($('.join-name').val()))){
       alert('소문자, 숫자, 한글이 포함된 문자열만 허용')
      }else if(!($('.nameCheck').hasClass('check'))){
      alert('닉네임 중복확인을 해주세요');
      }else if (!$('input[name = animal_gender]:checked').val() || !$('input[name = animal_type]:checked').val()) {
       	alert('성별 분류 항목을 선택해주세요');
       }else if($('input[name = animal_birth]').val() == ''){
       alert('생일 항목을 선택해주세요');
       }else if($('.join-user_id').val().includes('google_')){
        $("input[name = user_pass]").val(Math.floor(Math.random() * (999999 - 100000 + 1)) + 100000);
        $('.' + data).submit();
       }else{
         $('.' + data).submit();
       }
     }

     function nameCheck(){
             $('.nameCheck').removeClass('check');
         if(($('.join-name').val() == '') || !(nameRegex.test($('.join-name').val()))){
             alert('소문자, 숫자, 한글이 포함된 문자열만 허용')
          }else{

                            $.ajax({
                            url: "/nameCheck.mem",
                            data: { "user_name" : $(".join-name").val()},
                            method: "POST",
                            dataType: "text",
                            success:function(data){
                            if(data == 'false'){
                            alert('사용가능한 닉네임 입니다');
                            $('.nameCheck').addClass('check');
                            }else if(data == 'true'){
                            alert('존재하는 닉네임 입니다');
                            }
                             },
                             error:function(){
                             alert('다시시도해주세요');
                             }
                            });
          }

                }

function profileEdit(){
        if(($('.join-name').val() == '') || !(nameRegex.test($('.join-name').val()))){
               alert('소문자, 숫자, 한글이 포함된 문자열만 허용')
              }else if(!($('.nameCheck').hasClass('check')) && $('#user_name_check').val() != $('.join-name').val()){
              alert('닉네임 중복확인을 해주세요');
              }else if (!$('input[name = animal_gender]:checked').val() || !$('input[name = animal_type]:checked').val()) {
               	alert('성별 분류 항목을 선택해주세요');
               }else if($('input[name = animal_birth]').val() == ''){
               alert('생일 항목을 선택해주세요');
               }else{
               $('.profileEditForm').submit();
               }
}

function passEditSubmit(){
  if(($('.editPass').val() == '') || !(passwordRegex.test($('.editPass').val()))){
    $('.error-edit-pass').css('display' , '');
  }else{
  $('.passEditForm').submit();
  }
}
