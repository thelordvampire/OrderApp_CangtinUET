/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



 $(function () {
 
        $(".rateYo").rateYo({
            ratedFill: "RoyalBlue",
            starWidth: "15px",
            precision: 0
//            readOnly: true
        }) 
          .on("rateyo.set", function (e, data) {
                
                var orderID = $(this).parent().parent().
                find("input[type='hidden'][name='orderDay']").val();
                submit_rating(orderID, data.rating);
//                  alert("The rating is set to " + data.rating + "!");
              });;
 
});







function submit_rating(day, rate)
{
//    alert("hehehehhehehe : "+rate);
//    alert("hehehehhehehe : "+day);
    
    var data_send={
        day: day,
        rate: rate
    };
    
    
    $.ajax({
                type: "POST",
//              contentType: "application/json",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                url: "/order/rate",
                data:  data_send,
                dataType: "text",
                cache: false,
                timeout: 600000,
                success: function (data) {
//                    alert(data);
                            
//                            $('#message').html(createMessage([data["success.message"]]));
//                            var data_send1={day: day};
//                            
//                            $("#OrderForm").load("/order/refresh", data_send1);
                        },
                        error: function (e) { //jqXHR, textStatus, errorThrown
                        }
                    });
    
    
}