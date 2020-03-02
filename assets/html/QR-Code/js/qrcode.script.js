/**
 * 绘制二维码
 */
function makeCode () {		
	var elText = document.getElementById("input-text");
	
	var wh = Number(document.getElementById("wh").value);
	var colorDark = document.getElementById("colorDark").value;
	var colorLight = document.getElementById("colorLight").value;
	var $correctLevel = Number($("input[name='correctLevel']:checked").val());
	var canvas = document.getElementById("qrcode");
	var $urlencode = $("input[name='urlencode']:checked").val();
	
	if (!elText.value) {
		elText.focus();
		//return;
	}
	
	$('#qrcode').html("");
	
	var parameters = {
		width: wh,
		height: wh,
		// typeNumber : 4, //计算模式 1-40
		colorDark: colorDark,
		colorLight: colorLight,
		correctLevel: $correctLevel //L:1,M:0,Q:3,H:2
	};
	
	var qrcode = createrQRCode(canvas, parameters);
	
	qrcode.makeCode($urlencode == "true" ? encodeURI(elText.value) : elText.value);
}

/**
 * 创建二维码对象
 * @param {Object} id
 * @param {Object} parameters
 */
function createrQRCode(e, parameters) {
	return new QRCode(e, parameters);
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
