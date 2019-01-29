<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script>
//分页
$(function(){
    $('#pagination').twbsPagination({
        totalPages: ${pageInfo.pages} || 1,
        visiblePages: 3,
        first:'首页',
        prev:'上一页',
        next:'下一页',
        last:'尾页',
        startPage:${pageInfo.pageNum} || 1,
        onPageClick: function (event, page) {
            $('#currentPage').val(page);
            $('#searchForm').submit();
        }
    });
})
</script>