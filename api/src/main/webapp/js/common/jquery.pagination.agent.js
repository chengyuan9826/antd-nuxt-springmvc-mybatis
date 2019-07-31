/**
 * 2013-7-13 修改。第一次加载时，不调用2次。 by shandl
 _page.pagination( json.total , {  
    'items_per_page'      : pageParams.items_per_page,  
    'num_display_entries' : 10,  
    'num_edge_entries'    : 2,  
    'link_to': '#tradeRecordsIndex' ,
    'prev_text'           : "上一页",  
    'next_text'           : "下一页",  
    'call_callback_at_once' : false,  //控制分页控件第一次不触发callback.
    'callback'            : function(page_index, jq){  
								getDataList(page_index , false , pageParams );  
							}  
});  
 * 
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
jQuery.fn.pagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:10,//每页显示的条目数
		num_display_entries:6,//连续分页部分显示的最大数目
		current_page:0,//当前页，0位第一页
		num_edge_entries:1,//两侧显示的首尾分页条数
		link_to:"#",
		prev_text:"上一页",//上一页的文本
		next_text:"下一页",//下一页的文本
		ellipse_text:"...",//省略部分的文本
		prev_show_always:true,//总是显示'上一页'
		next_show_always:true,//总是显示'下一页'
		call_callback_at_once : true,//第一次加载的时候是否调用回调函数
		callback:function(){return false;}
	},opts||{});

	return this.each(function() {
		/**
		 * Calculate the maximum number of pages
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}

		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @return {Array}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}

		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			drawLinks();
			var continuePropagation = opts.callback(page_id, panel);
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
			
			// Added by shili.xu at 2015.08.11
			var ul = jQuery("<ul>");
			ul.appendTo(panel);
			
			var interval = getInterval();
			var np = numPages();
			// This helper function returns a handler function that calls pageSelected with the right page_id
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); }
			}
			// Helper function for generating a single link (or a span tag if it's the current page)
			var appendItem = function(page_id, appendopts){
				page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
				appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
				if(page_id == current_page){
					// Modified by shili.xu at 2015.08.11
					//var lnk = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
					var lnk = jQuery("<li id='current-page' class='current-page'>"+(appendopts.text)+"</li>");
				}
				else
				{
					// Modified by shili.xu at 2015.08.11
					/*
					var lnk = jQuery("<a>"+(appendopts.text)+"</a>")
					.bind("click", getClickHandler(page_id))
					.attr('href', opts.link_to.replace(/__id__/,page_id));
					*/

					var lnk = jQuery("<li>"+(appendopts.text)+"</li>")
					.bind("click", getClickHandler(page_id));
				}
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				
				//Modified by shili.xu at 2015.08.11
				//panel.append(lnk);
				ul.append(lnk);
			}
			
			// Remarked by shili.xu at 2015.08.11
			/*
			// Generate "Previous"-Link
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			*/


			// Added by shili.xu at 2015.08.11
			// Generate "Previous"-Link
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"page-pre"});
			}
			
			// Generate starting points
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(var i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					//Modified by shili.xu at 2015.08.11
					//jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
					jQuery("<li>"+opts.ellipse_text+"</li>").appendTo(ul);
				}
			}
			
			
			// Generate interval links
			for(var i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			
			// Generate ending points
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					//Modified by shili.xu at 2015.08.11
					//jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
					jQuery("<li>"+opts.ellipse_text+"</li>").appendTo(ul);
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(var i=begin; i<np; i++) {
					appendItem(i);
				}
			}
			
			
			
			// Generate "Next"-Link
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"page-pre"});
			}
		}

		// Extract current_page from options
		var current_page = opts.current_page;
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		// Store DOM element for easy access from all inner functions
		var panel = jQuery(this);
		// Attach control functions to the DOM element 
		this.selectPage = function(page_id){ pageSelected(page_id);}
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		// When all initialisation is done, draw the links
		drawLinks();
		// call callback function
		if (opts.call_callback_at_once) { // 修改。第一次加载时，不调用2次。
			opts.callback(current_page, this);
		}

	});
}


