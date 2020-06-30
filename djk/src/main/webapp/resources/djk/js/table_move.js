//表格排序移动（暂时只能放在最后，可能有冲突，抽空检查）
    var move = $(".move");
    var fixHelperModified = function(e, tr) {
        var $originals = tr.children();
        var $helper = tr.clone();
        $helper.children().each(function(index) {
            $(this).width($originals.eq(index).width())
            $(this).css("background-color","rgba(0,0,0,0.08)","border","1px dashed rgba(0,0,0,0.1)");
        });
        return $helper;
    },
        updateIndex = function(e, ui) {
            $('td.id', ui.item.parent()).each(function (i) {
                $(this).html(i + 1);
            });
        };
        
    $(".table_default tbody").sortable({
        helper: fixHelperModified,
        stop: updateIndex
    }).disableSelection();
