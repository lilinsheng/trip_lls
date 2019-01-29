<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":"
            + request.getServerPort() + path + "/";
    String basePath2 = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>私信</title>
    <script type="text/javascript" src="<%=basePath2%>resources/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="/message_files/chat/chat.css"/>
    <script src="/js/jquery/jquery.min.js"></script>
    <script src="/message_files/chat/flexible.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/plugins/jrender/jrender.min.js"></script>
    <link rel="stylesheet" href="/js/plugins/dialog2/dialog.css">
    <script src="/js/plugins/dialog2/dialog.min.js"></script>
    <script>



        $(function () {
            // 对话者的信息,做全局变量
            var interlocutor_headImgUrl;
            // 查询对话者的信息
            var interlocutorId = getParams().friendId;
            $.get("/users", {id: interlocutorId}, function (data) {
                interlocutor_headImgUrl = data.headImgUrl;
                $('.header').renderValues(data);
            });


            var path = '<%=basePath%>';
            // 取出msg中的值
            var uid=${uid};
            var from=uid;
            var fromName='${name}';
            var to=interlocutorId;

            var websocket;
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://" + path + "/ws?uid="+uid);
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://" + path + "/ws"+uid);
            } else {
                websocket = new SockJS("http://" + path + "/ws/sockjs"+uid);
            }
            websocket.onopen = function(event) {
                console.log("WebSocket:已连接");
            };
            websocket.onmessage = function(event) {

                var data=JSON.parse(event.data);
                console.log(data);
                console.log("==========--------========");
                console.log("WebSocket:收到一条消息",data);

                // 接受到消息
                var div = $('#cloneTemplate .send').clone(true);
                div.find('.time').html(data.date);
                div.find('img').attr("src", interlocutor_headImgUrl);
                div.find('p').html(data.text);
                $('#message').append(div);
                end();

                var textCss=data.from==-1?"sfmsg_text":"fmsg_text";
                // $("#content").append("<div class='fmsg'><label class='name'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='"+textCss+"'>"+data.text+"</div></div>");
                scrollToBottom();

            };
            websocket.onerror = function(event) {
                console.log("WebSocket:发生错误 ");
            };
            websocket.onclose = function(event) {
                console.log("WebSocket:已关闭");
            }



            var user = JSON.parse(sessionStorage.getItem("user"));

            // 自己的信息,做全局变量
            var mine_headImgUrl = user.headImgUrl;



            // 跳转到对话者用户界面
            $('.receiverData').click(function () {
                location.href = "/userProfiles.html?id=" + interlocutorId;
            });


            var currentPage = 1; //当前页
            var pages; //最大页数
            var msgArr = []; //合并消息数组

            // 默认查询
            query();
            var prevH;

            //跳转到底部
            function end() {
                var c = window.document.body.scrollHeight;
                window.scroll(0, c);
            }

            // 查询出对话的内容
            function query() {
                $.get("/mySession/myChats", {
                    currentPage: currentPage,
                    interlocutorId: interlocutorId
                }, function (data) {
                    console.log(data);
                    $.merge(data.list, msgArr);
                    msgArr = data.list;
                    $('#message').renderValues(data, {
                        showOrSend: function (item, value) {
                            if (value == user.id) { // 自己
                                $(item).attr("class", "show");
                                $(item).find('img').attr("src", mine_headImgUrl);
                            } else { // 别人
                                $(item).attr("class", "send");
                                $(item).find('img').attr("src", interlocutor_headImgUrl);
                            }
                        }
                    });
                    if (currentPage == 1) {
                        prevH = $(document).height() + 60;
                        end();
                    } else {
                        var NowH = $(document).height() - prevH;
                        prevH = $(document).height();
                        window.scroll(0, NowH);
                    }
                    pages = data.pages;
                    currentPage = currentPage + 1;
                });
            }

            // 滚动
            $(window).scroll(function () {
                if ($(document).scrollTop() <= 0) {
                    if (currentPage <= pages) {
                        query();
                    } else {
                        $(document).dialog({
                            type: 'notice',
                            infoText: '已经没有消息了',
                            autoClose: 2500,
                            position: 'bottom'  // center: 居中; bottom: 底部
                        });
                    }
                }
            });


            // 发送消息
            $('#sendBtn').click(function () {
                var msg = $('#msg').val();

                var data={};
                data["from"]=from;
                data["fromName"]=fromName;
                data["to"]=to;
                data["text"]=msg+'';
                websocket.send(JSON.stringify(data));


                $.post("/mySession/saveMsg", {"responder.id": interlocutorId, content: msg});
                // 回显刚发送的消息
                var date = new Date();
                var min;
                if (date.getMinutes() < 10) {
                    min = "0" + date.getMinutes();
                } else {
                    min = date.getMinutes();
                }
                var dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + min + ":" + date.getSeconds()
                $('#message').append('   <div class="show">\n' +
                    '        <div class="time">' + dateString + '</div>\n' +
                    '        <div class="msg">\n' +
                    '            <img src="' + mine_headImgUrl + '" width="40px" alt="" />\n' +
                    '            <p>' + msg + '</p>\n' +
                    '        </div>\n' +
                    '    </div>'
                );
                // 滚动到最后
                end();
                // 清除文本框中的值
                $('#msg').val('');
            });

            // 每一段时间,往后台查询新的数据
            /*setInterval(function () {
                $.get("/mySession/getInterlocutorMsg", {"responder.id": interlocutorId}, function (data) {
                    console.log(data);
                    $.each(data, function (index, ele) {
                        var div = $('#cloneTemplate .send').clone(true);
                        div.find('.time').html(ele.createTime);
                        div.find('img').attr("src", interlocutor_headImgUrl);
                        div.find('p').html(ele.content);
                        $('#message').append(div);
                    });
                });
                end();
            }, 1000);*/
        });
    </script>
