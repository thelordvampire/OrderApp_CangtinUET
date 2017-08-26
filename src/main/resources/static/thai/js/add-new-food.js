
$(document).ready(function(){
	$("#file2").on("change", function(){
		var file = $(this)[0].files[0].name;
		// alert(file);
		$(".custom-file-control").text(file);
	});
});

var loadFile = function(event) {
      var output = document.getElementById('output');
      output.src = URL.createObjectURL(event.target.files[0]);
};