$(function() {
    $('#submit').on("click", function () {
        const object = $("#form").serialize();

        $.ajax({
            url: "/user/map",
            type: "POST",
            data: object,
            dataType: "json",})
            .done(function (resp){ // 자동 parse
                //alert(resp); // js Object 상태에서는 Object Object로 표시됨
                alert("저장 성공");

            }).fail(function (error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        })
    })
})