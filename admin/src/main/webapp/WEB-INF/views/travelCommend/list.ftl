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
            //推荐模态框
            $('.btn-commend').click(function () {
                var json = $(this).data('json');
                console.log(json);
                console.log(1);
                if (json){
                    $('#commendModal [name="id"]').val(json.id);
                    $('#commendModal [name="title"]').val(json.title);
                    $('#commendModal [name="subTitle"]').val(json.subTitle);
                    $('#commendModal [name="coverUrl"]').val(json.coverUrl);
                    $('#commendModal [name="schedule"]').val(json.schedule);
                    $('#commendModal img').attr("src",json.coverUrl);
                    $('#commendModal [name=type]').val(json.type);
                    $('#commendModal #skip').attr("href","/travel/releaseList.do?travelId="+json.travelId);
                }
                $('#commendModal').modal("show");
            });

            //预览图片
            $('#commendModal [name=file]').on('change',function(){
                var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
                        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                        src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

                // 检查是否是图片
                if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
                    error_prompt_alert('上传错误,文件格式必须为：png/jpg/jpeg');
                    return;
                }

                $('#commendModal img').attr('src',src);
            });


            //推荐
            $('.btn-submit').click(function () {
                $('#editForm').ajaxSubmit(function (data) {
                    successAlert(data);
                });
            });
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#assign currentMenu="travelCommend">
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
            <form class="form-inline" id="searchForm" action="/travelCommend/list.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
            <div class="form-group">
                <select class="form-control" name="state">
                    <option value="0">全部</option>
                    <option value="1">每周推荐</option>
                    <option value="2">每月推荐</option>
                    <option value="3">攻略推荐</option>
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
                    <th>封面</th>
                    <th>标题</th>
                    <th>副标题</th>
                    <th>推荐时间</th>
                    <th>推荐类型</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td><img src="${(entity.coverUrl)!}" width="100px"></td>
                       <td>${(entity.title)!}</td>
                       <td>${(entity.subTitle)!}</td>
                       <td>${(entity.schedule)!?string('yyyy-MM-dd')}</td>
                       <td>${(entity.typeName)!}</td>
                       <td><a class="btn-commend" data-json='${entity.jsonString}'>修改</a></td>
                   </tr>
               </#list>
            </table>
            <#include "../common/page.ftl">
        </div>
    </div>
</div>

<div id="commendModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">推荐</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/travelCommend/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="subTitle" class="col-sm-3 control-label">副标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="subTitle" name="subTitle" placeholder="请输入副标题">
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
                        <label for="schedule" class="col-sm-3 control-label">推荐时间：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="schedule" name="schedule" onclick="WdatePicker()" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">推荐类型：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="type">
                                <option value="1">每周推荐</option>
                                <option value="2">每月推荐</option>
                                <option value="3">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <a target="_blank" id="skip">点击查看游记文章明细</a>
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