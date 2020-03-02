$(function(){
	$("#load").load("qrcode.html");
});

tab = function(em,ea,t){
	em.removeClass(t);
	ea.addClass(t)
}
$(function(){
	var $e = $("#select>ul>li");
	
	$("#jqqrc").click(function(){
		tab($e,$(this),"act")
		$("#load").load("jq-qrcode.html");
	});
	$("#qrc").click(function(){
		tab($e,$(this),"act")
		$("#load").load("qrcode.html");
	});
});
