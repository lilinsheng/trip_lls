<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>攻略管理</title>
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
                $("#strategyTitle").val( $("#strategySelect option:selected").text());
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






            //查询攻略
            $.get('/strategy/selectAll.do',function (regions) {
                var html;
                for(var i=0;i<regions.length;i++){
                    html+='<option value='+regions[i].id+'>'+regions[i].title+'</option>';
                }
                $('#everybodyWatching').append(html);

                //回显
                $("#everybodyWatching option[value='2']").prop("selected", true);

                $('#everybodyWatching').chosen({
                    no_results_text: "无相关攻略!",
                    disable_search_threshold: 5,//选项小于10时不显示搜索框
                });

            })


            //查询游记
            $.get('/travel/selectRelease.do',function (regions) {
                var html;
                for(var i=0;i<regions.length;i++){
                    html+='<option value='+regions[i].id+'>'+regions[i].title+'</option>';
                }
                $('#travelSelect').append(html);

                $('#travelSelect').chosen({
                    no_results_text: "无相关游记!",
                    disable_search_threshold: 5,//选项小于5时不显示搜索框
                });
            })


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
    <#assign currentMenu="everybodyWatching">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">大家都在看</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/everybodyWatching/list.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label >攻略搜索</label>
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
            <input type="button"  class="btn btn-success btn-input" id="strategyTitle" value="添加关联">
            </form>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>关联游记</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

            </div>

            <table class="table table-striped table-hover">
                <#--<thead>
                <tr>
                    <th>编号</th>
                    <th>分类名称</th>
                    <th>所属攻略</th>
                    <th>排序</th>
                    <th>状态</th>
                </tr>
                </thead>-->
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${entity.name}</td>
                       <#--<td>${(entity.strategy.title)!}</td>
                       <td>${(entity.sequence)!}</td>
                       <td>${(entity.state)!?string("启用","禁用")}</td>-->
                       <td><a class="btn-content" data-id="${entity.id}">查看文章</a></td>
                       <td><a class="btn-delete" data-json='${entity.jsonString}'>删除</a></td>
                   </tr>
               </#list>
            </table>
            <#include "../common/page.ftl">

        </div>
    </div>
</div>

<div id="myModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" method="post" enctype="multipart/form-data" action="/everybodyWatching/insertRelation.do" style="margin: -3px 118px">
                    <input id="strategyId" type="hidden" name="strategyId" value="" />

                    <#--攻略下拉-->
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="strategyId" readonly="readonly">
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <#--游记下拉-->
                    <div class="form-group">
                        <label class="col-sm-4 control-label">关联游记</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="strategySelect">
                                <#list travels as travel>
                                    <option value="${travel.id}">${travel.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:;" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:;" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>