


        function order_register() 
        {
            var data_send="";
            var day = $("#OrderForm input[type='hidden'][id='menuDay']").val();
            
//            var dishes = [];
//            $("#OrderForm input[type='checkbox'][name='dishes']:checked").each(function (){
//                dishes.push($(this).attr('value'));            
//            });
///////////////////// cách cũ dùng checkbox để kiểm tra
            
//            var dishes2 = [];
            var dishes = [];
            
            $("#OrderForm input[type='hidden'][name='dishHidden']").each(function (){
                if($(this).parent(".DetailMenuDish").css("border-bottom-style")==="solid")
                dishes.push($(this).attr('value'));            
            });
            
//            alert(dishes);
//            alert(dishes2);
            
            
            
            
            var price ="";
            if($("#OrderForm input[type='radio'][name='price']:checked").val()!==undefined)
                price = $("#OrderForm input[type='radio'][name='price']:checked").val();
                
            var infor =$("#OrderForm [name='infor']").val();
                        
            data_send={
                day: day,
                dishes: dishes,
                price: price,
                infor: infor
            };
//            alert(JSON.stringify(data_send));
            
            $.ajax({
                        type: "POST",
//                        contentType: "application/json",
                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                        url: "/order/add",
                        data:  data_send,
                        dataType: "text",
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            
//                            $('#message').html(createMessage([data["success.message"]]));
                            var data_send1={day: day};
                            
                            $("#OrderForm").load("/order/refresh", data_send1);
                        },
                        error: function (e) { //jqXHR, textStatus, errorThrown
//                            alert("haha: "+textStatus);
//                            alert("bebe: "+errorThrown);
//                            alert("kaka: "+JSON.stringify(jqXHR));
//                            alert('hahaha '+e.responseText);
                
                    try{
                        var myObject = JSON.parse(e.responseText);
                        $('#message').html(createMessage([myObject["error.dishes"], myObject["error.price"]]));
                    }catch(e)
                    {
                        
                    }
                        }
                    });
            
            
            
        }
        
    