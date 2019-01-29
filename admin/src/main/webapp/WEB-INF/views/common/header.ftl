<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery.twbsPagination.min.js"></script>
<script src="/js/jquery.form.js"></script>
<script src="/js/jquery.bootstrap.min.js"></script>


<script>

    $(function () {
        $.messager.model = {
            ok: {text: "确定"},
            cancel: {text: "取消"}
        };
        $('.btn-delete').click(function () {
            var url = $(this).attr("url");
            $.messager.confirm("温馨提示", "是否确定删除该条记录", function () {

                $.get(url,function (data) {
                    successAlert(data);
                });
            });
        });
    });
    //删除


    function successAlert(data) {
        if (data.success){
            $.messager.alert("操作成功，2秒后跳转");
            setTimeout(function () {
                window.location.reload();
            }, 2000);
        }
    }
</script>