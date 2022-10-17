<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>WhereEco</title>
    <link rel="stylesheet" href="/css/map.css" />
    <style>
        .name {
            background-color: #FFF7DA;
            height: 80px;
        }
        img{
            width:60px;
            height:60px;
        }
        .midList {
            text-align: center;
            margin: 50px 0;
        }
        .txt1 {
            color: #554C2B;
        }
        .one {
            /*margin-top: 0px;*/
            width: 500px;
            height: 45px;
            background-color: #FFF7DA;
            border: 2px solid #A09672;
            border-radius: 7px;
            font-size: 15px;
        }
        .two {
            /*margin-top: 20px;*/
            width: 500px;
            height: 45px;
            background-color: #FFF7DA;
            border: 2px solid #A09672;
            border-radius: 7px;
            font-size: 15px;
        }
        .three {
            width: 500px;
            height: 45px;
            background-color: #FFF7DA;
            border: 2px solid #A09672;
            border-radius: 7px;
            font-size: 15px;
        }
        .txt2 {
            margin-bottom: 0px;
            text-align: center;
            color: #554C2B;
        }
        table {
            border: none;
            /* 이중 테두리 제거 */
            border-collapse: collapse;
            display: flex;
            justify-content: center;
        }
        .chart {
            display: flex;
            justify-content: center;
            margin-top: 0px;
        }
        td {
            height: 20px;
            width: 100px;
            border: none;
        }
        input[type=checkbox] {
            zoom: 1.5;
        }
        input:focus {
            border: 3px solid #A09672;
            outline: none;
        }
        .videoPlus {
            display: block;
            margin-top: 20px;
            margin-right: auto;
            margin-left: auto;
            margin-bottom: auto;
        }
        .todook {
            margin-bottom: 60px; /*버튼 위치 조정*/
            margin-left: 10px;
            margin-top: 20px;
            padding: 10px; /*버튼 크기 조정*/
            color: white;
            background-color: #A09672;
            transition: 400ms ease all;
            border-color: #A09672;
            border-radius: 7px;
        }
        .todook:hover {
            background-color: #FFF7DA;
            border-color: #FFF7DA;
            color: #554C2B;
        }
    </style>
</head>

<body>
<div class="tool">
    <!-- 네브바랑 넘어가는 나뭇잎 이미지는 고정-->
    <div class="navbar"><img src="<c:url value="/images/wherelogo.png"/>">
        <!--logo를 클릭하면 map page로 이동-->
        <h1 class="logo">Where ECO</h1>
        </a>
        <a href="logout"><img src="<c:url value="/images/logout.png"/>"></a>
    </div>
    <div class="nextButton">
        <a class="js-mapA"><img src="<c:url value="/images/left.png"/>"></a>
    </div>
    <div class="mainNextButton">
        <a class="js-todoA"><img src="<c:url value="/images/right.png"/>"></a>
    </div>
    <!-- map 페이지 -->
    <div>
        <div class="itemTool js-map noticeShowing">
            <div class="centerWrapper"><br><br>
                <div class="option">
                    <div>
                            <form class="searchform cf" onsubmit="searchPlaces(); return false;">
                            <input type="text" value="검색어를 입력하세요" id="keyword" size="15">
                            <button type="submit">SEARCH</button>
                        </form>
                    </div>
                </div>
            </div>
            <br /><br />
            <div class="map-content">
                <div class="map_wrap">
                    <div id="mapa" style="width:900px; height: 460px; position:relative; overflow:hidden;">
                    </div>
                    <div id="menu_wrap" class="bg_white">
                        <ul id="placesList"></ul>
                        <div id="pagination"></div>
                    </div>

                </div>
            </div>
        </div>
        <!-- todo 페이지 -->
        <div>
            <div class="itemTool js-todo">
                <div class="centerWrapper">
                    <div class="midList">
                        <h3 class="txt1">오늘의 실천해요!</h3><br>
                        <form method="post">
                            <input class="one" type="text" name="todoText1" value="${user.todoText1}" placeholder="내용을 입력하세요">
                            <input type="checkbox" name="todo1" ${user.todo1 ? "checked" : ""}><br><br>

                            <input class="two" type="text" name="todoText2" value="${user.todoText2}" placeholder="내용을 입력하세요">
                            <input type="checkbox" name="todo2" ${user.todo2 ? "checked" : ""}><br><br>

                            <input class="three" type="text" name="todoText3" value="${user.todoText3}" placeholder="내용을 입력하세요">
                            <input type="checkbox" name="todo3" ${user.todo3 ? "checked" : ""}><br>

                            <input class="todook" type="submit" value="저장">
                        </form>
                    </div><br><br>
                    <div class="midVideo"></div>
                    <h3 class="txt2">더 찾아봐요!</h3>
                    <br>
                    <table class="chart">
                        <tbody>
                        <tr>
                            <td><iframe width="560" height="315" src="${randomUrl.url1}"
                                        title="YouTube video player" frameborder="0"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen></iframe></td>
                        </tr>
                        <tr>
                            <td><iframe width="560" height="315" src="${randomUrl.url2}"
                                        title="YouTube video player" frameborder="0"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen></iframe></td>
                        </tr>
                        <tr>
                            <td><iframe width="560" height="315" src="${randomUrl.url3}"
                                        title="YouTube video player" frameborder="0"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen></iframe></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script rel="script" type="text/javascript" src="/js/todo.js"></script>
<script type="text/javascript"
        src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=61eb4642306869b42c83d9bc71a5c1ba&libraries=services"></script>
<script rel="script" type="text/javascript" src="/js/map1.js"></script>
<!-- 지도 js -->
</body>
</html>