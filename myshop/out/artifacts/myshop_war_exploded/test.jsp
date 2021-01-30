<%--
  Created by IntelliJ IDEA.
  User: wgz
  Date: 2020/9/28
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery-1.12.4.js"></script>

    <script>

        function clickTest() {



            // 删除
            $.ajax({
                url:"http://127.0.0.1:8080/test",
                type:"get",

                success:function (data) {
                    alert(data);
                },
                error:function () {
                    alert("服务器错误")
                },xhrFields: {
                    // 跨域携带cookie
                    withCredentials: true
                }
            })


        }
    </script>
</head>
<body>


<button onclick="clickTest()"> 测试</button>



<h1>Test</h1>

</body>
</html>