</head>

<body>
<!-- 克隆模板:别人的 -->
<div id="cloneTemplate" style="display: none">
    <div class="send">
        <div class="time">05/22 06:30</div>
        <div class="msg">
            <img width="40px" alt=""/>
            <p></p>
        </div>
    </div>
</div>

<header class="header">
    <a class="back" href="javascript:history.go(-1);"></a>
    <h5 class="tit receiverName" render-html="nickName"></h5>
    <div class="right receiverData">资料</div>
</header>

<div class="message" id="message">
    <div render-loop="list">
        <!-- 模板 -->
        <div render-key="list.speaker_id" render-fun="showOrSend">
            <div class="time" render-html="list.createTime"></div>
            <div class="msg">
                <img width="40px" alt=""/>
                <p render-html="list.content"></p>
            </div>
        </div>
    </div>
    <!-- 别人
    <div class="send">
        <div class="time">05/22 06:30</div>
        <div class="msg">
            <img id="sendImg" width="40px" alt="" />
            <p><i class="msg_input"></i>你好在不在呀，妹子！</p>
        </div>
    </div>-->
    <!-- 自己 -->
    <!--<div class="show">
        <div class="time"></div>
        <div class="msg">
            <img id="receiverImg" width="40px" alt="" />
            <p><i class="msg_input"></i>你好你好你好</p>
        </div>
    </div>-->
</div>
<div class="footer">
    <img src="/img/xiaolian.png"/>
    <input type="text" id="msg">
    <p id="sendBtn">发送</p>
</div>

</body>
</html>

