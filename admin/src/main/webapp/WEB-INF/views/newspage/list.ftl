<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>待审核管理</title>
    <#include "../common/header.ftl">
    <script src="/js/My97DatePicker/WdatePicker.js"></script>
    <script src="/js/ckeditor/ckeditor.js"></script>

    <style>
        #myModal #content img{width: 100%};
    </style>

    <script>
        $(function () {
            $('.btn-input').click(function () {
                var json = $(this).data('json');
                console.log(json);
                if(json){
                    $('#myModal [name="id"]').val(json.id);
                    $('#myModal [name="title"]').val(json.title);
                    $('#myModal [name="subTitle"]').val(json.subTitle);
                    $('#myModal [name="coverUrl"]').val(json.coverUrl);
                    $('#myModal img').attr("src",json.coverUrl);
                    $('#myModal [name="createTime"]').val(json.createTime);
                    $.get('/newsPages/input.do',{id:json.id},function (data){
                        ck.setData(data.content.content);
                    })
                    $('.fade').modal("show")
                }else{
                $('.fade').modal("show")
                }
            })
            /*先触发，再用提交按钮，提交按钮的这个表单有提交地址*/
            $('.btn-submit').click(function () {
                $('#editor').val(ck.getData());
                $('#editForm').ajaxSubmit(function (data) {
                    successAlert(data);
                })
            })

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
    <#assign currentMenu="newspage">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">日报管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/newsPages/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <input type="button"  class="btn btn-success btn-input" value="添加攻略">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>副标题</th>
                    <th width="200px">封面</th>
                    <th>创建日期</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.title)!}</td>
                       <td>${(entity.subTitle)!}</td>
                       <td><img src="${(entity.coverUrl)!}" width="100px"></td>
                       <td>${(entity.createTime?string("yyyy-MM-dd"))!}</td>
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
                <h4 class="modal-title">添加日报</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/newsPages/saveOrUpdate.do" method="post" id="editForm" enctype="multipart/form-data">
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
                            <input type="hidden" class="form-control" name="coverUrl"><#--提交任何数据都需要一个name属性-->
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="createTime" class="col-sm-3 control-label">日期：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="createTime" name="createTime" onclick="WdatePicker()" readonly="readonly">
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