

        function order_update() 
        {
            var data_send="";
            var day = $("#OrderForm input[type='hidden'][id='orderDay']").val();
            
//            var dishes = [];
//            $("#OrderForm input[type='checkbox'][name='dishes']:checked").each(function (){
////                dishes.push($("#OrderForm input[type='hidden'][name='"+$(this).attr('value')+"']").val());            
//                dishes.push($(this).attr('value'));            
//            });
///////////// cách cũ dùng checkbox


            var dishes = [];
            $("#OrderForm input[type='hidden'][name='dishHidden']").each(function (){
                if($(this).parent(".DetailMenuDish").css("border-bottom-style")==="solid")
                dishes.push($(this).attr('value'));            
            });


            
            var price = $("#OrderForm input[type='radio'][name='price']:checked").val();
            var infor =$("#OrderForm [name='infor']").val();
//            var orderID = $("#OrderForm #orderID").val();
//            
//            alert(orderID);
            
            data_send={
//            	orderID: orderID,
                day: day,
                dishes: dishes,
                price: price,
                infor: infor
            };
                     
            $.ajax({
                        type: "POST",
//                        contentType: "application/json",
                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                        url: "/order/update",
                        data:  data_send,
                        dataType: 'json',
                        cache: false,
                        timeout: 600000
                        ,
                        success: function (data) {
                
                        var data_send1={day: day};
                        $("#OrderForm").load("/order/refresh", data_send1);
//                            var json = "<h4>Ajax Response</h4><pre>"
//                                + JSON.stringify(data, null, 4) + "</pre>";
//                            $('#message').html(json);
//                
//                            console.log("SUCCESS : ", data);
//                            $("#btn-search").prop("disabled", false);
                
                        },
                        error: function (e) {
                             try{
                                var myObject = JSON.parse(e.responseText);
                                $('#message').html(createMessage([myObject["error.dishes"], myObject["error.price"]]));
                            }catch(e)
                            {

                            }
                        }
                    });
            
            
            
        }


 