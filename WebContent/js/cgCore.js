/**
 * 
 */
$(function() {
	$("#tabs").tabs();
	$("[block='content_index_block']").on({
		mouseenter : function() {
			$(this).addClass("ui-state-highlight ui-corner-all");
		},
		mouseleave : function() {
			$(this).removeClass("ui-state-highlight ui-corner-all");
		}
	});
	
//
//	$('li.mainlevel').mousemove(function() {
//		$(this).find('ul').slideDown("1000");// you can give it a speed
//	});
//	$('li.mainlevel').mouseleave(function() {
//		$(this).find('ul').slideUp("fast");
//	});
//	$("#menu").corner("10px");
//	initMenu();
});

