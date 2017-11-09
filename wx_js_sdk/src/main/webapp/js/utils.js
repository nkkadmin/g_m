/**
 * 生成随机数
 * 
 * @param {Object}
 *            flag 是否是任意长度
 * @param {Object}
 *            min 任意长度最小值
 * @param {Object}
 *            max 任意长度最大值
 */
function randomStr(flag, min, max) {
	var str = "";
	var index = "";
	var range = min;
	var arr = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];
	// min,max范围内随机的一个数
	if (flag) {
		range = Math.floor(Math.random() * (max - min + 1) + min);
	}
	for (var i = 0; i < range; i++) {
		index = Math.round(Math.random() * (arr.length - 1));
		str += arr[index];
	}
	return str;
}

/**
 * 生成当前时间戳
 */
function nowTime() {
	var tmp = Date.parse(new Date()).toString();
	tmp = tmp.substr(0, 10);
	return tmp;
}
