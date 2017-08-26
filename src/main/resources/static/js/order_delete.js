


function order_delete()
{
            var data_send="";
            var orderDay = $("#OrderForm #orderDay").val();
            
//            alert(orderDay);
            data_send={
                day: orderDay
            };
            
            $.ajax({
                        type: "POST",
//                        contentType: "application/json",
                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                        url: "/order/delete",
                        data: data_send,
                        dataType: 'text',
                        cache: false,
                        timeout: 600000
                        ,
                        success: function (data) {
//                            alert("11111111111");
//                            alert(data);
                            
                            $("#OrderForm").load("/order/refresh", data_send);
                
//                            var json = "<h4>Ajax Response</h4><pre>"
//                                + JSON.stringify(data, null, 4) + "</pre>";
//                            $('#feedback').html(json);
//                
//                            console.log("SUCCESS : ", data);
//                            $("#btn-search").prop("disabled", false);
                
                        },
                        error: function (e) {
                            alert(e.responseText);
//                            window.location="/user/login";
                
//                            var json = "<h4>Ajax Response</h4><pre>"
//                                + e.responseText + "</pre>";
//                            $('#feedback').html(json);
//                
//                            console.log("ERROR : ", e);
//                            $("#btn-search").prop("disabled", false);
                
                        }
                    });
                    }       
            
        