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

    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#assign currentMenu="question">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">问题管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/questions/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>网友问题</th>
                    <th width="200px">封面</th>
                    <th>网友烦恼</th>
                </tr>
                </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${(entity.problem)!}</td>
                       <td><img src="${(entity.coverUrl)!}" width="100px"></td>
                       <td>${(entity.trouble)!}</td>
                       <td><a class="btn-input" href="/questions/delete.do?id=${entity_index+1}">删除</a></td>
                   </tr>
               </#list>
            </table>
            <#include "../common/page.ftl">
        </div>
    </div>
</div>

</body>
</html>