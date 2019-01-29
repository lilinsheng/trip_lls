<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>待审核管理</title>
    <#include "../common/header.ftl">
    <script src="/js/My97DatePicker/WdatePicker.js"></script>

    <style>
        #myModal #content img{width: 100%};
    </style>

    <script>
        $(function () {
            //添加攻略
            $('.btn-input').click(function () {
                var json = $(this).data("json");
                if (json){
                    $('#myModal [name="id"]').val(json.id);
                    $('#myModal [name="name"]').val(json.name);
                    $('#myModal [name="sequence"]').val(json.sequence);
                    $('#myModal [name="strategy.id"]').val(json.strategyId);
                    $('#myModal [name=state]').val(json.state+"");
                }
                $('#myModal').modal("show");
            });

            //保存攻略
            $('.btn-submit').click(function () {
                $('#editForm').ajaxSubmit(function (data) {
                    successAlert(data);
                })
            });


            //预览图片
            $('#myModal [name=file]').on('change',function(){
                var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
                        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                        src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

                // 检查是否是图片
                if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
                    error_prompt_alert('上传错误,文件格式必须为：png/jpg/jpeg');
                    return;
                }

                $('#myModal img').attr('src',src);
            });
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#assign currentMenu="strategyCatalog">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略分类管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyCatalog/list.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <select class="form-control" name="strategyId">
                        <#list strategies as strategy>
                            <option value="${strategy.id}">${strategy.title}</option>
                        </#list>
                    </select>
                    <script>
                        $('select[name="strategyId"]').val(${(qo.strategyId)!});
                    </script>
                </div>
            <input type="submit" id="btn_query" class="btn btn-success" value="查询">
            <input type="button"  class="btn btn-success btn-input" value="添加攻略">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>分类名称</th>
                    <th>所属攻略</th>
                    <th>排序</th>
                    <th>状态</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${entity.name}</td>
                       <td>${(entity.strategy.title)!}</td>
                       <td>${(entity.sequence)!}</td>
                       <td>${(entity.state)!?string("启用","禁用")}</td>
                       <td><a class="btn-input" data-json='${entity.jsonString}'>修改</a></td>
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
                <h4 class="modal-title">新增攻略分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategyCatalog/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">分类名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入分类名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="subTitle" class="col-sm-3 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="strategy.id">
                                    <#list strategies as strategy>
                                        <option value="${strategy.id}">${strategy.title}</option>
                                    </#list>
                            </select>
                            <script>
                                $('select[name="strategy.id"]').val(${(qo.strategyId)!});
                            </script>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sequence" class="col-sm-3 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence" placeholder="请输入序号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state">
                                    <option value="true">启用</option>
                                    <option value="false">禁用</option>
                            </select>
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