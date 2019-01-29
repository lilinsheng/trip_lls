<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <link rel="stylesheet" href="/js/plugins/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/css/core.css" type="text/css" />
    <script type="text/javascript" src="/js/plugins/jquery/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="/js/plugins/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/plugins/ckeditor/ckeditor.js"></script>

    <link href="/js/plugins/chosen/component-chosen.css" rel="stylesheet">
    <script src="/js/plugins/chosen/chosen.jquery.js"></script>


    <script type="text/javascript">

        //解决ckeditor和model模态框冲突问题
        var $modalElement = this.$element;
        $(document).on('focusin.modal', function (e) {
            var $parent = $(e.target.parentNode);
            if ($modalElement[0] !== e.target && !$modalElement.has(e.target).length
                    &&
                    !$parent.hasClass('cke_dialog_ui_input_select') && !$parent.hasClass('cke_dialog_ui_input_text')) {
                $modalElement.focus()
            }
        })


        $(function(){
            var ckeditor = CKEDITOR.replace('content');

            $("#pagination").twbsPagination({
                totalPages:2,
                visiblePages:5,
                startPage:1,
                first:"首页",
                prev:"上一页",
                next:"下一页",
                last:"尾页",
                onPageClick:function(event,page){
                    $("#currentPage").val(page);
                    $("#searchForm").submit();
                }
            });

            $("#query").click(function(){
                $("#currentPage").val(1);
                $("#searchForm").submit();
            });

            //点击添加攻略分类按钮
            $("#addStrategyBtn").click(function(){
                $("#editForm")[0].reset();
                $("#strategyModal").modal("show");
            });

            //点击保存
            $("#editForm").ajaxForm(function(data){
                $.messager.confirm("提示","保存成功",function(){
                    window.location.reload();
                });
            });
            $("#saveBtn").click(function(){
                //把文本编辑器的内容放到表单中
                $("#content").html(ckeditor.getData());
                $("#editForm").submit();
            });

            //点击修改按钮
            $(".edit_Btn").click(function(){
                var json=$(this).data("jsonstr");
                $("#id").val(json.id);
                $("#title").val(json.title);
                $("#coverUrl").attr('src',json.coverUrl);
                $("#sequence").val(json.sequence);
                $("#state option[value='"+json.state+"']").prop("selected",true);
                //回显所属类别
                if(json.catalog){
                    $('#catalogSelect').html("<option value='"+json.catalog.id+"' selected>"+json.catalog.name+"</option>");
                    $('#catalogSelect').trigger("chosen:updated");
                    //回显所属攻略
                    if(json.strategy){
                        $("#strategySelect option[value='"+json.strategy.id+"']").prop("selected",true);
                        $('#strategySelect').trigger("chosen:updated");
                    }
                }
                //获取该文章的内容
                $.get('/strategyDetail/getContentById.do',{id:json.id},function (data) {
                    ckeditor.setData(data.content);
                    $("#strategyModal").modal("show");
                });
            });


            //攻略下拉框
            $.get('/strategy/selectAll.do',function (list) {
                var html;
                for(let i=0;i<list.length;i++){
                    html+='<option value='+list[i].id+'>'+list[i].title+'</option>';
                }
                $('#strategySelect').append(html);

                $('#strategySelect').chosen({
                    no_results_text: "无相关数据!",
                    disable_search_threshold: 5,//选项小于5时不显示搜索框
                });

                $('#strategySelect').change(function () {
                    //获取该攻略下的分类列表
                    $.get('/strategyCatalog/selectByStrategyId.do',{id:$(this).val()},function (data) {
                        var html;
                        for (let i = 0; i < data.length; i++) {
                            html += '<option value=' + data[i].id + '>' + data[i].name + '</option>';
                        }
                        $('#catalogSelect').html(html);
                        $('#catalogSelect').trigger("chosen:updated");

                    });
                });

            })

            $('#catalogSelect').chosen({
                no_results_text: "无相关数据!",
                disable_search_threshold: 5,//选项小于10时不显示搜索框
            });

        });
    </script>
</head>

