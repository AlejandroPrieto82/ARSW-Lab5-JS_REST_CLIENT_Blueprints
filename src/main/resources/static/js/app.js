var app = (function () {

    // Cambia entre apimock y apiclient aquí
    var api = apiclient; // o apimock

    var selectedAuthor = "";
    var blueprintsList = [];

    function setAuthor(author) {
        selectedAuthor = author;
        $("#selectedAuthor").text(author);
    }

    function clearCanvas() {
        var canvas = document.getElementById("blueprintCanvas");
        if (canvas) {
            var ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
        }
        $("#drawingName").text("Ninguno");
    }

    function updateBlueprints(author) {
        setAuthor(author);
        $("#blueprintsTable").empty();
        clearCanvas();

        api.getBlueprintsByAuthor(author, function(data) {
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

            var totalPoints = data.reduce(function(acc, bp) {
                return acc + bp.points.length;
            }, 0);
            $("#totalPoints").text(totalPoints);

            $(".drawBtn").click(function() {
                var planName = $(this).data("plan");
                drawBlueprint(author, planName);
            });
        });
    }

    function drawBlueprint(author, planName) {
        api.getBlueprintsByNameAndAuthor(author, planName, function(bp) {
            if (!bp || !bp.points || bp.points.length === 0) {
                alert("Plano no encontrado o vacío");
                return;
            }

            $("#drawingName").text(planName);

            var canvas = document.getElementById("blueprintCanvas");
            if (!canvas) return;

            var ctx = canvas.getContext("2d");

            // Ajuste de escala automático
            var maxX = Math.max(...bp.points.map(p => p.x));
            var maxY = Math.max(...bp.points.map(p => p.y));
            var scaleX = canvas.width / (maxX + 10);
            var scaleY = canvas.height / (maxY + 10);

            ctx.beginPath();
            ctx.moveTo(bp.points[0].x * scaleX, bp.points[0].y * scaleY);

            for (var i = 1; i < bp.points.length; i++) {
                ctx.lineTo(bp.points[i].x * scaleX, bp.points[i].y * scaleY);
            }

            ctx.stroke();
        });
    }

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

    return {
        setAuthor: setAuthor,
        updateBlueprints: updateBlueprints,
        drawBlueprint: drawBlueprint
    };

})();
