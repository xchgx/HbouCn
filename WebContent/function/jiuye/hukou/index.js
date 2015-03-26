/**
 * 
 */
$(function() {
	var valid = false; // 合法性验证结果
	var resultTableData = "<table width='100%' style='border-width:1px;border-color:yellow;border-style:solid;'>" +
			"<tr><td align='left' bgcolor='#153087'>学号</td><td align='left'>hukouNo</td>" +
			"<td align='left' bgcolor='#153087'>身份证</td><td align='left'>hukouSfz</td></tr>" +
			"<tr><td align='left' bgcolor='#153087'>姓名</td><td align='left'>hukouName</td>" +
			"<td align='left' bgcolor='#153087'>性别</td><td align='left'>hukouSex</td></tr>" +
			"<tr><td align='left' bgcolor='#153087'>专业</td><td colspan=3 align='left'>hukouProfession</td></tr>" +
			"<tr><td align='left' bgcolor='#153087'>毕业去向</td><td colspan=3 align='left'>hukouWhere</td></tr>" +
			"<tr><td align='left' bgcolor='#153087'>报到证签往单位名称</td><td colspan=3 align='left'>hukouCompany</td></tr>" +
			"</table>";
	$(window).resize(
			function() { // 动态居中，css无法实现就用jquery实现
				$('.main')
						.css(
								{
									position : 'absolute',
									left : ($(window).width() - $('.main')
											.outerWidth()) / 2,
									top : ($(window).height() - $('.main')
											.outerHeight())
											/ 2 + $(document).scrollTop()
								});
			});
	$(window).resize();
	// 加载后定位焦点在文本框上
	$(".input_sfz").focus();
	// 单击搜索按钮触发的事件
	$(".btn_search").on({
		click : function() { // 单击事件
			$(".btn_search").hide("slow");
			doSearch();
			
		},
		mouseenter : function() { // 鼠标移入图片之上
			// alert("进入区域");
			$(".btn_search_img").attr({
				"src" : "btn_search_hove.png"
			});
		},
		mouseleave : function() {// 鼠标离开图片
			// alert("离开区域");
			$(".btn_search_img").attr({
				"src" : "btn_search.png"
			});
		},
		mousedown : function() {// 鼠标按下图片
			// alert("按下");
			$(".btn_search_img").attr({
				"src" : "btn_search_press.png"
			});
		}
	});
	$(".input_sfz").on(
			{
				blur : function() {
					// alert("文本框失去焦点");
					var value = $(this).val();
					// if(value.length)
				},
				keyup : function() {
					var value = $(this).val();
					var color = "red";
					var isOk = "";
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
					if (reg.test(value) === false) {
						isOk = "";
						valid = false;
					} else {
						isOk = "<br>输入值合法！可以点击放大镜查询";
						valid = true;
					}
					if(value.length==15||value.length==18){
						color = "#ADFF2F";
					}else{
						color = "red";
					}
					$(".status").html(
							"当前输入的长度为：<font style='font-size:20px;' color="
									+ color + ">" + value.length + isOk
									+ "</font>");
				},
				keydown : function(e) {
					if (e.which == 13) {
						doSearch();
					}
				}
			});

	/**
	 * 搜索按钮被按下，调用Ajax执行查询。
	 */
	function doSearch() {
		$(".result").html("正在查询，请稍后！");
		if (valid) {
			
			 $.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: getBasePath()+"/hukou/getHukouBySfz/"+$(".input_sfz").val()+".do",
					dataType:"json",
					success:function(msg){
						var data = resultTableData;
						data = data.replace("hukouNo",msg.no);
						data = data.replace("hukouName",msg.name);
						data = data.replace("hukouSfz",msg.sfz);
						data = data.replace("hukouProfession",msg.profession);
						data = data.replace("hukouWhere",msg.where);
						data = data.replace("hukouSex",msg.sex);
						data = data.replace("hukouCompany",msg.company);
						$(".result").html("查询结果如下：<br>"+data);
						
					},
					error:function(){
						alert("doSearch() error");
					}
				});
			
		} else {
			$(".status").html("请检查输入是否正确");
			$(".input_sfz").focus();
			$(".input_sfz").select();
		}
		$(".btn_search").show("slow");
	}
});