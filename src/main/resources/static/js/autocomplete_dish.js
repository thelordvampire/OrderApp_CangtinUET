/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
//
//$(function() {
//  var availableTags = [
//    "ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++",
//    "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran",
//    "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl",
//    "PHP", "Python", "Ruby", "Scala", "Scheme"
//  ];
//  
//  alert("auto complete");
//  
//  $(".autocomplete").autocomplete({
//    source: availableTags
//  });
//  
//});


$(function() {
    
    $('.dishAutocomplete').flexdatalist({
//        data: '/dish/autocomplete',
        url: '/dish/autocomplete',
     selectionRequired: 1,
     minLength: 1,
     removeOnBackspace : false,
     cache: true,
     cacheLifetime: 60,
     searchIn: 'name',
     noResultsText : "Không tìm thấy kết quả nào cho từ khóa '{keyword}'",
     searchContain : true,
//     iconProperty : 'image_link_to_show' ,
     valueProperty: 'id',
     visibleProperties: ['name']
//     textProperty: '{image_link_to_show} {name}'
     
});
//    $('#TestAutocomplete').click(function (){
//            alert($('.flexdatalist').flexdatalist('value'));
//            });

    
});

