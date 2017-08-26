




        function nextOrderMonth()
        {
//            alert("abc");
            var month = $("#monthValue").val();
            month+="-01";
            var data_send="";
//            alert(month);

                data_send={
                    month: month
                };
                $("#MonthOrder").load("/order/month/next", data_send);
            //            callAjax("next", data_send);
        }
        
        function  previousOrderMonth()
        {
            //        var month = "2017-9-12";
            var month = $("#monthValue").val();
            month+="-01";
            var data_send="";
//                    alert(month);

            data_send={
                month: month
            };
            $("#MonthOrder").load("/order/month/pre", data_send);
//            callAjax("next", data_send);
            
        }   