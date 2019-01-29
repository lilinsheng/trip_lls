<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>门票管理</title>
    <#include "../common/header.ftl">
    <script src="/js/My97DatePicker/WdatePicker.js"></script>
    <style>
        #myModal #content img {
            width: 100%
        }

        ;
    </style>

    <script>
        $(function () {
            //添加商品
            $('.btn-input').click(function () {

                $("#editForm input").val("");

                var json = $(this).data("json");
                if (json) {
                    $('#myModal [name="id"]').val(json.id);
                    $('#myModal [name="title"]').val(json.title);
                    $('#myModal [name="imgUrl"]').val(json.imgUrl);
                    $('#myModal img').attr("src", json.imgUrl);
                    $('#myModal [name="price"]').val(json.price);
                    $('#myModal [name=region]').val(json.region);
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
            $('#myModal [name=file]').on('change', function () {
                var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
                        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                        src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

                // 检查是否是图片
                if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
                    error_prompt_alert('上传错误,文件格式必须为：png/jpg/jpeg');
                    return;
                }

                $('#myModal img').attr('src', src);
            });
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#assign currentMenu="scenic">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">门票管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/scenic/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入">
                </div>
                <input type="submit" id="btn_query" class="btn btn-success" value="查询">
                <input type="button" class="btn btn-success btn-input" value="添加商品">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>景区标题</th>
                    <th width="200px">景区封面</th>
                    <th>门票价格</th>
                    <th>景区地址</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.title)!}</td>
                       <td><img src="${(entity.imgUrl)!}" width="100px"></td>
                       <td>${(entity.price)!}</td>
                       <td>${(entity.region)!}</td>
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
                <h4 class="modal-title">添加门票</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/scenic/saveOrUpdate.do" method="post" id="editForm"
                      enctype="multipart/form-data">
                    <input type="hidden" name="id">

                    <div class="form-group">
                        <label for="productName" class="col-sm-3 control-label">景区名称:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title"
                                   placeholder="请输入副标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="imageUrl" class="col-sm-3 control-label">景区图片:</label>
                        <div class="col-sm-6">
                            <img width="270px">
                            <input type="file" class="form-control" name="file">
                            <input type="hidden" class="form-control" name="imgUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="salePrice" class="col-sm-3 control-label">门票售价:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="price">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="productDescript" class="col-sm-3 control-label">景区地址：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="region">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit">保存</button>
            </div>
        </div><!-- /.modal-content &ndash;&gt;
    </div><!-- /.modal-dialog &ndash;&gt;
</div><!-- /.modal -->
</body>
</html>