<ul id="menu" class="list-group">

    <li class="list-group-item">
        <a href="#" data-toggle="collapse" >
            <span>攻略管理</span>
        </a>
        <ul class="in" >
            <li class="strategy"><a href="/strategy/list.do">大攻略管理</a></li>
            <li class="strategyCatalog"><a href="/strategyCatalog/list.do">攻略分类管理</a></li>
            <li class="strategyDetail"><a href="/strategyDetail/list.do">攻略分类管理</a></li>
            <li class="everybodyWatching"><a href="/everybodyWatching/list.do">大家都在看</a></li>
        </ul>
    </li>

    <li class="list-group-item">
        <a href="#" data-toggle="collapse" >
            <span>日报管理</span>
        </a>
        <ul class="in">
            <li class="newspage"><a href="/newsPages/list.do">日报分类管理</a></li>
        </ul>
    </li>
    <li class="list-group-item">
        <a href="#" data-toggle="collapse" >
            <span>问答管理</span>
        </a>
        <ul class="in">
            <li class="question"><a href="/questions/list.do">客户提问管理</a></li>
        </ul>
    </li>
    <li class="list-group-item">
        <a href="#" data-toggle="collapse" data-target="#formalCustomerMgr">
            <span>游记管理</span>
        </a>
        <ul class="in" id="formalCustomerMgr">
            <li class="releaseTravel"><a href="/travel/releaseList.do?state=1">已发布游记管理</a></li>
            <li class="defaultTravel"><a href="/travel/list.do">待审核游记</a></li>
            <li class="travelCommend"><a href="/travelCommend/list.do">游记推荐设置</a></li>
        </ul>
    </li>



    <li class="list-group-item">
        <a href="#" data-toggle="collapse" data-target="#hrMgr">
            <span>平台管理</span>
        </a>
        <ul class="in" id="hrMgr">
            <li class="user"><a href="/user/list.do">注册用户管理</a></li>
            <li class="region"><a href="/region/list.do">旅游地区管理</a></li>
            <li class="mall"><a href="/mall/list.do">商城管理</a></li>
            <li class="scenic"><a href="/scenic/list.do">门票系统管理</a></li>
        </ul>
    </li>
    <li class="list-group-item">
        <a href="#" data-toggle="collapse" data-target="#personMgr">
            <span>个人管理</span>
        </a>
        <ul class="in" id="personMgr">
            <li class="user"><a href="#">修改密码</a></li>
            <li class="region"><a href="#">个人偏好</a></li>
            <li class="region"><a href="#">签到签退</a></li>
        </ul>
    </li>
</ul>

<!--设置菜单回显-->
<script>
    $(".in li.${currentMenu}").addClass("active");
</script>