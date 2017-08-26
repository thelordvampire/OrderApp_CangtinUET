

        function createMessage(arr)
        {
            var result="<ul>";
            
            for(i=0;i<arr.length;i++)
            {
                if(arr[i]!==undefined)
                    result+="<li>"+arr[i]+"</li>";
            }
            result+="</ul>";
            
            return result;
        }