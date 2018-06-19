var data = {
    pageNum: '1',
    pageSize: '6',
    type: ''
};

jQuery.ajax({
    url: "/notation/page/list",
    type: 'POST',
    data: JSON.stringify(data),
    dataType: 'json',
    contentType: 'application/json',
    success: function (result) {
        console.log(result);
        var rows = result.data.info.list;
        var size = result.data.info.total;
        if (size > 0) {
            handleData(rows); //生成表格
            pageData(size); //调用分页插件代码
        } else {
            // $('#serviceTbile tbody').append('<tr><td colspan="10">暂无相关数据</td></tr>');
        }
    }
});

function handleData(arr) {
    // console.log(arr)
    for (var i = 0; i < arr.length; i++) {
        // DOM操作添加商品
        var str = '<div class="card" id="'+i+'">' +
            '<div class="card__container card__container--closed">' +
            '<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">' +
            '<defs>' +
            '<clipPath id="clipPath' + i + '">' +
            '<circle class="clip" cx="960" cy="250" r="992"></circle>' +
            '</clipPath>' +
            '</defs>' +
            '<image id="icon-'+i+'" clip-path="url(#clipPath' + i + ')" data-src="" width="1920" height="500" xlink:href="' + arr[i].resourceUrl + '"></image>' +
            '</svg>' +
            '<div class="card__content">' +
            '<i class="card__btn-close fa fa-times"></i>' +
            '<div class="card__caption">' +
            '<h2 class="card__title">' + arr[i].title + '</h2>' +
            '<p class="card__subtitle">' + arr[i].subtitle + '</p>' +
            '</div>' +
            '<div class="card__copy">' +
            '<div class="meta">' +
            '<img class="meta__avatar" src="' + arr[i].authorUrl + '" alt="author01" style="width: 50px;height: 50px"/>' +
            '<span class="meta__author">' + arr[i].accTitle + '</span>' +
            '<span class="meta__date">' + arr[i].createTime + '</span>' +
            '</div>' +
            '<div class="music" style="height: 72.4px;transition:all 0.3s;background-image: '+arr[i].bmgUrl+'">'+
            '<audio class="music-audio" id="music-audio-'+i+'" data-src="' + arr[i].accUrl + '" controls="controls" style="background-color: #7cc576">你的浏览器不支持</audio>'+
            // '<audio class="music-audio" id="muscic-audio-"'+id+' src="' + arr[i].accUrl + '" controls="controls" style="background-color: #7cc576">你的浏览器不支持</audio>'+
            '</div>'+
            '<p>' + arr[i].bio + '</p> ' +
            // '<p><img class="yp" id="yp-"'+id+'  src="' + arr[i].notationUrl + '" alt="author01" /></p>' +
            '<p><img class="yp" id="yp-'+i+'"  data-src="' + arr[i].notationUrl + '" alt="author01" /></p>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>'
        jQuery('.wrapper').eq(0).append(jQuery(str))
    }
            demo.init();

}
function setSrc(id,flag) {
    var i = jQuery('#icon-'+id);
    // console.log(i.attr('data-src'))
    // console.log(flag)
    // if(flag='0'){
        var f = jQuery('#music-audio-'+id);
        var s = jQuery('#yp-'+id);
        // i.attr('data-src',i.attr('xlink:href'))
        // i.attr('xlink:href','http://www.skizhou.com/page/image/harmonica/suddenlove/bmg.jpeg')

        if(!f.attr('src')){
            f.attr('src',f.attr('data-src'))
            s.attr('src',s.attr('data-src'))
        }
    // }else{

    //     console.log(i.attr('data-src'))
    //     i.attr('xlink:href',i.attr('data-src'))
    // }


}
function pageData(totalCount) {

    jQuery('.M-box4').pagination({
        totalData: totalCount,
        showData: 6,
        // mode: 'fixed',
        // count: 6,
        // prevContent: '?',
        // nextContent: '?',
        // pageCount: totalCount,
        jump: true,
        callback: function (api) {
            var data = {
                pageNum: api.getCurrent(),
                pageSize: '6',
                type: '',
                name: name
            };
            jQuery.ajax({
                url: '/notation/page/list',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    var rows = result.data.info.list;
                    // jQuery(".card").removeClass();
                    jQuery('#wrapper').empty();
                    handleData(rows);
                }
            });
        }
    });
}
function search() {
    var title = jQuery("#btn-search").val();
    console.log(title)
    var data = {
        pageNum: '1',
        pageSize: '6',
        title: title
    };
    jQuery.ajax({
        url: "/notation/page/fuzzy",
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            console.log(result);
            var rows = result.data.info.list;
            var size = result.data.info.total;
            jQuery('#pager').hide()
            jQuery('#wrapper').empty();
            console.log(size)
            console.log(rows.length)
            if (rows.length > 0) {
                handleData(rows); //生成表格
                if (size > 2) {
                    jQuery('#pager').show(100)
                    pageData(size); //调用分页插件代码
                }
            } else {
                jQuery('.wrapper').eq(0).append('<span class="meta__author" style="font-size: 30px;color: #8f9a8f; margin-left: 40%">空空如也</span>');
            }
        }
    });
}
jQuery("#s-about").click(function(){
    location.href = "./about.html";
})
