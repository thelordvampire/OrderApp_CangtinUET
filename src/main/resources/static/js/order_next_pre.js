


            function orderNext()
            {

                        var data_send="";
                        var day = $("#OrderForm #day").html();

                        data_send={
                            day: day
                        };

                        $("#OrderForm").load("/order/next", data_send);

            //            callAjax("next", data_send);


            }
            
            function orderPrevious()
            {
                
                var data_send="";
                var day = $("#OrderForm #day").html();
                data_send={
                    day: day
                };
                $("#OrderForm").load("/order/pre", data_send);
            }

//$(document).ready(function() {      
//    
//    $("#btnOrderNext").click(function(e) {
//        
//            var data_send="";
//            var day = $("#OrderForm #day").html();
//            
//            data_send={
//                day: day
//            };
//            
//            $("#OrderForm").load("/order/next", data_send);
//
////            callAjax("next", data_send);
//            
//            
//        }) ;
//
//    $("#btnOrderPrevious").click(function(e) {
//
//        var data_send="";
//        var day = $("#OrderForm #day").html();
//        data_send={
//            day: day
//        };
//        $("#OrderForm").load("/order/pre", data_send);
//        });
//    });        