/**
 * jquery.qrcode
 */

/**
 * 绘制二维码
 */
function makeCode() {		
	var elText = document.getElementById("input-text");
	
	var wh = Number(document.getElementById("wh").value);
	var colorDark = document.getElementById("colorDark").value;
	var colorLight = document.getElementById("colorLight").value;
	var $correctLevel = Number($("input[name='correctLevel']:checked").val());
	var canvas = document.getElementById("qrcode");
	var $urlencode = $("input[name='urlencode']:checked").val();
	
	var $render = $("input[name='render']:checked").val()
	var imgsrc = document.getElementById("imgsrc").value;
	var imgsize = Number(document.getElementById("imgsize").value);

	if (!elText.value) {
		elText.focus();
		//return;
	}
	$('#qrcode').html("");
	
	var parameters = {
		render: $render, //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
		text: $urlencode == "true" ? encodeURI(elText.value) : elText.value, //扫描二维码后显示的内容
		width: wh, //二维码的宽度
		height: wh,
		background: colorLight, //二维码的背景色
		foreground: colorDark, //二维码的前景色
		correctLevel: $correctLevel, //L:1,M:0,Q:3,H:2
		src: imgsrc,
		imgWidth: imgsize,
		imgHeight: imgsize,
	};
	$(canvas).qrcode(parameters);
}

makeCode();

/**
 * 自动根据输入内容生成二维码
 */
$(".ckin").click(function () {
			makeCode();
});
$("input[type='radio']").click(function () {
			makeCode();
});
$('.keyin').on("keyup", function (e) {
			makeCode();
});