var pHeight = $(window).height(),
    mddFilter = $('._j_mdd_filter'),
    timeFilter = $('._j_time_filter'),
    mddHtml = $('._j_mdd'),
    timeHtml = $('._j_time'),
    mddList = $('._j_mdd_list'),
    timeSelect = $('._j_time_select'),
    sort = $('._j_sort'),
    overlay = $('._j_overlay'),
    sortInfo = $('._j_sort_info'),
    together = $('._j_together'),
    fixed = $('._j_fixed'),
    mddTplEle = $('._j_render_tpl'),
    timeNum = $('._j_num'),
    hasMore = $('#_j_more').data('more'),
    fixedHeight = fixed.height() ? fixed.height() : 0,
    sortHtmlHeight = $('._j_sort_html').height() ? $('._j_sort_html').height() : 0,
    headHeight = $('.m-head').height() ? $('.m-head').height() : 0,
    mddListHeight,
    mddid = $('#_j_mdd_id').data('mddid'),
    offset = 10,
    timeFlag = 1,
    scrollDirection,
    flag = $('#_j_more').data('flag');

$('._j_not_login').on('tap', function () {
    alert('请先登录');
    location.href = "";
});

$('._j_not_logo').click(function (ev) {
    var error = $(ev.currentTarget).data('error') == 'lv'
        ? '发起一次结伴是5级以上用户的特权。等级越高的蜂蜂发起的结伴通常更容易成功。快下载个蚂蜂窝自由行app，努力提升等级吧～'
        : '拥有一个真实头像，将使你更容易获得他人信任。请上传有一个真实头像，才能发起结伴。快去蚂蜂窝自由行app里上传一张头像吧～';
    alert(error);
});

window.omc = window.omc || {
    allAction: [], onready: function (callback) {
        this.allAction.push([callback])
    }
};
window.omc.onready(function () {
    window.omc.bind($('._j_not_logo'), window.omc.getUrl(), window.omc.sGetDownloadUrl({
        wechat: 'https://a.app.qq.com/o/simple.jsp?pkgname=com.mfw.roadbook',
        android: 'https://m.mafengwo.cn/app/down/gl/',
        ios: 'https://m.mafengwo.cn/app/down/gl/'
    }), {});
});

mddFilter.click(function (ev) {
    pHeight = $(window).height();
    mddListHeight = pHeight - fixedHeight - sortHtmlHeight - headHeight;
    changeTabHeight(mddListHeight);
    var target = $(ev.currentTarget);
    if (target.hasClass('selected')) {
        mddHtml.hide();
        target.removeClass('selected');
        startScroll();
        return;
    }
    target.addClass('selected');
    timeFilter.removeClass('selected');
    timeHtml.hide();
    mddHtml.show();
    stopScroll();
});

timeFilter.click(function (ev) {
    pHeight = $(window).height();
    mddListHeight = pHeight - fixedHeight - sortHtmlHeight - headHeight;
    changeTabHeight(mddListHeight);
    var target = $(ev.currentTarget);
    if (target.hasClass('selected')) {
        timeHtml.hide();
        target.removeClass('selected');
        startScroll();
        return;
    }
    target.addClass('selected');
    mddFilter.removeClass('selected');
    mddHtml.hide();
    timeHtml.show();
    stopScroll();
});

sort.click(function () {
    overlay.show();
    sortInfo.css({
        'top': pHeight - 148 + 'px'
    });
    sortInfo.removeClass('hide');
});


together.click(function (ev) {
    var target = $(ev.currentTarget);
    together.removeClass('on');
    target.addClass('on');
    flag = target.data('flag');
    sortHide();
    $("._j_together_list").html('<div class="loading" style="height: 500px;  padding: 15px;text-align: center;font-size: 13px;color: #f39c11;">正在加载...</div>');
    offset = 0;
});


function sortHide() {
    overlay.hide();
    sortInfo.addClass('hide');
}

function moreBtn(hasMore) {
    if (hasMore) {
        $('._j_more_bnt').removeClass('hide');
        $('._j_more_bnt').html('加载更多');
    } else {
        $('._j_more_bnt').addClass('hide');
    }
}

//禁止滚动条
function stopScroll() {
    $('body,html').css({
        "overflow": "hidden",
        "position": "fixed"
    });
}

//启用滚动条
function startScroll() {
    $('body,html').css({
        "overflow": "",
        "position": ""
    });
}


function changeTabHeight(mddListHeight) {
    mddList.css({
        'height': mddListHeight + 'px'
    });
    timeHtml.css({
        'height': mddListHeight + 'px'
    });
    $('.col1').css({
        'height': mddListHeight - 45 + 'px'
    });
    $('.col2').css({
        'height': mddListHeight - 45 + 'px'
    });
    $('.col3').css({
        'height': mddListHeight - 45 + 'px'
    });
}
