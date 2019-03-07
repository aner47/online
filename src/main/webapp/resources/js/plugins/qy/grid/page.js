/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.1
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
jQuery.fn.pagination = function(maxentries, opts) {
    opts = jQuery.extend({
        items_per_page: 10,   //每页显示项数
        num_display_entries: 10,   //中间显示项数
        current_page: 0,   //开始项的下标
        num_edge_entries: 1,   //最开始和最结尾显示项数
        link_to: "#",
        prev_text: "Prev",   //上一页内容
        next_text: "Next",   //下一页内容
        ellipse_text: "",   //省略内容
        export_show_always: false,   //导出项显示
        maxentries_show_always: true,   //总数项显示
        select_show_always: false,   //跳转项显示
        prev_show_always: true,   //上一页显示
        next_show_always: true,   //下一页显示
        exportAllTitle:'确定导出所有数据？',   //导出全部
        exportSomeTitle:'确定导出已选数据？',   //导出选中
        exportThisTitle:'确定导出本页数据？',   //导出本页
        selectClass:'mygrid_selected',   //选中项的类名
        selectNum:function(panel){return panel.parent().find('.'+this.selectClass).length},   //选中项的类名
        exportAll:function(){console.log("all")},   //导出全部
        exportSome:function(){console.log("some")},   //导出选中
        exportThis:function(){console.log("this")},   //导出本页
        callback: function() { return false; }
    }, opts || {});


    // 真实最大值
    var maxTrue = maxentries;
    
    return this.each(function() {
        /**
        * Calculate the maximum number of pages
        */
        function numPages() {
            return Math.ceil(maxentries / opts.items_per_page);
        }

        /**
        * Calculate start and end point of pagination links depending on 
        * current_page and num_display_entries.
        * @return {Array}
        */
        function getInterval() {
            var ne_half = Math.ceil(opts.num_display_entries / 2);
            var other_half = opts.num_display_entries - ne_half;
            var np = numPages();
            var upper_limit = np - opts.num_display_entries;
            var start = current_page > other_half ? Math.max(Math.min(current_page - other_half, upper_limit), 0) : 0;
            var end = current_page > ne_half ? Math.min(current_page + ne_half, np) : Math.min(opts.num_display_entries, np);
            return [start, end];
        }

        /**
        * This is the event handling function for the pagination links. 
        * @param {int} page_id The new page number
        */
        function pageSelected(page_id, evt) {
            current_page = page_id;
            drawLinks();
            var continuePropagation = opts.callback(page_id,opts);
            if (!continuePropagation) {
                if (evt.stopPropagation) {
                    evt.stopPropagation();
                }
                else {
                    evt.cancelBubble = true;
                }
            }
            return continuePropagation;
        }

        /**
        * This function inserts the pagination links into the container element
        */
        function drawLinks() {
            panel.empty();
            var interval = getInterval();
            var np = numPages();
            var panelF=$('<div class="panelF"></div>');
            // This helper function returns a handler function that calls pageSelected with the right page_id
            var getClickHandler = function(page_id) {
                return function(evt) { return pageSelected(page_id, evt); }
            }
            // Helper function for generating a single link (or a span tag if it'S the current page)
            var appendItem = function(page_id, appendopts) {
                page_id = page_id < 0 ? 0 : (page_id < np ? page_id : np - 1); // Normalize page id to sane value
                appendopts = jQuery.extend({ text: page_id + 1, classes: "current" }, appendopts || {});
                if (page_id == current_page) {
                    var lnk = $("<span class='current'>" + (appendopts.text) + "</span>");
                }
                else {
                    var lnk = $("<a>" + (appendopts.text) + "</a>")
                        .bind("click", getClickHandler(page_id))
                    ;


                }
                if (appendopts.classes) { lnk.removeAttr('class'); lnk.addClass(appendopts.classes); }
                /*panelF.append(lnk);*/
            }
            var tableExport = function(){
                var tableExport = $('<div class="tableExport"></div>');
                var span = $('<span data-t="All">导出excel</span>');
                var icon = $('<i class="icon"></i>');
                var ul = $('<ul class="tableSpinner"></ul>');
                var l1 = $('<li data-t="All">导出全部</li>');
                var l2 = $('<li data-t="Some">导出选中</li>');
                var l3 = $('<li data-t="This">导出本页</li>');
                span.on('click',function(){
                    var t = $(this).data('t');
                    var title1 = opts['export'+t+'Title'];
                    var title2;
                    if (t=='All') {
                        title2 = '导出数据：'+maxTrue+'条';
                    }else if(t=='This'){
                        if(current_page < np-1){
                            title2 = '导出数据：'+opts.items_per_page+'条';
                        }else{
                            title2 = '导出数据：'+(maxTrue % opts.items_per_page)+'条';
                        }
                    }else{
                        title2 = '导出数据：'+opts.selectNum(panel)+'条';
                    };
                    qy.tableExport({
                        title1:title1,
                        title2:title2,
                        yes:function(index){
                            layer.close(index);
                            opts['export' + t]();
                        }
                    })
                })
                icon.on('click',function(e){
                    ul.toggleClass('active');
                    e.stopPropagation();
                })
                ul.on('click','li',function(){
                    span.data('t',$(this).data('t')).text($(this).text());
                    var t = $(this).data('t');
                    var title1 = opts['export'+t+'Title'];
                    var title2;
                    if (t=='All') {
                        title2 = '导出数据：'+maxTrue+'条';
                    }else if(t=='This'){
                        if(current_page < np-1){
                            title2 = '导出数据：'+opts.items_per_page+'条';
                        }else{
                            title2 = '导出数据：'+(maxTrue % opts.items_per_page)+'条';
                        }
                    }else{
                        title2 = '导出数据：'+opts.selectNum(panel)+'条';
                    };
                    qy.tableExport({
                        title1:title1,
                        title2:title2,
                        yes:function(index){
                            layer.close(index);
                            opts['export'+t]();
                        }
                    })
                    
                })
                $(document).click(function(){
                    ul.removeClass('active');
                })
                ul.append(l1,l2,l3);
                tableExport.append(span,icon,ul);
                panel.append(tableExport);
            }
            var selectJump = function(){
                var selectJump = $('<div class="selectJump"></div>');
                var select = $('<input type="text">');
                var span = $('<span class="sure">确定</span>');
                var num = current_page;
                select.val((num || 0)+1);
                var numDict = {
                    '0':true,
                    '1':true,
                    '2':true,
                    '3':true,
                    '4':true,
                    '5':true,
                    '6':true,
                    '7':true,
                    '8':true,
                    '9':true,
                }
                select.on('keydown',function(e){
                    if (e.keyCode != 13) {
                        return;
                    };
                    select.blur();
                    var n = parseInt(select.val())-1;
                    if (n >= 0 && n < np) {
                        if (n == num) {
                            return;
                        }else{
                            return pageSelected(n,e);
                        };
                    };
                })
                select.on('keypress',function(e){
                    if (!numDict[e.key]) {
                        return false;
                    };
                })
                span.on('click',function(e){
                    var n = parseInt(select.val())-1;
                    if (n >= 0 && n <= np) {
                        if (n == num) {
                            return;
                        }else{
                            return pageSelected(n,e);
                        };
                    };
                })
                selectJump.append('<span>跳转</span>',select,span);
                panel.append(selectJump);
            }
            // 导出表格
            if (opts.export_show_always) {
                tableExport();
            }
            // 跳页
            if (opts.select_show_always) {
                selectJump();
            }
            // Generate "Previous"-Link 上一页
            if (current_page > 0 || opts.prev_show_always) {
                appendItem(current_page - 1, { text: opts.prev_text, classes: "disabled" });
            }
            // Generate starting points
            if (interval[0] > 0 && opts.num_edge_entries > 0) {
                var end = Math.min(opts.num_edge_entries, interval[0]);
                for (var i = 0; i < end; i++) {
                    appendItem(i);
                }
                if (end < interval[0] && opts.ellipse_text) {
                    if (end + 1 == interval[0]) {
                        appendItem(end);
                    }else{
                        jQuery("<span>" + opts.ellipse_text + "</span>").appendTo(panelF);
                    };
                }
            }
            // Generate interval links
            for (var i = interval[0]; i < interval[1]; i++) {
                appendItem(i);
            }
            // Generate ending points
            if (interval[1] < np && opts.num_edge_entries > 0) {
                var begin = Math.max(np - opts.num_edge_entries, interval[1]);
                if (begin > interval[1] && opts.ellipse_text) {
                    if (begin == interval[1] + 1) {
                        appendItem(interval[1]);
                    }else{
                        jQuery("<span>" + opts.ellipse_text + "</span>").appendTo(panelF);
                    };
                }
                for (var i = begin; i < np; i++) {
                    appendItem(i);
                }

            }
            // Generate "Next"-Link 下一页
            if (current_page < np - 1 || opts.next_show_always) {
                appendItem(current_page + 1, { text: opts.next_text, classes: "disabled" });
            }
            // 分页框
            panel.append(panelF);
            // 总数
            if (opts.maxentries_show_always) {
                /*panel.append('<span class="maxentries">总记录'+maxTrue+'条</span>');*/
            }
        }

        // Extract current_page from options
        var current_page = (opts.current_page>=0) ? opts.current_page : 0;
        // Create a sane value for maxentries and items_per_page
        maxentries = (!maxentries || maxentries < 0) ? 1 : maxentries;
        opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0) ? 1 : opts.items_per_page;
        // Store DOM element for easy access from all inner functions
        var panel = jQuery(this);
        // Attach control functions to the DOM element 
        this.selectPage = function(page_id) { pageSelected(page_id); }
        this.prevPage = function() {
            if (current_page > 0) {
                pageSelected(current_page - 1);
                return true;
            }
            else {
                return false;
            }
        }
        this.nextPage = function() {
            if (current_page < numPages() - 1) {
                pageSelected(current_page + 1);
                return true;
            }
            else {
                return false;
            }
        }
        // When all initialisation is done, draw the links
        drawLinks();
    });
}