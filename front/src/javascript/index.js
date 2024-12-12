$(function() {
    console.on("click");
    $("#Actualizar").on("click", function() {
        $.get("https://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes", function(data) {
            $("#resActualizar").attr("mostrarocultar", data);
            console.log(data);
        })
        
    });
    $("#Actualizar").on("click", function() {
        $.get("https://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes", function(data) {
            $("#resActualizar").text("Ok"); 
            console.log(data);
        })
        
    });


    $('#crear').on('click',function() {
        $.ajax({
            url: "https://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes",
            method: "POST",
            "data": JSON.stringify({
                id: 0,
                nombre: "Fernando",
                apellido: "Alguno"
            }),
            success: function(data) {
                $("#resCrear").text("Ok"); 
                console.log(data);
            },
            error: function(data) {
                console.log(data);
            }
        });
    });


    $('#actualizar').on('click',function() {
        $.ajax({
            url: "https://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes",
            method: "PUT",
            "data": JSON.stringify({
                id: 1,
                nombre: "Nico",
                apellido: "Otro"
            }),
            success: function(data) {
                $("#resActualizar").text("Ok"); 
                console.log(data);
            },
            error: function(data) {
                console.log(data);
            }
        });
    });

    $('#borrar').on('click',function() {
        $.ajax({
            url: "https://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes",
            method: "DELETE",
            success: function(data) {
                $("#resBorrar").text("Ok"); 
                console.log(data);
            },
            error: function(data) {
                console.log(data);
            }
        });
    });
});