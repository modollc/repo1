 <%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
 </div>
    
    
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$('.nav-menu-content-box').hover(function() {
			$(this).addClass('dow-hover');
			$(".nav-menu-content").show('slow');
		}, function() {
			$(this).removeClass('dow-hover');
		});

	});
	var maxHeight = 400;
	$(function(){
		$(".nav-menu-content-box > li").hover(function() {

			var $container = $(this),
				$list = $container.find("ul"),
				$anchor = $container.find("a"),
				height = $list.height() * 1.1,
				multiplier = height / maxHeight;

			$container.data("origHeight", $container.height());

			$anchor.addClass("hover");

			$list
				.show()
				.css({
					paddingTop: $container.data("origHeight")
				});

			if (multiplier > 1) {
				$container
					.css({
						height: maxHeight,
						overflow: "hidden"
					})
					.mousemove(function(e) {
						var offset = $container.offset();
						var relativeY = ((e.pageY - offset.top) * multiplier) - ($container.data("origHeight") * multiplier);
						if (relativeY > $container.data("origHeight")) {
							$list.css("top", -relativeY + $container.data("origHeight"));
						};
					});
			}

		}, function() {

			var $el = $(this);

			$el
				.height($(this).data("origHeight"))
				.find("ul")
				.css({ top: 0 })
				.hide()
				.end()
				.find("a")
				.removeClass("hover");

		});

	});
   

</script>
</html>