<body>
<div class="container">
    <div class="navbar cm-navbar">
        <img class="logo" alt="Brand" src="/img/logo.png">
        <span class="pageTitle">&nbsp;</span>
        <ul class="nav navbar-nav navbar-right cm-navbar-nav">
            <li>
            </li>
            <li><a href="">安全退出</a></li>
            <li><a href="">个人设置</a></li>
        </ul>
    </div>    <div class="row">
    <div class="col-sm-3">
        <ul id="menu" class="list-group">
            <li class="list-group-item">
                <a href="#" data-toggle="collapse" data-target="#strategy_detail">
                    <span>攻略管理</span>
                </a>
                <ul class="in" id="strategy_detail">
                    <li class="strategy"><a href="/strategy/list.do">大攻略管理</a></li>
                    <li class="strategyCatalog"><a href="/strategyCatalog/list.do">攻略分类管理</a></li>
                    <li class="strategyDetail"><a href="/strategyDetail/list.do">攻略文章管理</a></li>
                    <li class="strategyComment"><a href="/strategyComment/list.do">攻略评论管理</a></li>
                    <li class="strategyTravel"><a href="/strategyTravel/list.do">大家都在看</a></li>
                </ul>
            </li>
            <li class="list-group-item">
                <a href="#" data-toggle="collapse" data-target="#travel_detail">
                    <span>游记管理</span>
                </a>
                <ul class="in" id="travel_detail">
                    <li class="travel"><a href="/travel/list.do">已发布游记管理</a></li>
                    <li class="audit_list"><a href="/travel/audit_list.do">待审核游记</a></li>
                    <li class="travelCommend"><a href="/travelCommend/list.do">游记推荐设置</a></li>
                </ul>
            </li>
            <li class="list-group-item">
                <a href="#" data-toggle="collapse" data-target="#system_detail">
                    <span>平台管理</span>
                </a>
                <ul class="in" id="system_detail">
                    <li class="user"><a href="/user/list.do">注册用户管理</a></li>
                    <li class="region"><a href="/region/list.do">旅游地区管理</a></li>
                </ul>
            </li>
        </ul>

        <script type="text/javascript">
            $(".in li.strategyDetail").addClass("active");
        </script>
    </div>
    <div class="col-sm-9">
        <div class="page-header">
            <h3>攻略文章管理</h3>
        </div>
        <div class="row">
            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/strategyDetail/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="">
                </div>
                <div class="form-group">
                    <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:void(-1);" class="btn btn-success" id="addStrategyBtn">添加攻略文章</a>
                </div>
            </form>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>封面</th>
                    <th>发布时间</th>
                    <th>排序</th>
                    <th>攻略类别</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>人气购物街区盘点</td>
                    <td><img src='/upload/11604d04-d9bd-4b5b-bb84-d89fa1e113fa.jpeg' width="50px"/></td>
                    <td>2018-07-12 20:54:55</td>
                    <td>0</td>
                    <td>广州必体验</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/11604d04-d9bd-4b5b-bb84-d89fa1e113fa.jpeg","sequence":0,"releaseTime":1531400095000,"catalog":{"name":"广州必体验","id":15},"id":12,"state":0,"title":"人气购物街区盘点","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州有哪些值得购买的手信？</td>
                    <td><img src='/upload/5b43c87c-ba4c-48aa-bbff-19d5972c806c.jpeg' width="50px"/></td>
                    <td>2018-07-10 19:44:16</td>
                    <td>1</td>
                    <td>广式美味</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/5b43c87c-ba4c-48aa-bbff-19d5972c806c.jpeg","sequence":1,"releaseTime":1531223056000,"catalog":{"name":"广式美味","id":18},"id":11,"state":0,"title":"广州有哪些值得购买的手信？","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州早茶必点什么？</td>
                    <td><img src='/upload/0b044f73-7e5c-4b18-86b1-7aed39b9efe0.jpeg' width="50px"/></td>
                    <td>2018-07-10 19:23:34</td>
                    <td>0</td>
                    <td>广式美味</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/0b044f73-7e5c-4b18-86b1-7aed39b9efe0.jpeg","sequence":0,"releaseTime":1531221814000,"catalog":{"name":"广式美味","id":18},"id":10,"state":0,"title":"广州早茶必点什么？","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州里文艺爆棚的创意园</td>
                    <td><img src='/upload/35f6b7aa-fcef-44a3-85a9-b5a73927911f.jpeg' width="50px"/></td>
                    <td>2018-07-10 19:21:46</td>
                    <td>0</td>
                    <td>文艺地标</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/35f6b7aa-fcef-44a3-85a9-b5a73927911f.jpeg","sequence":0,"releaseTime":1531221706000,"catalog":{"name":"文艺地标","id":17},"id":9,"state":0,"title":"广州里文艺爆棚的创意园","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州5日玩法</td>
                    <td><img src='/upload/4341433b-d9e9-4474-880a-6799acd04b30.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:56:32</td>
                    <td>1</td>
                    <td>路线推荐</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/4341433b-d9e9-4474-880a-6799acd04b30.jpeg","sequence":1,"releaseTime":1531220192000,"catalog":{"name":"路线推荐","id":16},"id":8,"state":0,"title":"广州5日玩法","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州市区4日玩法</td>
                    <td><img src='/upload/625e2256-276b-4f88-9a47-abfb4c9a43dc.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:54:39</td>
                    <td>0</td>
                    <td>路线推荐</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/625e2256-276b-4f88-9a47-abfb4c9a43dc.jpeg","sequence":0,"releaseTime":1531220079000,"catalog":{"name":"路线推荐","id":16},"id":7,"state":0,"title":"广州市区4日玩法","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>乘船夜游醉人珠江</td>
                    <td><img src='/upload/f096bb57-af3d-48cd-b88d-00124ab2e6a1.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:42:37</td>
                    <td>2</td>
                    <td>广州必体验</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/f096bb57-af3d-48cd-b88d-00124ab2e6a1.jpeg","sequence":2,"releaseTime":1531219357000,"catalog":{"name":"广州必体验","id":15},"id":6,"state":0,"title":"乘船夜游醉人珠江","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>品味广州的“饮茶”文化</td>
                    <td><img src='/upload/e496f2a1-cca4-4271-ba00-3b4c719b1643.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:41:48</td>
                    <td>1</td>
                    <td>广州必体验</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/e496f2a1-cca4-4271-ba00-3b4c719b1643.jpeg","sequence":1,"releaseTime":1531219308000,"catalog":{"name":"广州必体验","id":15},"id":5,"state":0,"title":"品味广州的“饮茶”文化","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>广州塔上挑战众多“世界之最”</td>
                    <td><img src='/upload/97faee05-bda2-4d7d-9aa3-eb185d0fa3a2.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:41:15</td>
                    <td>0</td>
                    <td>广州必体验</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/97faee05-bda2-4d7d-9aa3-eb185d0fa3a2.jpeg","sequence":0,"releaseTime":1531219275000,"catalog":{"name":"广州必体验","id":15},"id":4,"state":0,"title":"广州塔上挑战众多“世界之最”","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                <tr>
                    <td>消费水平</td>
                    <td><img src='/upload/bf6bcc63-82eb-49b7-9f47-6ca3bbaada6b.jpeg' width="50px"/></td>
                    <td>2018-07-10 18:39:16</td>
                    <td>2</td>
                    <td>概况</td>
                    <td>
                        发布
                    </td>
                    <td>
                        <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='{"coverUrl":"/upload/bf6bcc63-82eb-49b7-9f47-6ca3bbaada6b.jpeg","sequence":2,"releaseTime":1531219156000,"catalog":{"name":"概况","id":14},"id":3,"state":0,"title":"消费水平","strategy":{"id":2,"title":"广州攻略"}}'>修改</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div style="text-align: center;">
                <ul id="pagination" class="pagination"></ul>
            </div>
        </div>
    </div>
</div>
</div>

<div id="strategyModal" class="modal fade" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal " method="post" enctype="multipart/form-data" action="/strategyDetail/saveOrUpdate.do" style="margin: -3px 20px">
                    <input id="id" type="hidden" name="id" value="" />
                    <div class="form-group inline">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" placeholder="攻略标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="200px"/>
                            <input type="file" class="form-control" id="pic" name="pic" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select id="strategySelect" name="strategy.id" class="form-control form-control-chosen" data-placeholder="请选择所属攻略">
                                <option></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">攻略类别</label>
                        <div class="col-sm-6">
                            <select id="catalogSelect" name="catalog.id" class="form-control form-control-chosen" data-placeholder="请选择攻略类别">
                                <option></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">是否发布</label>
                        <div class="col-sm-6">
                            <select id="isRelease" class="form-control" autocomplete="off" name="isRelease">
                                <option value="true">是</option>
                                <option value="false">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence" placeholder="排序">
                        </div>
                    </div>
                    <textarea id="content" name="content" class="ckeditor"  cols="10"  rows="10">
                      </textarea>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>