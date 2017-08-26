/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var timeOut;

$(document).ready(function() {  
    
    
    $(".DetailMenu").hover(function(){
        var $self = $(this);
        
        timeOut = setTimeout(function(){
            
             var p = $self.offset();
//        alert(JSON.stringify(p));
//        $('.fast_dish_detail').text();
        
        $('.fast_order_detail').css({ 
                position: "absolute",
                zIndex: 10
            });
        var orderDay = $self.find("input[type='hidden'][name='orderDay']").val();
//alert(orderDay)            ;


        $('.fast_order_detail').load('/order/fast/'+orderDay);
        $('.fast_order_detail').show();
        $('.fast_order_detail').offset({ bottom: p.top-300, right: p.right});
        }, 700);
    }
    ,function (){
        clearTimeout(timeOut);
        
        $('.fast_order_detail').html("");
                $('.fast_order_detail').hide();
            }
    );

});