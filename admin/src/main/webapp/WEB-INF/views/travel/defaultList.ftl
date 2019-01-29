<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>待审核管理</title>
    <#include "../common/header.ftl">

    <style>
        #myModal #content img{width: 100%};
    </style>

    <script>
        $(function () {
            //发布或拒绝请求
            $('.btn-state').click(function () {
                var id = $(this).data('id');
                var state = $(this).data('state');
                $.ajax({
                    url:"/travel/updateState.do?id="+id+"&state="+state,
                    success:function (data) {
                        if (data.success){
                            window.location.reload();
                        }
                    }
                })
            });

            //查看文章内容
            $('.btn-content').click(function () {
                var id = $(this).data("id");
                $.get("/travel/getTravelContent.do",{id:id},function (data) {
                    $('#content').html(data.content);
                    $('#myModal').modal("show");
                });
            });
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#assign currentMenu="defaultTravel">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">游记管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/list.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
            <div class="form-group">
                <select class="form-control" name="state">
                    <option value="-2">全部</option>
                    <option value="0">待发布</option>
                    <option value="-1">已拒绝</option>
                </select>
                <script>
                    $('select[name=state]').val(${(qo.state)!});
                </script>
            </div>
            <input type="submit" id="btn_query" class="btn btn-success" value="查询">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
                    <th>创建时间</th>
                    <th>状态</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.title)!}</td>
                       <td><img src="${(entity.coverUrl)!}" width="30px" height="30px"></td>
                       <td>${(entity.place.name)!}</td>
                       <td>${(entity.author.nickName)!}</td>
                       <td>${(entity.createTime)!?string("yyyy-MM-dd HH:mm:ss")}</td>
                       <td>${(entity.stateName)!}</td>
                       <td><a class="btn-state" href="javascript:;" data-id="${entity.id}" data-state="1">发布</a></td>
                       <td><a class="btn-state" href="javascript:;" data-id="${entity.id}" data-state="-1">拒绝</a></td>
                       <td><a class="btn-content" data-id="${entity.id}">查看文章</a></td>
                   </tr>
               </#list>
            </table>
            <#include "../common/page.ftl">
        </div>
    </div>
</div>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">查看内容</h4>
            </div>
            <div class="modal-body">
                <div id="content"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <#--<button type="button" class="btn btn-primary btn-submit">保存</button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>