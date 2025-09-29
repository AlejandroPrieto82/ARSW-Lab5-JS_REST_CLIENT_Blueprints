var app = (function () {
    // Privado
    var selectedAuthor = "";
    var blueprintsList = [];

    // Cambiar autor seleccionado
    function setAuthor(author) {
        selectedAuthor = author;
        $("#selectedAuthor").text(author);
    }

    // Limpiar canvas
    function clearCanvas() {
        var canvas = document.getElementById("blueprintCanvas");
        if (canvas) {
            var ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
        }
        $("#drawingName").text("");
    }

    // Actualizar listado de planos desde la API REST
    function updateBlueprints(author) {
        setAuthor(author);

        // Limpiar tabla y canvas
        $("#blueprintsTable").empty();
        clearCanvas();

        // ------------------------------
        // OPCIÓN 1: API REST real
        // ------------------------------
        $.ajax({
            url: "/blueprints/" + author,
            method: "GET",
            success: function(data) {
                if (!data || data.length === 0) {
                    $("#totalPoints").text(0);
                    return;
                }

                // Guardar listado transformado (solo nombre y cantidad de puntos)
                blueprintsList = data.map(function(bp) {
                    return { name: bp.name, pointsCount: bp.points.length };
                });

                // Llenar tabla HTML con los planos
                blueprintsList.forEach(function(bp) {
                    $("#blueprintsTable").append(
                        "<tr><td>" + bp.name + "</td><td>" + bp.pointsCount + "</td>" +
                        "<td><button class='btn btn-sm btn-success drawBtn' data-plan='" + bp.name + "'>Dibujar</button></td></tr>"
                    );
                });

                // Total de puntos usando listado original
                var totalOriginal = data.reduce(function(acc, bp) {
                    return acc + bp.points.length;
                }, 0);

                $("#totalPoints").text(totalOriginal);

                // Asociar evento a botones de dibujo
                $(".drawBtn").click(function() {
                    var planName = $(this).data("plan");
                    drawBlueprint(author, planName);
                });
            },
            error: function() {
                alert("Error: autor no encontrado o fallo en el servidor.");
                $("#totalPoints").text(0);
            }
        });

        // ------------------------------
        // OPCIÓN 2: Para pruebas locales con apimock.js
        // ------------------------------
        /*
        apimock.getBlueprintsByAuthor(author, function (data) {
            if (!data || data.length === 0) {
                $("#totalPoints").text(0);
                return;
            }

            blueprintsList = data.map(function(bp) {
                return { name: bp.name, pointsCount: bp.points.length };
            });

            blueprintsList.forEach(function(bp) {
                $("#blueprintsTable").append(
                    "<tr><td>" + bp.name + "</td><td>" + bp.pointsCount + "</td>" +
                    "<td><button class='btn btn-sm btn-success drawBtn' data-plan='" + bp.name + "'>Dibujar</button></td></tr>"
                );
            });

            var totalOriginal = data.reduce(function(acc, bp) {
                return acc + bp.points.length;
            }, 0);

            $("#totalPoints").text(totalOriginal);

            $(".drawBtn").click(function() {
                var planName = $(this).data("plan");
                drawBlueprint(author, planName);
            });
        });
        */
    }

    // Dibujar plano en canvas
    function drawBlueprint(author, planName) {
    // clearCanvas(); // <-- quitar para que se acumulen trazos

    // OPCIÓN 1: API REST real
    $.ajax({
        url: "/blueprints/" + author + "/" + planName,
        method: "GET",
        success: function(bp) {
            if (!bp || !bp.points || bp.points.length === 0) {
                alert("Plano no encontrado o vacío");
                return;
            }

            $("#drawingName").text(planName);

            var canvas = document.getElementById("blueprintCanvas");
            if (!canvas) return;

            var ctx = canvas.getContext("2d");

            // Determinar escala para que los puntos se vean mejor
            var scaleX = canvas.width / 10; // ajusta según tamaño de los planos
            var scaleY = canvas.height / 10;


            ctx.beginPath();
            ctx.moveTo(bp.points[0].x * scaleX, bp.points[0].y * scaleY);

            for (var i = 1; i < bp.points.length; i++) {
                ctx.lineTo(bp.points[i].x * scaleX, bp.points[i].y * scaleY);
            }

            ctx.stroke();
        },
        error: function() {
            alert("Error al dibujar el plano");
        }
    });


        // ------------------------------
        // OPCIÓN 2: Para pruebas locales con apimock.js
        // ------------------------------
        /*
        apimock.getBlueprintsByNameAndAuthor(author, planName, function(bp) {
            if (!bp || !bp.points || bp.points.length === 0) {
                alert("Plano no encontrado o vacío");
                return;
            }

            $("#drawingName").text(planName);

            var canvas = document.getElementById("blueprintCanvas");
            if (!canvas) return;

            var ctx = canvas.getContext("2d");
            ctx.beginPath();
            ctx.moveTo(bp.points[0].x, bp.points[0].y);

            for (var i = 1; i < bp.points.length; i++) {
                ctx.lineTo(bp.points[i].x, bp.points[i].y);
            }

            ctx.stroke();
        });
        */
    }

    // Asociar botón al click
    $(document).ready(function () {
        $("#getBlueprints").click(function () {
            var author = $("#author").val().trim();
            if (author !== "") {
                updateBlueprints(author);
            } else {
                alert("Ingrese un autor válido");
                clearCanvas();
                $("#blueprintsTable").empty();
                $("#totalPoints").text(0);
            }
        });
    });

    // Público
    return {
        setAuthor: setAuthor,
        updateBlueprints: updateBlueprints,
        drawBlueprint: drawBlueprint
    };
})();
