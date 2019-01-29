<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>商城管理</title>
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
                var json = $(this).data("json");
                if (json) {
                    $('#myModal [name="id"]').val(json.id);
                    $('#myModal [name="productName"]').val(json.productName);
                    $('#myModal [name="coverUrl"]').val(json.imageUrl);
                    $('#myModal img').attr("src", json.imageUrl);
                    $('#myModal [name="salePrice"]').val(json.salePrice);
                    $('#myModal [name=productDescript]').val(json.productDescript);
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
    <#assign currentMenu="mall">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">商城管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/mall/list.do" method="post">
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
                    <th>商品名称</th>
                    <th width="200px">商品图片</th>
                    <th>商品售价</th>
                    <th>商品描述</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.productName)!}</td>
                       <td><img src="${(entity.imageUrl)!}" width="100px"></td>
                       <td>${(entity.salePrice)!}</td>
                       <td>${(entity.productDescript)!}</td>
                       <td>${(entity.stateName)!}</td>
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
                <h4 class="modal-title">添加攻略</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/mall/saveOrUpdate.do" method="post" id="editForm"
                      enctype="multipart/form-data">
                    <input type="hidden" name="id">

                    <div class="form-group">
                        <label for="productName" class="col-sm-3 control-label">商品名称:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="productName" name="productName" placeholder="请输入副标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="imageUrl" class="col-sm-3 control-label">商品图片:</label>
                        <div class="col-sm-6">
                            <img width="270px">
                            <input type="file" class="form-control" name="file">
                            <input type="hidden" class="form-control" name="imageUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="salePrice" class="col-sm-3 control-label">商品售价:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="salePrice">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="productDescript" class="col-sm-3 control-label">商品描述：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="productDescript">
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