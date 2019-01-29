<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>地区管理</title>
    <#include "../common/header.ftl">
    <link href="/js/plugins/treeview/bootstrap-treeview.min.css" rel="stylesheet">
    <script src="/js/plugins/treeview/bootstrap-treeview.min.js"></script>

    <script>
        $(function () {

            //当前地区的上级地区id
            var parentId = null;
            var parentName = null;

            //绑定模态框
            $('.btn-input').click(function () {
                var json = $(this).data('json');
                console.log(parentId);
                console.log(parentName);
                if (json){
                    $('#editForm [name=id]').val(json.id);
                    $('#editForm [name=name]').val(json.name);
                    console.log(json.parentName);
                    if (json.parentName==null){
                        $('#editForm [name=parentName]').val(parentName);
                    }else {

                        $('#editForm [name=parentName]').val(json.parentName);
                    }
                }else {
                    $('#editForm [name="parent.id"]').val(parentId);
                    $('#editForm [name=parentName]').val(parentName);
                }
                $('#myModal').modal("show");
            });
            
            //保存地区
            $('.btn-submit').click(function () {
                $('#editForm').ajaxSubmit(function (data) {
                    if (data.success){
                        window.location.href="/region/list.do";
                    }
                })
            });

            //修改推荐模态框
            $.messager.model = {
                ok: {text: "确定"},
                cancel: {text: "取消"}
            };
            $('.btn-status').click(function () {
                var url = $(this).attr("url");
                var json = $(this).data("json");
                $.messager.confirm("温馨提示", "是否确定修改状态", function () {
                    $.get("/region/updateStatus.do",{"id":json.id,"state":json.status},function (data) {
                        successAlert(data);
                    });
                });
            });


            $.get("/region/listByParentId.do",{},function (data) {
                console.log(data);
                var defaultData = [
                    {
                        text: '全部地区',
                        nodes: data
                    }
                ];

                $('#tree').treeview({
                    showTags: true,
                    data: defaultData,
                    lazyLoad:function (node,fun) {
                        console.log(node);

                        $.get("/region/listByParentId.do",{"parentId":node.id},function (data) {
                            fun(data);

                            //$('#tree').treeview('addNode',[data,node]);
                        })
                    },
                    onNodeSelected:function (event, data) {
                        parentId=data.id;
                        parentName = data.text;
                        $('#myTable tbody').empty();
                        $.get('/region/listByParentId.do',{"parentId":data.id,"type":"table"},function (data) {
                            $.each(data,function (index,ele) {
                                var $tr = $('#template').find("tr:nth-child(1)").clone(true);
                                $tr.find("td:nth-child(1)").html(index+1);
                                $tr.find("td:nth-child(2)").html(ele.name);
                                $tr.find("a:nth-child(1)").attr("data-json",ele.jsonString);
                                $tr.find("a:nth-child(2)").attr("data-json",ele.jsonString);
                                var json = JSON.parse(ele.jsonString);
                                if (json.status==1){
                                    $tr.find("a:nth-child(2)").append("取消推荐");
                                }else {
                                    $tr.find("a:nth-child(2)").append("设为推荐");
                                }
                                $('#myTable tbody').append($tr);
                            })
                        });
                    }
                });
            });


        })
    </script>
</head>
<body>
<table id="template" style="display: none">
    <tr>
        <td></td>
        <td></td>
        <td>
            <a class="btn btn-info btn-xs btn-input" href="javascript:;"'>
                <span class="glyphicon glyphicon-pencil"></span>修改
            </a>
           <a href="javascript:;" class="btn btn-danger btn-xs btn-status">
               <span class="glyphicon glyphicon-trash"></span>
           </a>
        </td>
    </tr>
</table>
<div class="container " style="margin-top: 20px">
    <#assign currentMenu="region">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">地区管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/user/list.do" method="post">
                <a href="javascript:;" class="btn btn-success btn-input">添加地区</a>
            </form>

            <div class="row">
                <div class="col-sm-4">
                    <div id="tree"></div>
                </div>
                <div class="col-sm-8">
                    <table class="table table-striped table-hover" id="myTable">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>名称</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>


        </div>
    </div>
</div>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/region/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id" id="id">
                    <input type="hidden" name="parent.id">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入地区名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="parentName" class="col-sm-3 control-label">上级地区：</label>
                        <div class="col-sm-6">
                            <input readonly="readonly" type="text" class="form-control" id="parentName" name="parentName" placeholder="请输入部门编码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>