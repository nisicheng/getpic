<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>网页图片批量下载器</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    <style>
        .lightBoxGallery img {
            margin: 5px;
            width: 160px;
        }
    </style>
</head>
<!-- 元素加背景图片 -->
<body>
<!--水平居中只需要margin:0 auto  -->
<!--垂直居中只需要line-height: 480px  -->
<!-- 设置测试边框border:5px solid red; -->
<div
        style="width: 100%; height: 480px; margin: 20px auto; text-align: center">
    <h1>网页图片自定义批量预览/下载器</h1>
    <form action="/getImage" method="post"
          onsubmit="return validator();">
        <input type="text" name="url" id="url" style="width: 260px; height: 35px" placeholder="http://www.meizitu.com/"
               value="http://www.meizitu.com/"><input type="submit"
                                                      value="获取网页图片" style="width: 120px; height: 35px">
    </form>
    <form action="/downImage" method="post">
        <h4>
            一共找到${imageSrc.size() }张图片&nbsp;&nbsp;
            <input type="submit" value="下载选中的图片">&nbsp;&nbsp;
            <input type="button" name="checkall" id="checkall" value="全选" onclick="All()">&nbsp;&nbsp;
            <input type="button" name="checkall" id="checkall" value="全部取消" onclick="Un()"> &nbsp;文件默认下载D盘 请自行修改
        </h4>
        <div class="lightBoxGallery">

            <c:forEach items="${imageSrc }" var="i">
                <a href="${i}" data-gallery=""> <img src='${i }' width='180' height='200'/></a>
                <input name="selected" type="checkbox" value="${i }">
            </c:forEach>
            <div id="blueimp-gallery" class="blueimp-gallery">
                <div class="slides"></div>
                <h3 class="title"></h3>
                <a class="prev"><</a>
                <a class="next">></a>
                <a class="close">×</a>
            </div>
        </div>
    </form>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/blueimp/jquery.blueimp-gallery.min.js"></script>
<script>
    function All() {
        var checkElements = document.getElementsByName('selected');
        for (var i = 0; i < checkElements.length; i++) {
            var checkElement = checkElements[i];
            checkElement.checked = "checked";
        }
    }

    function Un() {
        var checkElements = document.getElementsByName('selected');
        for (var i = 0; i < checkElements.length; i++) {
            var checkElement = checkElements[i];
            checkElement.checked = null;
        }

    }
</script>
<script type="text/javascript">
    function validator() {
        var url = document.getElementById("url").value;
        //空判断
        if (url == "" || url.length == 0) {
            alert("请输入URL,如http://www.baidu.com");
            document.getElementById("url").focus();
            return false;
        }

        //合法性判断
        if (url != "" && url.indexOf("http") == -1) {
            alert("请输入一个正确的URL,如http://www.baidu.com");
            document.getElementById("url").focus();
            return false;
        }

        return true;
    }
</script>
</body>
</html>
