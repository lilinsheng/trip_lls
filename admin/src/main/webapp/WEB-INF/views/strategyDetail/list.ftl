<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>待审核管理</title>
    <#include "../common/header.ftl">
    <script src="/js/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/ckeditor/ckeditor.js"></script>
    <link href="/js/plugins/chosen/component-chosen.css" rel="stylesheet">
    <script src="/js/plugins/chosen/chosen.jquery.js"></script>

    <style>
        #myModal #content img{width: 100%};
    </style>

    <script>
        $(function () {

            $('#strategySelect').chosen({
                no_results_text: "无相关数据!",
                disable_search_threshold: 5,//选项小于5时不显示搜索框
            });

            $('#editForm [name="catalog.id"]').chosen({
                no_results_text: "无相关数据!",
                disable_search_threshold: 5,//选项小于5时不显示搜索框
            });

            $.get("/strategyCatalog/getCatalogByStrategyId.do",{strategyId:$('#strategySelect option:first').val()},function (data) {
                console.log(data);

                var str="";
                $.each(data,function (index,ele) {
                    str+='<option value="'+ele.id+'">'+ele.name+'</option>'
                })
                $('#editForm [name="catalog.id"]').html(str);
                $('#editForm [name="catalog.id"]').trigger("chosen:updated");
            });

            //大攻略改变时，查找当前大攻略下面的攻略分类
            $('#strategySelect').change(function () {
                $.get("/strategyCatalog/getCatalogByStrategyId.do",{strategyId:$('#strategySelect').val()},function (data) {
                    console.log(data);

                    var str="";
                    $.each(data,function (index,ele) {
                        str+='<option value="'+ele.id+'">'+ele.name+'</option>'
                    })
                    $('#editForm [name="catalog.id"]').html(str);
                    $('#editForm [name="catalog.id"]').trigger("chosen:updated");
                });
            });

            //添加攻略
            $('.btn-input').click(function () {
                $('#editForm input,select,textarea').val("");
                $('#editForm img').attr("src",null);
                ck.setData("");

                var json = $(this).data("json");
                console.log(json);
                if (json){
                    $('#myModal [name="id"]').val(json.id);
                    $('#myModal [name="title"]').val(json.title);
                    $('#myModal [name="sequence"]').val(json.sequence);
                    console.log( $('#strategySelect').get(0));
                    $('#strategySelect').val(json.strategyId);
                    $('#strategySelect').trigger("chosen:updated");
                    $.get("/strategyCatalog/getCatalogByStrategyId.do",{strategyId:$('#strategySelect').val()},function (data) {
                        console.log(data);

                        var str="";
                        $.each(data,function (index,ele) {
                            str+='<option value="'+ele.id+'">'+ele.name+'</option>'
                        })
                        $('#editForm [name="catalog.id"]').html(str);
                        $('#editForm [name="catalog.id"]').trigger("chosen:updated");
                    });
                    $('#myModal [name="catalog.id"]').val(json.catalogId);
                    $('#myModal [name="catalog.id"]').trigger("chosen:updated");
                    $('#myModal [name="coverUrl"]').val(json.coverUrl);
                    $('#myModal img').attr("src",json.coverUrl);
                    $('#myModal [name=state]').val(json.state);
                    $.get("/strategyDetail/getContentById.do",{id:json.id},function (data) {
                        console.log(data);
                        ck.setData(data.content);
                        $('#myModal').modal("show");
                    });
                }else {
                    $('#myModal').modal("show");
                }
            });

            //保存攻略文章
            $('.btn-submit').click(function () {
                $('#editor').val(ck.getData());
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
    <#assign currentMenu="strategyDetail">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">大攻略管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyDetail/list.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
            <div class="form-group">
                <label for="keyword">关键字:</label>
                <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入">
            </div>
            <input type="submit" id="btn_query" class="btn btn-success" value="查询">
            <input type="button"  class="btn btn-success btn-input" value="添加攻略">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>发布时间</th>
                    <th>排序</th>
                    <th>攻略类别</th>
                    <th>状态</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.title)!}</td>
                       <td><img src="${(entity.coverUrl)!}" width="50px"/></td>
                       <td>${((entity.releaseTime)?string("yyyy-MM-dd"))!}</td>
                       <td>${(entity.sequence)!}</td>
                       <td>${(entity.catalog.name)!}</td>
                       <td>${(entity.stateName)!}</td>
                       <td><a class="btn-input" data-json='${(entity.jsonString)!}'>修改</a></td>
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
                <h4 class="modal-title">添加攻略文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategyDetail/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题名称">
                        </div>
                    </div>



                    <div class="form-group">
                        <label for="coverUrl" class="col-sm-3 control-label">封面：</label>
                        <div class="col-sm-6">
                            <img width="270px">
                            <input type="file" class="form-control" name="file">
                            <input type="hidden" class="form-control" name="coverUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="strategySelect">
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">攻略类别：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="catalog.id">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state">
                                    <option value="0">发布</option>
                                    <option value="-1">草稿</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sequence" class="col-sm-3 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence" placeholder="请输入序号">
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea id="editor" name="content.content"></textarea>
                    </div>
                    <script>
                        var ck = CKEDITOR.replace( 'editor',{
                            filebrowserUploadUrl: '/images.do'
                        });
                    </script>
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