<%--<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>私信</title>
    <link rel="stylesheet" type="text/css" href="/message_files/chat/chat.css"/>
    <script src="/js/jquery/jquery.min.js"></script>
    <script src="/message_files/chat/flexible.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/plugins/jrender/jrender.min.js"></script>
    <link rel="stylesheet" href="/js/plugins/dialog2/dialog.css">
    <script src="/js/plugins/dialog2/dialog.min.js"></script>
    <script>
        $(function () {
            var user = JSON.parse(sessionStorage.getItem("user"));
            // 对话者的信息,做全局变量
            var interlocutor_headImgUrl;
            // 自己的信息,做全局变量
            var mine_headImgUrl = user.headImgUrl;

            // 查询对话者的信息
            var interlocutorId = getParams().interlocutorId;
            $.get("/users", {id: interlocutorId}, function (data) {
                interlocutor_headImgUrl = data.headImgUrl;
                $('.header').renderValues(data);
            });

            // 跳转到对话者用户界面
            $('.receiverData').click(function () {
                location.href = "/userProfiles.html?id=" + interlocutorId;
            });


            var currentPage = 1; //当前页
            var pages; //最大页数
            var msgArr = []; //合并消息数组

            // 默认查询
            query();
            var prevH;

            //跳转到底部
            function end() {
                var c = window.document.body.scrollHeight;
                window.scroll(0, c);
            }

            // 查询出对话的内容
            function query() {
                $.get("/mySession/myChats", {
                    currentPage: currentPage,
                    interlocutorId: interlocutorId
                }, function (data) {
                    console.log(data);
                    $.merge(data.list, msgArr);
                    msgArr = data.list;
                    $('#message').renderValues(data, {
                        showOrSend: function (item, value) {
                            if (value == user.id) { // 自己
                                $(item).attr("class", "show");
                                $(item).find('img').attr("src", mine_headImgUrl);
                            } else { // 别人
                                $(item).attr("class", "send");
                                $(item).find('img').attr("src", interlocutor_headImgUrl);
                            }
                        }
                    });
                    if (currentPage == 1) {
                        prevH = $(document).height() + 60;
                        end();
                    } else {
                        var NowH = $(document).height() - prevH;
                        prevH = $(document).height();
                        window.scroll(0, NowH);
                    }
                    pages = data.pages;
                    currentPage = currentPage + 1;
                });
            }

            // 滚动
            $(window).scroll(function () {
                if ($(document).scrollTop() <= 0) {
                    if (currentPage <= pages) {
                        query();
                    } else {
                        $(document).dialog({
                            type: 'notice',
                            infoText: '已经没有消息了',
                            autoClose: 2500,
                            position: 'bottom'  // center: 居中; bottom: 底部
                        });
                    }
                }
            });


            // 发送消息
            $('#sendBtn').click(function () {
                var msg = $('#msg').val();
                $.post("/mySession/saveMsg", {"responder.id": interlocutorId, content: msg});
                // 回显刚发送的消息
                var date = new Date();
                var min;
                if (date.getMinutes() < 10) {
                    min = "0" + date.getMinutes();
                } else {
                    min = date.getMinutes();
                }
                var dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + min + ":" + date.getSeconds()
                $('#message').append('   <div class="show">\n' +
                    '        <div class="time">' + dateString + '</div>\n' +
                    '        <div class="msg">\n' +
                    '            <img src="' + mine_headImgUrl + '" width="40px" alt="" />\n' +
                    '            <p>' + msg + '</p>\n' +
                    '        </div>\n' +
                    '    </div>'
                );
                // 滚动到最后
                end();
                // 清除文本框中的值
                $('#msg').val('');
            });

            // 每一段时间,往后台查询新的数据
            setInterval(function () {
                $.get("/mySession/getInterlocutorMsg", {"responder.id": interlocutorId}, function (data) {
                    console.log(data);
                    $.each(data, function (index, ele) {
                        var div = $('#cloneTemplate .send').clone(true);
                        div.find('.time').html(ele.createTime);
                        div.find('img').attr("src", interlocutor_headImgUrl);
                        div.find('p').html(ele.content);
                        $('#message').append(div);
                    });
                });
                end();
            }, 1000);
        });
    </script>
</head>

<body>
<!-- 克隆模板:别人的 -->
<div id="cloneTemplate" style="display: none">
    <div class="send">
        <div class="time">05/22 06:30</div>
        <div class="msg">
            <img width="40px" alt=""/>
            <p></p>
        </div>
    </div>
</div>

<header class="header">
    <a class="back" href="javascript:history.go(-1);"></a>
    <h5 class="tit receiverName" render-html="nickName"></h5>
    <div class="right receiverData">资料</div>
</header>

<div class="message" id="message">
    <div render-loop="list">
        <!-- 模板 -->
        <div render-key="list.speaker_id" render-fun="showOrSend">
            <div class="time" render-html="list.createTime"></div>
            <div class="msg">
                <img width="40px" alt=""/>
                <p render-html="list.content"></p>
            </div>
        </div>
    </div>
    <!-- 别人
    <div class="send">
        <div class="time">05/22 06:30</div>
        <div class="msg">
            <img id="sendImg" width="40px" alt="" />
            <p><i class="msg_input"></i>你好在不在呀，妹子！</p>
        </div>
    </div>-->
    <!-- 自己 -->
    <!--<div class="show">
        <div class="time"></div>
        <div class="msg">
            <img id="receiverImg" width="40px" alt="" />
            <p><i class="msg_input"></i>你好你好你好</p>
        </div>
    </div>-->
</div>
<div class="footer">
    <img src="/img/xiaolian.png"/>
    <input type="text" id="msg">
    <p id="sendBtn">发送</p>
</div>

</body>
</html>--%>

