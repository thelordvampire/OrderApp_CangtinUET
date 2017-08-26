/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {  
    
$(".DetailMenuDish").click(function(){
//    e.preventDefault();
    
//    alert($(this).css("border-bottom-style"));
    if( $(this).css("border-bottom-style")==="none" )
    {
//        alert($(this).css().toString());
        $(this).css({
            'border-bottom': 'solid red 3px'
        });
//        alert('bat dau');
//        alert($(this).css("border-bottom"));
    }
    else
    {
//        alert("uncheck");
        $(this).css({
            "border-bottom": ""
        });
    }
    
});


});