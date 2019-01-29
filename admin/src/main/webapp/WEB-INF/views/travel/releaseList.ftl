<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>已发布游记管理</title>
    <#include "../common/header.ftl">
    <script src="/js/My97DatePicker/WdatePicker.js"></script>

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

            //推荐模态框
            $('.btn-commend').click(function () {
                var json = $(this).data('json');
                console.log(json);
                if (json){
                    $('#commendModal [name="travel.id"]').val(json.id);
                    $('#commendModal [name="title"]').val(json.title);
                    $('#commendModal [name="coverUrl"]').val(json.coverUrl);
                    $('#commendModal img').attr("src",json.coverUrl);
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
    <#assign currentMenu="releaseTravel">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">已发布游记管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/releaseList.do" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="1">
            <input type="hidden" name="state" value="${(qo.state)!}">
                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入标题/作者">
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
                       <td><a class="btn-content" data-id="${entity.id}">查看游记</a></td>
                       <td><a class="btn-state" href="javascript:;" data-id="${entity.id}" data-state="0">取消发布</a></td>
                       <td><a class="btn-commend" data-json='${entity.jsonString}'>推荐</a></td>
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
                    <input type="hidden" name="travel.id">
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