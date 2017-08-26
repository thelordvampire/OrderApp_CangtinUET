/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var timeOut;

$(document).ready(function() {  
    
    
    $(".DetailMenuDish").hover(function(){
        var $self = $(this);
        
        timeOut = setTimeout(function(){
            
            
            
             var p = $self.offset();
//        alert(JSON.stringify(p));
//        $('.fast_dish_detail').text();
        
        $('.fast_dish_detail').css({ 
                position: "absolute",
                zIndex: 10
            });
            
            
//             $("#OrderForm input[type='hidden'][name='dishHidden']").each(function (){
//                if($(this).parent(".DetailMenuDish").css("border-bottom-style")==="solid")
//                dishes.push($(this).attr('value'));            
//            });
            
            var dishID = $self.find("input[type='hidden'][name='dishHidden']").val();
            
            
            
//        var dishID = $self.find("input[type='checkbox'][name='dishes']").val();


        $('.fast_dish_detail').load('/dish/fast/'+dishID);
        $('.fast_dish_detail').show();
        $('.fast_dish_detail').offset({ top: p.top-350, left: p.left});
/*        $('.fast_dish_detail').css({ top: p.top, left: p.left});
        $('.fast_dish_detail').css({opacity:0.8, display:"none"}).fadeIn(400);*/
            
            
        }, 700);
            
       
    }
    ,function (){
        clearTimeout(timeOut);
        
        $('.fast_dish_detail').html("");
                $('.fast_dish_detail').hide();
            }
    );

//            $(".RegisterMenuDish").mouseenter(function(){
////                alert("vaor oi");
//                $('.fast_dish_detail').show();
//            });
//            
//            $(".RegisterMenuDish").mouseleave(function(){
//                
//                $('.fast_dish_detail').hide();
//            });


});